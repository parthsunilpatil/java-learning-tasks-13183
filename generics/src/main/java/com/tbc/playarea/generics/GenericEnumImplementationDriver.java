package com.tbc.playarea.generics;

public class GenericEnumImplementationDriver {
	public static void main(String[] args) {
		System.out.println(Days.fromValue("friday").compareTo(Days.FRIDAY));
		System.out.println(DaysAnnotated.fromValue("friday").compareTo(DaysAnnotated.FRIDAY));
	}
}
