package callOfDuty;

/**
 * Represents a barrack.
 * @author shenshenzyc
 *
 */
public class Barrack extends Target {
	
	/**
	 * Preset length of barrack.
	 */
	static final int BARRACK_LENGTH = 3;
	
	/**
	 * Preset width of barrack.
	 */
	static final int BARRACK_WIDTH = 1;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "barrack";
	
	/**
	 * Creates a barrack with given map of base.
	 * @param base where barrack is placed in
	 */
	public Barrack(Base base) {
		super(BARRACK_LENGTH, BARRACK_WIDTH, base);
	}
	
	/**
	 * @return the type of barrack as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how barrack will behave when it is destroyed.
	 */
	@Override
	public void explode() {
		return;
	}
}
