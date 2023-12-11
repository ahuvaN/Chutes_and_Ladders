package chutesAndLadders;

import java.awt.Image;

public class Player {

	private String name;
	private Position position;
	private Image image;
	private int num;

	public Player(String name, Image img, int value) {
		this.name = name;
		position = new Position();
		image = img;
		num = value;
	}

	public String getName() {
		return name;
	}

	public void changePosition(int row, int col) {
		position.setPosition(row, col);
	}

	public Position getPosition() {
		return position;
	}

	public int getRow() {
		return position.getRow();
	}

	public int getCol() {
		return position.getCol();
	}

	public Image getImage() {
		return image;
	}

	public int getNum() {
		return num;
	}

	public void turn(int moves) {
		int row = getPosition().getRow();
		int col = getPosition().getCol();
	
		do {
			boolean even = row % 2 == 0 ? true : false;
	
			if (even) {
				if (row == 0 && col < 6) {
					if (col - moves < 0) {
						changePosition(0, 0);
						return;
					}
				}
				if (col == 0) {
					row -= 1;
					changePosition(row, col);
					moves--;
				} else if (col >= moves) { // enough room to stay on same row
					col -= moves;
					changePosition(row, col);
					moves = 0;
				} else if (col < moves) { // need to go up a row
					// calculate how many moves will be used to complete the row
					moves = moves - col;
					changePosition(row, 0);
					row = getPosition().getRow();
					col = getPosition().getCol();
				}
	
			} else {
				if (col == 9) {
					row -= 1;
					changePosition(row, col);
					moves--;
				} else if (col + moves < 10) { // enough room to stay on same
					// row
					col += moves;
					changePosition(row, col);
					moves = 0;
					break;
				} else if (col + moves >= 10) { // need to go up a row
					// calc how many moves left in this row
					moves = moves - (9 - col);
					changePosition(row, 9);
					row = getPosition().getRow();
					col = getPosition().getCol();
				}
			}
		} while (moves != 0);
	}

}
