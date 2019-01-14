package com.carlibrary;

public class DieselCar extends Car {
	
	private static final long serialVersionUID = -325949713439366302L;
	//potential tasks specific to this vehicle type:
	private String[] taskList = {"Oil Change"};
	
	private static final String type = "Diesel";
	
	public DieselCar(String plate, String make, String model, int year, int odometer) {
		
		super(plate, type, make, model, year, odometer);
		
		for(int i = 0; i < taskList.length; i++) {
			allTasks.add(taskList[i]);
		}
		
	}

}
