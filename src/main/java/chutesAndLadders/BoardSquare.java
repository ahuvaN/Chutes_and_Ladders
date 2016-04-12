package chutesAndLadders;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

public class BoardSquare extends JComponent {

	private ArrayList<Image> players;

	public BoardSquare() {
		players = new ArrayList<Image>();
		//this.setBorderPainted(false);
		this.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				repaint();
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	public void addPlayer(Image image) {
		players.add(image);
		repaint();
		revalidate();
	}

	public void removePlayer(Image image) {
		players.remove(image);
		repaint();
		revalidate();
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
