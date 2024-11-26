package edu.umn.cs.csci3081w.project.model;

/**
 * Implements GenerationStrategy interface and defines
 * vehicle generation logic for the "night" period (After 6PM).
 */
public class BusStrategyNight implements GenerationStrategy {
  private int counter;

  /**
   * Constructor for BusStrategyNight.
   */
  public BusStrategyNight() {
    this.counter = 0;
  }

  @Override
  public String getTypeOfVehicle(StorageFacility storageFacility) {
    String typeOfVehicle = null;
    if (counter < 3) {
      if (storageFacility.getSmallBusesNum() > 0) {
        typeOfVehicle = SmallBus.SMALL_BUS_VEHICLE;
      }
    } else {
      if (storageFacility.getLargeBusesNum() > 0) {
        typeOfVehicle = LargeBus.LARGE_BUS_VEHICLE;
      }
    }

    if (typeOfVehicle != null) {
      counter++;
      counter = counter % 4;
    }
    return typeOfVehicle;
  }

  /**
   * Gets the current value of the counter.
   *
   * @return the current value of the counter
   */
  public int getCounter() {
    return counter;
  }
}
