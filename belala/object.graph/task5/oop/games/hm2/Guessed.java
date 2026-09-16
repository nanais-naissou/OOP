package oop.games.hm2;

public class Guessed {

	public Word word; // selected secret word to be guessed
	public char letters[]; // guessed letters and '-' for the others
	int ntries; // the remaining number of tries
	private int nguessed; // number of guessed letters so far.
	/*
	 * Construct this object from the given word which is the selected secret word
	 * to be guessed by the player.
	 */

	public Guessed(Word w) {
		this.word = w;
		this.letters = new char[this.word.letters().length];

		for (int i = 0; i < this.word.letters().length; i++) {
			this.letters[i] = '-';
		}
		this.ntries = this.word.ntries();
		this.nguessed = 0;
	}

	/*
	 * Returns the guessed letter at the given index.
	 */
	public char guessAt(int n) {
		return this.letters[n];
	}

	/*
	 * Set the guessed letter at the given index with the given character.
	 * Increments the number of guessed letters only if the letter at the given was
	 * not already guessed.
	 */
	public void guessAt(int n, char c) {
		if (letters[n] == '-') {
			letters[n] = c;
			nguessed++;
		}
	}

	/*
	 * makes a proposal for a guessed letter - it may be a letter that is not in the
	 * word - it may be a letter that was already guessed - it may be a valid guess
	 * whatever the case, the number of remaining tries is decremented.
	 */
	public void play(char c) {

		for (int i = 0; i < word.letters().length; i++) {
			if (word.letters()[i] == c) {
				guessAt(i, c);

			}
		}
		this.ntries--;
	}

	/*
	 * Returns a string that corresponds to the currently guessed characters by the
	 * player. For example: -a-a-a for the secret word "banana" with only the letter
	 * 'a' that has been guessed correctly.
	 */

	public String guessed() {
		String mot = "";
		for (int i = 0; i < this.word.letters().length; i++) {
			mot += this.letters[i];
		}

		return mot;
	}

	/*
	 * Returns true if the player has won. false otherwise.
	 */
	public boolean won() {
		for (int i = 0; i < this.word.letters().length; i++) {
			if (word.letters()[i] != this.letters[i]) {
				return false;
			}
		}
		return true;

	}

	/*
	 * Returns true if the player has lost. false otherwise.
	 */
	public boolean lost() {
		return this.ntries <= 0;
	}
}
