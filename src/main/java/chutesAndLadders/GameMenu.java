package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameMenu extends JFrame {

	public GameMenu() {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		Container center = new Container();
		JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource("/logo.png")));
		center.setLayout(new FlowLayout());
		center.add(logo);

		ButtonsPanel buttons = new ButtonsPanel();		


		add(buttons, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new GameMenu().setVisible(true);
	}
}