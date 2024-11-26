package edu.umn.cs.csci3081w.project.model;

/**
 * Generate unique IDs and update whenever new one is created.
 */
public class Counter {

  private int routeIdCounter = 10;
  private int stopIdCounter = 100;
  private int smallBusIdCounter = 1000;
  private int largeBusIdCounter = 2000;
  private int electricTrainIdCounter = 3000;
  private int dieselTrainIdCounter = 4000;
  private int lineIdCounter = 10000;

  /**
   * Constructor for Counter.
   */
  public Counter() {

  }

  /**
   * Gets current route ID and increments counter.
   *
   * @return the current route ID
   */
  public int getRouteIdCounterAndIncrement() {
    return routeIdCounter++;
  }

  /**
   * Gets current stop ID and increments counter.
   *
   * @return the current stop ID
   */
  public int getStopIdCounterAndIncrement() {
    return stopIdCounter++;
  }

  /**
   * Gets current small bus ID and increments counter.
   *
   * @return the current small bus ID
   */
  public int getSmallBusIdCounterAndIncrement() {
    return smallBusIdCounter++;
  }

  /**
   * Gets current large bus ID and increments counter.
   *
   * @return the current large bus ID
   */
  public int getLargeBusIdCounterAndIncrement() {
    return largeBusIdCounter++;
  }

  /**
   * Gets current electric train ID and increments counter.
   *
   * @return the current electric train ID
   */
  public int getElectricTrainIdCounterAndIncrement() {
    return electricTrainIdCounter++;
  }

  /**
   * Gets current diesel train ID and increments counter.
   *
   * @return the current diesel train ID
   */
  public int getDieselTrainIdCounterAndIncrement() {
    return dieselTrainIdCounter++;
  }

  /**
   * Gets current line ID and increments counter.
   *
   * @return the current line ID
   */
  public int getLineIdCounterAndIncrement() {
    return lineIdCounter++;
  }

}
