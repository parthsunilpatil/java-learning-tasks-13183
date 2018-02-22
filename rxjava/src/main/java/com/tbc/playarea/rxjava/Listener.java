package com.tbc.playarea.rxjava;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Listener<T> {
	private static final AtomicInteger COUNTER = new AtomicInteger(1);
	private final int ID;
	
	public Listener() {
		super();
		ID = COUNTER.getAndIncrement();
	}
	
	public abstract void priceTick(T event);
	public abstract void error(Throwable throwable);

	@Override
	public String toString() {
		return "Listener [ID=" + ID + ": " + super.toString() + "]";
	}
}
