package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameMenu extends JFrame {

	@Inject
	public GameMenu(ButtonsPanel buttonsPanel) {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		Container center = new Container();
		JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource("/logo.png")));
		center.setLayout(new FlowLayout());
		center.add(logo);

		ButtonsPanel buttons = new ButtonsPanel();		


		add(buttons, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		GameMenu frame = injector.getInstance(GameMenu.class);

		//new GameMenu().setVisible(true);
	}
}