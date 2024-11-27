package edu.umn.cs.csci3081w.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that generates passengers for given stops.
 */
public abstract class PassengerGenerator {
  private List<Stop> stops;
  private List<Double> probabilities;

  /**
   * Constructor for abstract class.
   *
   * @param stops         list of stops
   * @param probabilities list of probabilities
   */
  public PassengerGenerator(List<Stop> stops, List<Double> probabilities) {
    this.probabilities = new ArrayList<>();
    this.stops = new ArrayList<>();
    for (Stop s : stops) {
      this.stops.add(s);
    }
    for (Double probability : probabilities) {
      this.probabilities.add(probability);
    }
  }

  /**
   * Generates number of passengers based on implemented logic.
   *
   * @return the number of passengers generated
   */
  public abstract int generatePassengers();

  /**
   * Gets list of stops associated with this passenger generator.
   *
   * @return a list of stops
   */
  public List<Stop> getStops() {
    return stops;
  }

  /**
   * Gets list of probabilities associated with each stop.
   *
   * @return a list of probabilities
   */
  public List<Double> getProbabilities() {
    return probabilities;
  }

}