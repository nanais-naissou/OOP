package object.graph.shapes;
import java.lang.Math;


public class Point {
	public float x; 
	public float y; 
	
public 	Point(float x, float y){
		this.x=x; 
		this.y=y; 
	}
	
public 	Point (Point p){
		this.x=p.x; 
		this.y=p.y;
	}

	public boolean equals(Point p) {
		return this.x==p.x && this.y==p.y;
	}


	 public void translate(float dx, float dy) {
		 this.x+=dx; 
		 this.y+=dy; 
	 }
	 public void rotate(Point p, float angle) {
		 float x1= (float) ( (x-p.x)*Math.cos((double)angle)-(y-p.y)*Math.sin((double)angle)+p.x); 
		 
		 this.y = (float) ((x-p.x)*Math.sin((double)angle)+(y-p.y)*Math.cos((double)angle)+p.y); 
		 this.x=x1;
	 }
	
}
