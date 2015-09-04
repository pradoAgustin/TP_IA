package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class BoardParser {

	public static final int TOTAL_COLORS = 10;
	
	public Board parsingLevel(String filePath) {
		int rowSize;
		int colSize;
		String currentLine;
		String[] boardSize;
		BufferedReader br;
		ArrayList<Position> dotList = new ArrayList<Position>();
		try {
			br = new BufferedReader(new FileReader(filePath));
			currentLine = br.readLine();
			currentLine = currentLine.trim();
			boardSize = currentLine.split(",");
			/* getting the size of the board */
			rowSize = Integer.valueOf(boardSize[0]);
			colSize = Integer.valueOf(boardSize[1]);

			Cell[][] board = new Cell[rowSize][colSize];
			int row;

			for (row = 0; (currentLine = (br.readLine())) != null; row++) {

				for (int col = 0; col < currentLine.length(); col++) {
					char currentChar = currentLine.charAt(col);
					if (currentChar != ' ') {
						if (currentChar >= '0' && currentChar <= '9') {
							dotList.add(new Position(row, col));
							board[row][col] = new Cell(currentChar-'0');
						} 
					}
					else {
						board[row][col] = new Cell(-1);
					}
				}
				
			}
		
			return new Board(board,convertToDotArray(dotList,board));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
			return null;
		}

		

	}

	private Dot[] convertToDotArray(ArrayList<Position> dotList,Cell[][] board) {
		  ArrayList<Dot> ret = new ArrayList<Dot>();
	        int i, j;
	        for(i = 0; i <dotList.size(); i++){
	            for(j = i+1; j < dotList.size(); j++){
	                Position pos1 =dotList.get(i);
	                Position pos2 = dotList.get(j);
	                int color;
	                if( (color = board[pos1.row][pos1.col].getColor()) == board[pos2.row][pos2.col].getColor() ){
	                    ret.add(new Dot(pos1, pos2, color));
	                    continue;
	                }
	            }
	        }
	       
	        return ret.toArray(new Dot[ret.size()]);
	}
	
	
}
