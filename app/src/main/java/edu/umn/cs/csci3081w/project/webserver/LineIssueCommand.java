package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Line;
import java.util.List;

/**
 *  Command that handles line issues in the simulation.
 */
public class LineIssueCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  /**
   * Constructor for LineIssueCommand.
   *
   * @param simulator the visual transit simulator that contains line issue data
   */
  public LineIssueCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Injects issues in line.
   *
   * @param session current simulation session
   * @param command the initialize routes command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    List<Line> lines = simulator.getLines();
    int lineId = command.get("id").getAsInt();
    for (Line line : lines) {
      if (line.getId() == lineId) {
        line.createIssue();
      }
    }
  }

}
