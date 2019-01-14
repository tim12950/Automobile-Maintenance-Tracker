package com.carlibrary;

public class GasCar extends Car {
	
	private static final long serialVersionUID = -2564175489709573572L;
	//potential tasks specific to this vehicle type:
	private String[] taskList = {"Oil Change"};
	
	private static final String type = "Gas";
	
	public GasCar(String plate, String make, String model, int year, int odometer) {
		
		super(plate, type, make, model, year, odometer);
		
		for(int i = 0; i < taskList.length; i++) {
			allTasks.add(taskList[i]);
		}
		
	}

}
