package com.tbc.playarea.rxjava;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Feed {
	private AtomicInteger thrCtr = new AtomicInteger(1);
	
	private ExecutorService service = Executors.newCachedThreadPool(r -> {
		Thread thread = new Thread(r);
		thread.setName("Thread " + thrCtr.getAndIncrement());
		return thread;
	});
	
	private transient boolean running = true;
	private List<Listener<PriceTick>> listeners = new LinkedList<>();
	private int threadCount;
	private AtomicInteger sequence = new AtomicInteger(1);
	
	private final Random RANDOM = new Random(0);
	private static final Random RANDOM_PRICE = new Random(0);
	
	private static final String[] INSTRUMENTS = {"IBM", "NMR", "BAC", "AAPL", "MSFT"};
	
	public Feed() {
		this(INSTRUMENTS.length);
	}

	public Feed(int threadCount) {
		this.threadCount = threadCount;
		launchPublishers();
	}

	private void launchPublishers() {
		double[] prices = {160, 5, 15, 108, 57};
		for(int i = 0; i < threadCount; i++) {
			launchEventThread(INSTRUMENTS[i%INSTRUMENTS.length], prices[i%prices.length]);
		}
	}

	private void launchEventThread(String instrument, double startingPrice) {
		service.execute(() -> {
			final Object MUTEX = new Object();
			double price = startingPrice;
			while(running) {
				price += RANDOM_PRICE.nextGaussian();
				double finalPrice = price;
				listeners.forEach(subscriber -> {
					PriceTick tick = new PriceTick(sequence.getAndIncrement(), LocalDate.now(), instrument, finalPrice);
					//String message = LocalDate.now() + " " + instrument + " " + finalPrice;
					subscriber.priceTick(tick);
				});
				synchronized(MUTEX) {
					try {
						MUTEX.wait(RANDOM.nextInt(200) + 800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	public void register(Listener<PriceTick> listener) {
		listeners.add(listener);
	}
	
	public void terminate() {
		running = false;
	}
}
