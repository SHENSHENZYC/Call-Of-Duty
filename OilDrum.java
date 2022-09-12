package callOfDuty;

/**
 * Represents an oil drum.
 * @author shenshenzyc
 *
 */
public class OilDrum extends Target {
	
	/**
	 * Preset length of oil drum.
	 */
	static final int OILDRUM_LENGTH = 1;
	
	/**
	 * Preset width of oil drum.
	 */
	static final int OILDRUM_WIDTH = 1;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "oilDrum";
	
	/**
	 * Creates an oil drum with given map of base.
	 * @param base where oil drum is placed in
	 */
	public OilDrum(Base base) {
		super(OILDRUM_LENGTH, OILDRUM_WIDTH, base);
	}
	
	/**
	 * @return the type of oil drum as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how oil drum will behave when it is destroyed.
	 * An oil drum will explode when destroyed.
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
}
