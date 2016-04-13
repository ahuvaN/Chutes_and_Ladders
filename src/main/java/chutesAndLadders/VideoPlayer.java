package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class VideoPlayer extends JDialog implements WindowListener {

	private static final long serialVersionUID = 1L;
	private MediaPlayer mediaPlayer;
	private Media media;
	private MediaView view;
	private String up, down;
	private JLabel status;

	public VideoPlayer() throws IOException {
		setLayout(new BorderLayout());
		this.setTitle("Video");
		this.setSize(480, 390);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);

		final JFXPanel fxPanel = new JFXPanel();

		Platform.setImplicitExit(true);
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					initFX(fxPanel);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}

		});

		down = "CLIMBING UP";
		up = "SLIDING DOWN";
		status = new JLabel("", JLabel.CENTER);
		status.setBackground(Color.BLACK);
		status.setOpaque(true);
		status.setForeground(Color.WHITE);

		Dimension d = new Dimension(480, 25);

		status.setPreferredSize(d);
		status.setMaximumSize(d);
		status.setMinimumSize(d);

		add(status, BorderLayout.SOUTH);
		add(fxPanel, BorderLayout.CENTER);
		this.setVisible(true);

	}

	private void initFX(JFXPanel fxPanel) throws URISyntaxException {

		Group root = new Group();
		URL f = getClass().getResource("/ChutesAndLaddersSong.mp4");
		media = new Media(f.toURI().toString());

		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {

			public void run() {
				setVisible(false);
			}
		});

		view = new MediaView(mediaPlayer);
		root.getChildren().add(view);
		Scene scene = new Scene(root, 600, 800);

		fxPanel.setScene(scene);

	}

	public void playVideo(int position) {
		if (position == 1) {
			status.setText(up);

		} else if (position == 0) {
			status.setText(down);

		}
		this.setAlwaysOnTop(true);
		mediaPlayer.play();

	}

	public static void main(String[] args) throws IOException {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					final VideoPlayer p = new VideoPlayer();
					p.setVisible(true);

					Platform.runLater(new Runnable() {

						public void run() {
							p.playVideo(1);
						}
					});

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public void windowActivated(java.awt.event.WindowEvent e) {

	}

	public void windowClosed(java.awt.event.WindowEvent e) {

	}

	public void windowClosing(java.awt.event.WindowEvent e) {
		mediaPlayer.stop();
		this.setVisible(false);

	}

	public void windowDeactivated(java.awt.event.WindowEvent e) {

	}

	public void windowDeiconified(java.awt.event.WindowEvent e) {

	}

	public void windowIconified(java.awt.event.WindowEvent e) {

	}

	public void windowOpened(java.awt.event.WindowEvent e) {

	}
}
