package callOfDuty;

/**
 * Represents a weapon used by player.
 * @author shenshenzyc
 *
 */
public abstract class Weapon {
	
	/**
	 * Number of shots left of the weapon.
	 */
	private int shotleft;
	
	/**
	 * Creates a weapon with given number of initial shots.
	 * @param shotCount is number of initial shots
	 */
	public Weapon(int shotCount) {
		this.shotleft = shotCount;
	}
	
	/**
	 * @return number of shots left of the weapon
	 */
	public int getShotLeft() {
		return shotleft;
	}
	
	/**
	 * @return the type of weapon as a string
	 */
	public abstract String getWeaponType();
	
	/**
	 * Describes what happens when the weapon shoots at given coordinate
	 * @param row number of shot spot
	 * @param column number of shot spot
	 * @param base of the game
	 */
	public abstract void shootAt(int row, int column, Base base);
	
	/**
	 * Decreases the number of shots left of the weapon by 1.
	 */
	public void decrementShotLeft() {
		shotleft--;
	}
}
