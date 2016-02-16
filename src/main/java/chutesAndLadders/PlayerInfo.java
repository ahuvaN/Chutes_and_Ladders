package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayerInfo extends JFrame {

	private JLabel[] labels;
	private JTextField[] fields;
	private String[] names;
	private JButton submit;
	private JLabel logo;

	public PlayerInfo(int num) {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		Container center = new Container();
		JLabel logo = new JLabel(new ImageIcon("logo.png"));
		center.setLayout(new FlowLayout());
		center.add(logo);
		add(center, BorderLayout.CENTER);

		Container players = new Container();
		players.setLayout(new BoxLayout(players, BoxLayout.PAGE_AXIS));
		add(players, BorderLayout.WEST);

		Font font = new Font("Arial", Font.BOLD, 20);

		labels = new JLabel[num];
		fields = new JTextField[num];
		names = new String[num];

		for (int i = 1; i <= num; i++) {
			JLabel l = new JLabel("Player " + i);
			l.setFont(font);
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			l.setVerticalAlignment(JLabel.CENTER);
			labels[i - 1] = l;
			players.add(l);

			JTextField f = new HintTextField("Enter Name                             ");
			f.setFont(font);
			f.setMaximumSize(new Dimension(300, 35));
			f.setAlignmentX(Component.CENTER_ALIGNMENT);
			l.setVerticalAlignment(JLabel.CENTER);
			fields[i - 1] = f;
			players.add(f);
		}

		submit = new JButton("PLAY");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		players.add(submit);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < fields.length; i++) {
					names[i] = fields[i].getText();
				}
				try {
					ChutesAndLadders gameBoard = new ChutesAndLadders(names);
					gameBoard.setVisible(true);
					dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
