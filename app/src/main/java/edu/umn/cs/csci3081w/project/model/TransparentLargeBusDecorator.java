package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Large Bus.
 */
public class TransparentLargeBusDecorator extends LargeBusDecorator {

  /**
   * Constructor for TransparentLargeBusDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public TransparentLargeBusDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(239, 130, 238, 155));
  }
}
