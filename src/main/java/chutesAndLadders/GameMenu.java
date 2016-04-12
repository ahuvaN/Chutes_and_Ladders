package chutesAndLadders;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.inject.Guice;
import com.google.inject.Injector;

@Singleton
public class GameMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private ButtonsPanel buttons;
	private PlayerInfo playerInfo;
	private ChutesAndLadders gameBoard;
	private JLabel logo;
	private GameFrame frame;

	@Inject
	public GameMenu(ButtonsPanel buttonsPanel, PlayerInfo pInfo,
			ChutesAndLadders game) {

		setLayout(new BorderLayout());

		playerInfo = pInfo;
		playerInfo.setGameMenu(this);

		logo = new JLabel(new ImageIcon(this.getClass()
				.getResource("/logo.png")));

		buttons = buttonsPanel;
		buttons.setMenu(this);

		gameBoard = game;
		game.setMenu(this);



		add(buttons, BorderLayout.EAST);
		add(logo, BorderLayout.CENTER);

	}

	public void setPlayers(int num) {
		playerInfo.setNumPlayers(num);
		this.remove(buttons);
		this.add(playerInfo, BorderLayout.EAST);
		revalidate();
		
	}

	public void playGame(String[] players) {

		gameBoard.setPlayers(players);
		gameBoard.setVisible(true);

		removeAll();
		add(gameBoard, BorderLayout.CENTER);
		revalidate();
	}

	public void newGame() {

		frame.remove(this);
		Injector injector = Guice.createInjector(new GameModule());
		frame.add(injector.getInstance(GameMenu.class));
		frame.revalidate();



	}

	public void setFrame(GameFrame gameFrame) {
		frame = gameFrame;

	}

}