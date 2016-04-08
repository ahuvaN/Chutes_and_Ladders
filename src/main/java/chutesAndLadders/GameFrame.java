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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		add(this.gameMenu = gameMenu);

		setVisible(true);
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		injector.getInstance(GameFrame.class);

	}

}
