package com.tbc.playarea.javalearning.tasks.refactor;

public class ChildrenBook extends Book {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100285698901594122L;
	private double BASE_PRICE = 1.5;
	private double MULTIPLIER = 2;
	private int THRESHOLD = 3;
	private int RENTER_POINTS;

	public ChildrenBook(String title) {
		super(title, Book.CHILDRENS);
		if(!getRentalProperties().isEmpty()) {
			this.BASE_PRICE = Double.parseDouble(getRentalProperties().getProperty("children.base_price", "1.5"));
			this.MULTIPLIER = Double.parseDouble(getRentalProperties().getProperty("children.multiplier", "2"));
			this.THRESHOLD = Integer.parseInt(getRentalProperties().getProperty("children.threshold", "3"));
			this.RENTER_POINTS = Integer.parseInt(getRentalProperties().getProperty("children.renter_points", "1"));
		}
	}
	
	@Override
	public String toString() {
		return "ChildrenBook [BASE_PRICE=" + BASE_PRICE + ", MULTIPLIER=" + MULTIPLIER + ", THRESHOLD=" + THRESHOLD
				+ ", RENTER_POINTS=" + RENTER_POINTS + "]";
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
	
	public static void main(String[] args) {
		Book book = new ChildrenBook("Children");
		System.out.println(book.toString());
	}

}
