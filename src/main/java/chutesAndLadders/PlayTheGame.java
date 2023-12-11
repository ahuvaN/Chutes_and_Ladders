package chutesAndLadders;

import java.util.HashMap;

public class PlayTheGame {
	private Player[] players;
	Player current;
	private HashMap<Position, Position> snakes;
	private HashMap<Position, Position> ladders;

	public PlayTheGame(Player[] allPlayers) {
		players = allPlayers;
		current = players[0];
		snakes = new HashMap<Position, Position>();
		ladders = new HashMap<Position, Position>();

		setUpMaps();
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