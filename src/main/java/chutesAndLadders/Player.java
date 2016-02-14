package chutesAndLadders;

import java.awt.Image;

public class Player {

	private String name;
	private String color;
	private int num;
	private Position position;
	private Image image;

	public Player(String name, int nmbr, Image img) {
		this.name = name;
		this.num = nmbr == 1 ? 1 : 2;
		color = num == 1 ? "red" : "blue";
		position = new Position();
		image = img;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public int getNum() {
		return num;
	}

	public void changePosition(int row, int col) {
		position.setPosition(row, col);
	}

	public Position getPosition() {
		return position;
	}
	
	public Image getImage(){
		return image;
	}

}
