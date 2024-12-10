package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

public class TransparentLargeBusDecorator extends LargeBusDecorator {
  public TransparentLargeBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(239, 130, 238, 155));
  }
}
