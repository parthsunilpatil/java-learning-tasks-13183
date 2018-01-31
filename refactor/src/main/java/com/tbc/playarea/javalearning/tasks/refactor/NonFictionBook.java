package com.tbc.playarea.javalearning.tasks.refactor;

public class NonFictionBook extends Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6044532160735679821L;
	private double BASE_PRICE = 0;
	private double MULTIPLIER = 3;
	private int RENTER_POINTS;
	
	public NonFictionBook(String title) {
		super(title, Book.NON_FICTION);
		if(!getRentalProperties().isEmpty()) {
			this.BASE_PRICE = Double.parseDouble(getRentalProperties().getProperty("non_fiction.base_price", "0"));
			this.MULTIPLIER = Double.parseDouble(getRentalProperties().getProperty("non_fiction.multiplier", "3"));
			this.RENTER_POINTS = Integer.parseInt(getRentalProperties().getProperty("non_fiction.renter_points", "1"));
		}
	}

	@Override
	public double getAmount(int daysRented) {
		// TODO Auto-generated method stub
		double amount = BASE_PRICE;
		amount += daysRented * MULTIPLIER;
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
		return "NonFictionBook [BASE_PRICE=" + BASE_PRICE + ", MULTIPLIER=" + MULTIPLIER + ", RENTER_POINTS="
				+ RENTER_POINTS + "]";
	}
	
	public static void main(String[] args) {
		Book book = new NonFictionBook("NonFiction");
		System.out.println(book.toString());
	}

}
