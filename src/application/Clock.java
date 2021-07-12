package application;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Clock {
	
	//initiates timeline for running timer
	Timeline timeline;
	
	//times for 35 min and 10 min
	int time = 35*60;
	int resetTime = 10*60;
	
	//randomizes movement speed of pikachu (img)
	double moveX = 2, moveY = .75;
	boolean moveImg = false;
	
	//manage timeline's count, time, and the player
	int count = 0;
	double totalTime;
	boolean reset = true;
	
    @FXML
    private Button timerButton;

    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private ImageView image;
    
    @FXML
    void handle(ActionEvent event) { //resets timer to 35 min and its colors
    	
    	time = 35*60;
    	totalTime = time;
    	reset = true;
    	moveImg = true;
    	
		progressBar.setStyle("-fx-accent: springgreen");
		timerButton.setStyle("-fx-text-fill: darkgreen; -fx-border-color: darkgreen; -fx-border-width: 2.5; -fx-background-color: transparent;");
		image.setX(55.0);
		image.setY(20.0);
		
		setImg();
		ClockTime();
    }
    
    
    public void ClockTime() {
    	
    	//resets timeline when still running
    	if (timeline != null) {
            timeline.stop();
            count = 0;
        }
    	
    	//timer clock
    	timeline = new Timeline(
     		    new KeyFrame(Duration.seconds(.2), e ->{
     		    	
     		    	//updates countdown timer on app
		    		if(count%5 == 0) {
     		    		
		    			//switches totalTime var
		    			if(reset == true) totalTime = 35*60;
	     		    	else totalTime = 10*60;
	     		    		
		    				//sets progress bar and updates time label
	     		    		progressBar.setProgress(1 - (double)(time/totalTime));
	     		    		timerButton.setText(timeConversion(time));
	     		    		time--;	     		    		
	     		    	
	     		    		//switch colors to rest period and recursively calls this func
	     		    		if(time == -1 && reset == true) {
	     		    			timeline.setCycleCount(resetTime*5);
	     		    			time = resetTime;
	     		    			reset = false;
	     		    			progressBar.setStyle("-fx-accent: red");
	     		    			timerButton.setStyle("-fx-text-fill: darkred; -fx-border-color: darkred; -fx-border-width: 2.5; -fx-background-color: transparent;");
	     		    			ClockTime();
	     		    		}
     		    		}
		    		
		    		//updates sound during reset of every 2 min
		    		if(count%(5*120) == 0 && reset == false) {
	     		   		
		    			try {	     		   		
	     		   			
		    				//switches for which sound to play
	     		   			if(time == 600 || time == 0) play(1,1);
	     		   			else play(2,(600-time)/(120));
	     		   
	     		   		} catch (UnsupportedAudioFileException e1) {
	     		   			// TODO Auto-generated catch block
	     		   			e1.printStackTrace();
	     		   		} catch (IOException e1) {
	     		   			// TODO Auto-generated catch block
	     		   			e1.printStackTrace();
	     		   		} catch (LineUnavailableException e1) {
	     		   			// TODO Auto-generated catch block
	     		   			e1.printStackTrace();
	     		   		}
	     		   	}
		    		
		    		if(count%(60*5) == 0) moveImg = true; //move img every min
		    		
		    		//parameters to move img
		    		if(moveImg == true) {
			    		
			    		//move y dir of img
			    		image.setX(image.getX()-moveX);
			    		
			    		//move y dir of img
			    		if(image.getX() > -20) image.setY(image.getY()-moveY);
			    		else image.setY(image.getY()+moveY);
		    		
			    		//exit screen = stop img from moving
			    		if(image.getX() < -120) {
			    			image.setX(55);
			    			image.setY(20);
			    			moveX = Math.random()*.5 + 1.75;
			    			moveY = Math.random()*.5 + .5;
			    			moveImg = false;
			    		}
		    		}	
		    		
     		    	count ++; //enables timers to work
        		    })
        		);
		timeline.setCycleCount((time+1)*5); //adds 5 in order to show reset btn
		timeline.play(); //plays timeline
    }
    
    //run music by calling musicPlayer
    public void play(int fileNum, int repeats) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    		musicPlayer mu = new musicPlayer(fileNum);
    		mu.play(repeats);
    }
    
    //convert time to String
    public String timeConversion(int time) {
    	String i;
    	if(time%60 < 10) { 
    		i = time/60 + ":0" + time%60;
    	}else {
    		i = time/60 + ":" + time%60;
    	}
    	if(time == 0) i = "RE:ST";
    	return i;
    }
    
    //displays img for JAR purposes
    public void setImg() {
    	Image image1 = new Image(getClass().getClassLoader().getResource("pik.gif").toExternalForm());
    	image.setImage(image1);
    }
}
