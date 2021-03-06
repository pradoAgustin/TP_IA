package backend;

public class Board {
	private Cell[][] matrix;
	private int paintedSquares;
	private Dot[] currentDots;
	private int rowSize;
	private int colSize;
	int calls;

	private final static Direction[][] bestDirection = new Direction[][] {
	/* 0 */{ Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT },
	/* 1 */{ Direction.UP, Direction.LEFT, Direction.RIGHT, Direction.DOWN },
	/* 2 */{ Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT },
	/* 3 */{ Direction.LEFT, Direction.UP, Direction.DOWN, Direction.RIGHT },
	/* 4 */{ Direction.RIGHT, Direction.UP, Direction.DOWN, Direction.LEFT },
	/* 5 */{ Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT },
	/* 6 */{ Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP },
	/* 7 */{ Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.LEFT } };

	public Board(Cell[][] board, Dot[] dots) {
		this.matrix = board;
		this.currentDots = dots;
		if (board != null) {
			setRowSize(matrix.length);
			setColSize(matrix[0].length);
			
		}else{
			setColSize(0);
			setRowSize(0);
		}
		calls = 0;
		paintedSquares=dots.length*2;

	}

	public Cell[][] getMatrix() {
		return this.matrix;
	}

	public boolean isOrigin(int row, int col, int color) {
		for (Dot d : currentDots) {
			if (d.getColor() == color) {
				return d.getStart().col == col && d.getStart().row == row;
			}
		}
		return false;
	}

	public boolean isEnd(int row, int col, int color) {
		for (Dot d : currentDots) {
			if (d.getColor() == color) {
				return d.getEnd().col == col && d.getEnd().row == row;
			}
		}
		return false;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	/**
	 * solves the board for the exact solution
	 * */

	public Board solve(Listener listener) {
		Dot initialDot = currentDots[0];
		Board solution = new Board(null, currentDots);
		solve(initialDot.getColor(), null, initialDot.getStart(), 0, solution,
				listener);
		return solution.matrix == null ? null : solution;

	}

	 private boolean solve(int color, Position prevPos, Position currentPos, int index, Board solution,Listener listener){
	        calls++; // TODO recordar sacar calls antes de la entrega final!
	        if(matrix.length <= currentPos.row || currentPos.row < 0
	           || matrix[0].length <= currentPos.col || currentPos.col < 0) return false;

	        int currentPosColor = this.matrix[currentPos.row][currentPos.col].getColor();
	        if(color == currentPosColor){
	            if(!currentPos.equals(currentDots[index].getStart())){
	                if(currentPos.equals(currentDots[index].getEnd())){
	                    if(prevPos.equals(currentDots[index].getStart())) return false;
	                    this.paintedSquares++;
	                    if(currentDots.length == index+1){
	                        saveSolution(this, solution);
	                        if(solution.unpaintedSquares() == 0) return true;
	                    }else{
	                        Dot nextDot = currentDots[index+1];
	                        solve(nextDot.getColor(), null, nextDot.getStart(), index+1, solution,listener);
	                    }
	                    this.paintedSquares--;
	                }
	                return false;
	            }
	            if(prevPos != null){
	               return false;
	            }
	        }else if(currentPosColor!= -1) return false;

	        this.matrix[currentPos.row][currentPos.col].setColor(color);
	        this.paintedSquares++;
	        Position nextPos;

	        /*secci�n para imprimir con intervalos de a 100ms*/
	        if(listener!=null) listener.printToScreen();

	        for(Direction d : this.bestDirection[0]){
	            if( !(nextPos = currentPos.getPosition(d)).equals(prevPos) ){
	                if(solve(color, currentPos, nextPos, index, solution,listener)) return true;
	            }
	        }
	        this.matrix[currentPos.row][currentPos.col].setColor(currentPosColor);
	        this.paintedSquares--;
	        return false;
	    }
	 
	 private int unpaintedSquares() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void saveSolution(Board board, Board solution){
	        if( (solution.matrix==null)
	                || (solution.paintedSquares < board.paintedSquares) ){
	            solution.cloneMatrix(board);
	        }
	    }

	    private void cloneMatrix(Board board){
	        int row, col;
	        Cell c;
	        this.matrix = new Cell[board.matrix.length][board.matrix[0].length];
	        for(row = 0; row < board.matrix.length; row++){
	            for(col = 0; col < board.matrix[0].length; col++){
	                c = board.matrix[row][col];
	                this.matrix[row][col] = new Cell(c.getColor(),c.getNextDir());
	            }
	        }
	        this.paintedSquares = board.paintedSquares;
	    }

}
