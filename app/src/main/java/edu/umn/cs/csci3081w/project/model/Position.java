package edu.umn.cs.csci3081w.project.model;

/**
 * Geographical position defined from longitute and latitude.
 */
public class Position {

  private double longitude;
  private double latitude;

  /**
   * Constructs a Position with the specified longitude and latitude.
   *
   * @param longitude the longitude of position
   * @param latitude the latitude of position
   */
  public Position(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  /**
   * Gets longitude of position.
   *
   * @return the longitude of position
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Gets latitude of position.
   *
   * @return the latitude of position
   */
  public double getLatitude() {
    return latitude;
  }

}
