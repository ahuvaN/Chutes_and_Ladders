package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoPlayer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VideoPlayer() throws IOException {
		setLayout(new BorderLayout());
		this.setTitle("Swing and JavaFX");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);

		// file you want to play
		// URL mediaURL = new URL("[Scrubs] Bloopers.flv");// Whatever
		// create the media player with the media url
		Group root = new Group();
		SceneBuilder<?> sb = SceneBuilder.create().width(640).height(400)
				.root(root);
		String mediaString = "ChutesAndLaddersSong.mp4";
		JFXPanel fxPanel = new JFXPanel();
		Media media = new Media(new File(mediaString).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		MediaView view = new MediaView(mediaPlayer);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();

		root.getChildren().add(view);
		Scene scene = sb.build();

		fxPanel.setScene(scene);
		this.getContentPane().add(fxPanel);
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws IOException{
		new VideoPlayer().setVisible(true);
	}
}
