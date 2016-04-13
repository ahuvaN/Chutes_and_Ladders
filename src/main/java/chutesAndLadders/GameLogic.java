package chutesAndLadders;

import java.util.HashMap;

public class GameLogic {
	private Player[] players;
	private Player current;
	private HashMap<Position, Position> snakes;
	private HashMap<Position, Position> ladders;
	private Board board;

	public GameLogic(Player[] allPlayers, Board board) {
		players = allPlayers;
		current = players[0];
		snakes = new HashMap<Position, Position>();
		ladders = new HashMap<Position, Position>();
		this.board = board;

		setUpMaps();
	}

	public void turn(int moves) {
		int row = current.getPosition().getRow();
		int col = current.getPosition().getCol();

		do {
			boolean even = row % 2 == 0 ? true : false;

			if (even) {
				if (row == 0 && col < 6) {
					if (col - moves < 0) {
						current.changePosition(0, 0);
						return;
					}
				}
				if (col == 0) {
					row -= 1;
					current.changePosition(row, col);

					moves--;
				} else if (col >= moves) { // enough room to stay on same row
					col -= moves;
					current.changePosition(row, col);

					moves = 0;
				} else if (col < moves) { // need to go up a row
					// calculate how many moves will be used to complete the row
					moves = moves - col;
					current.changePosition(row, 0);

					row = current.getPosition().getRow();
					col = current.getPosition().getCol();
				}

			} else {
				if (col == 9) {
					row -= 1;
					current.changePosition(row, col);

					moves--;
				} else if (col + moves < 10) { // enough room to stay on same
					// row
					col += moves;
					current.changePosition(row, col);

					moves = 0;
					break;
				} else if (col + moves >= 10) { // need to go up a row
					// calc how many moves left in this row
					moves = moves - (9 - col);
					current.changePosition(row, 9);

					row = current.getPosition().getRow();
					col = current.getPosition().getCol();
				}
			}
		} while (moves != 0);
	}

	public Player switchPlayer() {
		if (current.getNum() + 1 < players.length) {
			current = players[current.getNum() + 1];
		} else {
			current = players[0];
		}
		return current;
	}

	private void setUpMaps() {
		ladders.put(new Position(9, 0), new Position(6, 2));
		ladders.put(new Position(9, 3), new Position(8, 6));
		ladders.put(new Position(9, 8), new Position(6, 9));
		ladders.put(new Position(7, 0), new Position(5, 1));
		ladders.put(new Position(7, 7), new Position(1, 3));
		ladders.put(new Position(4, 9), new Position(3, 6));
		ladders.put(new Position(2, 9), new Position(0, 9));
		ladders.put(new Position(2, 0), new Position(0, 0));

		snakes.put(new Position(0, 2), new Position(2, 1));
		snakes.put(new Position(0, 5), new Position(2, 5));
		snakes.put(new Position(0, 7), new Position(2, 7));
		snakes.put(new Position(1, 6), new Position(7, 3));
		snakes.put(new Position(3, 3), new Position(4, 0));
		snakes.put(new Position(3, 1), new Position(8, 1));
		snakes.put(new Position(4, 6), new Position(6, 6));
		snakes.put(new Position(8, 3), new Position(9, 6));
	}

	public boolean checkSnake(Position pos) {
		if (snakes.containsKey(pos)) {
			current.changePosition(snakes.get(pos).getRow(), snakes.get(pos)
					.getCol());
			return true;
		}
		return false;
	}

	public boolean checkLadder(Position pos) {
		if (ladders.containsKey(pos)) {
			current.changePosition(ladders.get(pos).getRow(), ladders.get(pos)
					.getCol());
			return true;
		}
		return false;
	}
}