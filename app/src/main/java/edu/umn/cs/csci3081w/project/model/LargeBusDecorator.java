package edu.umn.cs.csci3081w.project.model;

import java.awt.*;

public class LargeBusDecorator extends VehicleDecorator{
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
