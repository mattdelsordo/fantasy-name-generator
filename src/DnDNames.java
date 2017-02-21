/*
 * Matt DelSordo
 * generates spooky names for tessa
 * 9/25/16
 */

import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class DnDNames extends JFrame implements ActionListener{

	JButton button;
//	BufferedImage pump;
	Random rand;
	
	ArrayList<String> names;
	
	public DnDNames(){
		this.setTitle("Fantasy Name Generator");
		//this.setIconImage(imgButton.getImage()); TODO: add this
		this.setSize(325, 75); 
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		//add elements
//		try //get image for button
//		{
//			pump = ImageIO.read(getClass().getResourceAsStream("pump.png"));
//		}
//		catch(Exception e)
//		{
//			System.out.println("Error reading image.");
//			System.exit(1);
//		}
//		this.setIconImage(pump);
		
		
		//initialize random number generator
		rand = new Random();
		
		//fill arrays
		names = fillArray("namesGenUnique.txt");

		button = new JButton(getName());
		button.addActionListener(this);
		add(button);
		getRootPane().setDefaultButton(button);
		
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
		

	
	public void actionPerformed(ActionEvent ae)
	{
		if (button == ae.getSource())
		{
			button.setText(getName());
		}
	}
	
	public String getName(){
		
		String randName = names.get(rand.nextInt(names.size())) + " " + names.get(rand.nextInt(names.size()));
		return randName;
	}
	
	public ArrayList<String> fillArray(String source){
		//get text
		String line = null;
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			InputStream file = getClass().getResourceAsStream(source);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
			
			while((line = buffer.readLine()) != null)
			{
				String[] split = line.split(" ");
				//System.out.print(line);
				for(String s : split) System.out.print(s + " ");
				System.out.println();
				if(split.length ==1) list.add(split[0]);
			}
			
			//dictionary.print();
			//System.out.println("Dictionary loaded.");
			buffer.close();	
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Cannot find file " + source);
			System.exit(0);
		}
		catch(IOException e)
		{
			System.out.println("Error reading " + source);
			System.exit(0);
		}
		
		return list;

	}

	
	public static void main(String[] args){
		Random randOH = new Random();
		int number = randOH.nextInt(1000);
		if(number == 420) new REPENT();
		else new DnDNames();
	}
}
