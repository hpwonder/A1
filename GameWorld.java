package com.mycompany.a2;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
	private int lives = 3;
	private Random rand = new Random();
	private int clock = 0;
	private ArrayList<GameObject> gameObjectCollection;
	private int lastFlag = 4;
	private int maxSpeed;
	//set objects
	private Flag flag1;
	private Flag flag2;
	private Flag flag3;
	private Flag flag4;
	
	private FoodStation station1;
	private FoodStation station2;
	
	private Spider spider1;
	private Spider spider2;
	
	private Ant player;
	
	//1000x1000 gameworld
	final private float GAME_WIDTH = 1000;
	final private float GAME_LENGTH = 1000;
	
	public GameWorld() {
		gameObjectCollection = new ArrayList<GameObject>();
		
	}
	
	public void init(){ 
		//setup flags
		flag1 = new Flag((float) 101.1, (float) 105.6, 1);
		flag2 = new Flag((float) 300.5, (float) 345.6, 2);
		flag3 = new Flag((float) 500.6, (float) 200.3, 3);
		flag4 = new Flag((float) 700.3, (float) 400.2, 4);
		gameObjectCollection.add(flag1);
		gameObjectCollection.add(flag2);
		gameObjectCollection.add(flag3);
		gameObjectCollection.add(flag4);
		
		//setup foodStations
		station1 =  new FoodStation(rand.nextInt(41) + 10, (rand.nextFloat() * GAME_WIDTH), (rand.nextFloat() * GAME_LENGTH));
		station2 =  new FoodStation(rand.nextInt(41) + 10, (rand.nextFloat() * GAME_WIDTH), (rand.nextFloat() * GAME_LENGTH));
		gameObjectCollection.add(station1);
		gameObjectCollection.add(station2);
		
		//setup spiders
		spider1 =  new Spider(rand.nextInt(41) + 10, (rand.nextFloat() * GAME_WIDTH), (rand.nextFloat() * GAME_LENGTH), rand.nextInt(360), rand.nextInt(6) + 5);
		spider2 =  new Spider(rand.nextInt(41) + 10, (rand.nextFloat() * GAME_WIDTH), (rand.nextFloat() * GAME_LENGTH), rand.nextInt(360), rand.nextInt(6) + 5);
		gameObjectCollection.add(spider1);
		gameObjectCollection.add(spider2);
		
		//setup ant
		player = new Ant((float) 101.1, (float) 105.6, 0, 10);
		gameObjectCollection.add(player);
		maxSpeed = player.getMaximumSpeed();
	} 
	
	//a
	public void accelerate() {
		//if player speed is less than maxSpeed
		if (player.getSpeed() < maxSpeed) 
			//add 10 to speed
			player.setSpeed(player.getSpeed() + 10);
		//else if player speed is greater than or equal to max speed
		else if (player.getSpeed() >= maxSpeed)
			//set speed to max speed
			player.setSpeed(maxSpeed);
	}
		
	//b
	public void brake() {
		//if player speed is greater than 0
		if (player.getSpeed() > 0)
			//subtract 10 from speed
			player.setSpeed(player.getSpeed() - 10);
		//if player speed is less than or equal to 0
		else if (player.getSpeed() <= 0)
			//set speed to 0
			player.setSpeed(0);
	}
		
	//l
	public void turnLeft() {
		//turn left on increment of 5
		player.steer(-5);
	}
		
	//r
	public void turnRight() {
		//turn right on increment of 5
		player.steer(+5);
	}

	//1-9
	public void collideFlag(int seqNum) {
		//if the player is at the last flag end game
		if(player.getLastFlagReached() == lastFlag - 1)
		{
			System.out.println("\nWinner! Total time Elapsed: " + clock);
			System.exit(0);
		}
		else if(player.getLastFlagReached() == seqNum - 1) //else if last flag you are it is equal to your curr flag - 1 set last flag to curr flag in seq 
		{
			player.setLastFlagReached(seqNum);
		}
	}
	
	//f
	public void collideFoodStation() {
		ArrayList<FoodStation> stationFull = new ArrayList<FoodStation>();
		FoodStation stationCurrent;
		
		//add all stations that still have food into arraylist
		for (GameObject o : gameObjectCollection) {
			if (o instanceof FoodStation && ((FoodStation) o).getCapacity() != 0) {
				stationFull.add((FoodStation) o);
			}
		}
		
		//pick a random station from stations in array
		stationCurrent = stationFull.get(rand.nextInt(3));
		
		//set foodlevel of player to the current stations capacity
		player.setFoodLevel(stationCurrent.getCapacity());
		
		//set capacity of station to 0 and fade out color
		stationCurrent.setCapacity(0);
		stationCurrent.setColor(128,128 - rand.nextInt(30),128);
		
		//create a new foodstation
		FoodStation stationNew = new FoodStation(rand.nextInt(41) + 10, (rand.nextFloat() * GAME_WIDTH), (rand.nextFloat() * GAME_LENGTH));
		gameObjectCollection.add(stationNew);
	}
	
	//g
	public void collideSpider() {
		//decrease health by 1
		player.decreaseHealthLevel();
		
		//if player has less than 10 health
		if (player.getHealthLevel() < 10)
		{
			//set max speed to subtract percentage of health lost
			maxSpeed = player.getMaximumSpeed() - (10 * (10 - player.getHealthLevel()));
			//if current speed is greater than max speed
			if (player.getSpeed() > maxSpeed)
			{
				//set speed to max speed
				player.setSpeed(maxSpeed);
			}
			
		}
		
		// if health at 0 subtract life
		if (player.getHealthLevel() == 0) { 
			lives--;
			// if no lives game over
			if (lives == 0) {
				gameObjectCollection.clear();
				System.out.println("\nGame over!");
				System.exit(0);
			// else restart
			} else if (lives > 0) {
				gameObjectCollection.clear();
				System.out.println("\nlife lost. Remaining lives: " + lives);
				init();
			}
		}
	}
	
	//t
	public void tickClock() {
		//(1) Spiders update heading
		for (GameObject o : gameObjectCollection) {
			if (o instanceof Movable && o instanceof Spider) 
				((Movable) o).setHeading(rand.nextInt(6) + 5);
		}
		
		//(2) all movable objects are told to update positions 
		for (GameObject o : gameObjectCollection) {
			if (o instanceof Movable) {
				((Movable) o).Move(((Movable) o).getHeading(), ((Movable) o).getSpeed());
			}
		}
		
		//(3) ants food level is reduced by ant amount indicated by foodConsumptionRate
		player.setFoodLevel(player.getFoodLevel() - player.getFoodConsumptionRate());
		
		//if food level is zero subtract life
		if (player.getFoodLevel() == 0) {
			lives--;
			// if no lives game over
			if (lives == 0) {
				gameObjectCollection.clear();
				System.out.println("\nGame over!");
				System.exit(0);
			// else restart
			} else if (lives > 0) {
				gameObjectCollection.clear();
				System.out.println("\nlife lost. Remaining lives: " + lives);
				init();
			}}
		
		//(4) elapsed game clock is incremented by 1 
		clock++;
	}
	
	//d
	public void display() {
		System.out.print("\n1) Current Life: " + lives);
		System.out.print("\n2) Current Time: " + clock);
		System.out.print("\n3) Highest Flag Reached: " + player.getLastFlagReached());
		System.out.print("\n4) Current food level: " + player.getFoodLevel());
		System.out.print("\n5) Current health level: " + player.getHealthLevel());
	}
	
	//m
	public void outputMap() {
		for (GameObject o : gameObjectCollection) 
			System.out.println(o.toString());
	}
	
	//x
	public void exit() {
		System.out.println("\nExit Game? Y/N: ");
	}
	

} 
