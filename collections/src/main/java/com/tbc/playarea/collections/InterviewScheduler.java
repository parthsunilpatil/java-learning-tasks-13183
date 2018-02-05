package com.tbc.playarea.collections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class InterviewScheduler {
	
	public static void main(String[] args) {
		PriorityBlockingQueue<InterviewCandidate> PBQ = new PriorityBlockingQueue<>(1000, new InterviewScheduleComparator());
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("D", "Scheduled", "10:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("C", "Scheduled", "09:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("A", "Scheduled", "07:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("B", "Scheduled", "08:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("c", "Walk-in", "09:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("d", "Walk-in", "10:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("a", "Walk-in", "07:30")));
		service.execute(new IntervieweeThread(PBQ, new InterviewCandidate("b", "Walk-in", "08:30")));
		
		InterviewerThread interviewer = new InterviewerThread(PBQ);
		
		try {
			service.awaitTermination(500, TimeUnit.MILLISECONDS);
			while(!PBQ.isEmpty()) {
				interviewer.interview();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();
	}
}
