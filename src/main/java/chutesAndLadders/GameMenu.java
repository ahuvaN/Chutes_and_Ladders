package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GameMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private ButtonsPanel buttons;
	private PlayerInfo playerInfo;

	@Inject
	public GameMenu(ButtonsPanel buttonsPanel, final PlayerInfo playerInfo) {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container container = new Container();
		container.setLayout(new BorderLayout());

		JPanel center = new JPanel();
		JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource(
				"/logo.png")));
		center.add(logo);

		this.playerInfo = playerInfo;
		buttons = buttonsPanel;
		buttons.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent e) {
				if (e.getPropertyName().equals("buttonClicked")) {
					remove(buttons);
					add(playerInfo, BorderLayout.WEST);
					buttons.setButtonClicked(false);
				}

			}

		});
		// while(buttons.isButtonClicked()){
		// playerInfo.setNumPlayers(buttons.getNumPlayers());
		// this.remove(buttons);
		// this.add(playerInfo, BorderLayout.WEST);
		// buttons.setButtonClicked(false);
		// }

		add(buttons, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		injector.getInstance(GameMenu.class);

	}

}