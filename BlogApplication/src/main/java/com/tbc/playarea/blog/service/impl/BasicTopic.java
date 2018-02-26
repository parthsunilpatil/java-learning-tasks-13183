package com.tbc.playarea.blog.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.tbc.playarea.blog.model.ContentTypes;
import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;

public abstract class BasicTopic implements BlogSubject {
	
	protected Map<ContentTypes, List<Observer>> observers;
	protected boolean changed;
	protected static final Object MUTEX = new Object();
	public BasicTopic() {
		super();
		this.observers = new ConcurrentHashMap<>();
	}
	

	@Override
	public void register(Observer observer) {
		if(observer == null)
			throw new NullPointerException("Null Observer!!");
		synchronized(MUTEX) {
			List<Observer> observersList = (observers.containsKey(observer.getType())) 
					? observers.get(observer.getType()) : new LinkedList<>();
			if(!observersList.contains(observer)) {
				observersList.add(observer);
				observers.put(observer.getType(), observersList);
			}
		}
	}
	
	@Override
	public void notifyAllObserevers(String message) {
		Map<ContentTypes, List<Observer>> observersLocal = observersToNotify();
		if(observersLocal == null)
			return;
		for(Entry<ContentTypes, List<Observer>> observer : observersLocal.entrySet()) {
			for(Observer obs : observer.getValue()) {
				obs.consumeMessage(message);
			}
		}
	}

	protected Map<ContentTypes, List<Observer>> observersToNotify() {
		Map<ContentTypes, List<Observer>> observersLocal = null;
		synchronized(MUTEX) {
			if(!changed)
				return null;
			observersLocal = new ConcurrentHashMap<>(this.observers);
			this.changed = false;
		}
		return observersLocal;
	}

}
