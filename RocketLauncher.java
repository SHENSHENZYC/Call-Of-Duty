package callOfDuty;

/**
 * Represents a rocket launcher of a weapon type.
 * @author shenshenzyc
 *
 */
public class RocketLauncher extends Weapon {
	
	/**
	 * Preset initial number of shots in a rocket launcher
	 */
	static final int INIT_SHOTCOUNT = 20;
	
	/**
	 * Weapon type
	 */
	static final String WEAPON_TYPE = "rocketLauncher";
	
	/**
	 * Creates a rocket launcher.
	 */
	public RocketLauncher() {
		super(INIT_SHOTCOUNT);
	}
	
	/**
	 * @return the weapon type of rocket launcher as a string
	 */
	@Override
	public String getWeaponType() {
		return WEAPON_TYPE;
	}
	
	/**
	 * Describes what happens when the rocket launcher shoots at given coordinate
	 * @param row number of shot spot
	 * @param column number of shot spot
	 * @param base 
	 */
	@Override
	public void shootAt(int row, int column, Base base) {
		//rocket launcher shoots only at given coordinate
		base.shootAt(row, column);
		
		//uses one shot
		decrementShotLeft();
		base.incrementShotsCount();
	}
	
}
