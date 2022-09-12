package callOfDuty;

/**
 * Represents any target in the base, and describes characteristics common of all targets.
 * @author shenshenzyc
 *
 */
public abstract class Target {
	
	/**
	 * Coordinate of the head of a target.
	 */
	private int[] coordinate;
	
	/**
	 * Length of the target.
	 */
	private int length;
	
	/**
	 * Width of the target.
	 * Width always <= length.
	 */
	private int width;
	
	/**
	 * Indicates whether the target is horizontal or not.
	 */
	private boolean horizontal;
	
	/**
	 * Array of the same size as target, indicating the number of times a part of the target has been hit. 
	 */
	private int[][] hit;
	
	/**
	 * Base that the target is placed in.
	 */
	private Base base;
	
	/**
	 * Creates a target with given length, width and given map of base.
	 * @param length of target
	 * @param width of target
	 * @param base where target is placed in
	 */
	public Target(int length, int width, Base base) {
		this.length = length;
		this.width = width;
		this.base = base;
	}
	
	/**
	 * @return the coordinate array of target.
	 */
	public int[] getCoordinate() {
		return coordinate;
	}
	
	/**
	 * @return whether the target is horizontal or not.
	 */
	public boolean getHorizontal() {
		return horizontal;
	}
	
	/**
	 * @return the hit array of target.
	 */
	public int[][] getHit() {
		return hit;
	}
	
	/**
	 * @return the base where target is placed in.
	 */
	public Base getBase() {
		return base;
	}
	
	/**
	 * @return length of target.
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @return width of target.
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Sets the coordinate array of target.
	 * @param coordinate to set
	 */
	public void setCoordinate(int[] coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Sets the value of horizontal of target.
	 * @param horizontal value to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Sets the hit array of target.
	 * @param hit array to set
	 */
	public void setHit(int[][] hit) {
		this.hit = hit;
	}
	
	/**
	 * Defines the behavior when the target is destroyed.
	 */
	public abstract void explode();
	
	/**
	 * @return the type of target as a string.
	 */
	public abstract String getTargetName();
	
	/**
	 * If a part of the Target occupies the given row and column and it is not destroyed, 
	 * mark that part of the Target as hit.
	 * @param row number of hit spot
	 * @param column number of hit spot
	 */
	public void getShot(int row, int column) {
		
		//checks if target is destroyed
		if (isDestroyed()) {
			return;
		}
		
		//gets the hit spot coordinates relative to head of target
		int rowRel = row - coordinate[0];
		int colRel = column - coordinate[1];
		
		/*
		 * Checks if the hit spot is covered by target.
		 * First split the cases based on whether target is placed horizontally.
		 */
		if (horizontal) {
			if ((rowRel <= width - 1) && (colRel <= length - 1)) {
				//increases this part of hit array by 1
				hit[rowRel][colRel] += 1;
			}
		} else {
			if ((rowRel <= length - 1) && (colRel <= width - 1)) {
				//increases this part of hit array by 1
				hit[rowRel][colRel] += 1;
			}
		}
		
		//check again if target is destroyed after updating hit array
		if (isDestroyed()) {
			//prints message if target gets destroyed
			System.out.println("You have destroyed a " + this.getTargetName());
			
			explode();
			
			//increase destroyed target count by 1
			this.base.setDestroyedTargetCount(this.base.getDestroyedTargetCount() + 1);
		}
		
	}
	
	/**
	 * @return true if every part of target has been hit, false otherwise
	 */
	public boolean isDestroyed() {
		
		//sets default return value as false
		boolean isDestroyed = true;
		
		for (int[] row : hit) {
			for (int hitValue : row) {
				//if any part of target has not been hit, the target is not destroyed
				if (hitValue == 0) {
					isDestroyed = false;
				}
			}
		}
		
		return isDestroyed;
		
	}
	
	/**
	 * @param row number of hit spot
	 * @param column number of hit spot
	 * @return true if target has been hit at the given coordinate
	 */
	public boolean isHitAt(int row, int column) {
		
		//gets the hit spot coordinates relative to head of target
		int rowRel = row - coordinate[0];
		int colRel = column - coordinate[1];
		
		return (hit[rowRel][colRel] >= 1);
	}
	
	/**
	 * @return a single-character String to use in the Base’s print method
	 */
	@Override
	public String toString() {
		
		//returns 'X' if target has been destroyed
		if (this.isDestroyed()) {
			return "X";
		//returns 'O' if target has not been destroyed
		} else {
			return "O";
		}
	}
	
	/**
	 * @return true if given target is a building, false otherwise
	 */
	boolean isBuilding() {
		
		//gets type of target
		String targetName = getTargetName().toLowerCase();
		
		return ((targetName.equals("headquarter")) || (targetName.equals("armory"))
				|| (targetName.equals("barrack")) || (targetName.equals("sentrytower")));
	}
}
