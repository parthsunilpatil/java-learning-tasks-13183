package com.tbc.playarea.collections;

import java.util.concurrent.PriorityBlockingQueue;

public class IntervieweeThread implements Runnable {
	private PriorityBlockingQueue<InterviewCandidate> interviewQueue;
	private InterviewCandidate newCandidate;

	public IntervieweeThread(PriorityBlockingQueue<InterviewCandidate> interviewQueue,
			InterviewCandidate newCandidate) {
		super();
		this.interviewQueue = interviewQueue;
		this.newCandidate = newCandidate;
	}

	@Override
	public void run() {
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: Candidate Arrived :: " + newCandidate);
		interviewQueue.add(newCandidate);
		System.out.println("Thread :: " + Thread.currentThread().getName() + " :: " + newCandidate + " Interview Scheduled");
	}	
	
}
