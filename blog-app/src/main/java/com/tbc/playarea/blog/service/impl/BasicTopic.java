package com.tbc.playarea.blog.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.tbc.playarea.blog.service.BlogSubject;
import com.tbc.playarea.blog.service.Observer;


public abstract class BasicTopic<T> implements BlogSubject<T> {
	protected List<Observer<T>> observers;
	protected boolean changed;
	protected static final Object MUTEX = new Object();
	public BasicTopic() {
		super();
		this.observers = new LinkedList<>();
	}
	
	@Override
	public void register(Observer<T> observer) {
		if(observer == null)
			throw new NullPointerException("Null Observer!!");
		synchronized(MUTEX) {
			if(!observers.contains(observer)) 
				observers.add(observer);
		}
	}
	
	@Override
	public void unregister(Observer<T> observer) {
		synchronized(MUTEX) {
			observers.remove(observer);
		}
	}
	
	@Override
	public void notifyObservers(String message) {
		List<Observer<T>> observersLocal = null;
		synchronized(MUTEX) {
			if(!changed) 
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer<T> observer : observersLocal) {
			observer.consumeMessage(message);
		}
	}
}
