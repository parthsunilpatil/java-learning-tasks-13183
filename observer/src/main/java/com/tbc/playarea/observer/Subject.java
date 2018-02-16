/**
 * 
 */
package com.tbc.playarea.observer;

/**
 * @author parthp
 *
 */
public interface Subject<T> {
	public void register(Observer<T> observer);
	public void unregister(Observer<T> observer);
	
	public void notifyObservers();
	
	public T getUpdate(Observer<T> obj);
}
