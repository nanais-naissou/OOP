package oop.games.hm;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import oop.games.hm.HangedMan;
import oop.games.hm.Keyboard;

import java.io.IOException;

public class Game {
 
    private HangedMan hm; 
    private PrintStream ps;
    private Keyboard kb; 
    private Random rand; 
    
    public Game(String[] words, InputStream in, PrintStream out) {
    	this.hm=new HangedMan(words); 
    	this.kb=new Keyboard(in, out); 
    	this.ps=out; 
    	this.rand= new Random(); 
   
    }

	public void play() throws IOException {
		int index = rand.nextInt(this.hm.secrets.length);
		hm.newGame(index);
		
		
		while (!hm.won() && !hm.lost()) {
		    ps.println("word to guessz : " + hm.guessed());
		    ps.println("ntries : " + hm.essais);
		    
		    String input = kb.read("guess a letter: "); 
		        char c = Character.toLowerCase(input.charAt(0)); 
		        hm.play(c); 
		    
		}

        if (hm.won()) {
            ps.println("winner!"); 
		    ps.println("the word is : " + hm.guessed());

        } else {
            ps.println("loser!"); 

        }
		
	}
}
