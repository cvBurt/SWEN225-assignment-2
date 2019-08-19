import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Board {
	private Cell board[][];
	public Map<String, Cell[]> roomDoors;
	public Map<String, Cell[]> roomStore;

	public Board () {
		roomDoors = new HashMap<String, Cell[]>();
		roomStore = new HashMap<String, Cell[]>();
		constructBoard();
		//		for(int row=0; row<board.length;row++) {
		//			for(int col=0; col<board[row].length;col++) {
		//				Cell update = board[row][col];
		//				update.setPos(row,col);
		//			}
		//		}
		//		setDoors();
		//		setStoreLocations();
	}

	/**
	 * The method which draws out the board. At the top, the
	 * different types of char arrays used to determine what is
	 * drawn on the board are defined, and then creates Cells, which
	 * the Board holds in a 2D Array of 24 x 25.
	 * @param board contains the cells and when the
	 * draw method is called on it, draws the cells.
	 */
	public void constructBoard() {
		board = new Cell[24][25];
		char[] hallway = {'a', '⌈','⌉','⌊','⌋'};
		char[] blank = {'a',' ',' ',' ',' '};
		char[] topLeftCorner = {'b','|', '¯', '|', ' '};
		char[] topRightCorner = {'c','¯','|', ' ','|'};
		char[] bottomLeftCorner = {'d','|', ' ', '¯', '¯'};
		char[] bottomRightCorner = {'e',' ', '|', '¯', '¯'};
		char[] leftEdge = {'m','|', ' ', '|', ' '};
		char[] rightEdge = {'l',' ', '|', ' ', '|'};
		char[] verticalDoor = {'g', '/', ' ', '\\', ' '};
		char[] topEdge = {'n','¯', '¯', ' ', ' '};
		char[] bottomEdge = {'o',' ', ' ', '¯', '¯'};
		char[] horizontalDoor = {'f', ' ', ' ', '\\','/'};
		char[] conservatoryDoor = {'f', '|', ' ', '|','/'};
		char[] libraryTopDoor = {'f', '\\', '/', ' ', ' '};
		char[] hallDoorLeft = {'g', '\\', ' ', ' ',' '};
		char[] hallDoorRight = {'g', ' ', '/', ' ',' '};
		char[] loungeDoor = {'f', '/', '|', ' ','|'};
		char[] studyDoor = {'f', '|', '\\', '|',' '};
		char[] bottomStart = {'h','⌈','⌉','⌊','⌋'};
		char[] leftStart = {'i', '⌈','⌉','⌊','⌋'};
		char[] topStart = {'j', '⌈','⌉','⌊','⌋'};
		char[] rightStart = {'k', '⌈','⌉','⌊','⌋'};
		board[0][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[1][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[8][0] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[9][0] = new Cell("Hallway", topStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][0] = new Cell("Ball Room", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[11][0] = new Cell("Ball Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[12][0] = new Cell("Ball Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[13][0] = new Cell("Ball Room", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[14][0] = new Cell("Hallway", topStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[15][0] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[16][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[17][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[18][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][0] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][1] = new Cell("Kitchen", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[1][1] = new Cell("Kitchen", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[2][1] = new Cell("Kitchen", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[3][1] = new Cell("Kitchen", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[4][1] = new Cell("Kitchen", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[5][1] = new Cell("Kitchen", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[6][1] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][1] = new Cell("Ball Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][1] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][1] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][1] = new Cell("Ball Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[14][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[15][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][1] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][1] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[18][1] = new Cell("Conservatory", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[19][1] = new Cell("Conservatory", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[20][1] = new Cell("Conservatory", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[21][1] = new Cell("Conservatory", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[22][1] = new Cell("Conservatory", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[23][1] = new Cell("Conservatory", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[0][2] = new Cell("Kitchen", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][2] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][2] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][2] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][2] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][2] = new Cell("Kitchen", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[6][2] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][2] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][2] = new Cell("Ball Room", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[9][2] = new Cell("Ball Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[10][2] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][2] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][2] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][2] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][2] = new Cell("Ball Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[15][2] = new Cell("Ball Room", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[16][2] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][2] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][2] = new Cell("Conservatory", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[19][2] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("A.png")));
		board[20][2] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("B.png")));
		board[21][2] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][2] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][2] = new Cell("Conservatory", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][3] = new Cell("Kitchen", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][3] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][3] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][3] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][3] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][3] = new Cell("Kitchen", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[6][3] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][3] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][3] = new Cell("Ball Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[9][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[10][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][3] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[15][3] = new Cell("Ball Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[16][3] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][3] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][3] = new Cell("Conservatory", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[19][3] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][3] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][3] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][3] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][3] = new Cell("Conservatory", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][4] = new Cell("Kitchen", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][4] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][4] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][4] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][4] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][4] = new Cell("Kitchen", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[6][4] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][4] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][4] = new Cell("Ball Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[9][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[10][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][4] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[15][4] = new Cell("Ball Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[16][4] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][4] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][4] = new Cell("Conservatory", conservatoryDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[19][4] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][4] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][4] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][4] = new Cell("Conservatory", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][4] = new Cell("Conservatory", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[0][5] = new Cell("Kitchen", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[1][5] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][5] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][5] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][5] = new Cell("Kitchen", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][5] = new Cell("Kitchen", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[6][5] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][5] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][5] = new Cell("Ball Room", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[9][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[10][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][5] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[15][5] = new Cell("Ball Room", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[16][5] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][5] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][5] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][5] = new Cell("Conservatory", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[20][5] = new Cell("Conservatory", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[21][5] = new Cell("Conservatory", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[22][5] = new Cell("Conservatory", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[23][5] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][6] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[1][6] = new Cell("Kitchen", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[2][6] = new Cell("Kitchen", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[3][6] = new Cell("Kitchen", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[4][6] = new Cell("Kitchen", horizontalDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[5][6] = new Cell("Kitchen", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[6][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][6] = new Cell("Ball Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[9][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[10][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][6] = new Cell("Ball Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[15][6] = new Cell("Ball Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[16][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[20][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[21][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[22][6] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[23][6] = new Cell("Hallway", rightStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[0][7] = new Cell("Hallway", bottomEdge, new ImageIcon(getClass().getResource("Hallway.png")));
		board[1][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[2][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[3][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[4][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[5][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][7] = new Cell("Ball Room", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[9][7] = new Cell("Ball Room", horizontalDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[10][7] = new Cell("Ball Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[11][7] = new Cell("Ball Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[12][7] = new Cell("Ball Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[13][7] = new Cell("Ball Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[14][7] = new Cell("Ball Room", horizontalDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[15][7] = new Cell("Ball Room", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[16][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[20][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[21][7] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[22][7] = new Cell("Hallway", topLeftCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][7] = new Cell("None", topEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][8] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[1][8] = new Cell("Hallway", leftEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[3][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[4][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[5][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[11][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[12][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[13][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[14][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[15][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][8] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][8] = new Cell("Billiard Room", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[19][8] = new Cell("Billiard Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[20][8] = new Cell("Billiard Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[21][8] = new Cell("Billiard Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[22][8] = new Cell("Billiard Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[23][8] = new Cell("Billiard Room", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[0][9] = new Cell("Dining Room", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[1][9] = new Cell("Dining Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[2][9] = new Cell("Dining Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[3][9] = new Cell("Dining Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[4][9] = new Cell("Dining Room", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[5][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[11][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[12][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[13][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[14][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[15][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][9] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][9] = new Cell("Billiard Room", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[19][9] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][9] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][9] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][9] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][9] = new Cell("Billiard Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][10] = new Cell("Dining Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][10] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][10] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][10] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][10] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][10] = new Cell("Dining Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[6][10] = new Cell("Dining Room", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[7][10] = new Cell("Dining Room", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[8][10] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][10] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][10] = new Cell("None", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[11][10] = new Cell("None", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[12][10] = new Cell("None", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[13][10] = new Cell("None", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[14][10] = new Cell("None", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[15][10] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][10] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][10] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][10] = new Cell("Billiard Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[19][10] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][10] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][10] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][10] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][10] = new Cell("Billiard Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][11] = new Cell("Dining Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][11] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][11] = new Cell("Dining Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[8][11] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][11] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][11] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][11] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][11] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][11] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][11] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][11] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][11] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][11] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][11] = new Cell("Billiard Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[19][11] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][11] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][11] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][11] = new Cell("Billiard Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][11] = new Cell("Billiard Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][12] = new Cell("Dining Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][12] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][12] = new Cell("Dining Room", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[8][12] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][12] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][12] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][12] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][12] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][12] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][12] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][12] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][12] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][12] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][12] = new Cell("Billiard Room", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[19][12] = new Cell("Billiard Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[20][12] = new Cell("Billiard Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[21][12] = new Cell("Billiard Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[22][12] = new Cell("Billiard Room", horizontalDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[23][12] = new Cell("Billiard Room", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[0][13] = new Cell("Dining Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][13] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][13] = new Cell("Dining Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[8][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][13] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][13] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][13] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][13] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][13] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[20][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[21][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[22][13] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[23][13] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][14] = new Cell("Dining Room", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][14] = new Cell("Dining Room", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][14] = new Cell("Dining Room", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[8][14] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][14] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][14] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][14] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][14] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][14] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][14] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][14] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][14] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][14] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][14] = new Cell("Library", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[19][14] = new Cell("Library", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[20][14] = new Cell("Library", libraryTopDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[21][14] = new Cell("Library", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[22][14] = new Cell("Library", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[23][14] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][15] = new Cell("Dining Room", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[1][15] = new Cell("Dining Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[2][15] = new Cell("Dining Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[3][15] = new Cell("Dining Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[4][15] = new Cell("Dining Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[5][15] = new Cell("Dining Room", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[6][15] = new Cell("Dining Room", horizontalDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[7][15] = new Cell("Dining Room", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[8][15] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][15] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][15] = new Cell("None", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[11][15] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][15] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][15] = new Cell("None", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][15] = new Cell("None", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][15] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][15] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][15] = new Cell("Library", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[18][15] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][15] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][15] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][15] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][15] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][15] = new Cell("Library", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[0][16] = new Cell("None", bottomRightCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[1][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[2][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[3][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[4][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[5][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][16] = new Cell("None", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[11][16] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[12][16] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[13][16] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[14][16] = new Cell("None", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[15][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][16] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][16] = new Cell("Library", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[18][16] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][16] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][16] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][16] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][16] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][16] = new Cell("Library", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][17] = new Cell("Hallway", leftStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[1][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[2][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[3][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[4][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[5][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[10][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[11][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[12][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[13][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[14][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[15][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][17] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][17] = new Cell("Library", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[18][17] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][17] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][17] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][17] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][17] = new Cell("Library", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][17] = new Cell("Library", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[0][18] = new Cell("Hallway", topRightCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[1][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[2][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[3][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[4][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[5][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[6][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[7][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][18] = new Cell("Hall", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[10][18] = new Cell("Hall", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[11][18] = new Cell("Hall", hallDoorLeft, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[12][18] = new Cell("Hall", hallDoorRight, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[13][18] = new Cell("Hall", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[14][18] = new Cell("Hall", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[15][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][18] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][18] = new Cell("Library", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[19][18] = new Cell("Library", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[20][18] = new Cell("Library", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[21][18] = new Cell("Library", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[22][18] = new Cell("Library", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[23][18] = new Cell("None", bottomEdge, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][19] = new Cell("Lounge", topLeftCorner, new ImageIcon(getClass().getResource("TopLeftCorner.png")));
		board[1][19] = new Cell("Lounge", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[2][19] = new Cell("Lounge", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[3][19] = new Cell("Lounge", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[4][19] = new Cell("Lounge", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[5][19] = new Cell("Lounge", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[6][19] = new Cell("Lounge", loungeDoor, new ImageIcon(getClass().getResource("HorizontalDoor.png")));
		board[7][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][19] = new Cell("Hall", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[10][19] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][19] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][19] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][19] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][19] = new Cell("Hall", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[20][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[21][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[22][19] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[23][19] = new Cell("Hallway", rightStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[0][20] = new Cell("Lounge", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][20] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][20] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][20] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][20] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][20] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][20] = new Cell("Lounge", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[7][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][20] = new Cell("Hall", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[10][20] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][20] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][20] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][20] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][20] = new Cell("Hall", verticalDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[15][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[18][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[19][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[20][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[21][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[22][20] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[23][20] = new Cell("None", topLeftCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[0][21] = new Cell("Lounge", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][21] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][21] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][21] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][21] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][21] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][21] = new Cell("Lounge", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[7][21] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][21] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][21] = new Cell("Hall", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[10][21] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][21] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][21] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][21] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][21] = new Cell("Hall", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][21] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][21] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][21] = new Cell("Study", studyDoor, new ImageIcon(getClass().getResource("VerticalDoor.png")));
		board[18][21] = new Cell("Study", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[19][21] = new Cell("Study", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[20][21] = new Cell("Study", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[21][21] = new Cell("Study", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[22][21] = new Cell("Study", topEdge, new ImageIcon(getClass().getResource("TopEdge.png")));
		board[23][21] = new Cell("Study", topRightCorner, new ImageIcon(getClass().getResource("TopRightCorner.png")));
		board[0][22] = new Cell("Lounge", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][22] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][22] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][22] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][22] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][22] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][22] = new Cell("Lounge", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[7][22] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][22] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][22] = new Cell("Hall", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[10][22] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][22] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][22] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][22] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][22] = new Cell("Hall", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][22] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][22] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][22] = new Cell("Study", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[18][22] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][22] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][22] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][22] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][22] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][22] = new Cell("Study", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][23] = new Cell("Lounge", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[1][23] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[2][23] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[3][23] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[4][23] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[5][23] = new Cell("Lounge", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[6][23] = new Cell("Lounge", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[7][23] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][23] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[9][23] = new Cell("Hall", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[10][23] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[11][23] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[12][23] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[13][23] = new Cell("Hall", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[14][23] = new Cell("Hall", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[15][23] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[16][23] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][23] = new Cell("Study", leftEdge, new ImageIcon(getClass().getResource("LeftEdge.png")));
		board[18][23] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[19][23] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[20][23] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[21][23] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[22][23] = new Cell("Study", blank, new ImageIcon(getClass().getResource("Blank.png")));
		board[23][23] = new Cell("Study", rightEdge, new ImageIcon(getClass().getResource("RightEdge.png")));
		board[0][24] = new Cell("Lounge", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[1][24] = new Cell("Lounge", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[2][24] = new Cell("Lounge", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[3][24] = new Cell("Lounge", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[4][24] = new Cell("Lounge", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[5][24] = new Cell("Lounge", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[6][24] = new Cell("None", topRightCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[7][24] = new Cell("Hallway", bottomStart, new ImageIcon(getClass().getResource("Hallway.png")));
		board[8][24] = new Cell("None", topLeftCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[9][24] = new Cell("Hall", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[10][24] = new Cell("Hall", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[11][24] = new Cell("Hall", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[12][24] = new Cell("Hall", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[13][24] = new Cell("Hall", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[14][24] = new Cell("Hall", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
		board[15][24] = new Cell("None", topRightCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[16][24] = new Cell("Hallway", hallway, new ImageIcon(getClass().getResource("Hallway.png")));
		board[17][24] = new Cell("None", topLeftCorner, new ImageIcon(getClass().getResource("Blank.png")));
		board[18][24] = new Cell("Study", bottomLeftCorner, new ImageIcon(getClass().getResource("BottomLeftCorner.png")));
		board[19][24] = new Cell("Study", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[20][24] = new Cell("Study", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[21][24] = new Cell("Study", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[22][24] = new Cell("Study", bottomEdge, new ImageIcon(getClass().getResource("BottomEdge.png")));
		board[23][24] = new Cell("Study", bottomRightCorner, new ImageIcon(getClass().getResource("BottomRightCorner.png")));
	}

	/**
	 * The draw method which draws the top half of a cell, and
	 * then the bottom half. This also applies to players.
	 */
	public void draw() {
		JFrame boardDisplay = new JFrame("Board");
		JPanel boardPanel = new JPanel(new GridLayout(25,24));
		  boardPanel.addMouseListener(new MouseListener() {
			     @Override
			     public void mousePressed(MouseEvent e) {
			        System.out.println("X:" + e.getX() + "\nY:" + e.getY());
			     }

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Hello");
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Goodbye");

				}
			  });
		ImageIcon cellPic;
		JLabel label = null;
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 24; j++) {
				if (board[j][i].occupied) {
					cellPic = board[j][i].getPlayerInit().getImage();
				}
				else {
					cellPic = board[j][i].getPic();
				}
				label = new JLabel(cellPic);
				if (board[j][i].occupied) {
				label.setToolTipText(board[i][j].getPlayerInit().getName() + "/" + board[j][i].getPlayerInit().getcharacterName());
				}
				Border border = BorderFactory.createLineBorder(boardDisplay.getBackground(), 1);
				label.setBorder(border);
				boardPanel.add(label);
				boardDisplay.add(boardPanel);
			}
		}
		boardDisplay.setPreferredSize(new Dimension(570, 580));
		boardDisplay.pack();
		boardDisplay.setVisible(true);
		boardDisplay.setResizable(true);
	}
	//
	//	/**
	//	 * converts board into a string object (used for testing)
	//	 */
	//	public String toString() {
	//		StringBuilder builder = new StringBuilder();
	//		for (int j = 0; j < 25; j++) {
	//			builder.append(System.getProperty("line.separator"));
	//			Boolean lineSecond = false; //used by the program to determine if this is the first or second line of the cell being drawn
	//			for (int i = 0; i < 24; i++) {
	//				Cell cell = board[i][j];
	//				char[] draw;
	//				if(cell.hasPlayer()) {
	//					draw = cell.getPlayerInit();
	//				}
	//				else {
	//					draw = cell.getDraw();
	//				}
	//				if (lineSecond == false) {
	//					builder.append(draw[1]);
	//					builder.append(draw[2]);
	//					}
	//				else {
	//					builder.append(draw[3]);
	//					builder.append(draw[4]);
	//				}
	//				if (i == 23 && lineSecond == false) {
	//					builder.append(System.getProperty("line.separator"));
	//					i = -1;
	//					lineSecond = true;
	//				}
	//			}
	//		}
	//		return builder.toString();
	//	}

	/**
	 * get cell at given row and column
	 * @param row
	 * @param col (column)
	 * @return
	 */
	public Cell getCell(int x, int y) {
		return board[x][y];
	}

	/**
	 * stores the positions of exiting door for each room for when a player exits a room
	 */
	public void setDoors() {
		roomDoors.put("Conservatory", new Cell[] {board[18][5]});
		roomDoors.put("Ball Room", new Cell[] {board[7][5],board[16][5],board[9][8],board[14][8]});
		roomDoors.put("Kitchen", new Cell[] {board[4][7]});
		roomDoors.put("Billiard Room", new Cell[] {board[17][9],board[22][12]});
		roomDoors.put("Dining Room", new Cell[] {board[8][12],board[6][16]});
		roomDoors.put("Library", new Cell[] {board[20][13],board[16][16]});
		roomDoors.put("Hall", new Cell[] {board[11][17],board[12][17],board[15][20]});
		roomDoors.put("Lounge", new Cell[] {board[6][18]});
		roomDoors.put("Study", new Cell[] {board[17][20]});
	}

	public void setStoreLocations() {
		roomStore.put("Conservatory", new Cell[] {board[19][3],board[20][3],board[21][3],board[22][3],board[20][4],board[21][4]});
		roomStore.put("Ball Room", new Cell[] {board[10][5],board[12][5],board[11][6],board[10][2],board[12][2],board[11][1]});
		roomStore.put("Kitchen", new Cell[] {board[1][2],board[2][2],board[3][2],board[1][4],board[2][4],board[3][4]});
		roomStore.put("Billiard Room", new Cell[] {board[19][9],board[20][9],board[21][9],board[22][9],board[20][11],board[21][11]});
		roomStore.put("Dining Room", new Cell[] {board[1][14],board[3][14],board[5][14],board[1][10],board[2][10],board[3][10]});
		roomStore.put("None", new Cell[] {board[2][11],board[4][11],board[2][13],board[4][13],board[2][15]});
		roomStore.put("Library", new Cell[] {board[19][15],board[20][15],board[21][15],board[19][17],board[20][17],board[21][17]});
		roomStore.put("Hall", new Cell[] {board[11][20],board[12][19],board[13][20],board[11][23],board[12][22],board[13][23]});
		roomStore.put("Lounge", new Cell[] {board[1][23],board[3][23],board[4][23],board[1][19],board[3][19],board[4][19]});
		roomStore.put("Study", new Cell[] {board[22][23],board[22][21],board[20][23],board[20][21],board[19][23],board[19][21]});
	}
}