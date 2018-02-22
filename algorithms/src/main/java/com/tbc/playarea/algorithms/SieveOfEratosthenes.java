package com.tbc.playarea.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class SieveOfEratosthenes {
	
	public static List<Integer> sieve(int n) {
		/** 
		 * Declare marker array for primes numbers till given integer n.
		 * If primes[i] is true, i is a prime number
		 **/
		boolean[] primes = new boolean[n + 1];
		
		/**
		 * Initialise the marker array primes with all entries marked as true.
		 * Non-primes numbers will be unmarked by the algorithm.
		 * **/
		for(int i = 0; i < n; i++) 
			primes[i] = true;
		
		/**
		 * for all numbers p, from 2 to srqt(n) if p is a prime number,
		 * unmark all numbers which are a multiple of p (2p, 3p, 4p ...)
		 * **/
		for(int p = 2; p*p <= n; p++) {
			if(primes[p])
				for(int i = p*2; i <= n; i += p)
					primes[i] = false;
		}
		
		/**
		 * All numbers left still marked in primes array are now prime numbers.
		 * Add them to a list and return the list.
		 * **/
		List<Integer> returnList = new LinkedList<>();
		for(int i = 2; i <= n; i++)
			if(primes[i])
				returnList.add(i);
		return returnList;
	}
	
	public static void segmentedSieve(int n) {
		/**
		 * All primes less than or equal to sqrt(n) are marked using simple sieve 
		 * **/
		int limit = (int) (Math.floor(Math.sqrt(n)) + 1);
		List<Integer> primes = sieve(limit);
		for(int i = 0; i < primes.size(); i++)
			System.out.print(primes.get(i) + " ");
		
		/**
		 * The range of integers from [0..n-1] are divided into segments of size sqrt(n)
		 * **/
		int low = limit, high = 2 * limit;
		
		/**
		 * All segments are processed one at a time.
		 * **/
		while(low < n) {
			/**
			 * All numbers are marked as prime during initialization. 
			 * Finally, a value in markedPrime[i] is false if i - low is not a prime number.
			 * **/
			boolean[] markedPrimes = new boolean [limit + 1];
			for(int i = 0; i < markedPrimes.length; i++)
				markedPrimes[i] = true;
			
			/**
			 * Use primes found till this point to find primes in the current segment
			 * **/
			for(int i = 0; i < primes.size(); i++) {
				
				/**
				 * Find the smallest number in [low..high] that is a multiple of primes.get(i)
				 * **/
				int lowLimit = (int) (Math.floor(low / primes.get(i)) * primes.get(i));
				if(lowLimit < low) 
					lowLimit += primes.get(i);
				
				/**
				 * All multiples of primes.get(i) are unmarked in the array.
				 * Each number in range [low..high] is mapped to [0..high-low]
				 * Thus space is allocated every time only for the segment size
				 * **/
				for(int j = lowLimit; j < high; j += primes.get(i))
					markedPrimes[j - low] = false;
				
			}
			
			/**
			 * All numbers still marked are prime numbers update the primes list.
			 * **/
			for(int i = low; i < high; i++)
				if(markedPrimes[i - low])
					System.out.print(i + " ");
			
			/**
			 * Update low and high to process next segment. 
			 * **/
			low += limit;
			high += limit;
			if(high >= n) 
				high = n;
		}
		System.out.println();
	} 
	
	public static Vector<Integer> eulerSieve(int n) {
		Vector<Boolean> isPrime = new Vector<>();
		Vector<Integer> primes = new Vector<>();
		Vector<Integer> SPF = new Vector<>();
		
		for(int i = 0; i < 1000000; i++) {
			isPrime.add(true);
			SPF.add(2);
		}
		
		isPrime.set(0, false);
		isPrime.set(1, false);
		
		for(int i = 2; i < n; i++) {
			if(isPrime.get(i)) {
				primes.add(i);
				SPF.set(i, i);
			}
			
			for(int j = 0; j < primes.size() && i*primes.get(j) < n && primes.get(j) <= SPF.get(i); j++) {
				isPrime.set(i*primes.get(j), false);
				SPF.set(i*primes.get(j), primes.get(j));
			}
		}
		return primes;
	}
	
	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(SieveOfEratosthenes.sieve(1000));
		long e = System.currentTimeMillis();
		System.out.println("Time taken: " + (e - s) + " milliseconds");
		s = System.currentTimeMillis();
		SieveOfEratosthenes.segmentedSieve(1000);
		e = System.currentTimeMillis();
		System.out.println("Time taken: " + (e - s) + " milliseconds");		
		s = System.currentTimeMillis();
		System.out.println(SieveOfEratosthenes.eulerSieve(1000));
		e = System.currentTimeMillis();
		System.out.println("Time taken: " + (e - s) + " milliseconds");
	}

}
