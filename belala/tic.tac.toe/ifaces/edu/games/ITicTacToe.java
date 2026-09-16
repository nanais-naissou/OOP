package edu.games;

import java.io.PrintStream;

public interface ITicTacToe {

  /*
   * This is a synchronous listener.
   */
  public interface Listener {
    /*
     * This callback tells that a game started with 
     * the two given players.
     */
    void players(IPlayer p1, IPlayer p2);

    /**
     * This callback tells that the given player
     * declared himself forfeit (the method IPlayer.play() 
     * returns null) or it played an illegal move and lost.
     */
    void forfeit(IPlayer p);

    /**
     * This callback tells the latest move played 
     * by the given player.
     */
    void played(IPlayer p, int row, int column);

    /**
     * This callback tells which player won,
     * Incidentally also states that the other player lost
     * and that the game is over.
     */
    void winner(IPlayer p);

    /**
     * This callback indicates a tie, no winner, no loser.
     * Incidentally also states that the game is over.
     */
    void tie();
  }

  /*
   * Invoke this method to setup the two players that will play.
   * The player p1 will be 'X', the player p2 will be 'O'.
   * The play will start the player "X" making the first move.
   */
  public void players(IPlayer p1, IPlayer p2);

  /*
   * Invoke this method to start a new game, with the two players 
   * given earlier, when the method "players(IPlayer,IPlayer)" was 
   * invoked. The play will start with the player "X" making the 
   * first move. The given print stream can be used to print messages 
   * intended for the players. 
   */
  public void play(PrintStream ps);

  /*
   * Invoke this method to add a new listener to the current set 
   * of listeners for this game.
   */
  public void add(Listener l);

  /*
   * Invoke this method to tell this game that the given player
   * wishes to be forfeit (he quits the game and therefore is
   * considered to have lost)..
   */
  public void forfeit(IPlayer p);

  /*
   * This method returns 'true' if the game is over,
   * it returns 'false' otherwise.
   */
  public boolean gameOver();

  /*
   * This method returns the player that won or 
   * null if there is no winner.
   */
  public IPlayer winner();

  /*
   * This method returns the player that forfeited or 
   * null if no one is forfeit.
   */
  public IPlayer forfeit();

  /*
   * This method returns the current player, that is,
   * the player whose next to play a new move. 
   */
  public IPlayer player();

  /*
   * This method returns true if the cell on the game board,
   * at the given row and column, is available, available
   * for the next player to mark it.
   */
  public boolean available(int row, int col);

  /*
   * This method returns the content of a cell 
   * at the given row and column. It is either one
   * of the two players or null.
   */
  public IPlayer grid(int row, int col);

}
