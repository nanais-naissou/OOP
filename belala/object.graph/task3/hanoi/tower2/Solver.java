package hanoi.tower2;

import java.io.PrintStream;

import hanoi.tower2.Board;

public class Solver {

  // our three pegs
  private PrintStream ps;

  // our three pegs
  private Board board;

  // keep track of how many times we move a disk
  private int nmoves;

  // remember the number of disks
  private int ndisks;

  /*
   * The constructor is pretty simple, create the three pegs and initialize one as
   * a neat stack of disk of decreasing size.
   */
  public Solver(Board board) {
    this.board = board;
    this.ndisks = board.ndisks();
  }

  /*
   * The game completes when pegA and pegB are empty. This is correct given the
   * invariants we will maintain while we solve the puzzle.
   */
  boolean completed() {
    // check that we are not taking too many moves
    // to complete the puzzle.
    if (nmoves > ((1 << ndisks) - 1))
      throw new IllegalStateException();

    return board.completed();
  }

  public void solve() {
    boolean even = 2 * (ndisks / 2) == ndisks;
   // if (ps!=null) board.echo(ps);
    if (even)
      evenSolution();
    else
      oddSolution();

    if (ps!=null) 
      ps.println("Resolved in " + nmoves + " moves.");
  }

  /*
   * Solution for an even number of disks:
   *
   * - make the legal move between pegs A and B (in either direction) - make the
   * legal move between pegs A and C (in either direction) - make the legal move
   * between pegs B and C (in either direction) - repeat until complete
   */
  private void evenSolution() {
    while (true) {
      // make the legal move between pegs A and B (in either direction)
      if (board.legalMove(Board.PegA,Board.PegB))
        board.doMove(Board.PegA,Board.PegB);
      else {
        if (!board.legalMove(Board.PegB,Board.PegA))
          throw new IllegalStateException();
        board.doMove(Board.PegB,Board.PegA);
      }
      nmoves++;
      //if (ps!=null) board.echo(ps);
      if (completed())
        break;
      // make the legal move between pegs A and C (in either direction)
      if (board.legalMove(Board.PegA,Board.PegC))
        board.doMove(Board.PegA,Board.PegC);
      else {
        if (!board.legalMove(Board.PegC,Board.PegA))
          throw new IllegalStateException();
        board.doMove(Board.PegC,Board.PegA);
      }
      nmoves++;
     // if (ps!=null) board.echo(ps);
      if (completed())
        break;
      
        // make the legal move between pegs B and C (in either direction)
      if (board.legalMove(Board.PegB,Board.PegC))
        board.doMove(Board.PegB,Board.PegC);
      else {
        if (!board.legalMove(Board.PegC,Board.PegB))
          throw new IllegalStateException();
        board.doMove(Board.PegC,Board.PegB);
      }
      nmoves++;
     // if (ps!=null) board.echo(ps);
      if (completed())
        break;
    }
    if (this.board.l!=null) {
  this.board.l.revoked();}
    return;
  }

  /*
   * Solution for an odd number of disks:
   *
   * - make the legal move between pegs A and C (in either direction) - make the
   * legal move between pegs A and B (in either direction) - make the legal move
   * between pegs B and C (in either direction) - repeat until complete
   */
  private void oddSolution() {
    while (true) {
      // make the legal move between pegs A and C (in either direction)
      if (board.legalMove(Board.PegA,Board.PegC))
        board.doMove(Board.PegA,Board.PegC);
      else {
        if (!board.legalMove(Board.PegC,Board.PegA))
          throw new IllegalStateException();
        board.doMove(Board.PegC,Board.PegA);
      }
      nmoves++;
     // if (ps!=null) board.echo(ps);
      if (completed())
        break;

      // make the legal move between pegs A and B (in either direction)
      if (board.legalMove(Board.PegA,Board.PegB))
        board.doMove(Board.PegA,Board.PegB);
      else {
        if (!board.legalMove(Board.PegB,Board.PegA))
          throw new IllegalStateException();
        board.doMove(Board.PegB,Board.PegA);
      }
      nmoves++;
     // if (ps!=null) board.echo(ps);      
      if (completed())
        break;

      // make the legal move between pegs B and C (in either direction)
      if (board.legalMove(Board.PegB,Board.PegC))
        board.doMove(Board.PegB,Board.PegC);
      else {
        if (!board.legalMove(Board.PegC,Board.PegB))
          throw new IllegalStateException();
        board.doMove(Board.PegC,Board.PegB);
      }
      nmoves++;
     // if (ps!=null) board.echo(ps);
      if (completed())
        break;
    }
    if (this.board.l!=null) {
    	  this.board.l.revoked();}

  }

}
