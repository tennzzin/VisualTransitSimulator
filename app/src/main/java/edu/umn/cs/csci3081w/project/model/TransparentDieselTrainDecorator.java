package edu.umn.cs.csci3081w.project.model;

import java.awt.Color;

public class TransparentDieselTrainDecorator extends DieselTrainDecorator {
  public TransparentDieselTrainDecorator(Vehicle vehicle) {
    super(vehicle);
    setColor(new Color(255, 204, 51, 155));
  }
}