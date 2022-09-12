/**
 * Student Name: Yichen Zhao
 * Student ID: 13710688
 * Statement: I worked alone for this assignment.
 */

package callOfDuty;

import java.util.Scanner;

/**
 * Sets up the game and controls all operations during the game.
 * @author shenshenzyc
 *
 */
public class CallOfDutyGame {
	
	/**
	 * Entry point of the program.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			//prints welcome message
			System.out.println("Welcome to Call of Duty!");
			
			//creates a new base
			Base base = new Base();
			
			//this setup makes sure that all 18 targets are validly placed in base
			do {
				base.placeAllTargetRandomly();
			} while (base.getPlacedTargetCount() != 18);
			
			//creates two weapons
			Weapon rl = new RocketLauncher();
			Weapon mis = new Missile();
			Weapon[] weaponList = new Weapon[] {rl, mis};
			
			//keeps track of current weapon
			int weaponIndex = 0;
			
			//only used for developer for debugging
			//base.helpPrint();
			
			//prints initial base to player
			base.print();
			
			//asks an action from player until game is over
			while (!base.isGameOver(rl, mis)) {
				//prints some current status for player
				System.out.println("RPG: " + rl.getShotLeft() + " Missile: " + mis.getShotLeft());
				System.out.println("Your current weapon is: " + weaponList[(weaponIndex%2)].getWeaponType());
				System.out.println("Enter row,column to fire or q to switch weapon: ");
				String input = sc.nextLine();
				
				//checks if player wants to switch weapon
				while ((input.strip().equals("q")) || (weaponList[(weaponIndex%2)].getShotLeft() == 0)) {
					if (input.strip().equals("q")) {
						weaponIndex++;
						System.out.println("RPG: " + rl.getShotLeft() + " Missile: " + mis.getShotLeft());
						System.out.println("Your current weapon is: " + weaponList[(weaponIndex%2)].getWeaponType());
						System.out.println("Enter row,column to fire or q to switch weapon: ");
					//player has to switch weapon when current weapon is out of ammo
					} else if (weaponList[(weaponIndex%2)].getShotLeft() == 0) {
						System.out.println("Your current weapon is out of ammo. Enter q to switch weapon");
					} 
					input = sc.nextLine();
				} 
				
				//now the input is a coordinate, read the coordinate
				int row = Integer.parseInt(input.strip().split(",")[0]);
				int col = Integer.parseInt(input.strip().split(",")[1]);
				
				//gets the current weapon
				Weapon currentWeapon = weaponList[(weaponIndex%2)];
				
				//fire with current weapon at given coordinate
				currentWeapon.shootAt(row, col, base);
				
				//display current status of base to player
				base.print();
				
				System.out.println();
			}
			
			if (base.win()) {
				System.out.println("Congratulations! You won the game!");
			} else {
				System.out.println("You lost the game. Hope you had a good time though.");
			}
			
			System.out.println();
			System.out.println("Would you like to play again? Enter y for yes or n for no.");
			String isAgain = sc.nextLine();
			
			if (isAgain.strip().toLowerCase().equals("n")) {
				break;
			}
		}
		
		System.out.println("Thank you for playing Call of Duty! Byebye!");
		
		//closes the scanner
		sc.close();
	}
}
