package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Electric Trains.
 */
public class ElectricTrainDecorator extends VehicleDecorator {

  /**
   * Constructor for ElectricTrainDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public ElectricTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(60, 179, 113, 255));
  }

  @Override
  public void update() {
    super.update();
    if (getLine().isIssueExist()) {
      this.setVehicle(new TransparentElectricTrainDecorator(this.getVehicle()));
    } else {
      setColor(new Color(60, 179, 113, 255));
    }
  }
}
