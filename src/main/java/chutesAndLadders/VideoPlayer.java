package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
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

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.glass.events.WindowEvent;

public class VideoPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediaPlayer mediaPlayer;
	private Media media;
	private MediaView view;
	private WindowAdapter windowAdapter;

	public VideoPlayer() throws IOException {
		setLayout(new BorderLayout());
		this.setTitle("Video");
		this.setSize(480, 390);
		setVisible(true);

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
		// this.setDefaultCloseOperation();
		// JButton close = new JButton("Close");
		// close.addActionListener(new ActionListener() {
		//
		// public void actionPerformed(ActionEvent arg0) {
		// setVisible(false);
		// mediaPlayer.stop();
		// }
		//
		// });
		// close.setPreferredSize(new Dimension(100, 30));
		//
		// JPanel buttonPanel = new JPanel();
		// buttonPanel.setSize(new Dimension(100, 30));
		// buttonPanel.add(close);
		//
		// add(buttonPanel, BorderLayout.SOUTH);

		windowAdapter = new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				mediaPlayer.stop();
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		};

		addWindowListener(windowAdapter);

		add(fxPanel, BorderLayout.CENTER);
		this.setVisible(true);

	}

	private void initFX(JFXPanel fxPanel) throws URISyntaxException {
		// StackPane root = new StackPane();
		Group root = new Group();
		// String mediaString = "/ChutesAndLaddersSong.mp4";
		URL f = getClass().getResource("/ChutesAndLaddersSong.mp4");

		// media = new Media(new File(mediaString).toURI().toString());
		media = new Media(f.toURI().toString());
		// media = new Media(mediaString);
		mediaPlayer = new MediaPlayer(media);
		// mediaPlayer.setAutoPlay(true);

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

	public void playVideo() {
		this.setAlwaysOnTop(true);
		mediaPlayer.play();

	}

	// public void setDefaultCloseOperation() {
	// Platform.runLater(new Runnable() {
	//
	// public void run() {
	// mediaPlayer.stop();
	// setVisible(false);
	// }
	// });
	//
	// }

	public static void main(String[] args) throws IOException {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					final VideoPlayer p = new VideoPlayer();
					p.setVisible(true);

					Platform.runLater(new Runnable() {

						public void run() {
							p.playVideo();
						}
					});

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}
}
