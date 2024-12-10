package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Electric Train.
 */
public class TransparentElectricTrainDecorator extends ElectricTrainDecorator {

  /**
   * Constructor for TransparentElectricTrainDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public TransparentElectricTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(60, 179, 113, 155));
  }
}
