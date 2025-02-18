package edu.duke.sl846.battleship;

import java.util.HashSet;

/**
 * Represents a non-rectangle ship whose shape can be achieved by combining some
 * RectangleShips in our battleship game.
 */
public class CombinedShip<T> extends BasicShip<T> {
  private final String name;
  private HashSet<Ship<T>> shipComposition;

  /**
   * Returns a HashSet of all Coordinates that are occupied by the RectangleShip
   * compositions of this CombinedShip.
   * 
   * @return a HashSet of Coordinates occupied by this whole CombinedShip.
   */
  protected HashSet<Coordinate> getAllShipCoordinates() {
    HashSet<Coordinate> ans = new HashSet<>();
    for (Ship<T> ship : shipComposition) {
      for (Coordinate c : ship.getCoordinates()) {
        ans.add(c);
      }
    }
    return ans;
  }

  /**
   * Constructs an empty CombinedShip with a name and the view-specific display
   * information of the Ship.
   * 
   * @param name             is the name of the Ship.
   * @param myDisplayInfo    is the view-specific information of self's Ship.
   * @param enemyDisplayInfo is the view-specific information of enemy's Ship.
   */
  public CombinedShip(String name, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
    super(new HashSet<>(), myDisplayInfo, enemyDisplayInfo);
    this.name = name;
    this.shipComposition = new HashSet<>();
  }

  /**
   * Conviniently constructs an empty CombinedShip with a name and the
   * view-specific display information of the Ship.
   * 
   * @param name  is the name of the Ship.
   * @param data  is the view-specific information of the Ship when not hit.
   * @param onHit is the view-specific information of the Ship when hit.
   */
  public CombinedShip(String name, T data, T onHit) {
    this(name, new SimpleShipDisplayInfo<T>(data, onHit), new SimpleShipDisplayInfo<T>(null, data));
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
