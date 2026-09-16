package oop.games.hm2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

class Keyboard {
	private InputStreamReader in;  
	public PrintStream ps; 
	private BufferedReader br; 
	/*
	 * The constructor is given a print stream and an input stream. The print stream
	 * will be used to print the invite messages and an input stream of characters
	 * to read the one-line answers.
	 */
	Keyboard(InputStream in, PrintStream out) {
		 this.ps = out;
		 this.in = new InputStreamReader(in);
		 this.br = new BufferedReader(this.in);
		}
	/*
	 * If given a message, this method prints the message and then reads one line of
	 * characters as an answer to the printed invite.
	 */
	String read(String msg) throws IOException {
		if (msg!=null){
		ps.println(msg); }
		
		
		String mot= this.br.readLine(); 
       
		return mot; 
	}
}