package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a computer {@link TextPlayer} in our battleship game.
 * A computer player will not have any detailed prompts or boards printed out.
 * It will do moves automatically at random coordinates.
 */
public class ComputerTextPlayer extends TextPlayer {
  private final Random random;

  /**
   * Constructs a ComputerTextPlayer with given Board, input source, output
   * stream, ship
   * factory, and a name.
   * 
   * @param name        is the name given to the TextPlayer.
   * @param theBoard    is the Board used in battleship game.
   * @param inputSource is the input source to read from.
   * @param out         is the output stream to print to.
   * @param factory     is the ship factory to build ships.
   */
  public ComputerTextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> factory) {
    super(name, theBoard, inputSource, out, factory, false);
    this.random = new Random();
  }

  /**
   * Generates a random int within range (exclusive).
   * 
   * @param max is the maximum boundary.
   * @return the generated random number from 0 to max.
   */
  private int getRandomNumberWithin(int max) {
    return random.nextInt(max);
  }

  /**
   * Generates a random Coordinate.
   * 
   * @param prompt is the String to inform user on what to input.
   * @return the randomly generated Coordinate within bounds.
   * @throws IOException if the user input cannot build a valid Coordinate.
   */
  @Override
  public Coordinate readCoordinate(String prompt) throws IOException {
    int width = theBoard.getWidth();
    int height = theBoard.getHeight();
    int randomRow = getRandomNumberWithin(height);
    int randomColumn = getRandomNumberWithin(width);
    return new Coordinate(randomRow, randomColumn);
  }

  /**
   * Generates a random Placement.
   * 
   * @param prompt is the String to inform user on what to input.
   * @return the randomly generated Placement.
   * @throws IOException if the user input cannot build a valid Placement.
   */
  @Override
  public Placement readPlacement(String prompt) throws IOException {
    ArrayList<Character> possibleOrientations = new ArrayList<>();
    possibleOrientations.add('H');
    possibleOrientations.add('V');
    possibleOrientations.add('U');
    possibleOrientations.add('D');
    possibleOrientations.add('R');
    possibleOrientations.add('L');
    int randomIndex = getRandomNumberWithin(6);
    Coordinate randomCoordinate = readCoordinate(" ");
    return new Placement(randomCoordinate, possibleOrientations.get(randomIndex));
  }

  /**
   * Reads user input for action types.
   * 
   * @param prompt is the prompt as instruction.
   * @return the uppercased action type character.
   * @throws IOException if the user input is not a valid action type.
   */
  @Override
  public char readActionType(String prompt) throws IOException {
    return 'F';
  }

  /**
   * Does 'Fire At' action during the game.
   * 
   * @param enemy is the enemy TextPlayer.
   * @throws IOException if the user input cannot build a valid Placement.
   */
  @Override
  public void doFireAt(TextPlayer enemy) throws IOException {
    Coordinate coordinate = readCoordinate(" ");
    Ship<Character> injuredShip = enemy.theBoard.fireAt(coordinate);
    if (injuredShip == null) {
      String missInfo = "--------------------------------------------------------------------------------\n"
          + "Player " + name + " missed!\n"
          + "--------------------------------------------------------------------------------\n";
      out.print(missInfo);
    } else {
      String hitInfo = "--------------------------------------------------------------------------------\n"
          + "Player " + name + " hit your " + injuredShip.getName() + "!\n"
          + "--------------------------------------------------------------------------------\n";
      out.print(hitInfo);
    }
  }
}
