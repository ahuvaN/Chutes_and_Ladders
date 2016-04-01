package chutesAndLadders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameButton extends JButton implements ActionListener{

	public GameButton(String name, Color c) {
		setText(name);
		setPreferredSize(new Dimension(300, 100));
		setBackground(c);
		setFont(new Font("Arial", Font.BOLD, 33));
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		GameButton g = (GameButton) event.getSource();
		new PlayerInfo(Integer.parseInt(g.getText())).setVisible(true);

	}

}
