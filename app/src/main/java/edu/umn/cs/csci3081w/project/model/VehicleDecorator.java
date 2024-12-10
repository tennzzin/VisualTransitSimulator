package edu.umn.cs.csci3081w.project.model;

import com.google.gson.JsonObject;
import java.awt.Color;
import java.io.PrintStream;
import java.util.List;

/**
 * Decorator class for Vehicle Decorator.
 */
public class VehicleDecorator extends Vehicle {
  private Vehicle vehicle;
  private Color color;

  /**
   * Constructor for VehicleDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public VehicleDecorator(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  /**
   * Sets the color of vehicle.
   *
   * @param color the vehicle's color
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Gets color of vehicle.
   *
   * @return the vehicle's color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the vehicle.
   *
   * @return the vehicle
   */
  public Vehicle getVehicle() {
    return vehicle;
  }

  /**
   * Sets the vehicle.
   *
   * @param vehicle the vehicle
   */
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  @Override
  public void report(PrintStream out) {
    vehicle.report(out);
  }

  @Override
  public int getCurrentCO2Emission() {
    return vehicle.getCurrentCO2Emission();
  }

  @Override
  public void update() {
    vehicle.update();
  }

  @Override
  public int getId() {
    return vehicle.getId();
  }

  @Override
  public int getCapacity() {
    return vehicle.getCapacity();
  }

  @Override
  public double getSpeed() {
    return vehicle.getSpeed();
  }

  @Override
  public PassengerLoader getPassengerLoader() {
    return vehicle.getPassengerLoader();
  }

  @Override
  public PassengerUnloader getPassengerUnloader() {
    return vehicle.getPassengerUnloader();
  }

  @Override
  public List<Passenger> getPassengers() {
    return vehicle.getPassengers();
  }

  @Override
  public String getName() {
    return vehicle.getName();
  }

  @Override
  public void setName(String name) {
    vehicle.setName(name);
  }

  @Override
  public Position getPosition() {
    return vehicle.getPosition();
  }

  @Override
  public void setPosition(Position position) {
    vehicle.setPosition(position);
  }

  @Override
  public boolean isTripComplete() {
    return vehicle.isTripComplete();
  }

  @Override
  public int loadPassenger(Passenger newPassenger) {
    return vehicle.loadPassenger(newPassenger);
  }

  @Override
  public void move() {
    vehicle.move();
  }

  @Override
  public Stop getNextStop() {
    return vehicle.getNextStop();
  }

  @Override
  public Line getLine() {
    return vehicle.getLine();
  }

  @Override
  public double getDistanceRemaining() {
    return vehicle.getDistanceRemaining();
  }

  @Override
  public boolean provideInfo() {
    return vehicle.provideInfo();
  }

  @Override
  public JsonObject getTestOutput() {
    return vehicle.getTestOutput();
  }

  @Override
  public void setVehicleSubject(VehicleConcreteSubject vehicleConcreteSubject) {
    vehicle.setVehicleSubject(vehicleConcreteSubject);
  }
}