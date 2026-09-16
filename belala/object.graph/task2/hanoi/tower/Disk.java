package hanoi.tower;

import hanoi.tower.Peg;

public class Disk {
	 private int size;
	 private Peg peg;
	 
	 
	 public Disk(int size) {
		 if (size < 1)
			 throw new IllegalArgumentException("Illegal size: "+size);
		 this.size = size;
		 }
	 
		 
		 
		// notify the receiver that it has been pushed on the given peg.
		 public void pushed(Peg peg) {
			 if (peg==null)
				 throw new IllegalArgumentException();
			 /*if (this.peg!=null)
				 throw new IllegalStateException("hola");*/
			 this.peg = peg;
			 }
		 
		 // notify the receiver that it has been popped from the given peg.
		 public void popped(Peg peg) { 
			 if (peg==null)
				 throw new IllegalArgumentException();
			 if (this.peg==null)
				 throw new IllegalStateException();
			 this.peg=null; 
		 }
		 // returns the peg this disk is currently pushed on, if any.
		public Peg peg() { return this.peg;  }

		public int size() {
			return this.size;
		}
}
