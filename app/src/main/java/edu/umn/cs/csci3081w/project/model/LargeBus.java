package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Represents a Large Bus vehicle, inheriting from the Bus class.
 */
public class LargeBus extends Bus {

  /**
   * Constant representing the type of vehicle.
   */
  public static final String LARGE_BUS_VEHICLE = "LARGE_BUS_VEHICLE";

  /**
   * Constant representing the speed of bus.
   */
  public static final double SPEED = 0.5;

  /**
   * Constant representing the capacity of bus.
   */
  public static final int CAPACITY = 80;

  /**
   * Constructor for a bus.
   *
   * @param id       bus identifier
   * @param line     route of in/out bound
   * @param capacity capacity of bus
   * @param speed    speed of bus
   */
  public LargeBus(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }

  /**
   * Report statistics for the bus.
   *
   * @param out stream for printing
   */
  @Override
  public void report(PrintStream out) {
    out.println("####Large Bus Info Start####");
    out.println("ID: " + getId());
    out.println("Name: " + getName());
    out.println("Speed: " + getSpeed());
    out.println("Capacity: " + getCapacity());
    out.println("Position: " + (getPosition().getLatitude() + "," + getPosition().getLongitude()));
    out.println("Distance to next stop: " + getDistanceRemaining());
    out.println("****Passengers Info Start****");
    out.println("Num of passengers: " + getPassengers().size());
    for (Passenger pass : getPassengers()) {
      pass.report(out);
    }
    out.println("****Passengers Info End****");
    out.println("####Large Bus Info End####");
  }

  /**
   * co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  @Override
  public int getCurrentCO2Emission() {
    return ((2 * getPassengers().size()) + 5);
  }
}
