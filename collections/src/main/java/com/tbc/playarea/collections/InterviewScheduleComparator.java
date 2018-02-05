package com.tbc.playarea.collections;

import java.time.LocalTime;
import java.util.Comparator;

public class InterviewScheduleComparator implements Comparator<InterviewCandidate> {

	private static final LocalTime FIXED_SCHEDULED_TIME = LocalTime.parse("08:30");
	@Override
	public int compare(InterviewCandidate c1, InterviewCandidate c2) {
		LocalTime t1 = LocalTime.parse(c1.getReportingTime());
		LocalTime t2 = LocalTime.parse(c2.getReportingTime());
		if("scheduled".equalsIgnoreCase(c1.getType()) && !"scheduled".equalsIgnoreCase(c2.getType()))
			return (t1.isBefore(FIXED_SCHEDULED_TIME) || t1.equals(FIXED_SCHEDULED_TIME)) ? -1 : 1;
		else if(!"scheduled".equalsIgnoreCase(c1.getType()) && "scheduled".equalsIgnoreCase(c2.getType()))
			return (t2.isBefore(FIXED_SCHEDULED_TIME) || t2.equals(FIXED_SCHEDULED_TIME)) ? 1 : -1;
		else
			return t1.compareTo(t2);
			
	}
	
}
