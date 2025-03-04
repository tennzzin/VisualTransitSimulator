package edu.umn.cs.csci3081w.project.model;

import com.google.gson.JsonObject;

/**
 * Interface for sending vehicle data.
 */
public interface VehicleDataSender {
  void send(JsonObject data);
}