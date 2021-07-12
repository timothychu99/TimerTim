package application;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class musicPlayer { //plays the sounds

	//to track and enable clip func
	Clip clip;
	int fileNumber;   
	AudioInputStream audioInputStream;
	
	//sound URLs
	URL sound;
	public static String URL1 = "/PikaThunder.wav";
	public static String URL2 = "/PokeSound.wav";
  
 	// constructor to initialize streams and clip
	public musicPlayer(int fileNum)
	     throws UnsupportedAudioFileException,
	     IOException, LineUnavailableException 
	{
	
		 fileNumber = fileNum;	 
	
		 if(fileNumber == 1) sound = getClass().getResource(URL1);
		 else if(fileNumber == 2) sound = getClass().getResource(URL2);
		 else sound = getClass().getResource(URL1);
		 
		 // create AudioInputStream object	 
		 audioInputStream = AudioSystem.getAudioInputStream(sound);
		 
		 // create clip reference
	     clip = AudioSystem.getClip();
	       
	     // open audioInputStream to the clip
	     clip.open(audioInputStream);
	
	}
 
 	//sets repeats and plays clip
	public void play(int repeats) {

		try
	    {	 
			musicPlayer au = new musicPlayer(fileNumber);			
	        au.clip.start();
	        au.clip.loop(repeats-1);
	    } 
	      
	    catch (Exception ex) 
	    {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	      }
	}
}