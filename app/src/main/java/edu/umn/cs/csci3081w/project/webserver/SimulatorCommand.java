package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

/**
 * Abstract class for commands to interact with the visual transit simulator.
 */
public abstract class SimulatorCommand {

  /**
   * Constructor for SimulatorCommand.
   */
  protected SimulatorCommand() {
  }

  /**
   * Executes the specific command.
   *
   * @param session the current simulation session
   * @param command a JsonObject containing content of command to execute
   */
  public abstract void execute(WebServerSession session, JsonObject command);
}
