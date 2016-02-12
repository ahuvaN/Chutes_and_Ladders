package chutesAndLadders;

import java.util.HashMap;

public class PlayTheGame {
	private Player player1;
	private Player player2;
	private Player current;
	private int[][] board;
	private HashMap<Position, Position> snakes;
	private HashMap<Position, Position> ladders;

	public PlayTheGame(Player one, Player two) {
		player1 = current = one;
		player2 = two;
		board = new int[10][10];
		snakes = new HashMap<Position, Position>();
		ladders = new HashMap<Position, Position>();
		setUpMaps();
	}

	public void turn(int moves) {
		int row = current.getPosition().getRow();
		int col = current.getPosition().getCol();

		do {
			boolean even = row % 2 == 0 ? true : false;

			if (even) {
				if (col == 0) {
					current.changePosition(row--, col);
					moves--;
				} else if (col >= moves) { // enough room to stay on same row
					current.changePosition(row, col - moves);
					moves = 0;
				} else if (col < moves) { // need to go up a row
					// calculate how many moves will be used to complete the row
					moves = moves - col;
					current.changePosition(row, 0);
				}

			} else {
				if (col == 9) {
					current.changePosition(row--, col);
					moves--;
				} else if (col + moves < 10) { // enough room to stay on same
					// row
					current.changePosition(row, col + moves);
					moves = 0;
				} else if (col + moves >= 10) { // need to go up a row
					// calc how many moves left in this row
					moves = moves - (9 - col);
					current.changePosition(row, 9);
				}
			}
		} while (moves != 0);
		// check for snake/ladder
		checkSnakeLadder(current.getPosition());
		System.out.println(current.getPosition().getRow() + " " + current.getPosition().getCol());
		// if position = 100/ 0,0 showMessageDialog-"We have a winner"
		
		
		// move the player to correct location
		// ???????????

		// change the player
		current = current == player1 ? player2 : player1;
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

	private void checkSnakeLadder(Position pos) {
		if (snakes.containsKey(pos)) {
			current.changePosition(snakes.get(pos).getRow(), snakes.get(pos)
					.getCol());
		}

		else if (ladders.containsKey(pos)) {
			current.changePosition(ladders.get(pos).getRow(), ladders.get(pos)
					.getCol());
		}
	}
	
}
