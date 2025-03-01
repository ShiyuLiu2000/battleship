package edu.duke.sl846.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a Board in our battleship game.
 */
public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  // we are NOT going to write a getter for myShips, because nothing outside
  // BattleShipBoard should ever know how we keep out ships in an ArrayList, or be
  // able to operate directly an that ArrayList.
  final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  final T missInfo;
  HashMap<Coordinate, T> enemyDiscovery;

  /**
   * Constructs a Board with given width and height.
   * By default, the rule of this board is:
   * - all ships should have coordinates that fit in bounds, and
   * - there should be no collisions among ships.
   * 
   * @param width    is the width of this Board.
   * @param height   is the height of this Board.
   * @param missInfo is the view-specific display info to show on enemy's Board if
   *                 there is a miss.*
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height, T missInfo) {
    this(width, height, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)), missInfo);
  }

  /**
   * Constructs a Board with given width, height, and PlacementRuleChecker.
   * 
   * @param width            is the width of this Board.
   * @param height           is the height of this Board.
   * @param placementChecker is the rule checker for this Board.
   * @param missInfo         is the view-specific display info to show on enemy's
   *                         Board if there is a miss.
   * @throws IllegalArgumentException if width or height is negative or zero.
   */
  public BattleShipBoard(int width, int height, PlacementRuleChecker<T> placementChecker, T missInfo) {
    if (width <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + width);
    }
    if (height <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + height);
    }
    this.width = width;
    this.height = height;
    this.myShips = new ArrayList<>();
    this.placementChecker = placementChecker;
    this.enemyDiscovery = new HashMap<>();
    this.missInfo = missInfo;
  }

  /**
   * Gets the width of the Board.
   * 
   * @return the width of the Board.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the Board.
   * 
   * @return the height of the Board.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Attempts to add a Ship on the Board.
   * 
   * @param toAdd is the Ship to be added.
   * @return null if the addition is successful according to the rule checkers,
   *         a String describing the problem otherwise.
   */
  public String tryAddShip(Ship<T> toAdd) {
    String placementProblem = placementChecker.checkPlacement(toAdd, this);
    if (placementProblem == null) {
      myShips.add(toAdd);
      return null;
    } else {
      return placementProblem;
    }
  }

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as self's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  /**
   * Given a Coordinate, gets the view-specific display info of the Ship (if any)
   * to show as enemy's Board.
   * 
   * @param where is the Coordinate to check.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  /**
   * Given a Coordinate, gets the Ship (if any) which occupies that Coordinate in
   * self Board or in enemy's Board.
   * 
   * @param where  is the Coordinate to check.
   * @param isSelf is a boolean to indicate whether it's self's or enemy's Board
   *               to be checked.
   * @return whatever displayInfo of the Ship at those coordinates if a Ship
   *         exists there, null otherwise.
   */
  protected T whatIsAt(Coordinate where, boolean isSelf) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(where)) {
        if (isSelf == false && enemyDiscovery.containsKey(where) && enemyDiscovery.get(where).equals(missInfo)) {
          return missInfo;
        }
        if (isSelf == false && ship.wasHitAt(where) && !enemyDiscovery.containsKey(where)) {
          return null;
        }
        return ship.getDisplayInfoAt(where, isSelf);
      }
    }
    if (isSelf) {
      return null;
    } else {
      if (enemyDiscovery.containsKey(where)) {
        return enemyDiscovery.get(where);
      } else {
        return null;
      }
    }
  }

  /**
   * Fires at a Coordinate to see what happens.
   * 
   * @param Coordinate is the Coordinate to fire at.
   * @return the injured Ship if c is occupied by that Ship, null otherwise.
   */
  public Ship<T> fireAt(Coordinate c) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(c)) {
        ship.recordHitAt(c);
        ship.wasHitAt(c);
        enemyDiscovery.put(c, ship.getDisplayInfoAt(c, false));
        return ship;
      }
    }
    enemyDiscovery.put(c, missInfo);
    return null;
  }

  /**
   * Gets the sonar scan result as a HashMap, counting how many squares are
   * occupied by each kind of Ship.
   * 
   * @center is the center Coordinate of the sonar scan.
   * @return a HashMap of the sonar scan results.
   */
  public HashMap<String, Integer> sonarScanAt(Coordinate center) {
    HashMap<String, Integer> shipCountMap = new HashMap<>();
    int submarineSquareCount = 0;
    int destroyerSquareCount = 0;
    int battleshipSqaureCount = 0;
    int carrierSqaureCount = 0;
    HashSet<Coordinate> toBeScanned = getCoordinatesForSonarScan(center);
    for (Coordinate c : toBeScanned) {
      for (Ship<T> ship : myShips) {
        if (ship.occupiesCoordinates(c)) {
          String name = ship.getName();
          switch (name) {
            case "Submarine":
              submarineSquareCount++;
              break;
            case "Destroyer":
              destroyerSquareCount++;
              break;
            case "Battleship":
              battleshipSqaureCount++;
              break;
            case "Carrier":
              carrierSqaureCount++;
              break;
          }
        }
      }
    }
    shipCountMap.put("Submarine", submarineSquareCount);
    shipCountMap.put("Destroyer", destroyerSquareCount);
    shipCountMap.put("Battleship", battleshipSqaureCount);
    shipCountMap.put("Carrier", carrierSqaureCount);
    return shipCountMap;
  }

  /**
   * Gets the coordinates to be scanned in a sonar detect.
   * 
   * @param center is the center Coordinate of the sonar scan.
   * @return the HashSet of the Coordinates to be scanned.
   */
  private HashSet<Coordinate> getCoordinatesForSonarScan(Coordinate center) {
    HashSet<Coordinate> ans = new HashSet<>();
    int startRow = center.getRow();
    int startColumn = center.getColumn();
    for (int i = -3; i <= 3; i++) {
      int newRow = startRow + i;
      if (newRow >= 0 && newRow < height) {
        int horizontalOffset = 3 - Math.abs(i);
        for (int j = -horizontalOffset; j <= horizontalOffset; j++) {
          int newColumn = startColumn + j;
          if (newColumn >= 0 && newColumn < width) {
            ans.add(new Coordinate(newRow, newColumn));
          }
        }
      }
    }
    return ans;
  }

  /**
   * Checks if all ships has sunk.
   * 
   * @return true if all ships on this Board are sunk, false otherwise.
   */
  public boolean isAllShipSunk() {
    for (Ship<T> ship : myShips) {
      if (!ship.isSunk()) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the Ship that occupies a given Coordinate.
   * 
   * @param c is the Coordinate to be checked.
   * @return the ship if a ship occupies that Coordinate, null otherwise.
   */
  @Override
  public void removeShip(Ship<T> ship) {
    myShips.remove(ship);
  }

  /**
   * Checks if all ships has sunk.
   * 
   * @return true if all ships on this Board are sunk, false otherwise.
   */
  @Override
  public Ship<T> getShipThatOccupies(Coordinate c) {
    for (Ship<T> ship : myShips) {
      if (ship.occupiesCoordinates(c)) {
        return ship;
      }
    }
    return null;
  }
}
