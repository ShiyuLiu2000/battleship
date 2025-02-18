package edu.duke.sl846.battleship;

import java.util.ArrayList;

/**
 * Represents a non-rectangle ship whose shape can be achieved by combining some
 * RectangleShips in our battleship game.
 */
public class CombinedShip<T> extends BasicShip<T> {
  private final String name;

  /**
   * Constructs a CombinedShip with a name, an ordered list of Coordinates, and
   * the view-specific display information of the Ship.
   * 
   * @param name             is the name of the Ship.
   * @param myOrderedPieces  is the ordered list of Coordinates;
   * @param myDisplayInfo    is the view-specific information of self's Ship.
   * @param enemyDisplayInfo is the view-specific information of enemy's Ship.
   */
  public CombinedShip(String name, ArrayList<Coordinate> myOrderedPieces, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemyDisplayInfo) {
    super(myOrderedPieces, myDisplayInfo, enemyDisplayInfo);
    this.name = name;
  }

  /**
   * Constructs a CombinedShip with a name, an ordered list of Coordinates, and
   * the view-specific display information of the Ship.
   * 
   * @param name            is the name of the Ship.
   * @param myOrderedPieces is the ordered list of Coordinates;
   * @param data            is the view-specific information of the Ship when not
   *                        hit.
   * @param onHit           is the view-specific information of the Ship when hit.
   */
  public CombinedShip(String name, ArrayList<Coordinate> myOrderedPieces, T data, T onHit) {
    this(name, myOrderedPieces, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
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
