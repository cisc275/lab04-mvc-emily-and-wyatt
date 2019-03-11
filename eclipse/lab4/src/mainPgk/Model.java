package mainPgk;
/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	int xloc = 0;
	int yloc = 0;
	int xVel;
	int yVel;
	Direction dir;
	int width;
	int height;
	int imgWidth;
	int imgHeight;
	final static int yBuffer = 25; //improves visual collision 

	public Model(int w, int h, int iw, int ih) {
		width = w;
		height = h;
		imgWidth = iw;
		imgHeight = ih;
		xVel = 8;
		yVel = 2;
		dir = Direction.SOUTHEAST;
	}

	public void updateLocationAndDirection() {
		if(xloc < 0 || xloc > width - imgWidth) {
			xVel *= -1;
		}
		if(yloc + yBuffer < 0 || yloc + yBuffer > height - imgHeight) {
			yVel *= -1;
		}
		updateDirection();
		xloc += xVel;
		yloc += yVel;
	}
	
	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	public Direction getDirect() {
		return dir;
	}

	public void updateDirection() {
		if(xVel==0) {
			if(yVel>0) {
				dir= Direction.NORTH;
			} else {
				dir = Direction.SOUTH;
			}
		}else if(xVel>0) {
			if(yVel>0) {
				dir= Direction.SOUTHEAST;
			} else if(yVel<0){
				dir = Direction.NORTHEAST;
			} else {
				dir = Direction.EAST;
			}
		}else {
			if(yVel>0) {
				dir= Direction.SOUTHWEST;
			} else if(yVel<0){
				dir = Direction.NORTHWEST;
			} else {
				dir = Direction.WEST;
			}
		}
	}

}