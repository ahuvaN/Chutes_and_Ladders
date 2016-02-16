package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseNumPlayers extends JFrame {

	private JButton two, three, four, five, six;

	public ChooseNumPlayers() {
		setTitle("How many players");
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

		
		Container buttons = new Container();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
		add(buttons, BorderLayout.WEST);

		Dimension d = new Dimension(300, 100);
		two = new JButton("2");
		two.setPreferredSize(d);
		two.addActionListener(twoListen);
		three = new JButton("3");
		three.setPreferredSize(d);
		three.addActionListener(threeListen);
		four = new JButton("4");
		four.setPreferredSize(d);
		four.addActionListener(fourListen);
		five = new JButton("5");
		five.setPreferredSize(d);
		five.addActionListener(fiveListen);
		six = new JButton("6");
		six.addActionListener(sixListen);
		six.setPreferredSize(d);

		JPanel p2 = new JPanel();
		p2.add(two);
		buttons.add(p2);

		JPanel p3 = new JPanel();
		p3.add(three);
		buttons.add(p3);

		JPanel p4 = new JPanel();
		p4.add(four);
		buttons.add(p4);

		JPanel p5 = new JPanel();
		p5.add(five);
		buttons.add(p5);

		JPanel p6 = new JPanel();
		p6.add(six);
		buttons.add(p6);

	}

	ActionListener twoListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(2).setVisible(true);
			dispose();
		}
	};

	ActionListener threeListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(3).setVisible(true);
			dispose();
		}
	};

	ActionListener fourListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(4).setVisible(true);
			dispose();
		}
	};

	ActionListener fiveListen = new ActionListener() {
		public void actionPerformed(ActionEvent event) {

			new PlayerInfo(5).setVisible(true);
			dispose();
		}
	};

	ActionListener sixListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(6).setVisible(true);
			dispose();
		}
	};

	public static void main(String[] args) {
		new ChooseNumPlayers().setVisible(true);
		;
	}
}
