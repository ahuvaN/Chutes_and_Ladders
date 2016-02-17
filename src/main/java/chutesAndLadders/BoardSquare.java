package chutesAndLadders;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

public class BoardSquare extends JComponent {

	private ArrayList<Image> players;

	public BoardSquare() {
		players = new ArrayList<Image>();

	}

	public void addPlayer(Image image) {
		players.add(image);
	}

	public void removePlayer(Image image) {
		players.remove(image);
	}

	public int getLength() {
		return players.size();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < players.size(); i++) {
			g.drawImage(players.get(i), i * 10 + 2, i * 10 + 2, null);
		}

	}

}
