package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerInfo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel[] labels;
	private JTextField[] fields;
	private JButton submit;
	private JPanel players;
	private String[] playerNames;

	@Inject
	public PlayerInfo() {
		setLayout(new BorderLayout());

		Dimension d = new Dimension(300, 600);
		setPreferredSize(d);
		setMinimumSize(d);
		setMaximumSize(d);

		players = new JPanel();
		players.setLayout(new GridLayout(6, 1));

		submit = new JButton("PLAY");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				playerNames = new String[fields.length];
				for (int i = 0; i < fields.length; i++) {
					playerNames[i] = fields[i].getText();
				}
				try {
					ChutesAndLadders gameBoard = new ChutesAndLadders();
					gameBoard.setVisible(true);
					gameBoard.setPlayers(playerNames);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		JLabel instructions = new JLabel("ENTER PLAYER NAMES", JLabel.CENTER);
		instructions.setFont(new Font("Arial", Font.BOLD, 20));
		d = new Dimension(300, 100);
		instructions.setPreferredSize(d);
		instructions.setMinimumSize(d);
		instructions.setMaximumSize(d);

		add(players, BorderLayout.CENTER);
		add(submit, BorderLayout.SOUTH);
		add(instructions, BorderLayout.NORTH);

	}

	public void setNumPlayers(int num) {
		labels = new JLabel[num];
		fields = new JTextField[num];

		Font font = new Font("Arial", Font.BOLD, 20);
		for (int i = 1; i <= num; i++) {
			JPanel player = new JPanel();
			JLabel l = new JLabel("Player " + i);
			l.setFont(font);
			l.setVerticalAlignment(JLabel.BOTTOM);
			labels[i - 1] = l;
			player.add(l);

			Dimension d = new Dimension(200, 35);

			JTextField f = new JTextField();
			f.setFont(font);
			f.setPreferredSize(d);
			f.setMinimumSize(d);
			f.setMaximumSize(d);
			//f.setAlignmentX(Component.CENTER_ALIGNMENT);
			//l.setVerticalAlignment(JLabel.CENTER);
			fields[i - 1] = f;
			player.add(f);
			players.add(player);
		}
	}

}
