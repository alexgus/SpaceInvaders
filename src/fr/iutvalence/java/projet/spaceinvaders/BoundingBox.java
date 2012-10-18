/**
 * 
 */
package fr.iutvalence.java.projet.spaceinvaders;

// FIXME what about defining BB as immutable ?
/**
 * The BoundingBox class define area.<br/>
 * An area is defined by position (Coordinates) and size (Coordinates) on the grid.
 * The 0 point is bottom left. 
 * @author Gallet Guyon
 */
public class BoundingBox
{
	// FIXME fix comment (BB is not related to element objects)
	/**
	 * The position of the Element object on the 2D grid.
	 */
	private final Coordinates position;
	
	/**
	 * The size of the movable on the grid.<br/>
	 * The size is defined by two values as a Coordinate object whose x means width and y means height.
	 */
	private final Coordinates size;

	// ********************* Constructor ************************
	// FIXME (SEEN) fix and finish writing comment (to discuss)
	/**
	 * Creates a new bounding box, whose position and size are given as parameters 
	 * @param position Set the position of the element in 2D grid
	 * @param size Can't be change once it's allocated
	 */
	public BoundingBox(Coordinates position, Coordinates size)
	{
		super();
		this.position = position;
		this.size = size;
	}

	//**************** Method ************************

	/**
	 * Method to change position of element on the 2D grid.
	 * @param newPosition (Coordinates) the new position to set 
	 * @return New BoundingBox with new coordinates.
	 */
	public BoundingBox moveTo(Coordinates newPosition)
	{
		return new BoundingBox(newPosition, this.size);
	}
	
	/**
	 * Method to translate position of element on the 2D grid.
	 * @param delta (Coordinates) take the old coordinates and add delta to it.
	 * @return New BoundingBox with new coordinates.
	 */
	public BoundingBox translate(Coordinates delta)
	{
		return this.moveTo(this.position.translate(delta));
	}
	
	// TODO Translate Size
	
	// FIXME add a method to compute the intersection with another BB (the result is a BB)	
	/**
	 * 
	 * @param C
	 * @param B
	 * @return
	 */
	private boolean pointIn(Coordinates C, BoundingBox B)
	{
		int x,y,bx1,bx2,by1,by2;
		
		x = C.getX();
		y = C.getY();
		bx1 = B.position.getX();
		bx2 = B.position.getX() + B.size.getX();
		by1 = B.position.getY();
		by2 = B.position.getY() + B.size.getY();
		
		return ((x >= bx1) && (x <= bx2) && (y >= by1) && (y <= by2));
	}
	
	/**
	 * 
	 * @param B
	 * @return (BoundingBox)
	 */
	public BoundingBox interBound(BoundingBox B)
	{
		//  Hypothese : Prendre l'objet courant comme reference (hx1,hx2,hy1,hy2)
		//				B (x1,x2,y1,y2)
		//	Area :
		//
		//
		//	Y
		//	^
		//	|
		//	|
		//	|
		//	|	 (x1,y2)__________(x2,y2)
		//	|		|				 |
		//	|		|				 |
		//	|		|				 |
		//	|		|				 |
		//	|		|				 |
		//	|		|				 |
		//	|		|				 |
		//	|	 (x1,y1)__________(x2,y1)
		//	|
		//	|
		//	0-------------------------------------------------> X
		
		// Declaration des variables
		int x1,x2,y1,y2,hx1,hx2,hy1,hy2;
		
		//Initialisation
		hx1 = this.position.getX();
		hx2 = this.position.getX() + this.size.getX();
		hy1 = this.position.getY();
		hy2 = this.position.getY() + this.size.getY();
		
		x1 = B.position.getX();
		x2 = B.position.getX() + B.size.getX();
		y1 = B.position.getY();
		y2 = B.position.getY() + B.size.getY();
		
		
		// Pour chaque points test
		if(this.pointIn(new Coordinates(hx1,hy1),B))
		{
			return new BoundingBox();
		}
		
		
		return B; 
	}

	//***************** Getters and Setters ************************
	
	/**
	 * Return the size of the element by the couple (width,height).
	 * @return the size of the element by the couple (width,height).
	 */
	public Coordinates getSize()
	{
		return this.size;
	}
	
	/**
	 * Getter to return position of the element on 2D grid.
	 * @return the position of the element.
	 */
	public Coordinates getPosition()
	{
		return this.position;
	}
	
	@Override
	public String toString()
	{
		return "BoundingBox [position=" + this.position + ", size=" + this.size + "]";
	}
}
