/**
 * 
 */
package com.tbc.playarea;

import java.util.Map;

/**
 * @author parthp
 *
 */
public interface BlogSubject<T> {
	public void register(Observer<T> observer);
	public void unregister(Observer<T> observer);
	
	public void notifyObservers(String message);
	
	public void addOrModifyContent(String key, T content);
	public void removeContent(String key);
	public Map<String, T> getContent(Observer<T> observer);
}
