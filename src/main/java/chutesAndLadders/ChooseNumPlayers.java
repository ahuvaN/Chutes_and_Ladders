package chutesAndLadders;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChooseNumPlayers extends JFrame {

	private JButton two, three, four, five, six;

	public ChooseNumPlayers() {
		setTitle("How many players");
		setSize(400, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		Dimension d = new Dimension(400, 100);
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
		}
	};

	ActionListener threeListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(3).setVisible(true);
		}
	};

	ActionListener fourListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(4).setVisible(true);
		}
	};

	ActionListener fiveListen = new ActionListener() {
		public void actionPerformed(ActionEvent event) {

			new PlayerInfo(5).setVisible(true);
		}
	};

	ActionListener sixListen = new ActionListener() {

		public void actionPerformed(ActionEvent event) {
			new PlayerInfo(6).setVisible(true);
		}
	};

	public static void main(String[] args) {
		new ChooseNumPlayers().setVisible(true);
		;
	}
}
