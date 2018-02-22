package com.tbc.playarea.rxjava;

import java.time.LocalDate;

public class PriceTick {
	private final int sequence;
	private final LocalDate date;
	private final String instrument;
	private final double price;
	
	public PriceTick(int sequence, LocalDate date, String instrument, double price) {
		super();
		this.sequence = sequence;
		this.date = date;
		this.instrument = instrument;
		this.price = price;
	}

	public int getSequence() {
		return sequence;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getInstrument() {
		return instrument;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "PriceTick [sequence=" + sequence + ", date=" + date + ", instrument=" + instrument + ", price=" + price
				+ "]";
	}
	
	public boolean isLast() {
		return false;
	}
}
