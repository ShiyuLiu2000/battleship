package edu.duke.sl846.battleship;

import java.util.ArrayList;

/**
 * Builds a CombinedShip factory for the battleship game.
 */
public class V2ShipFactory implements AbstractShipFactory<Character> {

  /**
   * Makes a battleship composed of 2 small rectangle battleships.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return new CombinedShip<Character>("Battleship", makeBattleshipCoordinates(where), 'b', '*');
  }

  /**
   * Makes a carrier ship composed of 2 small rectangle carrier ships.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return new CombinedShip<Character>("Carrier", makeCarrierCoordinates(where), 'c', '*');
  }

  /**
   * Makes a 1x3 destroyer ship.
   * 
   * @param where is the Placement of the ship.
   * @return the destroyer ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return new CombinedShip<Character>("Destroyer", makeDestroyerCoordinates(where), 'd', '*');
  }

  /**
   * Makes a 1x2 submarine ship.
   * 
   * @param where is the Placement of the ship.
   * @return the submarine ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return new CombinedShip<Character>("Submarine", makeSubmarineCoordinates(where), 's', '*');
  }

  /**
   * Makes an ordered ArrayList of the pieces of new battleship.
   * 
   * @param where is the Placement of the ship.
   * @return the ordered pieces of the Ship.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  public ArrayList<Coordinate> makeBattleshipCoordinates(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    ArrayList<Coordinate> ans = new ArrayList<>();
    switch (orientation) {
      case 'U':
        ans.add(new Coordinate(row, column + 1));
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row + 1, column + 2));
        break;
      case 'R':
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 2, column));
        break;
      case 'D':
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row, column + 2));
        ans.add(new Coordinate(row, column + 1));
        ans.add(new Coordinate(row, column));
        break;
      case 'L':
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 2, column + 1));
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row, column + 1));
        break;
      default:
        throw new IllegalArgumentException(
            "Battleship only accepts Up(u/U), Right(r/R), Down(d/D), Left(l/L) as orientation, but is " + orientation);
    }
    return ans;

  }

  /**
   * Makes an ordered ArrayList of the pieces of new carrier.
   * 
   * @param where is the Placement of the ship.
   * @return the ordered pieces of the Ship.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  public ArrayList<Coordinate> makeCarrierCoordinates(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    ArrayList<Coordinate> ans = new ArrayList<>();
    switch (orientation) {
      case 'U':
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 2, column));
        ans.add(new Coordinate(row + 3, column));
        ans.add(new Coordinate(row + 2, column + 1));
        ans.add(new Coordinate(row + 3, column + 1));
        ans.add(new Coordinate(row + 4, column + 1));
        break;
      case 'R':
        ans.add(new Coordinate(row, column + 4));
        ans.add(new Coordinate(row, column + 3));
        ans.add(new Coordinate(row, column + 2));
        ans.add(new Coordinate(row, column + 1));
        ans.add(new Coordinate(row + 1, column + 2));
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row + 1, column));
        break;
      case 'D':
        ans.add(new Coordinate(row + 4, column + 1));
        ans.add(new Coordinate(row + 3, column + 1));
        ans.add(new Coordinate(row + 2, column + 1));
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row + 2, column));
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row, column));
        break;
      case 'L':
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 1, column + 1));
        ans.add(new Coordinate(row + 1, column + 2));
        ans.add(new Coordinate(row + 1, column + 3));
        ans.add(new Coordinate(row, column + 2));
        ans.add(new Coordinate(row, column + 3));
        ans.add(new Coordinate(row, column + 4));
        break;
      default:
        throw new IllegalArgumentException(
            "Carrier only accepts up (u/U), right (r/R), down (d/D), left (l/L) as orientation, but is " + orientation);
    }
    return ans;
  }

  /**
   * Makes an ordered ArrayList of the pieces of new submarine.
   * 
   * @param where is the Placement of the ship.
   * @return the ordered pieces of the Ship.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  public ArrayList<Coordinate> makeSubmarineCoordinates(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    ArrayList<Coordinate> ans = new ArrayList<>();
    switch (orientation) {
      case 'H':
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row, column + 1));
        break;
      case 'V':
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row + 1, column));
        break;
      default:
        throw new IllegalArgumentException(
            "Submarine only accepts horizontal (h/H) and vertical (v/V) as orientation, but is " + orientation);
    }
    return ans;
  }

  /**
   * Makes an ordered ArrayList of the pieces of new destroyer.
   * 
   * @param where is the Placement of the ship.
   * @return the ordered pieces of the Ship.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  public ArrayList<Coordinate> makeDestroyerCoordinates(Placement where) {
    char orientation = where.getOrientation();
    Coordinate upperLeft = where.getWhere();
    int row = upperLeft.getRow();
    int column = upperLeft.getColumn();
    ArrayList<Coordinate> ans = new ArrayList<>();
    switch (orientation) {
      case 'H':
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row, column + 1));
        ans.add(new Coordinate(row, column + 2));
        break;
      case 'V':
        ans.add(new Coordinate(row, column));
        ans.add(new Coordinate(row + 1, column));
        ans.add(new Coordinate(row + 2, column));
        break;
      default:
        throw new IllegalArgumentException(
            "Destroyer only accepts horizontal (h/H) and vertical (v/V) as orientation, but is " + orientation);
    }
    return ans;
  }
}
