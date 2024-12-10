package edu.umn.cs.csci3081w.project.model;

import java.awt.*;
import java.io.PrintStream;

public class ElectricTrainDecorator extends VehicleDecorator{
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
