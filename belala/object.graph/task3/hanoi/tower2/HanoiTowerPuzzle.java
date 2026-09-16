package hanoi.tower2;

import java.io.IOException;

import hanoi.tower2.Board;
import hanoi.tower2.Solver;

public class HanoiTowerPuzzle {

	public static void main(String args[]) throws IOException {
		int ndisks;
		if (args.length > 0)
			ndisks = Integer.parseInt(args[0]);
		else
			ndisks = 5;
		Board master = new Board(ndisks);
		Board slave = new Board(ndisks);
		slave.set(new Printer(System.out));
		master.set(new Repeater(System.out, slave));
		Solver solver = new Solver(master);
		solver.solve();
	}
}
