package callOfDuty;

/**
 * Represents a missile of a weapon type.
 * @author shenshenzyc
 *
 */
public class Missile extends Weapon {
	
	/**
	 * Preset initial number of shots in a missile
	 */
	static final int INIT_SHOTCOUNT = 3;
	
	/**
	 * Weapon type
	 */
	static final String WEAPON_TYPE = "missile";
	
	/**
	 * Creates a missile.
	 */
	public Missile() {
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
	 * Describes what happens when the missile shoots at given coordinate
	 * @param row number of shot spot
	 * @param column number of shot spot
	 * @param base 
	 */
	@Override
	public void shootAt(int row, int column, Base base) {
		//missile shoots a 3*3 block centered at given coordinate
		for (int i = Math.max(0, row - 1); i <= Math.min(9, row + 1); i++) {
			for (int j = Math.max(0, column - 1); j <= Math.min(9, column + 1); j++) {
				base.shootAt(i, j);
			}
		}
		
		//uses one shot
		decrementShotLeft();
		base.incrementShotsCount();
	}
}
