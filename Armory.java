package callOfDuty;

/**
 * Represents an armory.
 * @author shenshenzyc
 *
 */
public class Armory extends Target {
	
	/**
	 * Preset length of armory.
	 */
	static final int ARMORY_LENGTH = 3;
	
	/**
	 * Preset width of armory.
	 */
	static final int ARMORY_WIDTH = 2;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "armory";
	
	/**
	 * Creates an armory with given map of base.
	 * @param base where armory is placed in
	 */
	public Armory(Base base) {
		super(ARMORY_LENGTH, ARMORY_WIDTH, base);
	}
	
	/**
	 * @return the type of armory as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how armory will behave when it is destroyed.
	 * An armory will explode when destroyed.
	 */
	@Override
	public void explode() {
		
		//gets exploding area
		int rowStart, rowEnd, colStart, colEnd;
		
		if (this.getHorizontal()) {
			rowStart = Math.max(this.getCoordinate()[0] - 2, 0);
			rowEnd = Math.min(this.getCoordinate()[0] + 3, 9);
			colStart = Math.max(this.getCoordinate()[1] - 2, 0);
			colEnd = Math.min(this.getCoordinate()[1] + 4, 9);
		} else {
			rowStart = Math.max(this.getCoordinate()[0] - 2, 0);
			rowEnd = Math.min(this.getCoordinate()[0] + 4, 9);
			colStart = Math.max(this.getCoordinate()[1] - 2, 0);
			colEnd = Math.min(this.getCoordinate()[1] + 3, 9);
		}
				
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
}
