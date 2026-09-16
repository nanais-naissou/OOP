package object.graph.shapes;

public class Polygon {
	private Point[] points; 
	
	public Polygon(Point[] polygon){
        this.points = new Point[polygon.length];

		for (int i=0; i<polygon.length; i++) {

		this.points[i]= polygon[i]; }
	}
	
	public Polygon(Polygon polygon){
        this.points = new Point[polygon.points.length];

		for (int i=0; i<polygon.points.length; i++) {
			this.points[i]= polygon.points[i]; }
	}
		
	public boolean equals(Polygon polygon) {
	    if (this.points.length != polygon.points.length) return false;
	    for (int i = 0; i < points.length; i++) {
	        if (!this.points[i].equals(polygon.points[i])) return false;
	    }
	    return true;
	}

	public boolean same(Polygon polygon) {
	    if (this.points.length != polygon.points.length) return false;
	    for (int i = 0; i < points.length; i++) {
	        if (this.points[i] != polygon.points[i]) return false;
	    }
	    return true;
	}

	
	 public void translate(float dx, float dy) {
		 for (int i=0; i<points.length; i++) {
			 this.points[i].translate(dx, dy); 
	 }
	 }
	
	 public void rotate(Point p, float theta){
			 for (int i=0; i<points.length; i++) {
			 this.points[i].rotate(p, theta); 
		 }
		 }
	
	public int npoints() {return this.points.length; }
	
	public Point pointAt(int index) {
		return this.points[index]; 
	}
	
	
}
