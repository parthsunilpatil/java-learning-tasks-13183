package com.tbc.playarea.observer;

public class Subscriber<T> implements Observer<T> {
	
	private String name;
	private Subject<T> topic;

	public Subscriber(String name) {
		super();
		this.name = name;
	}

	@Override
	public void update() {
		T content = topic.getUpdate(this);
		if(null == content)
			System.out.println(name + ", no new updates");
		else
			System.out.println(name + ", consuming: " + content.toString());
	}

	@Override
	public void setSubject(Subject<T> subject) {
		this.topic = subject;
	}
	
	public String getName() {
		return this.name;
	}

}
