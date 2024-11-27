package edu.umn.cs.csci3081w.project.webserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents state of session in visual transit simulation server.
 */
public class WebServerSessionState {

  private Map<String, SimulatorCommand> commands;

  /**
   * Constructor for WebServerSessionState.
   */
  public WebServerSessionState() {
    this.commands = new HashMap<String, SimulatorCommand>();
  }

  /**
   * Gets the map of available commands for the session.
   *
   * @return a map where the keys are command names and the values are
   *         the corresponding SimulatorCommand objects
   */
  public Map<String, SimulatorCommand> getCommands() {
    return commands;
  }
}
