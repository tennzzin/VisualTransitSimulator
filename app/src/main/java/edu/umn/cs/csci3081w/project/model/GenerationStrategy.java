package edu.umn.cs.csci3081w.project.model;

/**
 * Interface representing a strategy for determining the type of vehicle to generate.
 */
public interface GenerationStrategy {

  /**
   * Determines the type of vehicle to generate based on the current state
   * of the storage facility.
   *
   * @param storageFacility the storage facility that holds the available vehicles
   * @return the type of vehicle to generate
   */
  public String getTypeOfVehicle(StorageFacility storageFacility);
}
