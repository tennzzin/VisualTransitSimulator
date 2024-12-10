package edu.umn.cs.csci3081w.project.webserver;


import edu.umn.cs.csci3081w.project.model.Line;
import edu.umn.cs.csci3081w.project.webserver.VisualTransitSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisualTransitSimulatorTest {

  private VisualTransitSimulator simulator;
  private WebServerSession mockSession;
  private String configFile;
  private List<Integer> timeSinceLastVehicle;
  @BeforeEach
  public void setUp() {
    configFile = "Config.txt";;
    mockSession = mock(WebServerSession.class);
    simulator = new VisualTransitSimulator(configFile, mockSession);
  }

  /**
   * Test constructor initialization.
   */
  @Test
  public void testConstructor() {
    assertNotNull(simulator.getLines());
    assertNotNull(simulator.getActiveVehicles());
  }


  /**
   * Test setting vehicle factories.
   */
  @Test
  public void testSetVehicleFactories() {
    simulator.setVehicleFactories(9);
    assertNotNull(simulator.getActiveVehicles());
    assertNotNull(simulator.getActiveVehicles());
  }
}


