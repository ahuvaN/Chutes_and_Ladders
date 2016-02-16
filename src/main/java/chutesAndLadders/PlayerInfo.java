package chutesAndLadders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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

public class PlayerInfo extends JFrame {

	private JLabel[] labels;
	private JTextField[] fields;
	private String[] names;
	/*private JLabel p1;
	private JLabel p2;
	private JTextField p1Name;
	private JTextField p2Name;*/
	private JButton submit;
	private JLabel logo;

	public PlayerInfo(int num) {
		setTitle("CHUTES AND LADDERS");
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		logo = new JLabel(new ImageIcon("logo.png"));
		logo.setBounds(0,0, getWidth(), getHeight());
		
		setContentPane(logo);
		

		// use grid layout
		Container container = getContentPane();
		container.setBackground(Color.CYAN);
		setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

		Font font = new Font("Arial", Font.BOLD, 25);

		labels = new JLabel[num];
		fields = new JTextField[num];
		names = new String[num];
		
		for (int i = 1; i <= num; i++){
			JLabel l = new JLabel("Player " + i);
			l.setFont(font);
			l.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(l);
			
			JTextField f = new HintTextField("Enter Name of player");
			f.setFont(font);
			f.setMaximumSize(new Dimension(300, 35));
			f.setAlignmentX(Component.CENTER_ALIGNMENT);
			add(f);
		}
		
		submit = new JButton("PLAY");
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(submit);

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < fields.length; i++){
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
	
	public static void main(String[] args){
		new PlayerInfo(3).setVisible(true);
	}
}
