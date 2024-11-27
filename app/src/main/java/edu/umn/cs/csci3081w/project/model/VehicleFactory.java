package edu.umn.cs.csci3081w.project.model;

/**
 * Interface that defines methods for creating and returning vehicles.
 */
public interface VehicleFactory {

  /**
   * Generate new vehicles based on provided line.
   *
   * @param line the line that vehicle will follow
   * @return the newly generated vehicle
   */
  public Vehicle generateVehicle(Line line);

  /**
   * Returns vehicle to system.
   *
   * @param vehicle the vehicle to be returned
   */
  public void returnVehicle(Vehicle vehicle);
}
