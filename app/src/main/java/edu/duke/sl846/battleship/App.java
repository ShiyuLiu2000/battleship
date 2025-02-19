/**
 * This source file was generated by the Gradle 'init' task
 */
package edu.duke.sl846.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Plays the Battleship game.
 */
public class App {
  TextPlayer player1;
  TextPlayer player2;

  /**
   * Contructs the App with two TextPlayers.
   * 
   * @param player1 is the first player of the game.
   * @param player2 is the second player of the game.
   */
  public App(TextPlayer player1, TextPlayer player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  /**
   * Runs the placement phase of the battleship game.
   */
  public void doPlacementPhase() throws IOException {
    player1.doPlacementPhase();
    player2.doPlacementPhase();
  }

  /**
   * Runs the attacking phase of the battleship game.
   */
  public void doAttackingPhase() throws IOException {
    boolean isGameEnd = false;
    while (!isGameEnd) {
      isGameEnd = player1.playOneTurnAgainst(player2) || player2.playOneTurnAgainst(player1);
    }
  }

  /**
   * Prompts user to choose from human TextPlayer or computer TextPlayer, and
   * generate the corresponding type of player for the game.
   * 
   * @param name        is the name given to the TextPlayer.
   * @param theBoard    is the Board used in battleship game.
   * @param inputReader is the input source to read from.
   * @param out         is the output stream to print to.
   * @return the TextPlayer of corresponding type.
   * @throws IOException if the user input is null;
   */
  static TextPlayer readTextPlayer(String name, Board<Character> theBoard, BufferedReader inputReader, PrintStream out)
      throws IOException {
    TextPlayer ans = null;
    V2ShipFactory factory = new V2ShipFactory();
    while (ans == null) {
      out.print("Please choose the type (H for human, C for computer) for Player " + name + ": ");
      String s = inputReader.readLine();
      if (s == null) {
        throw new EOFException("The input for player type is empty.");
      }
      s = s.toUpperCase();
      if (s.length() != 1) {
        out.print("Expect the input for player type to be length 1, but is " + s.length() + ".\n");
        continue;
      }
      char playerType = s.charAt(0);
      if (playerType != 'H' && playerType != 'C') {
        out.print("Player type can only be human (h/H) or computer (c/C), but is " + playerType + ".\n");
        continue;
      }
      switch (playerType) {
        case 'H':
          ans = new HumanTextPlayer(name, theBoard, inputReader, out, factory);
          break;
        case 'C':
          ans = new ComputerTextPlayer(name, theBoard, inputReader, out, factory);
          break;
      }
    }
    return ans;
  }

  /**
   * Plays the battleship game.
   */
  public static void main(String[] args) throws IOException {
    Board<Character> board1 = new BattleShipBoard<>(10, 20, 'X');
    Board<Character> board2 = new BattleShipBoard<>(10, 20, 'X');
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    TextPlayer player1 = App.readTextPlayer("A", board1, input, System.out);
    TextPlayer player2 = App.readTextPlayer("B", board2, input, System.out);
    App app = new App(player1, player2);

    app.doPlacementPhase();
    app.doAttackingPhase();
  }
}
