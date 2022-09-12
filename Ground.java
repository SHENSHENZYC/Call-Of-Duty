package callOfDuty;

/**
 * Represents a ground target.
 * @author shenshenzyc
 *
 */
public class Ground extends Target {
	
	/**
	 * Target type
	 */
	static final String TARGET_TYPE = "ground";
	
	/**
	 * Creates ground to represent absence of target.
	 * @param base where ground is placed in
	 */
	public Ground(Base base) {
		super(1, 1, base);
	}
	
	/**
	 * @return the type of ground as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how ground will behave when it is hit.
	 */
	@Override
	public void explode() {
		return;
	}
	
	/**
	 * Always returns false as nothing is actually destroyed when a ground is hit.
	 */
	@Override
	public boolean isDestroyed() {
		return false;
	}
	
	/**
	 * Returns "-" to represent a ground has been hit.
	 */
	@Override
	public String toString() {
		return "-";
	}
}
