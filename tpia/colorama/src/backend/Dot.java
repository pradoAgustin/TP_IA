package backend;
/* Dot class keeps 
 * references to the intial-final positions of each color*/
public class Dot {
 private Position start;
 private Position end;
 private int color;
 
 public Dot(Position start , Position end, int color){
		this.start = start;
		this.end = end;
     this.color = color;
	}
 
	public Position getStart(){
        return this.start;
    }

    public Position getEnd(){
        return this.end;
    }

    public int getColor(){
        return this.color;
    }
}
