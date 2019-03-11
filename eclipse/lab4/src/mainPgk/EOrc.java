package mainPgk;

public enum EOrc {
	north ("orc_animation/orc_forward_north.png"), 
	northEast ("orc_animation/orc_forward_northeast.png"), 
	east ("orc_animation/orc_forward_east.png"), 
	southEast ("orc_animation/orc_forward_southeast.png"),  
	south ("orc_animation/orc_forward_south.png"), 
	southWest ("orc_animation/orc_forward_southwest.png"),  
	west ("orc_animation/orc_forward_west.png"), 
	northWest ("orc_animation/orc_forward_northwest.png");
	
	private final String file;
	
	EOrc(String file) {
		this.file = file;
	}
	public String getFile() {
		return file;
	}
	
//	//returns a diagonal moving EOrc
//	public static EOrc selectOrc(boolean right, boolean down) {
//		if(right) {
//			if(down) return southEast;
//			return northEast;
//		}
//		if(down) return southWest;
//		return northWest;
//	}
//	//returns an EOrc of the correct orientation
//	public static EOrc selectOrc(boolean right, boolean down, boolean noX, boolean noY) {
//		if(!noX && !noY) return selectOrc(right, down);
//		if(noX) {
//			if(down) return south;
//			return north;
//		}
//		if(right) return east;
//		return west;
//	}
}
