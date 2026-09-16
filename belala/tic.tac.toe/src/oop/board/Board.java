package oop.board;

import java.io.PrintStream;

public class Board {
	public char player1;
	public char player2;
	public char[][] board;
	int nmoves;
	public Board(char c1, char c2, Listener l) {
		this.player1 = c1;
		this.player2 = c2;
		this.board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.board[i][j] = '-';
			}
		}
		this.nmoves=0;
		this.set(l);
	}

	public Board(char c1, char c2) {
		this(c1, c2, null);
	}

	public interface Listener {
		void players(char player1, char player2);

		void played(int row, int column, char player);

		void winner(char player);

		void tie();
	}

	Listener l;

	public void set(Listener l) {
		if (l != null) {
			this.l = l;
		}
		if (this.l != null) {
			this.l.players(player1, player2);
		}
		nmoves++;
	}

	public char get(int row, int column) {
		return this.board[row][column];
	}

	public void set(int row, int column, char player) {
		if (this.board[row][column] == '-') {
			this.board[row][column] = player;
			nmoves++;
			if (this.l != null)

			{
				this.l.played(row, column, player);
			}
		} else {
			throw new IllegalArgumentException("case déja joué!");
		}
	}

	public char winner() {
		// lgines
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				if (this.l != null) {
					this.l.winner(board[i][0]);
				}

			return board[i][0];}
		}
		// colonnes
		for (int i = 0; i < 3; i++) {
			if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				if (this.l != null) {
					this.l.winner(board[0][i]);
				}

			return board[0][i];}
		}
		// diag droite
		if (board[0][0] != '-' && board[0][0] == board[0][1] && board[0][1] == board[0][2]) {
			if (this.l != null) {
				this.l.winner(board[0][0]);
			}

			return board[0][0];
		}
		// diag gauche
		if (board[0][2] != '-' && board[0][2] == board[1][1] && board[0][2] == board[2][0])

		{
			if (this.l != null) {
				this.l.winner(board[0][2]);
			}
			return board[0][2];
		}
		
		if (this.l != null)

		{
			this.l.tie();
		}
		return ' ';
	}
}
