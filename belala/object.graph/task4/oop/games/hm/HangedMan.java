package oop.games.hm;

import java.util.ArrayList;
import java.util.List;

public class HangedMan {
	/*
	 * A constructor given secret words and their corresponding maximum number of
	 * tries.
	 */
	
	public char[][] secrets;
	private int[] ntries; 
	private char[]secret; 
	int essais; 
	private boolean[] guessed; 
	
	public HangedMan(char[][] words, int[] ntries) {
		this.secrets=words;
		this.ntries=ntries; 
		
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
		char[][] tab=new char[words.length][];
		int[] ntries=new int[words.length]; 
		for (int i=0; i<length1; i++) {
			char[] word=words[i].toCharArray();
			tab[i]=word; 
			ntries[i]=different_caracters(tab[i])*2; 
			
			
		}
		this.secrets = tab;
		this.ntries=ntries; 
		
	}

	public int different_caracters(char[] mot) {
	    List<Character> lettres = new ArrayList<>();
	    for (char c : mot) {
	        if (!lettres.contains(c)) {
	            lettres.add(c);
	        }
	    }
	    return lettres.size();
	}

	/*
	 * Initializes a new game, using the word indexed by the given number. The given
	 * number is an index in the array of words that was given to the constructor.
	 */
	public void newGame(int n) {
		this.secret=this.secrets[n]; 
		this.essais=secret.length; 
		this.guessed=new boolean[secret.length]; 
		
	}

	/*
	 * Once a game has been initialized, this method is used to propose a character
	 * for the player.
	 */
	public void play(char c) {
		boolean found=false; 
		for (int i=0; i<secret.length; i++) {
			if (secret[i]==c) {
				if (!guessed[i]) {
					guessed[i]=true; 
					found=true; 
				}
				
			}
		}
		if (!found) {this.essais--; }
	}

	/*
	 * Returns a string that corresponds to the currently guessed characters by the
	 * player. For example: -a-a-a for the secret word "banana" with only the letter
	 * 'a' that has been guessed correctly. At the start of a new game, the string
	 * is composed of only '-', one for each letter of the secret word to guess.
	 */
	public String guessed() {
		String mot=""; 
		for (int i=0; i<this.secret.length; i++) {
			if(guessed[i]) {
			mot+=secret[i]; }
			else {
				mot+="-"; 
			}
		}
		return mot; 
	}

	/*
	 * Returns true if the player has won, false otherwise.
	 */
	public boolean won() {		
		for (int i=0; i<secret.length; i++) {
			if (!guessed[i])
					return false; 
		}
		return true; 
	}

	/*
	 * Returns true if the player has lost, false otherwise.
	 */
	public boolean lost() {
		return this.essais<=0; 
	}
}