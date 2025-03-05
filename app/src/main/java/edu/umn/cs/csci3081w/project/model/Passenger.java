package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Represents a passenger in the vehicle transit simulation.
 */
public class Passenger {

  private String name;
  private int destinationStopId;
  private int waitAtStop;
  private int timeOnVehicle;

  /**
   * Constructor for passenger.
   *
   * @param name              name of passenger
   * @param destinationStopId destination stop id
   */
  public Passenger(int destinationStopId, String name) {
    this.name = name;
    this.destinationStopId = destinationStopId;
    this.waitAtStop = 0;
    this.timeOnVehicle = 0;
  }

  /**
   * Updates time variables for passenger.
   */
  public void pasUpdate() {
    if (isOnVehicle()) {
      timeOnVehicle++;
    } else {
      waitAtStop++;
    }
  }

  /**
   * Initializes passenger's time on vehicle to 1.
   */
  public void setOnVehicle() {
    timeOnVehicle = 1;
  }

  /**
   * Checks if passenger is currently on vehicle.
   *
   * @return true if passenger is on vehicle, false otherwise
   */
  public boolean isOnVehicle() {
    return timeOnVehicle > 0;
  }

  /**
   * Gets the ID of the destination stop.
   *
   * @return the destination stop ID
   */
  public int getDestination() {
    return destinationStopId;
  }

  /**
   * Report statistics for passenger.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Passenger Info Start####");
    out.println("Name: " + name);
    out.println("Destination: " + destinationStopId);
    out.println("Wait at stop: " + waitAtStop);
    out.println("Time on vehicle: " + timeOnVehicle);
    out.println("####Passenger Info End####");
  }

  /**
   * Gets time passenger has waited at stop.
   *
   * @return the wait time at stop
   */
  public int getWaitAtStop() {
    return waitAtStop;
  }

  /**
   * Gets passenger time on vehicle.
   *
   * @return the time on vehicle
   */
  public int getTimeOnVehicle() {
    return timeOnVehicle;
  }

  /**
   * Gets the name of the passenger.
   *
   * @return the passenger's name
   */
  public String getName() {
    return name;
  }
}
