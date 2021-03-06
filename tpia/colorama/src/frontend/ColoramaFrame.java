package frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import backend.Board;
import backend.BoardParser;
import backend.Cell;

public class ColoramaFrame {
	private JFrame frame;
	private JPanel[][] colorCells;
	private Board board;

	public ColoramaFrame(Board board) {

		this.board = board;
		frame = new JFrame();
		frame.setSize(500, 500);
		colorCells = new JPanel[board.getRowSize()][board.getColSize()];
		frame.setLayout(new GridLayout(colorCells.length, colorCells[0].length));

		for (int i = 0; i < colorCells.length; i++) {
			for (int j = 0; j < colorCells[0].length; j++) {
				colorCells[i][j] = new JPanel();
				frame.add(colorCells[i][j]);
			}
		}

		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void changeBoard(Board b) {
		this.board = b;
	}

	public void refresh() {
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	/* this method shows the board */
	void colorSquares() {
		Cell[][] matrix = board.getMatrix();
		Color currentColor;
		for (int i = 0; i < board.getRowSize(); i++) {
			for (int j = 0; j < board.getColSize(); j++) {
				switch (matrix[i][j].getColor()) {

				case -1:
					currentColor = Color.white;
					break;
				case 1:
					currentColor = Color.red;
					break;
				case 2:
					currentColor = Color.MAGENTA;
					break;
				case 3:
					currentColor = Color.blue;
					break;
				case 4:
					currentColor = Color.yellow;
					break;
				case 5:
					currentColor = Color.black;
					break;
				case 6:
					currentColor = Color.green;
					break;
				case 7:
					currentColor = Color.pink;
					break;
				case 8:
					currentColor = Color.GRAY;
					break;
				case 9:
					currentColor = Color.ORANGE;
					break;
				default:
					currentColor = Color.white;
				}
				colorCells[i][j].setBackground(currentColor);

				/*
				 * checks if current cell is a Dot and if it is origin or
				 * destiny
				 */
				int current = matrix[i][j].getColor();
				if (current != -1) {
					JLabel image;
					if (board.isOrigin(i, j, current)) {
						image = new JLabel(new ImageIcon("img/start.png"));
					} else if (board.isEnd(i, j, current)){
						image = new JLabel(new ImageIcon("img/end.png"));
					}
					else {
						image = new JLabel(new ImageIcon("img/target.png"));
					}

					colorCells[i][j].removeAll();
					colorCells[i][j].add(image);
					colorCells[i][j].validate();
				}
			}
		}
		
		frame.validate();
		frame.repaint();

	}

	public static void main(String[] args) {
		/*
		 * BoardParser parser=new BoardParser(); Board
		 * b=parser.parsingLevel("resources/3x3_1color.txt"); ColoramaFrame
		 * cFrame=new ColoramaFrame(b); cFrame.colorSquares();
		 */
	}

}
