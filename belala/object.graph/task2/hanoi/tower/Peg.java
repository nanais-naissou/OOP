package hanoi.tower;

import hanoi.tower.Disk;

public class Peg {
	private Disk[] disks; 
	private int ndisks;
	private String name; 
	
	public Peg(String name, int max) {
		 if (max <= 0)
		 throw new IllegalArgumentException();
		 this.disks = new Disk[max];
		 this.ndisks = 0;//current number of disks
		 this.name = name;
		}
	
	public void push(Disk d) {
	  
	    if (this.ndisks == this.disks.length)
	        throw new IllegalStateException("peg already full");
	    
	    if (this.ndisks > 0) { 
	        int size1 = d.size();
	        int size2 = this.disks[ndisks - 1].size();
	        if (size1 > size2)
	            throw new IllegalArgumentException();
	    }
	    if (d.peg() != null)
	        throw new IllegalArgumentException("coucou");

	    this.disks[ndisks] = d; 
	    this.ndisks++; 
	    d.pushed(this);
	
	}


	
	public Disk pop() {
		if (this.ndisks==0)
		 throw new IllegalStateException("peg vide");
		Disk d = this.disks[ndisks-1];
		this.disks[ndisks-1]=null;
		this.ndisks--;
		d.popped(this); 
		return d;
	}

	public int size() {
		return this.ndisks; 
	}
	public Disk[] Disks() {
		return this.disks; 
	}
	public Disk peekAt( int index) {
		if (index<0|| index>this.ndisks-1) {
			 throw new IllegalStateException();
	}
	return this.disks[index]; 
	}
	
	
	
}
