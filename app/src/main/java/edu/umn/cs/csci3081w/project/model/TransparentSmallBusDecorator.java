package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Constructor for Small Bus.
 */
public class TransparentSmallBusDecorator extends SmallBusDecorator {

  /**
   * Constructor for TransparentSmallBusDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public TransparentSmallBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(122, 0, 25, 155));
  }
}
