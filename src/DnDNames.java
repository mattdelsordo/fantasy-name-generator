/*
 * Matt DelSordo
 * Compiles fantasy names for your fantasy characters.
 * 2/23/1
 */

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DnDNames extends JFrame{

	JButton button;
	BufferedImage icon;
	Random rand;
	
	ArrayList<String> fantasyName, firstName, surname;
	
	public DnDNames(){
		this.setTitle("Fantasy Name Generator");
		//this.setIconImage(imgButton.getImage()); TODO: add this
		this.setSize(550, 110); 
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		//add elements
		try //get image for button
		{
			icon = ImageIO.read(getClass().getResourceAsStream("sword.png"));
		}
		catch(Exception e)
		{
			System.out.println("Error reading image.");
			System.exit(1);
		}
		this.setIconImage(icon);
		
		
		//initialize random number generator
		rand = new Random();
		
		//fill arrays
		fantasyName = fillArray("namesGenUnique.txt");
		firstName = fillArray("firstnames.txt");
		surname = fillArray("surnames.txt");

		button = new JButton(getName());
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				onClick();
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 40));
		add(button);
		getRootPane().setDefaultButton(button);
		
		onClick();
		//pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//gets a random word from a set of names
	public String getRand(ArrayList<String> list){
		return list.get(rand.nextInt(list.size()));
	}
	
	//generates a random name based on several models
	public String getName(){
		int selection = rand.nextInt(5);
		//list all cases
		
		//fantasy fantasy
		if(selection == 0) return getRand(fantasyName) + " " + getRand(fantasyName);
		//fantasy surname
		else if(selection == 1) return getRand(fantasyName) + " " + getRand(surname);
		//first fantasy
		else if(selection == 2) return getRand(firstName) + " " + getRand(fantasyName);
		//fantasy fantasy surname
		else if(selection == 3) return getRand(fantasyName) + " " + getRand(fantasyName) + " " + getRand(surname);
		//just fantasy
		else return getRand(fantasyName);
	}
	
	//fills an arraylist with content from a text file
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
				//System.out.println(source + " " + line);
				//String[] split = line.split(" ");
				//if(split.length == 1) list.add(split[0]);
				if(!line.equals("") && !line.substring(1).equals("#")) list.add(line);
			}
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
	
	//what happens on button click
	public void onClick(){
		String name = getName();
		button.setText(name);
		System.out.println(name);
	}

	
	public static void main(String[] args){
		new DnDNames();
	}
}
