/**
 * 
 */
package fr.iutvalence.java.projet.spaceinvaders;

import java.awt.event.KeyListener;

import fr.iutvalence.java.projet.spaceinvaders.enumerations.Etat;
import fr.iutvalence.java.projet.spaceinvaders.enumerations.Type;

/**
 * A space invader game.<br/>
 * 
 * @author Gallet Guyon
 */
public class SpaceInvadersMaVa extends SpaceInvaders implements TankControler, MonsterControler
{

	// ********************* Constructors ************************

	/**
	 * Initialize the game.<br/>
	 * This is the default constructor. It set all to default value. If you don't want to use this default
	 * characteristic use another constructor
	 * 
	 * @param name
	 *            The name of score
	 * @param score
	 *            The interface of score
	 * @param d
	 *            The display object to use.
	 */
	public SpaceInvadersMaVa(String name, Score score, Display d)
	{
		super(name, score, d);
	}

	/**
	 * Initialize the game.<br/>
	 * This constructor sets the X axis to 300, and the Y axis to 300 too.<br/>
	 * If you don't want to use this default characteristic use another constructors
	 * 
	 * @param name
	 *            The name of score
	 * @param score
	 *            The interface of score
	 * @param nbMonsters
	 *            Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTanks
	 *            Set the number of Tank you want instantiate (with default constructors, it sets to 20)
	 * @param d
	 *            The display object to use.
	 */
	public SpaceInvadersMaVa(String name, Score score, int nbMonsters, int nbTanks, Display d)
	{
		super(name, score, d, nbMonsters, nbTanks);
	}

	/**
	 * Initialize the game.<br/>
	 * This constructor uses default value of acceleration
	 * 
	 * @param name
	 *            The name of score
	 * @param score
	 *            The interface of score
	 * @param nbMonsters
	 *            Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTanks
	 *            Set the number of Tank you want instantiate (with default constructors, it sets to 20)
	 * @param max
	 *            Set the Max point of the grid (Coordinates)
	 * @param d
	 *            The display object to use.
	 */
	public SpaceInvadersMaVa(String name, Score score, int nbMonsters, int nbTanks, Coordinates max, Display d)
	{
		super(name, score, d, nbMonsters, nbTanks, max);
	}

	/**
	 * Initialize the game.<br/>
	 * This constructor no default value.
	 * 
	 * @param name
	 *            The name of score
	 * @param score
	 *            The interface of score
	 * @param nbMonsters
	 *            Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTanks
	 *            Set the number of Tank you want instantiate (with default constructors, it sets to 20)
	 * @param max
	 *            Set the Max point of the grid (Coordinates)
	 * @param sleepTime
	 *            Set the time between each move of monster (in milliseconds)
	 * @param d
	 *            The display object to use.
	 */
	public SpaceInvadersMaVa(String name, Score score, int nbMonsters, int nbTanks, Coordinates max, int sleepTime,
			Display d)
	{
		super(name, score, d, nbMonsters, nbTanks, max, sleepTime);
	}

	/**
	 * Initialize the game.<br/>
	 * This constructor uses no default value.
	 * 
	 * @param name
	 *            The name of score
	 * @param score
	 *            The interface of score
	 * @param nbMonsters
	 *            Set the number of Monster you want instantiate (with default constructors, it sets to 20)
	 * @param nbTanks
	 *            Set the number of Tank you want instantiate (with default constructors, it sets to 20)
	 * @param max
	 *            Set the Max point of the grid (Coordinates)
	 * @param sleepTime
	 *            Set the time between each move of monster (in milliseconds)
	 * @param acceleration
	 *            Set the acceleration of Invaders. (not uses for now)
	 * @param d
	 *            The display object to use.
	 */
	public SpaceInvadersMaVa(String name, Score score, int nbMonsters, int nbTanks, Coordinates max, int sleepTime,
			int acceleration, Display d)
	{
		super(name, score, d, nbMonsters, nbTanks, max, sleepTime, acceleration);
	}

	// ********************* Main ************************

	/**
	 * Main of the thread. It create to other threads for Invaders and for Tanks
	 */
	public void run()
	{
		this.time = System.currentTimeMillis();
		this.score.init();
		this.display.init(this.listenController, this.elements, this.maxSize);
		while (true)
		{
			moveShoots();

			testCollision();

			this.display.show();

			if (this.countAlive(this.elements, Type.TANK) == 0)
			{
				this.display.loose();
				this.score.showSheet();
				break;
			}
			if (this.countAlive(this.elements, Type.MONSTER) == 0)
			{
				this.display.win();
				this.score.save(this.name, System.currentTimeMillis() - this.time);
				this.score.showSheet();
				break;
			}

			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setControleur(KeyListener k)
	{
		this.listenController = k;
	}

	@Override
	public boolean working()
	{
		return (this.countAlive(this.elements, Type.TANK) == 0 || this.countAlive(this.elements, Type.MONSTER) == 0);
	}

	// ******************** Method ***********************

	// [[[[[[[[[[[[[ Monsters behavior ]]]]]]]]]]]]]

	@Override
	public void waitLoop()
	{
		try
		{
			Thread.sleep((long) ((Math.sqrt(((double) countAlive(this.elements, Type.MONSTER) / this.monstersAmount)) * this.sleepTime) + this.timeDifficulty));
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int monstersMove()
	{
		switch (this.etat)
		{
			case LEFT_UP:
				try
				{
					moveTab(new Coordinates(this.coorMove.getX(), 0), this.elements, Type.MONSTER);
				}
				catch (OutOfGridException e)
				{
					this.etat = Etat.RIGHT_UP;
					monstersMove();
				}
				break;
			case RIGHT_UP:
				try
				{
					moveTab(new Coordinates(0, -this.coorMove.getY()), this.elements, Type.MONSTER);
				}
				catch (OutOfGridException e1)
				{
					if (e1.getOutOfGridException().getArea().getPosition().getY() <= this.sizeMovable.getY())
						this.killTank();
					else
						monstersMove();
				}
				this.etat = Etat.RIGHT_BOTTOM;
				break;
			case RIGHT_BOTTOM:
				try
				{
					moveTab(new Coordinates(-this.coorMove.getX(), 0), this.elements, Type.MONSTER);
				}
				catch (OutOfGridException e)
				{
					this.etat = Etat.LEFT_BOTTOM;
					monstersMove();
				}
				break;
			case LEFT_BOTTOM:
				try
				{
					moveTab(new Coordinates(0, -this.coorMove.getY()), this.elements, Type.MONSTER);
				}
				catch (OutOfGridException e1)
				{
					if (e1.getOutOfGridException().getArea().getPosition().getY() <= this.sizeMovable.getY())
						this.killTank();
					else
						monstersMove();
				}
				this.etat = Etat.LEFT_UP;
				break;
		}
		return (int) (Math.sqrt(((double) countAlive(this.elements, Type.MONSTER) / this.monstersAmount)) + this.sleepTime);
	}

	@Override
	public void monsterShoot()
	{
		int i, j;
		FiringMovable invaderAbove = null;
		for (i = 0; i < this.tanksAmount; i++)
		{
			if (this.elements[i] != null && this.elements[i].isAlive() && this.elements[i].getType() == Type.TANK)
			{
				for (j = this.elements.length - 1; j >= 0; j--)
				{
					if (this.elements[j] != null && this.elements[j].isAlive()
							&& this.elements[j].getType() == Type.MONSTER)
					{
						if (((this.elements[j].getArea().getPosition().getX() + (this.elements[j].getArea().getSize()
								.getX()) / 2) - (this.sizeShoots.getX() / 2) < (this.elements[i].getArea()
								.getPosition().getX() + this.elements[i].getArea().getSize().getX()) && (this.elements[j]
								.getArea().getPosition().getX() + (this.elements[j].getArea().getSize().getX()) / 2)
								- (this.sizeShoots.getX() / 2) > (this.elements[i].getArea().getPosition().getX()))
								|| ((this.elements[j].getArea().getPosition().getX() + (this.elements[j].getArea()
										.getSize().getX()) / 2) + (this.sizeShoots.getX() / 2) < (this.elements[i]
										.getArea().getPosition().getX() + this.elements[i].getArea().getSize().getX()) && (this.elements[j]
										.getArea().getPosition().getX() + (this.elements[j].getArea().getSize().getX()) / 2)
										+ (this.sizeShoots.getX() / 2) > (this.elements[i].getArea().getPosition()
											.getX())))
						{
							if (invaderAbove != null)
							{
								if (invaderAbove.getArea().getPosition().getY() > this.elements[j].getArea()
										.getPosition().getY())
									if (!(this.elements[i] instanceof FiringMovable))
										invaderAbove = (FiringMovable) this.elements[j];
							}
							else
								invaderAbove = (FiringMovable) this.elements[j];
						}

					}
				}
			}
			shootFrom(invaderAbove);
		}
	}

	// [[[[[[[[[[[[[ Controls ]]]]]]]]]]]]]

	/**
	 * Allows random tank control
	 * 
	 * @throws OutOfGridException
	 *             Indicate when Tank want to go over the screen
	 */
	public void tankMoveRand() throws OutOfGridException
	{
		int i;
		int x;
		long neg;

		for (i = 0; i < this.elements.length; i++)
		{
			if (this.elements[i] != null && this.elements[i].isAlive() && this.elements[i].getType() == Type.TANK)
			{
				x = (int) (Math.random() * this.coorMove.getX());
				neg = Math.round(Math.random());

				if (neg == 0)
					neg = -1;

				try
				{
					if ((this.elements[i].getArea().getPosition().getX() + (int) (x * neg)) > 0
							&& (this.elements[i].getArea().getPosition().getX()
									+ this.elements[i].getArea().getSize().getX() + (int) (x * neg)) < this.maxSize
									.getX())
					{
						this.elements[i].move(new Coordinates((int) (x * neg), 0));
					}
					else
					{
						throw new OutOfGridException(this.elements[i]);
					}
				}
				catch (NegativeSizeException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void tankShoot()
	{
		int i, nb = this.countShoot();

		if (nb < DEFAULT_NB_SHOOTS_TANK)
		{
			for (i = 0; i < this.tanksAmount; i++)
			{
				if (this.elements[i] != null && this.elements[i].isAlive())
					this.shootFrom((FiringMovable) this.elements[i]);
			}
		}
	}

	@Override
	public void tankMove(Coordinates delta)
	{
		for (int i = 0; i < this.elements.length; i++)
		{
			Movable toMove = null;

			try
			{
				toMove = (Movable) this.elements[i];
			}
			catch (ClassCastException e)
			{
				continue;
			}

			if (toMove == null) continue;
			
			if (toMove.getType() != Type.TANK)
				continue;

			if (!(toMove.isAlive()))
				continue;

			try
			{
				BoundingBox toMoveNextBb = toMove.getArea().translate(delta);
				BoundingBox gridBb = new BoundingBox(new Coordinates(0, 0), this.maxSize);
				
				BoundingBox intersection = toMoveNextBb.intersection(gridBb);

				if (!(intersection.equals(toMoveNextBb)))
					return;

				toMove.move(delta);
			}
			catch (NegativeSizeException e)
			{
				// ignore this, nothing is moved
			}
		}
	}
	
}
