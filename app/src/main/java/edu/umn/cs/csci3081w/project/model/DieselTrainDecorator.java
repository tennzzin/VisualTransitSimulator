package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Diesel Train.
 */
public class DieselTrainDecorator extends VehicleDecorator {

  /**
   * Constructor for DieselTrainDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public DieselTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(255, 204, 51, 255));
  }

  @Override
  public void update() {
    super.update();
    if (getLine().isIssueExist()) {
      this.setVehicle(new TransparentDieselTrainDecorator(this.getVehicle()));
    } else {
      setColor(new Color(255, 204, 51, 255));
    }
  }
}
