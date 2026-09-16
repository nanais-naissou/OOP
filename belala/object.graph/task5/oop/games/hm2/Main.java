package oop.games.hm2;

import java.io.IOException;

import oop.games.hm2.Game;

public class Main {
	public static void main(String[] args) throws IOException {
		Game game;
		game = new Game(args, System.in, System.out);
		game.play();
	}
}