package edu.umn.cs.csci3081w.project.webserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.PassengerFactory;
import edu.umn.cs.csci3081w.project.model.RandomPassengerGenerator;
import edu.umn.cs.csci3081w.project.model.TestVehicleDataSender;
import edu.umn.cs.csci3081w.project.model.Vehicle;
import javax.websocket.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class WebServerSessionTest {
  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Test command for initializing the simulation.
   */
  @Test
  public void testSimulationInitialization() {
    WebServerSession webServerSessionSpy = spy(WebServerSession.class);
    doNothing().when(webServerSessionSpy).sendJson(Mockito.isA(JsonObject.class));
    Session sessionDummy = mock(Session.class);
    webServerSessionSpy.onOpen(sessionDummy);
    JsonObject commandFromClient = new JsonObject();
    commandFromClient.addProperty("command", "initLines");
    webServerSessionSpy.onMessage(commandFromClient.toString());
    ArgumentCaptor<JsonObject> messageCaptor = ArgumentCaptor.forClass(JsonObject.class);
    verify(webServerSessionSpy).sendJson(messageCaptor.capture());
    JsonObject commandToClient = messageCaptor.getValue();
    assertEquals("2", commandToClient.get("numLines").getAsString());
  }

  /**
   * Test vehicle provide info with a mocked session.
   */
  @Test
  public void testVehicleProvideInfo() {
    //create a mock session
    Session sessionMock = mock(Session.class);

    //mock WebServerSession
    WebServerSession webServerSessionMock = mock(WebServerSession.class);

    //stub sendJson to do nothing
    doNothing().when(webServerSessionMock).sendJson(Mockito.any(JsonObject.class));

    //attach the session
    webServerSessionMock.onOpen(sessionMock);

    //create a mock vehicle
    Vehicle mockVehicle = mock(Vehicle.class);
    TestVehicleDataSender testSender = new TestVehicleDataSender();
    mockVehicle.setDataSender(testSender);

    //mock vehicle behavior
    JsonObject mockJsonObj = new JsonObject();
    mockJsonObj.addProperty("command", "observedVehicle");
    mockJsonObj.addProperty("text", "Test Vehicle Data");

    //simulate sending JSON
    webServerSessionMock.sendJson(mockJsonObj);

    //capture the JSON output
    ArgumentCaptor<JsonObject> jsonCapture = ArgumentCaptor.forClass(JsonObject.class);
    verify(webServerSessionMock).sendJson(jsonCapture.capture());
    JsonObject capturedData = jsonCapture.getValue();

    assertEquals("observedVehicle", capturedData.get("command").getAsString());
    assertEquals("Test Vehicle Data", capturedData.get("text").getAsString());
  }
}
