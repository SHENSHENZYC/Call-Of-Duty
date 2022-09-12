package callOfDuty;

/**
 * Represents a sentry tower.
 * @author shenshenzyc
 *
 */
public class SentryTower extends Target {
	
	/**
	 * Preset length of sentry tower.
	 */
	static final int SENTRYTOWER_LENGTH = 1;
	
	/**
	 * Preset width of sentry tower.
	 */
	static final int SENTRYTOWER_WIDTH = 1;
	
	/**
	 * Target type.
	 */
	static final String TARGET_TYPE = "sentryTower";
	
	/**
	 * Creates a sentry tower with given map of base.
	 * @param base where sentry tower is placed in
	 */
	public SentryTower(Base base) {
		super(SENTRYTOWER_LENGTH, SENTRYTOWER_WIDTH, base);
	}
	
	/**
	 * @return the type of sentry tower as a string
	 */
	@Override
	public String getTargetName() {
		return TARGET_TYPE;
	}
	
	/**
	 * Defines how sentry tower will behave when it is destroyed. 
	 */
	@Override
	public void explode() {
		return;
	}
}
