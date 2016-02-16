package chutesAndLadders;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseNumPlayers extends JFrame {

	private JButton two, three, four, five, six;
	private JLabel howMany;

	public ChooseNumPlayers() {
		setTitle("CHUTES AND LADDERS");
		setSize(400, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		Font font = new Font("Arial", Font.BOLD, 33);
		Font font2 = new Font("Arial", Font.BOLD, 13);
		Dimension d = new Dimension(400, 100);

		howMany = new JLabel();
		howMany.setPreferredSize(d);
		howMany.setFont(font2);
		howMany.setText("SELECT HOW MANY PLAYERS WOULD LIKE TO PLAY");
		c.add(howMany);
		two = new JButton("2");
		two.setPreferredSize(d);
		two.setBackground(Color.RED);
		two.setFont(font);
		two.addActionListener(twoListen);
		three = new JButton("3");
		three.setPreferredSize(d);
		three.setBackground(Color.BLUE);
		three.setFont(font);
		three.addActionListener(threeListen);
		four = new JButton("4");
		four.setFont(font);
		four.setBackground(Color.ORANGE);
		four.setPreferredSize(d);
		four.addActionListener(fourListen);
		five = new JButton("5");
		five.setFont(font);
		five.setPreferredSize(d);
		five.setBackground(Color.GREEN);
		five.addActionListener(fiveListen);
		six = new JButton("6");
		six.setFont(font);
		six.setBackground(Color.MAGENTA);
		six.addActionListener(sixListen);
		six.setPreferredSize(d);

		JPanel p2 = new JPanel();
		p2.add(two);
		c.add(p2);

		JPanel p3 = new JPanel();
		p3.add(three);
		c.add(p3);

		JPanel p4 = new JPanel();
		p4.add(four);
		c.add(p4);

		JPanel p5 = new JPanel();
		p5.add(five);
		c.add(p5);

		JPanel p6 = new JPanel();
		p6.add(six);
		c.add(p6);

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
