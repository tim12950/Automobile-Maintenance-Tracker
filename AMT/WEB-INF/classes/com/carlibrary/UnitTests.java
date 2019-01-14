package com.carlibrary;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;

public class UnitTests {
	
	ElectricCar tesla = new ElectricCar("ABC123", "Tesla", "Model S", 2018, 100);
	GasCar honda = new GasCar("ABC123", "Honda", "Civic", 2018, 100);
	DieselCar bmw = new DieselCar("ABC123", "BMW", "5-series", 2018, 100);
	String oilChange = "Oil Change";
	String tireRotation = "Tire Rotation";
	String washerFluid = "Top up Washer Fluid";
	
	@Test
	public void testGetAllTasks() {
		//get all potential tasks in alphabetical order:
		Iterator<String> tasks = tesla.getAllTasks();
		assertEquals(tasks.next(), tireRotation);
		assertEquals(tasks.next(), washerFluid);
		assertFalse(tasks.hasNext());
	}
	
	@Test
	public void testAddAndGetScheduledTasks() {
		//by default, no tasks scheduled:
		Iterator<String> tasks = honda.getScheduledTasks();
		assertFalse(tasks.hasNext());
		
		//add a task and see if it gets scheduled:
		try {
			honda.addTask(oilChange);
		}
		catch(Exception e) {
			fail();
		}
		tasks = honda.getScheduledTasks();
		assertEquals(tasks.next(), oilChange);
		assertFalse(tasks.hasNext());
		
		//add an invalid task and see if exception thrown:
		boolean thrown = false;
		Exception ex = new Exception();
		
		try {
			tesla.addTask(oilChange);
		}
		catch(Exception e) {
			thrown = true;
			ex = e;
		}
		assertTrue(thrown);
		assertEquals(ex.getMessage(), "Invalid task for this car type!");
	}
	
	@Test
	public void testGetStatusesI() {
		//add a task and see if status if incomplete (i.e. false):
		try {
			bmw.addTask(tireRotation);
		}
		catch(Exception e) {
			fail();
		}
		Iterator<Boolean> statuses = bmw.getStatuses();
		assertFalse(statuses.next());
		assertFalse(statuses.hasNext());
	}
	
	@Test
	public void testUpdateTask() {
		//add a task and update it to complete (i.e. true):
		try {
			honda.addTask(washerFluid);
			honda.updateTask(washerFluid);
		}
		catch(Exception e) {
			fail();
		}
		Iterator<Boolean> statuses = honda.getStatuses();
		assertTrue(statuses.next());
		assertFalse(statuses.hasNext());
		
		//update an invalid task and see if correct exception thrown:
		boolean thrown = false;
		Exception ex = new Exception();
		
		try {
			tesla.updateTask(oilChange);
		}
		catch(Exception e) {
			thrown = true;
			ex = e;
		}
		assertTrue(thrown);
		assertEquals(ex.getMessage(), "Invalid task for this car type!");
		
		//update an unscheduled task and see if correct exception thrown:
		thrown = false;
		
		try {
			bmw.updateTask(tireRotation);
		}
		catch(Exception e) {
			thrown = true;
			ex = e;
		}
		assertTrue(thrown);
		assertEquals(ex.getMessage(), "The task hasn't been scheduled!");
	}
	
	@Test
	public void testGetStatusesII() {
		//add several tasks and update them and observe the order the iterator returns the statuses in:
		try {
			honda.addTask(washerFluid);
			honda.addTask(tireRotation);
			honda.addTask(oilChange);
			honda.updateTask(oilChange);
			honda.updateTask(washerFluid);
		}
		catch(Exception e) {
			fail();
		}
		//order should be alphabetical: oilchange(true), tirerotation(false), washerfluid(true):
		Iterator<String> tasks = honda.getScheduledTasks();
		Iterator<Boolean> statuses = honda.getStatuses();
		assertEquals(tasks.next(), oilChange);
		assertTrue(statuses.next());
		assertEquals(tasks.next(), tireRotation);
		assertFalse(statuses.next());
		assertEquals(tasks.next(), washerFluid);
		assertTrue(statuses.next());
		assertFalse(tasks.hasNext());
		assertFalse(statuses.hasNext());
	}
	
	@Test
	public void testRemoveTask() {
		//add a task and remove it:
		try {
			bmw.addTask(oilChange);
			bmw.removeTask(oilChange);
		}
		catch (Exception e) {
			fail();
		}
		bmw.removeTask(oilChange);
		Iterator<String> tasks = bmw.getScheduledTasks();
		assertFalse(tasks.hasNext());
		
		//remove invalid and non-scheduled tasks; there should be no effect:
		tesla.removeTask(tireRotation);
		tesla.removeTask(oilChange);
		tasks = tesla.getScheduledTasks();
		assertFalse(tasks.hasNext());
	}
	
}
