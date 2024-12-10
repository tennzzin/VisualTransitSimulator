package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Small Bus.
 */
public class SmallBusDecorator extends VehicleDecorator {

  /**
   * Constructor for SmallBusDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public SmallBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(122, 0, 25, 255));
  }

  @Override
  public void update() {
    super.update();
    if (getLine().isIssueExist()) {
      this.setVehicle(new TransparentSmallBusDecorator(this.getVehicle()));
    } else {
      setColor(new Color(122, 0, 25, 255));
    }
  }
}
