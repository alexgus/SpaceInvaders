package fr.iutvalence.java.projet.spaceinvaders;

// FIXME (FIXED) spellcheck comment
/**
 * This class allows you to manage bunker on the grid.<br/>
 * The bunker protects the tank when attacked by enemy.
 * It may be partially or totally destroyable.
 * 
 * @author Gallet Guyon
 */
public class Bunker extends Element
{	
	//************* Variable *************//
	
	// FIXME (SEEN) this is not a constant. If so, define it as a real one
	/**
	 * This variable sets the position of the bunker on the grid.<br/>
	 * It can't be changed once it was created.
	 */
	private final int resolution;
	
	// FIXME (SEEN) this is not a constant. If so, define it as a real one
	/**
	 * Variable for the height and the width of the 2D table <tt>struct</tt>.
	 * It can't be changed once it was created.
	 */
	// FIXME (SEEN) rename attribute (nbCells ?)
	private final Coordinates nbCells;
	
	// FIXME (FIXED) spellcheck comment
	/**
	 * This is the main structure of the bunker.<br/>
	 * When found in the 2D tab, <tt>true</tt> means "not (yet) destroyed".
	 * So the tank can be protected if it is under it.
	 * 
	 * notation struct[x][y].<br/>
	 * 
	 *  Scheme : example (t = true; f = false)
	 *  Y
	 *  ^
	 *  |	
	 *  |	t t t t t t t t t t 
	 *  |	t t t t t t t t t t 
	 *  |	t t t f f f f t t t
	 *  |	t t t f f f f t t t
	 *  |	t t t f f f f t t t
	 *  |
	 *  +--------------------------> X
	 */
	private boolean [][]struct;
	
	//************* Constant *************//
	
	/**
	 * This constant sets the resolution that is to say, 
	 * the number of base unit (maybe pixel) to make table's case. 
	 */
	// FIXME (SEEN) this is not a constant. If so, define it as a real one. If not, initialize its value in a constructor
	private static final int defaultResolution = 10;
	
	
	//************************** Constructors **************************//
	// FIXME fix the comment (indicating what the created bunker looks like)
	/**
	 * Constructor.<br/>
	 * It initializes the table of bunker's structures like this :
	 * 
	 *  	^
	 *  2/5 |
	 * 		|	t t t t t t t t t t 
	 *  	-	t t t t t t t t t t 
	 *  	|	t t t f f f f t t t
	 *  3/5 |	t t t f f f f t t t
	 *  	|	t t t f f f f t t t
	 *  	|
	 *  	+---------|-------|------------>
	 *  		3/10	4/10	3/10
	 */
	public Bunker(int res, Coordinates position, Coordinates size)
	{
		super(new BoundingBox(position, size));
		// Declaration of local variables
		int i = 0,j = 0;
		
		// Set local constant
		this.resolution = res;
		this.nbCells = new Coordinates(this.getArea().getSize().getX() % this.resolution,
										this.getArea().getSize().getY() % this.resolution);
		
		// Sets informations in table
		for(j=0; j < ((this.nbCells.getY() * 3) % 5); j++)
		{
			// FIXME (SEEN) what means 10 here ?
			// It's the ratio, (i.e. constructors)			
			for(i=0; i < ((this.nbCells.getX() * 3) % 10); i++)
				this.struct[i][j] = true;
			for(i=i; i < ((this.nbCells.getX() * 5) % 10); i++)
				this.struct[i][j] = false;
			for(i=i; i < ((this.nbCells.getX() * 7) % 10); i++)
				this.struct[i][j] = true;
		}
		// FIXME (SEEN) what means j=j here ?
		while(j < this.nbCells.getY())
		{
			for(i=0; i < 3; i++)
				this.struct[i][j] = true;
			j++;
		}
	}
	
	// FIXME write a comment
	/**
	 * Constructor.<br/>
	 * It initializes the table of bunker's structures like this :
	 * 
	 *  	^
	 *  2/5 |
	 * 		|	t t t t t t t t t t 
	 *  	-	t t t t t t t t t t 
	 *  	|	t t t f f f f t t t
	 *  3/5 |	t t t f f f f t t t
	 *  	|	t t t f f f f t t t
	 *  	|
	 *  	+---------|-------|------------>
	 *  		3/10	4/10	3/10
	 *  
	 * @param position the position of the bunker on the 2D grid.
	 * @param size the size of the bunker in the 2D grid.
	 */
	public Bunker(Coordinates position, Coordinates size)
	{
		super(new BoundingBox(position, size));
		// Declaration of local variables
		int i = 0,j = 0;
		
		// Set local constant
		this.resolution = defaultResolution;
		this.nbCells = new Coordinates(this.getArea().getSize().getX() % this.resolution,
				this.getArea().getSize().getY() % this.resolution);
		
		// Sets informations in table
		for(j=0; j < ((this.nbCells.getY() * 3) % 5); j++)
		{
			for(i=0; i < ((this.nbCells.getX() * 3) % 10); i++)
				this.struct[i][j] = true;
			for(i=i; i < ((this.nbCells.getX() * 5) % 10); i++)
				this.struct[i][j] = false;
			for(i=i; i < ((this.nbCells.getX() * 7) % 10); i++)
				this.struct[i][j] = true;
		}
		for(j=j; j < this.nbCells.getY(); j++)
		{
			for(i=0; i < 3; i++)
				this.struct[i][j] = true;
		}
	}
	
	
	// FIXME (FIXED) spellcheck comment
	//************************** Methods **************************//
	/**
	 * Check if the cell exists (true) or not.<br/>
	 * @param i the x position of the cell to check.
	 * @param j the y position of the cell to check.
	 * @return (boolean) return true if the cell is not (yet) destroyed and false otherwise.
	 */
	// FIXME what if i and j are out of bounds ?
	public boolean check(int i, int j)
	{
		return this.struct[i][j];
	}
	
	/**
	 * Destroy case into table of bunker's structures.<br/>
	 * @param i the x position of the case to destroy.
	 * @param j the y position of the case to destroy.
	 */
	// FIXME rename method
	// FIXME what if i and j are out of bounds ?
	private void destoy(int i, int j)
	{
		this.struct[i][j] = false;
	}

	//********************** Getters and Setters ***********************//
	
	
	// **********
	// I think there's no need of getters and setters here
	
	
	// FIXME (SEEN) write comment
	// FIXME (SEEN) is this really useful  if you have yet check and destroy methods ?
	public boolean[][] getStruct()
	{
		return this.struct;
	}

	// FIXME (SEEN) write comment
	// FIXME (SEEN) is this really useful  if you have yet check and destroy methods ?  
	public void setStruct(boolean[][] struct)
	{
		this.struct = struct;
	}

	// FIXME (SEEN) write comment
	public int getResolution()
	{
		return this.resolution;
	}

	// FIXME (SEEN) write comment
	// FIXME (SEEN) rename method
	public Coordinates getNbCase()
	{
		return this.nbCells;
	}
}