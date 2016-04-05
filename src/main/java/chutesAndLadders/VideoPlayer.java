package chutesAndLadders;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
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
	private MediaPlayer mediaPlayer;

	public VideoPlayer() throws IOException {
		setLayout(new BorderLayout());
		this.setTitle("Video");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(640, 400);

		Group root = new Group();
		SceneBuilder<?> sb = SceneBuilder.create().width(640).height(400)
				.root(root);
		String mediaString = "ChutesAndLaddersSong.mp4";
		JFXPanel fxPanel = new JFXPanel();
		Media media = new Media(new File(mediaString).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		MediaView view = new MediaView(mediaPlayer);
		// mediaPlayer.setAutoPlay(true);

		root.getChildren().add(view);
		Scene scene = sb.build();

		fxPanel.setScene(scene);
		//add(fxPanel, BorderLayout.CENTER);
		//this.getContentPane().add(fxPanel);
		add(fxPanel, BorderLayout.CENTER);
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediaPlayer.stop();
			}

		});
		close.setPreferredSize(new Dimension(100, 30));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(100, 30));
		buttonPanel.add(close);
		add(buttonPanel, BorderLayout.SOUTH);
		this.setVisible(true);

	}

	public void playVideo() {
		mediaPlayer.play();
		this.setAlwaysOnTop(true);
	}

	/*
	 * @Override public void setDefaultCloseOperation(int operation) {
	 * mediaPlayer.stop();
	 * 
	 * //this.setVisible(false);
	 * 
	 * }
	 */
	public static void main(String[] args) throws IOException {
		new VideoPlayer().setVisible(true);
	}
}
