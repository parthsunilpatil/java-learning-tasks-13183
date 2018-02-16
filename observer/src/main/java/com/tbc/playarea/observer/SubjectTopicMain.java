package com.tbc.playarea.observer;

public class SubjectTopicMain {
	public static void main(String[] args) {
		String[] observerNames = {"A", "B", "C", "D", "E"};
		Topic<String> topic = new Topic<>();
		for(String name : observerNames) {
			Observer<String> observer = new Subscriber<>(name);
			topic.register(observer);
			observer.setSubject(topic);
		}
		topic.getSubscriber("A").update();
		topic.postUpdate("Hello World!!");
		
		Observer<String> F = new Subscriber<>("F");
		Observer<String> G = new Subscriber<>("G");
		Observer<String> H = new Subscriber<>("H");
		
		topic.registerObservers(F, G, H);
		F.setSubject(topic);
		G.setSubject(topic);
		H.setSubject(topic);
		
		topic.getSubscriber("A").update();
		topic.postUpdate("Hello World, Again!!");
	}
}
