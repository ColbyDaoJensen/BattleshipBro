// Board Class for Battleship

import static java.lang.System.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Board
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

  public Board()
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

  //Ships are placed horizontally when the location of
  //the first leg is inputted, and knows if input
  //on the board is false so it does not mess up.
  private boolean placeShipHorizontal(int row, int col, int type)
  {
    int size = shipTypeSize(type);
    if(col + size > board[0].length)
      return false;

    int checksum = 0;
    for(int c = col; c < col + size; c++)
      checksum += board[row][c];
    if(checksum != EMPTY)
      return false;
    
    for(int c = col; c < col + size; c++)
      board[row][c] = type;
    return true;
  }

  //Determines if the player hit or missed the ship
  public boolean attack(String loc)
  {
    loc = loc.trim().toUpperCase();
    if(!validLocation(loc))
      return false;
    int row = loc.charAt(0) - 'A';
    int col = Integer.parseInt(loc.substring(1)) - 1;
    if(row < 0 || row > 9 || col < 0 || col > 9)
      return false;
    int val = board[row][col];
    boolean hit = false;

    if(val > 0)
    {
      shipStrengths[val-1]--;
      hit = true;
    }
    if(val != HIT && val != MISS)
      board[row][col] = hit ? HIT : MISS;
    return hit;
  }

  //Prints out the opponent's board to show all
  //hits and misses
  public void printBoardForOpponent()
  {
    out.println("    1 2 3 4 5 6 7 8 9 10");
    for(int r = 0; r < 10; r++)
    {
      out.print((char)(r + 65) + " | ");
      for(int c = 0; c < 10; c++)
      {
        switch(board[r][c])
        {
          case MISS:  out.print("O "); break;
          case HIT:   out.print("X "); break;
          default:    out.print("~ "); break;
        }
      }
      out.println("|");
    }
    out.println();
  }

  //Prints out the board that shows the player
  //all of their hits and misses.
  public void printBoardForOwner()
  {
    out.println("    1 2 3 4 5 6 7 8 9 10");
    for(int r = 0; r < 10; r++)
    {
      out.print((char)(r + 65) + " | ");
      for(int c = 0; c < 10; c++)
      {
        switch(board[r][c])
        {
          case MISS:
          case EMPTY: out.print("~ ");  break;
          case HIT:   out.print("X ");  break;
          default:    out.print("# ");  break;
        }
      }
      out.println("|");
    }
    out.println();
  }

  //Shows the status of the player currently playing.
  public void printStatusForOwner()
  {
    out.println("Here is the status of " + name + "'s fleet...");
    for(int i = 0; i < Game.shipNames.length; i++)
    {
      out.print(Game.shipNames[i] + ":\t\t");
      out.println(shipStrengths[i]);
    }
    out.println();
  }

  //Shows the status of the opponent currently playing.
  public void printStatusForOpponent()
  {
    out.println("Here is the status of " + name + "'s fleet...");
    for(int i = 0; i < Game.shipNames.length; i++)
    {
      out.print(Game.shipNames[i] + ":\t\t");
      out.println(shipStrengths[i] == 0 ? "SUNK" : "in play");
    }
    out.println();
  }

  //Determines if the player won or not.
  public boolean allSunk()
  {
    for(int strength : shipStrengths)
      if(strength > 0)
        return false;
    return true;
  }

  //Uses boards already set
  public void presetBoard(int player) throws IOException
  {
    file = new Scanner(new File("PresetBoards.txt"));
    if(player == 2)
      for(int i = 0; i < 10; i++)
        file.nextLine();
    for(int r = 0; r < board.length; r++)
      for(int c = 0; c < board[0].length; c++)
        board[r][c] = file.nextInt();
    file.close();
  }

  //Checks validity of location
  public static boolean validLocation(String loc)
  {
    return loc.matches("[A-Ja-j]\\d0?");
  }
}