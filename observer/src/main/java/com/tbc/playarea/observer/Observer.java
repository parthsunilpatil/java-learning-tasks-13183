/**
 * 
 */
package com.tbc.playarea.observer;

/**
 * @author parthp
 *
 */
public interface Observer<T> {
	public void update();
	
	public void setSubject(Subject<T> subject);
}
