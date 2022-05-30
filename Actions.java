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

  //Places the ship in the location inputted
  public boolean placeShip(String loc, boolean vert, int type)
  {
    loc = loc.trim().toUpperCase();
    if(!validLocation(loc))
      return false;
    int row = loc.charAt(0) - 'A';
    int col = Integer.parseInt(loc.substring(1)) - 1;
    if(vert)
      return placeShipVertical(row, col, type);
    else
      return placeShipHorizontal(row, col, type);
  }

  //Creates the types of ship sizes used for the players.
  private int shipTypeSize(int type)
  {
    switch(type)
    {
      case CARRIER:     return 5;
      case BATTLESHIP:  return 4;
      case CRUISER:     return 3;
      case SUBMARINE:   return 3;
      case DESTROYER:   return 2;
      default:          return 0;
    }
  }

  //Ships are placed vertically when the location of
  //the first leg is inputted, and knows if input
  //on the board is false so it does not mess up.
  private boolean placeShipVertical(int row, int col, int type)
  {
    int size = shipTypeSize(type);
    if(row + size > board.length)
      return false;
    
    int checksum = 0;
    for(int r = row; r < row + size; r++)
      checksum += board[r][col];
    if(checksum != EMPTY)
      return false;
    
    for(int r = row; r < row + size; r++)
      board[r][col] = type;
    return true;
  }

  
}