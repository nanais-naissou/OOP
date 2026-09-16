package hanoi.tower2;

import java.io.PrintStream;

public class Printer implements Board.Listener {
	PrintStream ps;
	Board board;
	int nmoves; // counts the number of moves to solve the puzzle

	public Printer(PrintStream ps) {
		this.ps = ps;
	}

	/*
	 * Will echo the initial state of the board.
	 */
	@Override
	public void started(Board board) {
		this.board = board;
		this.nmoves = this.board.steps;
		//this.board.echo(ps);
	}

	/*
	 * Will echo the current state of the board and if the puzzle is completed, it
	 * will also print the total number of moves it took.
	 */
	@Override
	public void moved(int p1, int p2) {
		this.nmoves = this.board.steps;
		if (this.ps != null) {
			this.board.echo(ps);
		}

	}

	/*
	 * Will print that this listener has been revoked after a certain number of
	 * moves.
	 */
	@Override
	public void revoked() {
			if (board.completed())
				ps.print("Resolved");
			else
				ps.println("Revoked");
			ps.println(" after " + nmoves + " moves.");
		
	}

}
