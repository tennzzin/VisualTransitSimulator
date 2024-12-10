package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

public class TransparentElectricTrainDecorator extends ElectricTrainDecorator {
  public TransparentElectricTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(60, 179, 113, 155));
  }
}
