package backend;

public class Cell {

private int color;
private Direction nextDir; 
 
 public Direction getNextDir() {
	return nextDir;
}
 

 public Cell(int color, Direction nextPathDir){
     this.color = color;
     this.nextDir = nextPathDir;
 }

public void setNextDir(Direction nextDir) {
	this.nextDir = nextDir;
}

public Cell(int i) {
		this.setColor(i);
	}

public int getColor() {
	return color;
}

public void setColor(int color) {
	this.color = color;
}
}
