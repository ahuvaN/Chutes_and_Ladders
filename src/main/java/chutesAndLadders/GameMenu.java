package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Container;

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
	public GameMenu(ButtonsPanel buttonsPanel, PlayerInfo playersInfo) {
		setTitle("CHUTES AND LADDERS");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.playerInfo = playersInfo;
		Container container = new Container();
		container.setLayout(new BorderLayout());

		JPanel center = new JPanel();
		JLabel logo = new JLabel(new ImageIcon(this.getClass().getResource(
				"/logo.png")));
		center.add(logo);

		//this.playerInfo = playerInfo;
		buttons = buttonsPanel;
		buttons.setMenu(this);
		// buttons.addPropertyChangeListener("buttonClicked",
		// new PropertyChangeListener() {
		//
		// public void propertyChange(PropertyChangeEvent e) {
		// resetContainer();
		//
		// }
		//
		// });

		add(buttons, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);

		setVisible(true);



	}

	public void setPlayers(int num){
		playerInfo.setNumPlayers(num);
		//JOptionPane.showMessageDialog(null, "Set players");
		this.remove(buttons);
		this.add(playerInfo, BorderLayout.WEST);
		revalidate();
	}

	//	private void resetContainer() {
	//		remove(buttons);
	//		add(playerInfo, BorderLayout.WEST);
	//		buttons.setButtonClicked(false);
	//		revalidate();
	//		repaint();
	//	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new GameModule());
		injector.getInstance(GameMenu.class);

	}

}