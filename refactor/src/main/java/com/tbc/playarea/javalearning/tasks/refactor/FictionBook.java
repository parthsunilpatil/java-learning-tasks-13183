package com.tbc.playarea.javalearning.tasks.refactor;

public class FictionBook extends Book {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -569496606916456767L;
	private double BASE_PRICE = 2;
	private double MULTIPLIER = 1.5;
	private int THRESHOLD = 2;
	private int RENTER_POINTS = 1;

	public FictionBook(String title) {
		super(title, Book.FICTION);
		if(!getRentalProperties().isEmpty()) {
			this.BASE_PRICE = Double.parseDouble(getRentalProperties().getProperty("fiction.base_price", "2"));
			this.MULTIPLIER = Double.parseDouble(getRentalProperties().getProperty("fiction.multiplier", "1.5"));
			this.THRESHOLD = Integer.parseInt(getRentalProperties().getProperty("fiction.threshold", "2"));
			this.RENTER_POINTS = Integer.parseInt(getRentalProperties().getProperty("fiction.renter_points", "1"));
		}
	}

	@Override
	public double getAmount(int daysRented) {
		// TODO Auto-generated method stub
		double amount = BASE_PRICE;
		if(daysRented > THRESHOLD) amount += (daysRented - THRESHOLD) * MULTIPLIER;
		return amount;
	}

	@Override
	public int getPoints(int daysRented) {
		if(daysRented > 1)
			return RENTER_POINTS;
		else 
			return 1;
	}
	
	@Override
	public String toString() {
		return "FictionBook [BASE_PRICE=" + BASE_PRICE + ", MULTIPLIER=" + MULTIPLIER + ", THRESHOLD=" + THRESHOLD
				+ ", RENTER_POINTS=" + RENTER_POINTS + "]";
	}

	public static void main(String[] args) {
		Book book = new FictionBook("FICTION");
		System.out.println(book.toString());
	}

}
