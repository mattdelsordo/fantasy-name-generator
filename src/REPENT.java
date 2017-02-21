/*
 * god has come to reap the sinners
 */


import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class REPENT extends JWindow{
	
	ImageIcon repent;
	BufferedImage flamicon;
	Random rand;
	JLabel label;
	Synthesizer synth;
	MidiChannel[] channels;
	
	int screenWidth, screenHeight;
	
	public REPENT(){
		//this.setTitle("");
		//this.setIconImage(imgButton.getImage()); TODO: add this
		this.setSize(440, 300); 
		this.setLayout(new FlowLayout());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add elements
		try //get image for button
		{
			repent = new ImageIcon(getClass().getResource("repent.gif"));
			flamicon = ImageIO.read(getClass().getResourceAsStream("flamicon.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error reading image.");
			System.exit(1);
		}
		this.setIconImage(flamicon);
		
		//music
		rand = new Random();
		try{
			synth = MidiSystem.getSynthesizer();
			channels = synth.getChannels();
		}
		catch(Exception e){
			System.out.println("Error initializing synthesizer.");
			System.exit(0);
		}
		
		label = new JLabel(repent);
		add(label);
		
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		music();
	}
	
	
	public void music(){
		try{
			synth.open();
			while(true)
			{
				//int volume = 90;
				//System.out.println(p.pitch);
				channels[rand.nextInt(channels.length)].noteOn(rand.nextInt(128), 127);
				//System.out.println(p.duration);
				Thread.sleep((1000*(rand.nextInt(4))));
				
				setLocation(rand.nextInt(screenWidth), rand.nextInt(screenHeight));
			}
			//synth.close();
		}
		catch(Exception e)
		{
			System.out.println("Error playing song " + e);		
		}
		
	}
	
//	public static void main(String agrs[]){
//		new REPENT();
//	}
}
