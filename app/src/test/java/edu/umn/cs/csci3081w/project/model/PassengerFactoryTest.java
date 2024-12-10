package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerFactoryTest {

  /**
   * Setup deterministic operations before each test run.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Tests generate function.
   */
  @Test
  public void testGenerate() {
    assertEquals(3, PassengerFactory.generate(1, 10).getDestination());

  }

  /**
   * Tests generate function.
   */
  @Test
  public void nameGeneration() {
    assertEquals("Goldy", PassengerFactory.nameGeneration());
  }

  /**
   * Tests name generator in deterministic mode.
   */
  @Test
  public void testNameGenerationNonDeterministic() {
    PassengerFactory.DETERMINISTIC = false;

    String name = PassengerFactory.nameGeneration();
    // Check that the name is not null or empty
    assertNotNull(name);
    assertFalse(name.isEmpty());

    // Check that the first character of the name is capitalized
    assertTrue(Character.isUpperCase(name.charAt(0)));
  }

  /**
   * Test generate method for non-deterministic destination generation.
   */
  @Test
  public void testGenerateNonDeterministicDestination() {
    PassengerFactory.DETERMINISTIC = false;
    Passenger passenger = PassengerFactory.generate(2, 7);
    assertNotNull(passenger.getName());
  }
}
