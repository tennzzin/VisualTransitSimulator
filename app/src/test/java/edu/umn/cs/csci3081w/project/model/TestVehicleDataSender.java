package edu.umn.cs.csci3081w.project.model;

import com.google.gson.JsonObject;

/**
 * Captures vehicle data for testing purposes.
 */
public class TestVehicleDataSender implements VehicleDataSender {
  private JsonObject testOutput;

  @Override
  public void send(JsonObject data) {
    this.testOutput = data;
  }

  public JsonObject getTestOutput() {
    return testOutput;
  }
}
