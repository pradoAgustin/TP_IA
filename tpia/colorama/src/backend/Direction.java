package backend;

/* enum for the posible directions */
 enum Direction {
	    UP(-1,0), DOWN(1,0), LEFT(0,-1), RIGHT(0,1),
	    UPPERRIGHT(-1,1), UPPERLEFT(-1,-1), LOWERRIGHT(1,1), LOWERLEFT(1,-1);

	    int row, col;

	    Direction(int row, int col){
	        this.row = row;
	        this.col = col;
	    }
	}

