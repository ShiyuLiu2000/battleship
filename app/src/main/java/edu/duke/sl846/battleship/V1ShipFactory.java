package edu.duke.sl846.battleship;

import java.io.IOException;

/**
 * Builds a Ship factory for the battleship game.
 */
public class V1ShipFactory implements AbstractShipFactory<Character> {
  /**
   * Creates a specific type of {@link RectangleShip}.
   * 
   * @param where  is the Placement of the ship.
   * @param width  is the width of the ship.
   * @param height is the height of the ship.
   * @param letter is the char representation of the ship. e.g. 'd' for destroyer.
   * @param name   is the name of the ship, e.g. "Submarine".
   * @return a RectangleShip corresponded.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  protected Ship<Character> createShip(Placement where, int width, int height, char letter, String name) {
    int newWidth = width;
    int newHeight = height;
    char orientation = where.getOrientation();
    where.checkValidOrientation(orientation);
    if (orientation == 'H') {
      newWidth = height;
      newHeight = width;
    }
    return new RectangleShip<Character>(name, where.getWhere(), newWidth, newHeight, letter, '*');
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
    return createShip(where, 1, 2, 's', "Submarine");
  }

  /**
   * Makes a 1x4 battleship.
   * 
   * @param where is the Placement of the ship.
   * @return the battleship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return createShip(where, 1, 4, 'b', "Battleship");
  }

  /**
   * Makes a 1x6 carrier ship.
   * 
   * @param where is the Placement of the ship.
   * @return the carrier ship being created.
   * @throws IllegalArgumentException if Placement orientation is not valid.
   */
  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return createShip(where, 1, 6, 'c', "Carrier");
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
    return createShip(where, 1, 3, 'd', "Destroyer");
  }
}
