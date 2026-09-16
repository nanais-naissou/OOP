package hanoi.tower2;
import java.io.PrintStream;

import hanoi.tower2.Disk;
import hanoi.tower2.Peg;
import hanoi.tower2.Printer;

public class Board {
	
	 public interface Listener {
		 void started(Board board);
		 void moved(int p1, int p2);
		 void revoked();
		 }
	 Listener l;
	public final static int PegA=0;
	 public final static int PegB=1;
	 public final static int PegC=2;
	 
	 private int ndisks;
	 
	 private Peg pegA; 
	 private Peg pegB; 
	 private Peg pegC; 
	 
	 public int steps; 

	 public Board(int ndisks, Listener l) {
		 this.ndisks=ndisks; 
		 this.pegA=new Peg("PegA", ndisks); 
		 this.pegB=new Peg("PegB", ndisks); 
		 this.pegC=new Peg("PegC", ndisks); 
		 this.steps=0; 
		 for (int i = ndisks; i >= 1; i--) {
	            this.pegA.push(new Disk(i));
	        }
		 this.set(l);
		 this.steps=0; 
		
	 }

	 public Board(int ndisks) {
		 this(ndisks, null); 
	 }
	 
	 
	 public void set(Listener l) {
	        if (this.l != null) {
	            this.l.revoked();
	        }
	        this.l = l;

	        if (this.l != null) {
	            this.l.started(this);}
	    }
	 
	 
	 
	 
	 public int ndisks() {
		 return this.ndisks; 
	 }
	 
	 public Peg peg(int no) {
		 switch (no) {
			 case PegA:
				 return pegA;
			 case PegB: 
				 return pegB; 
			 case PegC: 
				 return pegC; 
			default: 
			    throw new IllegalArgumentException("Invalid peg number: " + no);
	 }}
	 
	 public boolean completed() {
		 return (pegA.size()==0 && pegB.size()==0) || (pegA.size()==0 && pegC.size()==0)
			;	
		 }
	 
	 public boolean legalMove(int fromPeg,  int toPeg) {
		 Peg fromPegg = peg(fromPeg); 
		 Peg toPegg = peg(toPeg); 
		 if (fromPegg.size()==0) {return false; }
		 if (toPegg.size()==0) {return true; }

		 Disk fromPeg_disk= fromPegg.Disks()[fromPegg.size()-1]; 
		 Disk toPeg_disk= toPegg.Disks()[toPegg.size()-1]; 
		 
		 
		 return  fromPeg_disk.size()< toPeg_disk.size();
	 }
	 
	 public void doMove(int fromPeg, int toPeg) {
		 Peg fromPegg = peg(fromPeg); 
		 Peg toPegg = peg(toPeg); 
		 Disk disk= fromPegg.Disks()[fromPegg.size()-1]; 

		 fromPegg.pop();
		 toPegg.push(disk); 
		 steps++;
		 if (this.l!=null) {
		 this.l.moved(fromPeg, toPeg);}
		 
	 }
	 public void move(int fromPeg, int toPeg) {
		 Peg fromPegg = peg(fromPeg); 
		 Peg toPegg = peg(toPeg); 
		 Disk disk= fromPegg.Disks()[fromPegg.size()-1]; 

		 fromPegg.pop();
		 toPegg.push(disk); 
		 steps++;
		 if (this.l!=null) {
		 this.l.moved(fromPeg, toPeg);}
		 
	 }
	 
	 public void echo(PrintStream ps) {
		    ps.println("------------------------------------------");
		    ps.println("Step " + this.steps);

		    int maxHeight = Math.max(pegA.size(), Math.max(pegB.size(), pegC.size()));

		    for (int level = maxHeight - 1; level >= 0; level--) {
		        Peg[] pegs = { pegA, pegB, pegC };
		        for (Peg peg : pegs) {
		            if (level < peg.size()) {
		                ps.printf(" [%d]\t", peg.peekAt(level).size());
		            } else {
		                ps.print("   \t");
		            }
		        }
		        ps.println();
		    }

		    ps.println(" A\t B\t C");
		
		 
		 /*if (this.completed()) {
			 ps.printf("Resolved in %d moves.", steps); 
		 }*/
	 }
	
}
 