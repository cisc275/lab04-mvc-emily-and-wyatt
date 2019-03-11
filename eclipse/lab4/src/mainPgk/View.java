package mainPgk;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

@SuppressWarnings("serial")
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    EOrc currentOrc;
    int xLoc;
    int yLoc;
    static JFrame frame;
    final static int xIncr = 8; 
    final static int yIncr = 2; 
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

	//Constructor: get image, segment and store in array
	public View() { 
		//load imgs
		pics = new BufferedImage[EOrc.values().length][frameCount];
		for (EOrc orc : EOrc.values()) {
			BufferedImage img = createImage(orc);
			for (int frameNum = 0; frameNum < frameCount; frameNum++)
				pics[orc.ordinal()][frameNum] = img.getSubimage(imgWidth * frameNum, 0, imgWidth, imgHeight);
		}
		//make frame
		frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
	}

	public int getWidth(){
		return frameWidth;
	}
	public int getHeight(){
		return frameHeight;
	}
	public int getImageWidth(){
		return imgWidth;
	}
	public int getImageHeight(){
		return imgHeight;
	}

	public void update(int x, int y, Direction dir){
		xLoc = x;
		yLoc = y;
		picNum = (picNum + 1) % frameCount;
		//Direction -> EOrc
		//I would rather this be inside one the the enums or just have a single enum, but apparently the code for it has to be 
		//here so let's go with this for now
		switch(dir) {
		case NORTH: currentOrc = EOrc.north; break;
		case NORTHEAST: currentOrc = EOrc.northEast; break;
		case EAST: currentOrc = EOrc.east; break;
		case SOUTHEAST: currentOrc = EOrc.southEast; break;
		case SOUTH: currentOrc = EOrc.south; break;
		case SOUTHWEST: currentOrc = EOrc.southWest; break;
		case WEST: currentOrc = EOrc.west; break;
		case NORTHWEST: currentOrc = EOrc.northWest; break;
		}
		
		try {
			frame.repaint();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
	
	public void paint(Graphics g) {
		g.drawImage(pics[currentOrc.ordinal()][picNum], 
				xLoc, yLoc, Color.gray, this);
    }
}