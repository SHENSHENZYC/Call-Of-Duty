package callOfDuty;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a base map where all targets are placed.
 * @author shenshenzyc
 *
 */
public class Base {
	
	/**
	 * Array that keeps record of the location of every target in base
	 */
	private Target[][] targets;
	
	/**
	 * Total number of shots fired by user
	 */
	private int shotsCount;
	
	/**
	 * Number of target destroyed by user
	 */
	private int destroyedTargetCount;
	
	/**
	 * Number of target placed in base during construction
	 */
	private int placedTargetCount;
	
	/**
	 * Creates a base.
	 */
	public Base() {
		//initializes the base filled with Ground objects in every spot
		targets = new Target[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Target ground = new Ground(this);
				ground.setCoordinate(new int[] {i, j});
				ground.setHorizontal(true);
				ground.setHit(new int[1][1]);
				
				targets[i][j] = ground;
			}
		}
		
		//initializes value of instance variables
		shotsCount = 0;
		destroyedTargetCount = 0;
		placedTargetCount = 0;
	}
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is okay 
	 * to put the Target with its head at this location; false otherwise. 
	 * The buildings must not overlap another Target, or touch another building (vertically, horizontally, or diagonally).
	 * And targets must not ”stick out” beyond the base.
	 * @param target to put in base	
	 * @param row number of location to put at
	 * @param column number of location to put at
	 * @param horizontal is given orientation of target
	 * @return whether valid to put given target at given location
	 */
	public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
		
		//gets dimension value of target
		int length = target.getLength();
		int width = target.getWidth();
		
		//checks whether the target sticks out beyond the base
		if (horizontal) {
			if ((row + width - 1 > 9) || (column + length - 1 > 9)) {
				return false;
			}
		} else {
			if ((row + length - 1 > 9) || (column + width - 1 > 9)) {
				return false;
			}
		}
		
		//in the following code block, it is assumed that the entire target is inside base
		
		//gets the row number and column number of four boundaries of target
		int rowStart = row;
		int rowEnd;
		int colStart = column;
		int colEnd;
		
		if (horizontal) {
			rowEnd = row + width - 1;
			colEnd = column + length - 1;
		} else {
			rowEnd = row + length - 1;
			colEnd = column + width - 1;
		}
		
		//checks whether the target overlaps another target
		for (int i = rowStart; i <= rowEnd ; i++) {
			for (int j = colStart; j <= colEnd; j++) {
				if (isOccupied(i, j)) {
					return false;
				}
			}
		}
		
		//now checks if the target touches a building, given that the target itself is a building
		if (target.isBuilding()) {
			//scan the locations that the target touches
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
					if (((i == rowStart - 1) && (j >= colStart - 1) && (j <= colEnd + 1))
						|| ((i == rowEnd + 1) && (j >= colStart - 1) && (j <= colEnd + 1))
						|| ((j == colStart - 1) && (i >= rowStart - 1) && (i <= rowEnd + 1))
						|| ((j == colEnd + 1) && (i >= rowStart - 1) && (i <= rowEnd + 1))) {
						if (targets[i][j].isBuilding()) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public void placeTargetAt(Target target, int row, int column, boolean horizontal) {
		
		//gets dimension value of target
		int length = target.getLength();
		int width = target.getWidth();
		
		//sets coordinate array, and horizontal boolean value of the target
		target.setCoordinate(new int[] {row, column});
		target.setHorizontal(horizontal);
		
		//sets hit array of the target
		if (horizontal) {
			target.setHit(new int[width][length]);
		} else {
			target.setHit(new int[length][width]);
		}
		
		//put the target in the base
		if (horizontal) {
			for (int i = row; i < row + width; i++) {
				for (int j = column; j < column + length; j++) {
					targets[i][j] = target;
				}
			}
		} else {
			for (int i = row; i < row + length; i++) {
				for (int j = column; j < column + width; j++) {
					targets[i][j] = target;
				}
			}
		}
	}
	
	/**
	 * Create and place all targets randomly on the base: 
	 * 1 headquarter, 2 armory, 3 barracks, 4 sentry towers, 4 tanks, 4 oil drums
	 */
	public void placeAllTargetRandomly() {
		
		//creates instances for 1 headquarter, 2 armory, 3 barracks, 4 sentry towers,
		//4 tanks and 4 oil drums
		Target hq = new HeadQuarter(this);
		Target armory1 = new Armory(this);
		Target armory2 = new Armory(this);
		Target barrack1 = new Barrack(this);
		Target barrack2 = new Barrack(this);
		Target barrack3 = new Barrack(this);
		Target st1 = new SentryTower(this);
		Target st2 = new SentryTower(this);
		Target st3 = new SentryTower(this);
		Target st4 = new SentryTower(this);
		Target tank1 = new Tank(this);
		Target tank2 = new Tank(this);
		Target tank3 = new Tank(this);
		Target tank4 = new Tank(this);
		Target od1 = new OilDrum(this);
		Target od2 = new OilDrum(this);
		Target od3 = new OilDrum(this);
		Target od4 = new OilDrum(this);
		
		//creates an Array that stores all targets to be placed in base
		Target[] targetList = {hq, armory1, armory2, barrack1, barrack2, barrack3, st1, st2, st3, st4,
				tank1, tank2, tank3, tank4, od1, od2, od3, od4};
		
		//creates a random number generator
		Random rng = new Random();
		
		//iterate over targetList to find valid coordinates for every target to be placed in base
		for (Target target : targetList) {
			
			//creates an ArrayList to store all valid coordinates for this target
			ArrayList<int[]> validCoordinates = new ArrayList<int[]>();
			//stores orientation for each valid coordinate, should be same size as validCoordinates
			ArrayList<Boolean> isHorizontal = new ArrayList<Boolean>();
			
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
					boolean horizontal = rng.nextBoolean();
					if (okToPlaceTargetAt(target, i, j, horizontal)) {
						validCoordinates.add(new int[] {i, j});
						isHorizontal.add(horizontal);
					}
				}
			}
			
			//checks if there are no valid coordinates
			if (validCoordinates.size() == 0) {
				continue;
			}
			
			//generates a random index for ArrayList validCoordinates
			int randomIndex = rng.nextInt(validCoordinates.size());
			
			//sets coordinates and orientation to this target
			target.setCoordinate(validCoordinates.get(randomIndex));
			target.setHorizontal(isHorizontal.get(randomIndex));
			
			if (target.getHorizontal()) {
				target.setHit(new int[target.getWidth()][target.getLength()]);
			} else {
				target.setHit(new int[target.getLength()][target.getWidth()]);
			}
			
			//place this target in base
			placeTargetAt(target, target.getCoordinate()[0], target.getCoordinate()[1], target.getHorizontal());
			
			//increases placedTargetCount by 1
			placedTargetCount++;
		}
	}
	
	/**
	 * @param row number of location
	 * @param column number of location
	 * @return true if the given location contains a target (not a Ground), false if it does not.
	 */
	public boolean isOccupied(int row, int column) {
		return (!targets[row][column].getTargetName().toLowerCase().equals("ground"));
	}
	
	/**
	 * Attack the position specified by the row and the column.
	 * @param row number of location to shoot at
	 * @param column number of location to shoot at
	 */
	public void shootAt(int row, int column) {
		//target at given coordinate gets hit
		targets[row][column].getShot(row, column);
	}
	
	/**
	 * @param weapon1 used by player
	 * @param weapon2 used by player
	 * @return true if run out of ammunition or if all targets have been destroyed, false otherwise
	 */
	public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
		
		//checks whether player is running out of ammunition
		boolean outOfAmmo = ((weapon1.getShotLeft() == 0) && (weapon2.getShotLeft() == 0));
		
		//checks whether player has destroyed all targets in base
		boolean allTargetsDestroyed = (destroyedTargetCount == 18);
	
		//return true if one of the above is true, false otherwise
		return (outOfAmmo || allTargetsDestroyed);
	}
	
	/**
	 * @return true if all targets have been destroyed, false otherwise
	 */
	public boolean win() {
		return (destroyedTargetCount == 18);
	}
	
	/**
	 * Prints the present status of the base.
	 */
	public void print() {
		
		//prints the first row (all column indices)
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		
		//keeps track of row index, printed at the beginning of each row
		int rowCount = 0;
		
		for (int i = 0; i <= 9; i++) {
			System.out.print(rowCount + " ");
			
			for (int j = 0; j <= 9; j++) {
				Target target = targets[i][j];
				
				if (target.isHitAt(i, j)) {
					System.out.print(target + " ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
			
			rowCount++;
		}
	}
	
	/**
	 * Prints the initial construction of the base
	 */
	public void helpPrint() {
		
		//prints the first row (all column indices)
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
				
		//keeps track of row index, printed at the beginning of each row
		int rowCount = 0;
		
		for (Target[] row : targets) {
			System.out.print(rowCount + " ");
			for (Target target : row) {
				if (target.getTargetName().toLowerCase().equals("headquarter")) {
					System.out.print("H ");
				} else if (target.getTargetName().toLowerCase().equals("armory")) {
					System.out.print("A ");
				} else if (target.getTargetName().toLowerCase().equals("barrack")) {
					System.out.print("B ");
				} else if (target.getTargetName().toLowerCase().equals("sentrytower")) {
					System.out.print("S ");
				} else if (target.getTargetName().toLowerCase().equals("tank")) {
					System.out.print("T ");
				} else if (target.getTargetName().toLowerCase().equals("oildrum")) {
					System.out.print("O ");
				} else if (target.getTargetName().toLowerCase().equals("ground")) {
					System.out.print("- ");
				}
			}
			System.out.println();
			rowCount++;
		}
	}
	
	/**
	 * @return number of shots fired by player
	 */
	public int getShotsCount() {
		return shotsCount;
	}
	
	/**
	 * @return 2-dimensional array of targets of the base
	 */
	public Target[][] getTargetsArray() {
		return targets;
	}
	
	/**
	 * @return number of placed targets in base
	 */
	public int getPlacedTargetCount() {
		return placedTargetCount;
	}
	
	/**
	 * Increases number of shots fired by player by 1
	 */
	public void incrementShotsCount() {
		shotsCount++;
	}
	
	/**
	 * @return number of destroyed targets
	 */
	public int getDestroyedTargetCount() {
		return destroyedTargetCount;
	}
	
	/**
	 * Sets the number of destroyed targets to be given int i
	 * @param i is number of destroyed targets to set to
	 */
	public void setDestroyedTargetCount(int i) {
		this.destroyedTargetCount = i;
	}
}
