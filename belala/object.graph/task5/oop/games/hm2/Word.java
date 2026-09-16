package oop.games.hm2;

import java.util.ArrayList;
import java.util.List;

public class Word {
	private int ntries;
	private char[] letters;
	private Word next;

	public Word(Word prev, int ntries, char[] letters) {
	    	this.ntries = ntries;
		this.letters = letters;
		if (prev != null) {
			prev.next = this;
		}
	}

	public Word(Word prev, char[] letters) {
		this.letters = letters;
		if (prev != null) {
			prev.next = this;
		}
		this.ntries = computeTries();// a vérifier

	}

	/*
	 * Invoked on the first word of the list of secret words, it will return the
	 * word that the given index in array of secret that was given initially when
	 * creating the object instance of the class `HangedMan`.
	 */
	public Word wordAt(int n) {
		Word current=this;
		int i=0;
		while(current!=null && i<n) {
			current=current.next; 
			i++;
		}
		return current; 
	}

	/*
	 * method to retrieve the array of letters of the word.
	 */
	public char[] letters() {
		return this.letters;
	}

	public int ntries() {
		return this.ntries;
	}

	private int computeTries() {
		List<Character> lettres = new ArrayList<>();
		for (char c : this.letters) {
			if (!lettres.contains(c)) {
				lettres.add(c);
			}
		}
		return lettres.size() * 2;

	}
}