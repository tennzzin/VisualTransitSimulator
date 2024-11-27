package edu.umn.cs.csci3081w.project.model;

/**
 * interface that defines the methods for a subject.
 */
public interface VehicleSubject {

  /**
   * Attaches an observer to the subject.
   *
   * @param observer the observer to be added
   */
  public void attachObserver(VehicleObserver observer);

  /**
   * Detaches an observer to the subject.
   *
   * @param observer the observer to be removed
   */
  public void detachObserver(VehicleObserver observer);

  /**
   * Notify attached observers.
   */
  public void notifyObservers();
}
