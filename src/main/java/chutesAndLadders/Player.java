package chutesAndLadders;

public class Player {

	private String name;
	private String color;
	private int num;
	private Position position;

	public Player(String name, int nmbr) {
		this.name = name;
		this.num = nmbr == 1 ? 1 : 2;
		color = num == 1 ? "red" : "blue";
		position = new Position();
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
}
