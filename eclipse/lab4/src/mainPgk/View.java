import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View{
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;
    final static int xIncr = 8; 
    final static int yIncr = 2; 
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    

	//Constructor: get image, segment and store in array
	public View() {
		pics = new BufferedImage[EOrc.values().length][frameCount];
		for (EOrc orc : EOrc.values()) {
			BufferedImage img = createImage(orc);
			for (int frameNum = 0; frameNum < frameCount; frameNum++)
				pics[orc.ordinal()][frameNum] = img.getSubimage(imgWidth * frameNum, 0, imgWidth, imgHeight);
		}
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
		int xV;
		int yV;
		picNum = (picNum + 1) % frameCount;
    	g.drawImage(pics[EOrc.selectOrc(xV > 0, yV > 0, xV == 0, yV ==0).ordinal()][picNum],
    			x+=xVel, y+=yVel, Color.gray, this);
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