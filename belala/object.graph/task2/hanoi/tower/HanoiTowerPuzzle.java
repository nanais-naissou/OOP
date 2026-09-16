package hanoi.tower;

import java.io.IOException;

import hanoi.tower.Board;
import hanoi.tower.Solver;

public class HanoiTowerPuzzle {

  public static void main(String args[]) throws IOException {
    int ndisks;
    if (args.length > 0)
      ndisks = Integer.parseInt(args[0]);
    else
      ndisks = 3;
    Board board = new Board(ndisks);
    Solver solver = new Solver(board, System.out);
    solver.solve();
  }
}
