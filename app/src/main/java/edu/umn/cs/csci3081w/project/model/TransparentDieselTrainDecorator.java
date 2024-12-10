package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

/**
 * Decorator class for Diesel Train.
 */
public class TransparentDieselTrainDecorator extends DieselTrainDecorator {

  /**
   * Constructor for TransparentDieselTrainDecorator.
   *
   * @param vehicle the vehicle to be decorated
   */
  public TransparentDieselTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(255, 204, 51, 155));
  }
}