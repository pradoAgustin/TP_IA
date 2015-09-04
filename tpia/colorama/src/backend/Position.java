package backend;

public class Position {
  int row;
  int col;
  

  public Position(int row2, int col2) {
	this.row=row2;
	this.col=col2;
}

public String toString(){
  	return "Position("+row+","+col+");";
  }

  public boolean equals(Object o){
      if(o == null) return false;
      if(!(o instanceof Position)) return false;
      Position other = ((Position)o);
      return this.row == other.row && this.col == other.col;
  }
  
  public Position getPosition(Direction d){
	  return new Position(row+d.row,col+d.col);
  }
}
