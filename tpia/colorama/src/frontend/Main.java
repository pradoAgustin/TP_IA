package frontend;

import java.awt.Color;

import backend.Board;
import backend.BoardParser;
import backend.Listener;

public class Main {
	public static void main(String[] args) {
		BoardParser parser = new BoardParser();
		Board board = parser.parsingLevel("resources/5x5_4colores.txt");
		ColoramaFrame colorama = new ColoramaFrame(board);
		Board solution = null;
		Listener l;
		l = new PrintListener(colorama);
		solution = board.solve(l);
		System.out.println(board.getMatrix()[1][0].getColor());
	}
}
