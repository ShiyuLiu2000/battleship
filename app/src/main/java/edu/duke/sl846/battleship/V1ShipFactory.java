package edu.duke.sl846.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {

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
  
  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return createShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    return createShip(where, 1, 4, 'b', "Battleship");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    return createShip(where, 1, 6, 'c', "Carrier");
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return createShip(where, 1, 3, 'd', "Destroyer");
  }
  
}
