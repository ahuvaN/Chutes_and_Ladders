package chutesAndLadders;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PlayerInfo extends JFrame {

	private JLabel p1;
	private JLabel p2;
	private JTextField p1Name;
	private JTextField p2Name;
	private JButton submit;

	public PlayerInfo() {
		setTitle("CHUTES AND LADDERS");
		setSize(250, 250);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// use grid layout
		Container container = getContentPane();
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		p1 = new JLabel("Player 1: ");
		add(p1);
		p1Name = new JTextField("Enter Name");
		p1Name.setMaximumSize(new Dimension(300, 35));
		p1Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p1Name);

		p2 = new JLabel("Player 2:");
		add(p2);
		p2Name = new JTextField("Enter Name");
		p2Name.setMaximumSize(new Dimension(300, 35));
		p2Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p2Name);

		submit = new JButton("PLAY");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(submit);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ChutesAndLadders gameBoard;
				try {
					gameBoard = new ChutesAndLadders(p1Name
							.getText(), p2Name.getText());
					gameBoard.setVisible(true);
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		});

	}

	public static void main(String[] args) {
		new PlayerInfo().setVisible(true);

	}

}
