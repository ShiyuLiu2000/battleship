package edu.duke.sl846.battleship;

import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class ComputerTextPlayerTest {
  @Test
  public void test_constructor() {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    String inputData = "a0";
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    TextPlayer computerplayer = createComputerPlayer(10, 20, input, bytes);
  }

  private TextPlayer createComputerPlayer(int w, int h, BufferedReader input, OutputStream bytes) {
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new ComputerTextPlayer("A", board, input, output, shipFactory);
  }
}
