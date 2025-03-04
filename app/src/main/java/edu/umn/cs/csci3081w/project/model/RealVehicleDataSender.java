package edu.umn.cs.csci3081w.project.model;

import com.google.gson.JsonObject;

/**
 * Sends vehicle data to the visualization system.
 */
public class RealVehicleDataSender implements VehicleDataSender {
  @Override
  public void send(JsonObject data) {
    //placeholder for real data sending logic
    System.out.println("Sending data to visualization: " + data);
  }
}
