package callOfDuty;

/**
 * Represents a tank.
 * @author shenshenzyc
 *
 */
public class Tank extends Target {
	
	/**
	 * Preset length of tank.
	 */
	static final int TANK_LENGTH = 1;
	
	/**
	 * Preset width of tank.
	 */
	static final int TANK_WIDTH = 1;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "tank";
	
	/**
	 * Creates a tank with given map of base.
	 * @param base where tank is placed in
	 */
	public Tank(Base base) {
		super(TANK_LENGTH, TANK_WIDTH, base);
	}
	
	/**
	 * @return the type of tank as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how tank will behave when it is destroyed.
	 * A tank will explode when destroyed.
	 */
	@Override
	public void explode() {
		
		//gets exploding area
		int rowStart = Math.max(this.getCoordinate()[0] - 2, 0);
		int rowEnd = Math.min(this.getCoordinate()[0] + 2, 9);
		int colStart = Math.max(this.getCoordinate()[1] - 2, 0);
		int colEnd = Math.min(this.getCoordinate()[1] + 2, 9);
		
		for (int i = rowStart; i <= rowEnd; i++) {
			for (int j = colStart; j <= colEnd; j++) {
				Target target = this.getBase().getTargetsArray()[i][j];
				
				//avoid double-counting the center of explosion
				if (!this.equals(target)) {
					target.getShot(i, j);
				}
			}
		}
	}
	
	/**
	 * @return true if every part of tank is hit at least twice
	 */
	@Override
	public boolean isDestroyed() {
		
		//sets default return value as false
		boolean isDestroyed = true;
				
		if (this.getHit()[0][0] < 2) {
			isDestroyed = false;
		}
				
		return isDestroyed;
	}
	
	/**
	 * @return a single-character String to use in the Base’s print method
	 */
	@Override
	public String toString() {
		
		//returns 'X' if a tank has been destroyed
		if (this.isDestroyed()) {
			return "X";
		} else {
			return "T";
		}
	}
}
