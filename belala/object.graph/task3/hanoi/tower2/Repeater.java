package hanoi.tower2;

import java.io.PrintStream;

public class Repeater implements Board.Listener{

	Board slave; 
	PrintStream ps;
	Board board;
	int nmoves; // counts the number of moves to solve the puzzle

	public Repeater(PrintStream ps, Board slave ) {
		this.slave=slave; 
		this.ps = ps;
	}

	/*
	 * Will echo the initial state of the board.
	 */
	@Override
	public void started(Board board) {
		this.board = board;
		this.nmoves = this.board.steps;
		this.slave.steps=this.nmoves;
		this.board.echo(ps);


	}

	/*
	 * Will echo the current state of the board and if the puzzle is completed, it
	 * will also print the total number of moves it took.
	 */
	@Override
	public void moved(int p1, int p2) {
		
		slave.doMove(p1, p2);


	}

	/*
	 * Will print that this listener has been revoked after a certain number of
	 * moves.
	 */
	@Override
	public void revoked() {
		if (slave.l != null)
	        slave.l.revoked();
	}

}
