package mainPgk;
//T Harvey, Wyatt Achey
//based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import EOrc;

public class Animation extends JPanel {

    final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    int xloc = 0;
    int yloc = 0;
    int xVel = 8; //should be +-xIncr or 0
    int yVel = 2; //should be +-yIncr or 0
    final static int xIncr = 8; 
    final static int yIncr = 2; 
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    final static int yBuffer = 25; //improves visual collision 


    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	if(xloc < 0 || xloc > frameWidth - imgWidth) {
    		xVel *= -1;
    	}
    	if(yloc + yBuffer < 0 || yloc + yBuffer > frameHeight - imgHeight) {
    		yVel *= -1;
    	}
    	g.drawImage(pics[EOrc.selectOrc(xVel > 0, yVel > 0, xVel == 0, yVel ==0).ordinal()][picNum],
    			xloc+=xVel, yloc+=yVel, Color.gray, this);
    }

    //Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new Animation());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }

    //Constructor: get image, segment and store in array
	public Animation() {
		pics = new BufferedImage[EOrc.values().length][frameCount];
		for (EOrc orc : EOrc.values()) {
			BufferedImage img = createImage(orc);
			for (int frameNum = 0; frameNum < frameCount; frameNum++)
				pics[orc.ordinal()][frameNum] = img.getSubimage(imgWidth * frameNum, 0, imgWidth, imgHeight);
		}
	}
    
    //Read image from file and return
    private BufferedImage createImage(EOrc orc){
    	try {
    		BufferedImage bufferedImage = ImageIO.read(new File(orc.getFile()));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
}