package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ChutesAndLadders extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton spinButton;
	private ImageIcon[] diceFaces;
	private Board board;
	private GameLogic logic;
	private Player[] players;
	private Player current;
	private JLabel playersTurn;
	private ImageIcon[] pieces;
	private Timer pieceTimer;
	private Random random;
	private Timer timer;
	private int number;
	private JLabel playersImg;

	private GameMenu gameMenu;

	@Inject
	public ChutesAndLadders() {

		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 0, 7, 7));
		panel.setBackground(new Color(204, 204, 204));
		panel.setPreferredSize(new Dimension(230, 400));

		JLabel logo = new JLabel(new ImageIcon(getClass().getResource(
				"/logo_small.png")));
		panel.add(logo);

		Font font = new Font("Arial", Font.BOLD, 30);
		board = new Board();
		add(board, BorderLayout.CENTER);

		JPanel playerPanel = new JPanel(new GridLayout(2, 1));
		playerPanel.setOpaque(false);
		playersTurn = new JLabel("", JLabel.CENTER);
		playersTurn.setFont(font);
		playersTurn.setForeground(Color.WHITE);
		playersTurn.setVerticalAlignment(JLabel.BOTTOM);
		playerPanel.add(playersTurn);

		JLabel turn = new JLabel("turn", JLabel.CENTER);
		turn.setFont(font);
		turn.setForeground(Color.WHITE);
		turn.setVerticalAlignment(JLabel.TOP);
		playerPanel.add(turn);

		panel.add(playerPanel);

		playersImg = new JLabel();
		playersImg.setHorizontalAlignment(JLabel.CENTER);
		panel.add(playersImg);

		spinButton = new JButton();

		spinButton.setOpaque(false);
		spinButton.setContentAreaFilled(false);
		spinButton.setBorderPainted(false);
		spinButton.setDisabledIcon(spinButton.getIcon());
		spinButton.setIcon(new ImageIcon(getClass().getResource("/ROLL.png")));
		spinButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				rollDice();
			}

		});

		spinButton.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				JButton b = (JButton) e.getSource();
				b.setBorderPainted(false);

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mousePressed(MouseEvent e) {
				JButton b = (JButton) e.getSource();
				b.setContentAreaFilled(false);
			}

			public void mouseReleased(MouseEvent e) {

			}

		});

		diceFaces = new ImageIcon[] {
				new ImageIcon(getClass().getResource("/one.png")),
				new ImageIcon(getClass().getResource("/two.png")),
				new ImageIcon(getClass().getResource("/three.png")),
				new ImageIcon(getClass().getResource("/four.png")),
				new ImageIcon(getClass().getResource("/five.png")),
				new ImageIcon(getClass().getResource("/six.png")) };

		panel.add(spinButton);

		add(panel, BorderLayout.LINE_START);

		pieces = new ImageIcon[] {
				new ImageIcon(getClass().getResource("/red.png")),
				new ImageIcon(getClass().getResource("/blue.png")),
				new ImageIcon(getClass().getResource("/green.png")),
				new ImageIcon(getClass().getResource("/yellow.png")),
				new ImageIcon(getClass().getResource("/orange.png")),
				new ImageIcon(getClass().getResource("/purple.png")) };

	}

	public void setPlayers(String[] playerNames) {
		players = new Player[playerNames.length];

		int x = 0;
		for (int i = 0; i < playerNames.length; i++) {
			players[i] = new Player(playerNames[i], pieces[i].getImage(), x++);
		}

		current = players[0];

		playersTurn.setText(current.getName() + "'s");

		playersImg.setIcon(new ImageIcon(current.getImage()));

		logic = new GameLogic(players, board);
	}

	private void displayWinner() {
		// playSound("sound.wav");
		int again = JOptionPane.showConfirmDialog(this, "CONGRAGULATIONS! "
				+ current.getName() + " WINS!!!! \nDo you want to play again?",
				"Chutes and Ladders", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, new ImageIcon("smile.jpeg"));

		if (again == JOptionPane.YES_OPTION) {
			gameMenu.newGame();
			gameMenu.revalidate();

		} else {
			JOptionPane.showMessageDialog(this,
					"HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("bye.png"));

			gameMenu.getFrame().dispose();
		}
	}

	private void checkBoard() {
		boolean found = false;
		int row = current.getRow();
		int col = current.getCol();

		if (logic.checkSnake(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"OH NO! YOU HIT A SNAKE - GOING DOWN...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("snake.gif"));
			found = true;
		} else if (logic.checkLadder(current.getPosition())) {
			JOptionPane.showMessageDialog(this,
					"YAY! YOU HIT A LADDER - GOING UP...!",
					"chutes and ladders", JOptionPane.PLAIN_MESSAGE,
					new ImageIcon("ladder.jpeg"));
			found = true;
		}

		// repaint piece

		if (found) {
			board.removeImage(current.getImage(), row, col);

			board.addImage(current.getImage(), current.getRow(),
					current.getCol());
		}
	}

	private void rollDice() {
		random = new Random();
		number = 0;

		timer = new Timer(50, new ActionListener() {
			int count = 1;

			public void actionPerformed(ActionEvent evt) {

				number = random.nextInt(6);
				spinButton.setIcon(diceFaces[number]);
				repaint();
				count++;

				if (count == 6) {
					timer.stop();
					timer = null;
					movePlayer(number + 1);

				}
			}

		});
		timer.start();

	}

	private void movePlayer(final int number) {
		pieceTimer = new Timer(500, new ActionListener() {
			int count = 0;

			public void actionPerformed(ActionEvent e) {
				if (current.getCol() != -1) {
					board.removeImage(current.getImage(), current.getRow(),
							current.getCol());
				}
				logic.turn(1);

				board.addImage(current.getImage(), current.getRow(),
						current.getCol());
				repaint();

				count++;
				if (count == number) {
					pieceTimer.stop();
					pieceTimer = null;
					nextTurn();
				}
			}

		});

		spinButton.setDisabledIcon(spinButton.getIcon());
		spinButton.setEnabled(false);

		pieceTimer.start();

	}

	private void nextTurn() {
		checkBoard();

		if (current.getRow() <= 0 && current.getCol() <= 0) {
			displayWinner();
		}

		current = logic.switchPlayer();
		playersImg.setIcon(new ImageIcon(current.getImage()));
		playersTurn.setText(current.getName() + "'s");
		spinButton.setEnabled(true);
	}

	public void setMenu(GameMenu gameMenu) {
		this.gameMenu = gameMenu;
	}
}
