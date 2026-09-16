package oop.games.hm2;

import java.util.ArrayList;
import java.util.List;

public class HangedMan {
	/*
	 * A constructor given secret words and their corresponding maximum number of
	 * tries.
	 */
	
	/*public char[][] secrets;
	private int[] ntries; 
	private char[]secret; 
	int essais; 
	private boolean[] guessed; */
	
	 private Word tete; 
	Guessed secret; 
	int length_words;
	

	public HangedMan(char[][] words, int[] ntries) {
	    int length1 = words.length;
	    this.length_words=length1;
	    this.tete = new Word(null, ntries[0], words[0]);
	    Word current = this.tete;

	    for (int i = 1; i < length1; i++) {
	        current = new Word(current, ntries[i], words[i]);
	    }
	}


	/*
	 * A constructor only the given secret words. The number of maximum tries per
	 * secret word must be computed with the following formula:
	 *
	 * ntries = nchars * 2.0;
	 *
	 * with nchars being the number of different characters in each secret word. For
	 * examples:
	 *
	 * kiwi: 3 different characters -> 6 tries banana: 3 different characters -> 6
	 * tries orange: 6 different characters -> 12 tries
	 */
	public HangedMan(String words[]) {
		int length1=words.length; 
	    this.length_words=length1;

		this.tete=new Word(null, words[0].toCharArray());
		 Word current = this.tete;

		
		for (int i=1; i<length1; i++) {
			current=new Word(current, words[i].toCharArray());
			
		}
	}

	/*
	 * Initializes a new game, using the word indexed by the given number. The given
	 * number is an index in the array of words that was given to the constructor.
	 */
	public void newGame(int n) {
		this.secret=new Guessed(this.tete.wordAt(n));
	
		
	}

	/*
	 * Once a game has been initialized, this method is used to propose a character
	 * for the player.
	 */
	public void play(char c) {
		this.secret.play(c);
			}

	/*
	 * Returns a string that corresponds to the currently guessed characters by the
	 * player. For example: -a-a-a for the secret word "banana" with only the letter
	 * 'a' that has been guessed correctly. At the start of a new game, the string
	 * is composed of only '-', one for each letter of the secret word to guess.
	 */
	public String guessed() {
		return this.secret.guessed();
	}

	/*
	 * Returns true if the player has won, false otherwise.
	 */
	public boolean won() {		
		return this.secret.won();
	}

	/*
	 * Returns true if the player has lost, false otherwise.
	 */
	public boolean lost() {
		return this.secret.lost()	;
		}
}