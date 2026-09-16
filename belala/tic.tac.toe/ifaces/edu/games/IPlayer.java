package edu.games;

/* 
 * This represents a player for the game Tic-Tac-Toe.
 */

public interface IPlayer {

  /*
   * Provides the name of this player.
   */
  public String name();
  
  /*
   * Called to inform the player the given
   * game started and he will play with the given 
   * character, like an 'X' or 'O' for example..
   */
  public void playing(ITicTacToe t3, char id);

  /*
   * Returns this player's mark, that is,
   * the character used to represent moves
   * by this player on the board game. It is
   * often either 'X' or 'O'.
   */
  public char mark();

  /*
   * Ask this player for his next play, returning 
   * the pair (row,column) in an array: 
   *   array[0] is the chosen row
   *   array[1] is the chosen column
   * Row and column numbers must be valid, within 
   * the range [0,2].
   * 
   * If this method shall return 'null', it means
   * that the player wants to quit the game, declaring 
   * to be forfeit.
   *
   * If the game is over, invoking this method must throw 
   * an illegal-state exception.     
   */
  public int[] play();

  /*
   * Informs this player that he has either 
   * won or lost the current game.
   * If the given argument is 'true', he has won,
   * otherwise he has lost.
   */
  public void won(boolean won);

  /*
   * Informs this player that the game is a tie,
   * the board is full and neither player has 
   * won, failing to align 3 marks. 
   */
  public void tie();

  /*
   * Returns 'true' if this player has won the last game
   * he played, returns false otherwise.
   */
  public boolean won();

}
