package chutesAndLadders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private JLabel logo;

	public PlayerInfo() {
		setTitle("CHUTES AND LADDERS");
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// use grid layout
		Container container = getContentPane();
		container.setBackground(Color.CYAN);
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		Font font = new Font("Arial", Font.BOLD, 25);

		p1 = new JLabel("Player 1: ");
		p1.setFont(font);
		p1.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p1);

		p1Name = new JTextField("Enter Name");
		p1Name.setFont(font);
		p1Name.setMaximumSize(new Dimension(300, 35));
		p1Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p1Name);

		p2 = new JLabel("Player 2:");
		p2.setFont(font);
		p2.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p2);

		p2Name = new JTextField("Enter Name");
		p2Name.setFont(font);
		p2Name.setMaximumSize(new Dimension(300, 35));
		p2Name.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(p2Name);

		submit = new JButton("PLAY");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(submit);

		logo = new JLabel();
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setIcon(new ImageIcon("logo.png"));
		add(logo);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				ChutesAndLadders gameBoard = new ChutesAndLadders(p1Name
						.getText(), p2Name.getText());
				gameBoard.setVisible(true);
				dispose();
			}

		});

	}

	public static void main(String[] args) {
		new PlayerInfo().setVisible(true);

	}

}
