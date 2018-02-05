package com.tbc.playarea.collections;

import java.util.concurrent.PriorityBlockingQueue;

public class InterviewerThread {
	
	private PriorityBlockingQueue<InterviewCandidate> interviewQueue;

	public InterviewerThread(PriorityBlockingQueue<InterviewCandidate> interviewQueue) {
		super();
		this.interviewQueue = interviewQueue;
	}

	public void interview() {
		try {
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Interview Starting :: ");
			InterviewCandidate candidate = interviewQueue.take();
			System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Interview Completed :: " + candidate);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
