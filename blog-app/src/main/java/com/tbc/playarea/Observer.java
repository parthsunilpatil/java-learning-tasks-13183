package com.tbc.playarea;

public interface Observer<T> {
	public void setSubject(BlogSubject<T> subject);
	public void consumeMessage(String message);
}
