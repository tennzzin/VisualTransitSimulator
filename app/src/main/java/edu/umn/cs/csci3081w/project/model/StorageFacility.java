package edu.umn.cs.csci3081w.project.model;

/**
 * Storage facility that holds information about the number of buses and trains.
 */
public class StorageFacility {
  private int smallBusesNum;
  private int largeBusesNum;
  private int electricTrainsNum;
  private int dieselTrainsNum;

  /**
   * Creates an empty storage facility.
   */
  public StorageFacility() {
    smallBusesNum = 0;
    largeBusesNum = 0;
    electricTrainsNum = 0;
    dieselTrainsNum = 0;
  }

  /**
   * Creates a storage facility with the given numbers.
   * @param smallBusesNum number of small buses
   * @param largeBusesNum number of large buses
   * @param electricTrainsNum number of electric trains
   * @param dieselTrainsNum number of diesel trains
   */
  public StorageFacility(int smallBusesNum, int largeBusesNum,
                         int electricTrainsNum, int dieselTrainsNum) {
    this.smallBusesNum = smallBusesNum;
    this.largeBusesNum = largeBusesNum;
    this.electricTrainsNum = electricTrainsNum;
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * Gets number of small buses currently in storage facility.
   * @return the number of small buses
   */
  public int getSmallBusesNum() {
    return smallBusesNum;
  }

  /**
   * Gets number of electric trains currently in storage facility.
   *
   * @return the number of electric trains
   */
  public int getElectricTrainsNum() {
    return electricTrainsNum;
  }

  /**
   * Sets number of small buses in storage facility.
   *
   * @param smallBusesNum the number of small buses
   */
  public void setSmallBusesNum(int smallBusesNum) {
    this.smallBusesNum = smallBusesNum;
  }

  /**
   * Sets number of electric trains in storage facility.
   *
   * @param electricTrainsNum the number of electric trains
   */
  public void setElectricTrainsNum(int electricTrainsNum) {
    this.electricTrainsNum = electricTrainsNum;
  }

  /**
   * Gets number of large buses currently in storage facility.
   *
   * @return the number of large buses
   */
  public int getLargeBusesNum() {
    return largeBusesNum;
  }

  /**
   * Sets number of large buses in storage facility.
   *
   * @param largeBusesNum the number of large buses
   */
  public void setLargeBusesNum(int largeBusesNum) {
    this.largeBusesNum = largeBusesNum;
  }

  /**
   * Gets number of diesel trains currently in storage facility.
   *
   * @return the number of diesel trains
   */
  public int getDieselTrainsNum() {
    return dieselTrainsNum;
  }

  /**
   * Sets number of diesel trains in storage facility.
   *
   * @param dieselTrainsNum the number of diesel trains
   */
  public void setDieselTrainsNum(int dieselTrainsNum) {
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * Decreases number of small buses in storage facility by 1.
   */
  public void decrementSmallBusesNum() {
    smallBusesNum--;
  }

  /**
   * Decreases number of large buses in storage facility by 1.
   */
  public void decrementLargeBusesNum() {
    largeBusesNum--;
  }

  /**
   * Decreases number of electric trains in storage facility by 1.
   */
  public void decrementElectricTrainsNum() {
    electricTrainsNum--;
  }

  /**
   * Decreases number of diesel trains in storage facility by 1.
   */
  public void decrementDieselTrainsNum() {
    dieselTrainsNum--;
  }

  /**
   * Increases number of small buses in storage facility by 1.
   */
  public void incrementSmallBusesNum() {
    smallBusesNum++;
  }

  /**
   * Increase number of large buses in storage facility by 1.
   */
  public void incrementLargeBusesNum() {
    largeBusesNum++;
  }

  /**
   * Increases number of electric trains in storage facility by 1.
   */
  public void incrementElectricTrainsNum() {
    electricTrainsNum++;
  }

  /**
   * Increases number of diesel trains in storage facility by 1.
   */
  public void incrementDieselTrainsNum() {
    dieselTrainsNum++;
  }
}
