// Actions Class for Battleship

import static java.lang.System.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Actions
{
  private String name;
  private int[][] board;
  private int[] shipStrengths;
  private Scanner file;
  
  private final int EMPTY = 0;
  private final int MISS = -1;
  private final int HIT = -2;
  public static final int CARRIER = 1;
  public static final int BATTLESHIP = 2;
  public static final int CRUISER = 3;
  public static final int SUBMARINE = 4;
  public static final int DESTROYER = 5;
  public static final boolean VERTICAL = true;
  public static final boolean HORIZONTAL = false;

  public Actions()
  {
    board = new int[10][10];
    shipStrengths = Arrays.copyOf(Game.shipSizes, Game.shipSizes.length);
  }

  //Sets the usernames for the players.
  public void setName(String name)
  {
    this.name = name.toUpperCase();
  }
  //Returns the usernames to use in the game.
  public String getName()
  {
    return name;
  }

  
}