package edu.duke.sl846.battleship;

import java.util.HashSet;

/**
 * Represents a non-rectangle ship whose shape can be achieved by combining some
 * RectangleShips in our battleship game.
 */
public class CombinedShip<T> extends BasicShip<T> {
  private final String name;
  private final HashSet<Ship<T>> myShips;

    /**
   * Constructs a RectangleShip with a Coordinate, width, height, and the
   * view-specific display information of the Ship.
   * 
   * @param upperLeft        is the upper left Coordinate of the RectangleShip.
   * @param width            is the width of the RectangleShip.
   * @param height           is the height of the RectangleShip.
   * @param myDisplayInfo    is the view-specific information of self's Ship.
   * @param enemyDisplayInfo is the view-specific information of enemy's Ship.
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoordinates(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  /**
   * Conviniently constructs a RectangleShip with a Coordinate, width, height, and
   * the view-specific display information of the Ship.
   * 
   * @param upperLeft is the upper left Coordinate of the RectangleShip.
   * @param width     is the width of the RectangleShip.
   * @param height    is the height of the RectangleShip.
   * @param data      is the view-specific information of the Ship when not hit.
   * @param onHit     is the view-specific information of the Ship when hit.
   */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  
  /**
   * Gets the name of the CombinedShip.
   * 
   * @return the name of the CombinedShip.
   */
  @Override
  public String getName() {
    return name;
  }

}
