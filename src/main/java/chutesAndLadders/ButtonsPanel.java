package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel{

	public ButtonsPanel(){
		setLayout(new BorderLayout());

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(6,1));

		JLabel select = new JLabel();
		select.setText("  SELECT HOW MANY PLAYERS WOULD LIKE TO PLAY");

		GameButton two, three, four, five, six;
		two = new GameButton("2", Color.RED);
		three = new GameButton("3", Color.BLUE);
		four = new GameButton("4", Color.ORANGE);
		five = new GameButton("5", Color.GREEN);
		six = new GameButton("6", Color.MAGENTA);

		buttons.add(two);
		buttons.add(three);
		buttons.add(four);
		buttons.add(five);
		buttons.add(six);


		add(select, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}


}
