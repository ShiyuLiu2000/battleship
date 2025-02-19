package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Represents a human {@link TextPlayer} in our battleship game.
 * A human will see prompts and boards explicitly printed for them to read, and
 * can input things to interact with the game.
 */
public class HumanTextPlayer extends TextPlayer {

  /**
   * Constructs a HumanTextPlayer with given Board, input source, output stream,
   * ship
   * factory, and a name.
   * 
   * @param name        is the name given to the TextPlayer.
   * @param theBoard    is the Board used in battleship game.
   * @param inputSource is the input source to read from.
   * @param out         is the output stream to print to.
   * @param factory     is the ship factory to build ships.
   */
  public HumanTextPlayer(String name, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> factory) {
    super(name, theBoard, inputSource, out, factory, true);
  }
}
