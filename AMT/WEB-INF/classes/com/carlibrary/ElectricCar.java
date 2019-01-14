package com.carlibrary;

public class ElectricCar extends Car {
	
	private static final long serialVersionUID = -7713150336817959963L;
	//potential tasks specific to this vehicle type:
	private String[] taskList = {};
	
	private static final String type = "Electric";
	
	public ElectricCar(String plate, String make, String model, int year, int odometer) {
		
		super(plate, type, make, model, year, odometer);
		
		for(int i = 0; i < taskList.length; i++) {
			allTasks.add(taskList[i]);
		}
		
	}

}
