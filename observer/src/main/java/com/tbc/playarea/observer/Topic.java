package com.tbc.playarea.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Topic<T> implements Subject<T> {
	
	private List<Observer<T>> observers;
	private T content;
	private boolean changed;
	private static final Object MUTEX = new Object();
	
	public Topic() {
		super();
		observers = new LinkedList<>();
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
	
	@SafeVarargs
	public final void registerObservers(final Observer<T>... observers) {
		for(Observer<T> observer : observers)
			register(observer);
	}

	@Override
	public void unregister(Observer<T> observer) {
		synchronized(MUTEX) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers() {
		List<Observer<T>> observersLocal = null;
		synchronized(MUTEX) {
			if(!changed) 
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer<T> observer : observersLocal) {
			observer.update();
		}
	}

	@Override
	public T getUpdate(Observer<T> obj) {
		return content;
	}
	
	public void postUpdate(T content) {
		System.out.println("Content posted to Topic: " + content.toString());
		this.content = content;
		this.changed = true;
		notifyObservers();
	}
	
	public Observer<T> getSubscriber(String observerName) {
		for(Observer<T> observer : this.observers) {
			if(observer instanceof Subscriber<?>) {
				if(observerName.equalsIgnoreCase(((Subscriber<T>) observer).getName()))
					return observer;
			}
		}
		throw new NullPointerException("No Observer found with name: " + observerName);
	}

}
