package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import java.util.List;

/**
 * Command that registers a vehicle as an observer in the simulation.
 */
public class RegisterVehicleCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for RegisterVehicleCommand.
   *
   * @param simulator the simulator instance where the vehicle will be registered
   */
  public RegisterVehicleCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Registers vehicle in observer.
   *
   * @param session current simulation session
   * @param command the get vehicles command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    List<Vehicle> activeVehicles = simulator.getActiveVehicles();
    int vehicleId = command.get("id").getAsInt();
    Vehicle vehicle = null;
    for (Vehicle activeVehicle : activeVehicles) {
      if (activeVehicle.getId() == vehicleId) {
        vehicle = activeVehicle;
      }
    }
    simulator.addObserver(vehicle);
  }
}
