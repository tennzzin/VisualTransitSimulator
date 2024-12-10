package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Large Bus.
 */
public class LargeBusDecorator extends VehicleDecorator {

  /**
   * Constructor for LargeBusDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public LargeBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(239, 130, 238, 255));
  }

  @Override
  public void update() {
    super.update();
    if (getLine().isIssueExist()) {
      this.setVehicle(new TransparentLargeBusDecorator(this.getVehicle()));
    } else {
      setColor(new Color(239, 130, 238, 255));
    }
  }

}
