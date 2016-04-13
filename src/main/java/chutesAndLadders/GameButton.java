package chutesAndLadders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class GameButton extends JButton {

	public GameButton(String name, Color c) {
		setText(name);
		setPreferredSize(new Dimension(300, 100));
		setBackground(c);
		setFont(new Font("Arial", Font.BOLD, 33));

	}

}
