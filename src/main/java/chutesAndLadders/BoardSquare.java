package chutesAndLadders;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JComponent;

public class BoardSquare extends JComponent {

	private List<Image> players;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < players.size(); i++) {
			g.drawImage(players.get(i), i * 10, 0, null);
		}

	}

}
