package com.tbc.playarea.collections;

public class InterviewCandidate {
	
	private String name;
	private String type;
	private String reportingTime;
	
	public InterviewCandidate(String name, String type, String reportingTime) {
		super();
		this.name = name;
		this.type = type;
		this.reportingTime = reportingTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(String reportingTime) {
		this.reportingTime = reportingTime;
	}

	@Override
	public String toString() {
		return "InterviewCandidate [name=" + name + ", type=" + type + ", reportingTime=" + reportingTime + "]";
	}

}
