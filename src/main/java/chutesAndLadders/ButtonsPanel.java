package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.inject.Inject;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel implements ActionListener {
	private GameMenu menu;
	private boolean buttonClicked;
	private int numPlayers;

	@Inject
	public ButtonsPanel() {
		setLayout(new BorderLayout());


		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(6, 1));

		JLabel select = new JLabel();
		select.setText("SELECT HOW MANY PLAYERS WOULD LIKE TO PLAY");

		GameButton two, three, four, five, six;
		two = new GameButton("2", Color.RED);
		three = new GameButton("3", Color.BLUE);
		four = new GameButton("4", Color.ORANGE);
		five = new GameButton("5", Color.GREEN);
		six = new GameButton("6", Color.MAGENTA);

		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);

		buttons.add(two);
		buttons.add(three);
		buttons.add(four);
		buttons.add(five);
		buttons.add(six);

		add(select, BorderLayout.NORTH);
		add(buttons, BorderLayout.CENTER);
	}


	public void actionPerformed(ActionEvent event) {
		GameButton g = (GameButton) event.getSource();
		numPlayers = Integer.parseInt(g.getText());
		setButtonClicked(true);

		//remove player number choice

		// //add player info display to type in player names
		// PlayerInfo playerInfo = new PlayerInfo();
		// playerInfo.setNumPlayers(Integer.parseInt(g.getText()));
		// menu.add(playerInfo, BorderLayout.WEST);
	}





	public boolean isButtonClicked() {
		return buttonClicked;
	}


	public int getNumPlayers() {
		return numPlayers;
	}


	public void setButtonClicked(boolean buttonClicked) {
		this.buttonClicked = buttonClicked;
	}


	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}



}
