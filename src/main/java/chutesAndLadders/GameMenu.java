package chutesAndLadders;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@Singleton
public class GameMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private ButtonsPanel buttons;
	private PlayerInfo playerInfo;
	private ChutesAndLadders gameBoard;
	private JLabel logo;

	@Inject
	public GameMenu(ButtonsPanel buttonsPanel, PlayerInfo pInfo,
			ChutesAndLadders game) {

		setLayout(new BorderLayout());

		playerInfo = pInfo;
		playerInfo.setGameMenu(this);

		logo = new JLabel(new ImageIcon(this.getClass().getResource(
				"/logo.png")));

		// this.playerInfo = playerInfo;
		buttons = buttonsPanel;
		buttons.setMenu(this);

		gameBoard = game;
		// buttons.addPropertyChangeListener("buttonClicked",
		// new PropertyChangeListener() {
		//
		// public void propertyChange(PropertyChangeEvent e) {
		// resetContainer();
		//
		// }
		//
		// });

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

		playerInfo = new PlayerInfo();
		playerInfo.setGameMenu(this);


		buttons = new ButtonsPanel();
		buttons.setMenu(this);

		gameBoard = new ChutesAndLadders();

		add(buttons, BorderLayout.WEST);
		add(logo, BorderLayout.CENTER);

	}

}