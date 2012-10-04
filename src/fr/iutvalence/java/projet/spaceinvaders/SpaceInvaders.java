/**
 * 
 */
package fr.iutvalence.java.projet.spaceinvaders;

import java.util.Arrays;

/**
 * The main parts of the game.<br/>
 * It provides the initializations and all objects are referenced in it. 
 * 
 * @author Gallet Guyon
 */
public class SpaceInvaders
{
	//************* Variable *************//
	/**
	 * Boolean to know if the game is finished
	 */
	private boolean work;
	
	/**
	 * The maximum size of the area
	 */
	private final Coordinates maxSize;

	//************* Tableau *************//
	/**
	 * Table of all monsters' instance variable
	 */
	private Monster[] tabMonster;

	/**
	 * Table of all tanks' instance variable
	 */
	private Tank[] tabTank;

	//************* Constant *************//
	/**
	 * It defines the number of monster you have in tabMonster by default,
	 * if it's not set in constructor. 
	 */
	private final int nbMonsterDefault = 20;

	/**
	 * It defines the number of tank you have in tabTank by default,
	 * if it's not set in constructor.
	 */
	private final int nbTankDefault = 1;
	
	/**
	 * It defines the maximum (default) of X axis,
	 * if it's not set in constructor.
	 */
	private final int XGrid = 300;
	
	/**
	 * It defines the maximum (default) of Y axis,
	 * if it's not set in constructor.
	 */
	private final int YGrid = 300;
	
	/**
	 * Default delta between 2 monsters
	 */
	private final int defaultDelta = 10;
	
	/**
	 * Default size of element (e.g. Doc Movable)
	 */
	private final int defaultSize = 2;

	
	//************************** Constructors **************************//
	/**
	 * Initialize the game.<br/>
	 * This is the default constructor. It sets the number of tank to 1, the number of monster to 20,
	 * the X axis to 300, and the Y axis to 300 too.<br/>
	 * If you don't want to use this default characteristic use another constructors 
	 */
	public SpaceInvaders()
	{
		this.work = true;
		this.maxSize = new Coordinates(this.XGrid, this.YGrid);
		initTab(this.nbMonsterDefault, this.nbTankDefault);
	}
	
	/**
	 * Initialize the game.<br/>
	 * This is the default constructor. It sets the X axis to 300, and the Y axis to 300 too.<br/>
	 * If you don't want to use this default characteristic use another constructors
	 * 
	 * @param nbMonster Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTank Set the number of Tank you want instantiate (with default constructors, it sets to 20)
	 */
	public SpaceInvaders(int nbMonster, int nbTank)
	{
		this.work = true;
		this.maxSize = new Coordinates(this.XGrid, this.YGrid);
		initTab(nbMonster, nbTank);
	}
	
	/**
	 * Initialize the game.<br/>
	 * This is the default constructor. It uses no default value.
	 * 
	 * @param nbMonster Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTank Set the number of Tank you want instantiate (with default constructors, it sets to 20)
 	 * @param nbTank Set the X axis (with default constructors, it sets to 300)
 	 * @param nbTank Set the Y axis (with default constructors, it sets to 300)
	 */
	public SpaceInvaders(int nbMonster, int nbTank, Coordinates Max)
	{
		this.work = true;
		this.maxSize = Max;
		initTab(nbMonster, nbTank);
	}

	//************************** Methods **************************//
	
	/**
	 * Initialize the table of movable elements.
	 * 
	 * 
	 * @param Set the max of monster 
	 * @param Set the max of tank
	 */
	private void initTab(int nbMonstre, int nbTank)
	{
		// local variable
		Coordinates tank_position = new Coordinates((this.maxSize.getX() / 2) 
														- (this.defaultSize / 2), 
													(this.maxSize.getY() / 2)
														- (this.defaultSize / 2));
		Coordinates monster_position = new Coordinates(this.maxSize.getX() - (this.defaultSize + this.defaultDelta), 
														this.maxSize.getY() - (this.defaultSize + this.defaultDelta));
		int i = 0; //line
		int j = 0; //column

		// Allocations
		this.tabMonster = new Monster[nbMonstre];
		this.tabTank = new Tank[nbTank];

		// Set-up Tabs
		this.tabTank[0] = new Tank(tank_position);

		
		//TODO =~finish
	}

	@Override
	public String toString()
	{
		return "SpaceInvaders [tabMonster=" + Arrays.toString(this.tabMonster)
				+ ", tabTank=" + Arrays.toString(this.tabTank) + "]";
	}
	
	

	// TODO move enemy alone
	
	// oldFIXME public methods ?
	// later =)
}
