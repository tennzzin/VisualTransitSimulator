package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleTest {

  private Vehicle testVehicle;
  private Route testRouteIn;
  private Route testRouteOut;
  private LargeBus largeBus;
  private TestVehicleDataSender testSender;
  private ElectricTrain electricTrain;


  /**
   * Setup operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;

    List<Stop> stopsIn = new ArrayList<Stop>();
    Stop stop1 = new Stop(0, "test stop 1", new Position(-93.243774, 44.972392));
    Stop stop2 = new Stop(1, "test stop 2", new Position(-93.235071, 44.973580));
    stopsIn.add(stop1);
    stopsIn.add(stop2);
    List<Double> distancesIn = new ArrayList<>();
    distancesIn.add(0.843774422231134);
    List<Double> probabilitiesIn = new ArrayList<Double>();
    probabilitiesIn.add(.025);
    probabilitiesIn.add(0.3);
    PassengerGenerator generatorIn = new RandomPassengerGenerator(stopsIn, probabilitiesIn);

    testRouteIn = new Route(0, "testRouteIn",
        stopsIn, distancesIn, generatorIn);

    List<Stop> stopsOut = new ArrayList<Stop>();
    stopsOut.add(stop2);
    stopsOut.add(stop1);
    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.843774422231134);
    List<Double> probabilitiesOut = new ArrayList<Double>();
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(.025);
    PassengerGenerator generatorOut = new RandomPassengerGenerator(stopsOut, probabilitiesOut);

    testRouteOut = new Route(1, "testRouteOut",
        stopsOut, distancesOut, generatorOut);

    testSender = new TestVehicleDataSender();
    testVehicle = new VehicleTestImpl(1, new Line(10000, "testLine",
        "VEHICLE_LINE", testRouteOut, testRouteIn,
        new Issue()), 3, 1.0, new PassengerLoader(), new PassengerUnloader());
    testVehicle.setDataSender(testSender);
  }

  /**
   * Tests constructor.
   */
  @Test
  public void testConstructor() {
    assertEquals(1, testVehicle.getId());
    assertEquals("testRouteOut1", testVehicle.getName());
    assertEquals(3, testVehicle.getCapacity());
    assertEquals(1, testVehicle.getSpeed());
    assertEquals(testRouteOut, testVehicle.getLine().getOutboundRoute());
    assertEquals(testRouteIn, testVehicle.getLine().getInboundRoute());
  }

  /**
   * Tests if testIsTripComplete function works properly.
   */
  @Test
  public void testIsTripComplete() {
    assertEquals(false, testVehicle.isTripComplete());
    testVehicle.move();
    testVehicle.move();
    testVehicle.move();
    testVehicle.move();
    assertEquals(true, testVehicle.isTripComplete());

  }


  /**
   * Tests if loadPassenger function works properly.
   */
  @Test
  public void testLoadPassenger() {

    Passenger testPassenger1 = new Passenger(3, "testPassenger1");
    Passenger testPassenger2 = new Passenger(2, "testPassenger2");
    Passenger testPassenger3 = new Passenger(1, "testPassenger3");
    Passenger testPassenger4 = new Passenger(1, "testPassenger4");

    assertEquals(1, testVehicle.loadPassenger(testPassenger1));
    assertEquals(1, testVehicle.loadPassenger(testPassenger2));
    assertEquals(1, testVehicle.loadPassenger(testPassenger3));
    assertEquals(0, testVehicle.loadPassenger(testPassenger4));
  }


  /**
   * Tests if move function works properly.
   */
  @Test
  public void testMove() {

    assertEquals("test stop 2", testVehicle.getNextStop().getName());
    assertEquals(1, testVehicle.getNextStop().getId());
    testVehicle.move();

    assertEquals("test stop 1", testVehicle.getNextStop().getName());
    assertEquals(0, testVehicle.getNextStop().getId());

    testVehicle.move();
    assertEquals("test stop 1", testVehicle.getNextStop().getName());
    assertEquals(0, testVehicle.getNextStop().getId());

    testVehicle.move();
    assertEquals("test stop 2", testVehicle.getNextStop().getName());
    assertEquals(1, testVehicle.getNextStop().getId());

    testVehicle.move();
    assertEquals(null, testVehicle.getNextStop());

  }

  /**
   * Tests if update function works properly.
   */
  @Test
  public void testUpdate() {

    assertEquals("test stop 2", testVehicle.getNextStop().getName());
    assertEquals(1, testVehicle.getNextStop().getId());
    testVehicle.update();

    assertEquals("test stop 1", testVehicle.getNextStop().getName());
    assertEquals(0, testVehicle.getNextStop().getId());

    testVehicle.update();
    assertEquals("test stop 1", testVehicle.getNextStop().getName());
    assertEquals(0, testVehicle.getNextStop().getId());

    testVehicle.update();
    assertEquals("test stop 2", testVehicle.getNextStop().getName());
    assertEquals(1, testVehicle.getNextStop().getId());

    testVehicle.update();
    assertEquals(null, testVehicle.getNextStop());

  }

  /**
   * Test to see if observer got attached.
   */
  @Test
  public void testProvideInfo() {
    testVehicle.update();
    testVehicle.provideInfo();
    JsonObject capturedData = testSender.getTestOutput();

    String command = capturedData.get("command").getAsString();
    String expectedCommand = "observedVehicle";
    assertEquals(expectedCommand, command);

    String observedText = capturedData.get("text").getAsString();
    String expectedText = "1" + System.lineSeparator()
        + "-----------------------------" + System.lineSeparator()
        + "* Type: " + System.lineSeparator()
        + "* Position: (-93.235071,44.973580)" + System.lineSeparator()
        + "* Passengers: 0" + System.lineSeparator()
        + "* CO2: 0" + System.lineSeparator();
    assertEquals(expectedText, observedText);
  }

  /**
   * Tests provideInfo functionality using mocks.
   */
  @Test
  public void testProvideInfoWithMocks() {
    //mock Route and Stops for outbound
    Route mockedOutboundRoute = mock(Route.class);
    Stop mockedPrevStopOutbound = mock(Stop.class);
    Stop mockedNextStopOutbound = mock(Stop.class);
    Position mockedPrevPositionOutbound = mock(Position.class);
    Position mockedNextPositionOutbound = mock(Position.class);

    //set up outbound route
    when(mockedOutboundRoute.prevStop()).thenReturn(mockedPrevStopOutbound);
    when(mockedOutboundRoute.getNextStop()).thenReturn(mockedNextStopOutbound);
    when(mockedPrevStopOutbound.getPosition()).thenReturn(mockedPrevPositionOutbound);
    when(mockedNextStopOutbound.getPosition()).thenReturn(mockedNextPositionOutbound);
    doReturn(-93.243774d).when(mockedPrevPositionOutbound).getLongitude();
    doReturn(44.972392d).when(mockedPrevPositionOutbound).getLatitude();
    doReturn(-93.235071d).when(mockedNextPositionOutbound).getLongitude();
    doReturn(44.973580d).when(mockedNextPositionOutbound).getLatitude();

    //mock Route and Stops for inbound
    Route mockedInboundRoute = mock(Route.class);
    Stop mockedPrevStopInbound = mock(Stop.class);
    Stop mockedNextStopInbound = mock(Stop.class);
    Position mockedPrevPositionInbound = mock(Position.class);
    Position mockedNextPositionInbound = mock(Position.class);

    //set up inbound route
    when(mockedInboundRoute.prevStop()).thenReturn(mockedPrevStopInbound);
    when(mockedInboundRoute.getNextStop()).thenReturn(mockedNextStopInbound);
    when(mockedPrevStopInbound.getPosition()).thenReturn(mockedPrevPositionInbound);
    when(mockedNextStopInbound.getPosition()).thenReturn(mockedNextPositionInbound);
    doReturn(-93.235071d).when(mockedPrevPositionInbound).getLongitude();
    doReturn(44.973580d).when(mockedPrevPositionInbound).getLatitude();
    doReturn(-93.243774d).when(mockedNextPositionInbound).getLongitude();
    doReturn(44.972392d).when(mockedNextPositionInbound).getLatitude();

    //mock Line and set outbound and inbound routes
    Line mockedLine = mock(Line.class);
    when(mockedLine.getOutboundRoute()).thenReturn(mockedOutboundRoute);
    when(mockedLine.getInboundRoute()).thenReturn(mockedInboundRoute);

    TestVehicleDataSender testSenderMock = new TestVehicleDataSender();
    Vehicle realVehicle = new SmallBus(1, mockedLine, 50, 5.0);
    Vehicle spyVehicle = spy(realVehicle); //spy on the real vehicle
    spyVehicle.setDataSender(testSenderMock);

    //stub getCurrentCO2Emission() to return 0
    doReturn(0).when(spyVehicle).getCurrentCO2Emission();

    spyVehicle.update();
    spyVehicle.provideInfo();

    //verify captured data
    JsonObject capturedData = testSenderMock.getTestOutput();
    assertNotNull(capturedData);
    assertEquals("observedVehicle", capturedData.get("command").getAsString());

    String expectedText = "1" + System.lineSeparator()
        + "-----------------------------" + System.lineSeparator()
        + "* Type: SMALL_BUS_VEHICLE" + System.lineSeparator()
        + "* Position: (-93.243774,44.972392)" + System.lineSeparator()
        + "* Passengers: 0" + System.lineSeparator()
        + "* CO2: 0" + System.lineSeparator();

    String observedText = capturedData.get("text").getAsString();
    assertEquals(expectedText, observedText);
  }

  /**
   * Tests move and update functions with mock behaviors.
   */
  @Test
  public void testMoveAndUpdateWithMock() {
    Line mockLine = mock(Line.class);
    Route mockRoute = mock(Route.class);
    Stop mockStop = mock(Stop.class);
    when(mockRoute.getNextStop()).thenReturn(mockStop);
    when(mockStop.getName()).thenReturn("Mock Stop");
    when(mockLine.getOutboundRoute()).thenReturn(mockRoute);
    when(mockLine.getInboundRoute()).thenReturn(mockRoute);
    Vehicle mockVehicle = mock(Vehicle.class);
    when(mockVehicle.getLine()).thenReturn(mockLine);
    when(mockVehicle.getNextStop()).thenReturn(mockStop);
    mockVehicle.move();
    assertEquals("Mock Stop", mockVehicle.getNextStop().getName());
  }

  /**
   * Test the provideInfo method for LargeBus when trip is not complete.
   */
  @Test
  public void testProvideInfoLargeBusNotComplete() {
    testSender = new TestVehicleDataSender();
    Route mockRoute = mock(Route.class);
    Stop mockStop = mock(Stop.class);
    when(mockRoute.getNextStop()).thenReturn(mockStop);
    when(mockStop.getName()).thenReturn("Mock Stop");
    when(mockStop.getPosition()).thenReturn(new Position(-93.243774, 44.972392));  // Mock Position
    Line mockLine = mock(Line.class);
    when(mockLine.getOutboundRoute()).thenReturn(mockRoute);
    when(mockLine.getInboundRoute()).thenReturn(mockRoute);
    largeBus = new LargeBus(1, mockLine, 50, 4.0);
    largeBus.setDataSender(testSender);
    when(largeBus.isTripComplete()).thenReturn(false);
    boolean tripCompleted = largeBus.provideInfo();
    JsonObject capturedData = testSender.getTestOutput();
    String text = capturedData.get("text").getAsString();
    assertTrue(text.contains(LargeBus.LARGE_BUS_VEHICLE));
    assertFalse(tripCompleted);
  }

  /**
   * Test the provideInfo method for ElectricTrain trip is complete.
   */
  @Test
  public void testProvideInfoElectricTrainComplete() {
    testSender = new TestVehicleDataSender();
    Route mockRoute = mock(Route.class);
    Stop mockStop = mock(Stop.class);
    when(mockRoute.getNextStop()).thenReturn(mockStop);
    when(mockStop.getName()).thenReturn("Mock Stop");
    when(mockStop.getPosition()).thenReturn(new Position(-93.243774, 44.972392));  // Mock Position
    Line mockLine = mock(Line.class);
    when(mockLine.getOutboundRoute()).thenReturn(mockRoute);
    when(mockLine.getInboundRoute()).thenReturn(mockRoute);
    electricTrain = new ElectricTrain(1, mockLine, 50, 4.0);
    electricTrain.setDataSender(testSender);  // Inject the real sender
    when(electricTrain.isTripComplete()).thenReturn(true);
    boolean tripCompleted = electricTrain.provideInfo();
    JsonObject capturedData = testSender.getTestOutput();
    String text = capturedData.get("text").getAsString();
    assertEquals("", text);
    assertTrue(tripCompleted);
  }

  /**
   * Clean up our variables after each test.
   */
  @AfterEach
  public void cleanUpEach() {
    testVehicle = null;
  }

}
