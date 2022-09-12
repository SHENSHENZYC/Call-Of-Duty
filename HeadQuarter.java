package callOfDuty;

/**
 * Represents a headquarter.
 * @author shenshenzyc
 *
 */
public class HeadQuarter extends Target {
	
	/**
	 * Preset length of headquarter.
	 */
	static final int HEADQUARTER_LENGTH = 6;
	
	/**
	 * Preset width of headquarter.
	 */
	static final int HEADQUARTER_WIDTH = 1;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "headQuarter";
	
	/**
	 * Creates a headquarter with given map of base.
	 * @param base where headquarter is placed in
	 */
	public HeadQuarter(Base base) {
		super(HEADQUARTER_LENGTH, HEADQUARTER_WIDTH, base);
	}
	
	/**
	 * @return the type of headquarter as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how headquarter will behave when it is destroyed.
	 */
	@Override
	public void explode() {
		return;
	}
}
