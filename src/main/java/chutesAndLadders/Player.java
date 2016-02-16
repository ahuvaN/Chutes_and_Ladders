package chutesAndLadders;

import java.awt.Image;

public class Player {

	private String name;
	private Position position;
	private Image image;
	private int num;

	private static int numbr = 0;

	public Player(String name, Image img) {
		this.name = name;
		position = new Position();
		image = img;
		num = numbr++;
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

}
