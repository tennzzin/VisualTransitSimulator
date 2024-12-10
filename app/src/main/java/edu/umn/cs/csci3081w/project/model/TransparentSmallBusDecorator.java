package edu.umn.cs.csci3081w.project.model;

import java.awt.*;

public class TransparentSmallBusDecorator extends SmallBusDecorator {
  public TransparentSmallBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(122, 0, 25, 155));
  }
}
