package edu.umn.cs.csci3081w.project.model;

import com.google.gson.JsonObject;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vehicle in the simulation system.
 */
public abstract class Vehicle implements VehicleObserver {

  /**
   * For testing purposes.
   */
  public static boolean TESTING = false;
  private int id;
  private int capacity;
  //the speed is in distance over a time unit
  private double speed;
  private PassengerLoader loader;
  private PassengerUnloader unloader;
  private List<Passenger> passengers;
  private String name;
  private Position position;
  private Line line;
  private double distanceRemaining;
  private Stop nextStop;
  private List<Integer> carbonEmissionHistory;
  private VehicleConcreteSubject vehicleConcreteSubject;
  private JsonObject testOutput;


  /**
   * Constructor for a vehicle.
   *
   * @param id       vehicle identifier
   * @param line     line
   * @param capacity vehicle capacity
   * @param speed    vehicle speed
   * @param loader   passenger loader for vehicle
   * @param unloader passenger unloader for vehicle
   */
  public Vehicle(int id, Line line, int capacity, double speed, PassengerLoader loader,
                 PassengerUnloader unloader) {
    this.id = id;
    this.capacity = capacity;
    this.speed = speed;
    this.loader = loader;
    this.unloader = unloader;
    this.passengers = new ArrayList<Passenger>();
    this.line = line;
    this.distanceRemaining = 0;
    this.nextStop = line.getOutboundRoute().getNextStop();
    setName(line.getOutboundRoute().getName() + id);
    setPosition(new Position(nextStop.getPosition().getLongitude(),
        nextStop.getPosition().getLatitude()));
    carbonEmissionHistory = new ArrayList<Integer>();
  }

  /**
   * Abstract method for reporting the vehicle's details.
   *
   * @param out The PrintStream to which the report will be written
   */
  public abstract void report(PrintStream out);

  /**
   * Abstract method for getting current CO2 emission.
   *
   * @return the current CO2 emission
   */

  public abstract int getCurrentCO2Emission();

  /**
   * Gets ID of vehicle.
   *
   * @return the vehicle ID
   */
  public int getId() {
    return id;
  }

  /**
   * Gets capacity of vehicle.
   *
   * @return the vehicle's capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Gets speed of vehicle.
   *
   * @return the vehicle's speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Gets the passenger loader for the vehicle.
   *
   * @return the passenger loader
   */
  public PassengerLoader getPassengerLoader() {
    return loader;
  }

  /**
   * Gets the passenger unloader for the vehicle.
   *
   * @return the passenger unloader
   */
  public PassengerUnloader getPassengerUnloader() {
    return unloader;
  }

  /**
   * Gets list of passengers currently on vehicle.
   *
   * @return the list of passengers
   */
  public List<Passenger> getPassengers() {
    return passengers;
  }

  /**
   * Gets name of vehicle.
   *
   * @return the vehicle name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of vehicle.
   *
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the current position of vehicle.
   *
   * @return the position
   */
  public Position getPosition() {
    return position;
  }

  /**
   * Sets the position of vehicle.
   *
   * @param position the position to set
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Checks if vehicle's trip is complete.
   *
   * @return true if the trip is complete, false otherwise
   */
  public boolean isTripComplete() {
    return line.getOutboundRoute().isAtEnd() && line.getInboundRoute().isAtEnd();
  }

  /**
   * Loads a new passenger onto vehicle.
   *
   * @param newPassenger the passenger to be loaded
   * @return the number of passengers loaded
   */
  public int loadPassenger(Passenger newPassenger) {
    return getPassengerLoader().loadPassenger(newPassenger, getCapacity(), getPassengers());
  }

  /**
   * Moves the bus on its route.
   */
  public void move() {
    //actually move
    double speed = updateDistance();
    if (!isTripComplete() && distanceRemaining <= 0) {
      //load & unload
      int passengersHandled = handleStop();
      if (passengersHandled >= 0) {
        // if we spent time unloading/loading
        // we don't get to count excess distance towards next stop
        distanceRemaining = 0;
      }
      //switch to next stop
      toNextStop();
    }

    // Get the correct route and early exit
    Route currentRoute = line.getOutboundRoute();
    if (line.getOutboundRoute().isAtEnd()) {
      if (line.getInboundRoute().isAtEnd()) {
        return;
      }
      currentRoute = line.getInboundRoute();
    }
    Stop prevStop = currentRoute.prevStop();
    Stop nextStop = currentRoute.getNextStop();
    double distanceBetween = currentRoute.getNextStopDistance();
    // the ratio shows us how far from the previous stop are we in a ratio from 0 to 1
    double ratio;
    // check if we are at the first stop
    if (distanceBetween - 0.00001 < 0) {
      ratio = 1;
    } else {
      ratio = distanceRemaining / distanceBetween;
      if (ratio < 0) {
        ratio = 0;
        distanceRemaining = 0;
      }
    }
    double newLongitude = nextStop.getPosition().getLongitude() * (1 - ratio)
        + prevStop.getPosition().getLongitude() * ratio;
    double newLatitude = nextStop.getPosition().getLatitude() * (1 - ratio)
        + prevStop.getPosition().getLatitude() * ratio;
    setPosition(new Position(newLongitude, newLatitude));
  }

  /**
   * Update the simulation state for this bus.
   */
  public void update() {
    // update passengers FIRST
    // new passengers will get "updated" when getting on the bus
    for (Passenger passenger : getPassengers()) {
      passenger.pasUpdate();
    }
    if (!line.isIssueExist()) {
      move();
    }
    carbonEmissionHistory.add(0, getCurrentCO2Emission());
  }

  private int unloadPassengers() {
    return getPassengerUnloader().unloadPassengers(getPassengers(), nextStop);
  }

  private int handleStop() {
    // This function handles arrival at a bus stop
    int passengersHandled = 0;
    // unloading passengers
    passengersHandled += unloadPassengers();
    // loading passengers
    passengersHandled += nextStop.loadPassengers(this);
    // if passengers were unloaded or loaded, it means we made
    // a stop to do the unload/load operation. In this case, the
    // distance remaining to the stop is 0 because we are at the stop.
    // If no unload/load operation was made and the distance is negative,
    // this means that we did not stop and keep going further.
    if (passengersHandled != 0) {
      distanceRemaining = 0;
    }
    return passengersHandled;
  }

  private void toNextStop() {
    //current stop
    currentRoute().nextStop();
    if (!isTripComplete()) {
      // it's important we call currentRoute() again,
      // as nextStop() may have caused it to change.
      nextStop = currentRoute().getNextStop();
      distanceRemaining +=
          currentRoute().getNextStopDistance();
      // note, if distanceRemaining was negative because we
      // had extra time left over, that extra time is
      // effectively counted towards the next stop
    } else {
      nextStop = null;
      distanceRemaining = 999;
    }
  }

  /**
   * Updates the distance remaining.
   * Train does not move if speed is negative or train is at end of route
   *
   * @return the effective speed of the vehicle
   */
  private double updateDistance() {
    // Updates the distance remaining and returns the effective speed of the bus
    // Bus does not move if speed is negative or bus is at end of route
    if (isTripComplete()) {
      return 0;
    }
    if (getSpeed() < 0) {
      return 0;
    }
    distanceRemaining -= getSpeed();
    return getSpeed();
  }

  /**
   * gets current route for the vehicle.
   *
   * @return the outboudRoute if that route has not ended, else returns InboundRoute
   */
  private Route currentRoute() {
    // Figure out if we're on the outgoing or incoming route
    if (!line.getOutboundRoute().isAtEnd()) {
      return line.getOutboundRoute();
    }
    return line.getInboundRoute();
  }

  /**
   * Gets the bus's next stop on route.
   *
   * @return the next stop
   */
  public Stop getNextStop() {
    return nextStop;
  }

  /**
   * Gets the line of bus.
   *
   * @return the line of bus
   */
  public Line getLine() {
    return line;
  }

  /**
   * Gets the distance remaining for the route.
   *
   * @return the distance remaining
   */
  public double getDistanceRemaining() {
    return distanceRemaining;
  }

  /**
   * Retrieves the current vehicle information sends the information to the visualization module.
   *
   * @return whether the trip was completed
   */
  public boolean provideInfo() {
    boolean tripCompleted = false;
    if (!isTripComplete()) {
      JsonObject data = new JsonObject();
      data.addProperty("command", "observedVehicle");

      String type = "";
      if (this instanceof SmallBus) {
        type = SmallBus.SMALL_BUS_VEHICLE;
      } else if (this instanceof LargeBus) {
        type = LargeBus.LARGE_BUS_VEHICLE;
      } else if (this instanceof ElectricTrain) {
        type = ElectricTrain.ELECTRIC_TRAIN_VEHICLE;
      } else if (this instanceof DieselTrain) {
        type = DieselTrain.DIESEL_TRAIN_VEHICLE;
      }

      StringBuilder carbonEmissionHistoryString = new StringBuilder();
      int length = Math.min(5, carbonEmissionHistory.size());
      if (length > 0) {
        carbonEmissionHistoryString.append(carbonEmissionHistory.get(0));
        for (int i = 1; i < length; i++) {
          carbonEmissionHistoryString.append(", ");
          carbonEmissionHistoryString.append(carbonEmissionHistory.get(i));
        }
      }

      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(String.format("%d", getId()) + System.lineSeparator());
      stringBuilder.append("-----------------------------" + System.lineSeparator());
      stringBuilder.append(String.format("* Type: %s", type) + System.lineSeparator());
      stringBuilder.append(String.format("* Position: (%f,%f)", getPosition().getLongitude(),
          getPosition().getLatitude()) + System.lineSeparator());
      stringBuilder.append(String.format("* Passengers: %d", getPassengers().size())
          + System.lineSeparator());
      stringBuilder.append(String.format("* CO2: %s", carbonEmissionHistoryString.toString())
          + System.lineSeparator());

      data.addProperty("text", stringBuilder.toString());
      if (TESTING) {
        testOutput = data;
      } else {
        vehicleConcreteSubject.getSession().sendJson(data);
      }
      tripCompleted = false;
      return tripCompleted;
    } else {
      JsonObject data = new JsonObject();
      data.addProperty("command", "observedVehicle");
      data.addProperty("text", "");
      if (TESTING) {
        testOutput = data;
      } else {
        vehicleConcreteSubject.getSession().sendJson(data);
      }
      tripCompleted = true;
      return tripCompleted;
    }
  }

  /**
   * Gets test output in JSON format.
   *
   * @return the test output
   */
  public JsonObject getTestOutput() {
    return testOutput;
  }

  /**
   * Sets the vehicle concrete subject that will be used for observing the vehicle.
   *
   * @param vehicleConcreteSubject the concrete subject to set
   */
  public void setVehicleSubject(VehicleConcreteSubject vehicleConcreteSubject) {
    this.vehicleConcreteSubject = vehicleConcreteSubject;
  }
}
