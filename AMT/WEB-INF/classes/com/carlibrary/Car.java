package com.carlibrary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public abstract class Car implements Serializable {
	
	private static final long serialVersionUID = -9159503786483725072L;
	protected final String plate;
	protected final String type;
	protected final String make;
	protected final String model;
	protected final int year;
	protected int odometer;
	
	//list of potential tasks common to all vehicle types:
	private String[] taskList = {"Top up Washer Fluid", "Tire Rotation"};
	protected TreeSet<String> allTasks;
	
	//mapping of scheduled tasks to their status:
	//true means scheduled and completed,
	//false means scheduled and not completed
	protected TreeMap<String, Boolean> tasks;
	
	//constructor:
	
	public Car(String plate, String type, String make, String model, int year, int odometer) {
		
		this.plate = plate;
		this.type = type;
		this.make = make;
		this.model = model;
		this.year = year;
		this.odometer = odometer;
		
		allTasks = new TreeSet<String>();
		for(int i = 0; i < taskList.length; i++) {
			allTasks.add(taskList[i]);
		}
		
		tasks = new TreeMap<String, Boolean>();
		
	}
	
	//getters:
	
	public String getPlate() {
		return plate;
	}
	
	public String getType() {
		return type;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getOdometer() {
		return odometer;
	}
	
	public void setOdometer(int km) {
		this.odometer = km;
	}
	
	public TreeMap<String,Boolean> getTasks() {
		return tasks;
	}
	
	//list all potential tasks:
	public Iterator<String> getAllTasks() {
		return allTasks.iterator();
	}
	
	//list all scheduled tasks (elements of TreeSet ordered alphabetically):
	public Iterator<String> getScheduledTasks() {
		return tasks.keySet().iterator();
	}
	
	//list statuses of scheduled tasks(The iterator returns the values in order of the corresponding keys):
	public Iterator<Boolean> getStatuses() {
		return tasks.values().iterator();
	}
	
	//schedule a new task with status not complete:
	public void addTask(String task) throws Exception {
		if (allTasks.contains(task)) {
			tasks.put(task, false);
		}
		else {
			throw new Exception("Invalid task for this car type!");
		}
	}
	
	//toggle status of a scheduled task:
	public void updateTask(String task) throws Exception {
		if (allTasks.contains(task)) {
			Boolean status = tasks.get(task);
			if (status != null) {
				if (status) {
					tasks.put(task, false);
				}
				else {
					tasks.put(task, true);
				}
			}
			else {
				throw new Exception("The task hasn't been scheduled!");
			}
		}
		else {
			throw new Exception("Invalid task for this car type!");
		}
	}
	
	//delete a scheduled task; no effect if task not scheduled or doesn't exist:
	public void removeTask(String task) {
		tasks.remove(task);
	}
	

}
