package object.graph.shapes;

public class Line {
	private Point start; 
	private Point end; 
	
	public Line (Point start, Point end){
		this.start=start; 
		this.end=end; 
	}
	
	
	public Line (Line l){
		this.start=l.start; 
		this.end=l.end; 
	}
	
	public boolean equals(Line l) {
		return  this.start.equals(l.start) && this.end.equals(l.end)  ; 
	}
	
	public boolean same(Line l) {
		return this.start==l.start && this.end==l.end;
	}
	
	public void translate(float dx, float dy) {
		this.start.translate(dx, dy);
		this.end.translate(dx, dy);
		
	}
	public void rotate(Point p, float theta) {
		this.start.rotate(p, theta);
		this.end.rotate(p, theta);
	}
	
	public Point start() {
		return this.start; 
	}
	
	public Point end() {
		return this.end; 
	}
	
}
