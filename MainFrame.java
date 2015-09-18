import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;

import java.awt.Component;
import java.io.IOException;

import javax.swing.SwingConstants;

//import uk.co.caprica.vlcj.player.MediaPlayer; //getTime(), skip(), mute(), pause(), play()
//import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent; 

public class MainFrame extends JFrame {
	
	int festID = 0; //because process ID is very unlikely to be 0
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("VIDIVOX by twerking-hippo :)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 1000, 650);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenNewVideo = new JMenuItem("Open New Video...");
		mntmOpenNewVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//changing the video that we are editing
			}
		});
		mnFile.add(mntmOpenNewVideo);
		
		JPanel screen_play = new JPanel();
		screen_play.setLayout(new BorderLayout(0, 0));
		
		JPanel screen = new JPanel();
		screen.setBackground(Color.BLACK);
		//EmbeddedMediaPlayerComponent screen = new EmbeddedMediaPlayerComponent();
		//screen.getMediaPlayer().playMedia("bunny.avi");
		screen_play.add(screen, BorderLayout.CENTER);
		
		JPanel controls = new JPanel();
		screen_play.add(controls, BorderLayout.SOUTH);
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		
		JPanel video_control = new JPanel();
		controls.add(video_control);
		video_control.setLayout(new BoxLayout(video_control, BoxLayout.X_AXIS));
		
		JButton btnReverse = new JButton("l <<");
		btnReverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//should continue rewinding until user clicks play
			}
		});
		video_control.add(btnReverse);
		
		JButton btnPlayPause = new JButton("> / l l");
		btnPlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//play or pause video
			}
		});
		video_control.add(btnPlayPause);
		
		JButton btnForward = new JButton(">> l");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//should continue forwarding until user clicks play
			}
		});
		video_control.add(btnForward);
		
		JPanel volume_control = new JPanel();
		controls.add(volume_control);
		volume_control.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		volume_control.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblSound = new JLabel("Sound");
		panel_1.add(lblSound);
		lblSound.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JSlider slider = new JSlider();
		panel_1.add(slider);
		
		final JButton btnMute = new JButton("Mute");
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mute the sound when clicked, unmute when clicked again
				if(btnMute.getText().equals("Mute")) {
					btnMute.setText("UnMute");
					//video.mute(); //toggles mute for the video
				} else {
					btnMute.setText("Mute");
					//video.mute(); //toggles mute for the video
				}
			}
		});
		panel_1.add(btnMute);
		
		JPanel audio_editing = new JPanel();
		audio_editing.setMinimumSize(new Dimension(300, 500));
		screen_play.setMinimumSize(new Dimension(300, 500));
		
		JSplitPane splitPane = new JSplitPane();
		setContentPane(splitPane);
		splitPane.setLeftComponent(screen_play);
		splitPane.setRightComponent(audio_editing);
		splitPane.setDividerLocation(700 + splitPane.getInsets().left);
		audio_editing.setLayout(new BoxLayout(audio_editing, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		audio_editing.add(panel_2);
		
		JLabel lblEnterYourCommentary = new JLabel("Commentary here:");
		lblEnterYourCommentary.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterYourCommentary.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblEnterYourCommentary.setFont(new Font("Tahoma", Font.PLAIN, 15));
		audio_editing.add(lblEnterYourCommentary);
		
		final JTextArea txtrCommentary = new JTextArea();
		txtrCommentary.setText("(max 40 words)");
		txtrCommentary.setLineWrap(true);
		txtrCommentary.setPreferredSize(new Dimension(270, 300));
		audio_editing.add(txtrCommentary);
		
		JPanel audio_options = new JPanel();
		audio_editing.add(audio_options);
		
		JButton btnSpeak = new JButton("Speak");
		btnSpeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//use festival to speak out what the user has inputed in text area
					
				//execute background process of festival
				String input = txtrCommentary.getText();
				BgFestival bg = new BgFestival(input);
				bg.execute();
			}
		});
		audio_options.add(btnSpeak);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//kill the process --------------------------------------------------------not working yet
				if(festID != 0) {
					String cmd = "kill "+(festID+4);
					ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
					try {
						builder.start();
						festID = 0;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		audio_options.add(btnStop);
		
		JButton btnSaveAs = new JButton("Save as MP3");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save input in textarea as .wav file and convert to .mp3 and save
			}
		});
		audio_options.add(btnSaveAs);
		
		JPanel panel = new JPanel();
		audio_editing.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnMerge = new JButton("Merge With MP3");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//merge mp3 with current video 
				//prompt user to choose mp3 file to merge with
			}
		});
		panel.add(btnMerge);
	}

}
