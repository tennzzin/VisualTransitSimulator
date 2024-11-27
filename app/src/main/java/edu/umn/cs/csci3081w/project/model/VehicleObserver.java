package edu.umn.cs.csci3081w.project.model;

/**
 * Interface that observe and receive updates.
 */
public interface VehicleObserver {

  /**
   * Retrieves current vehicle information and sends it.
   *
   * @return true if the vehicle trip is complete, false otherwise
   */
  public boolean provideInfo();

  /**
   * Sets the vehicle concrete subject that the observer is associated with.
   *
   * @param vehicleConcreteSubject the concrete subject to set
   */
  public void setVehicleSubject(VehicleConcreteSubject vehicleConcreteSubject);
}
