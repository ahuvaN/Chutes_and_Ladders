package chutesAndLadders;

import javax.inject.Inject;
import javax.swing.JFrame;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameFrame extends JFrame {

	private GameMenu gameMenu;

	@Inject
	public GameFrame(GameMenu gameMenu) {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		add(this.gameMenu = gameMenu);
		this.gameMenu.setFrame(this);

		setVisible(true);
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		injector.getInstance(GameFrame.class);

	}

}
