package AllLocations;
import java.io.*;

import Characters.Enemy;

public class Dormitory extends Location implements Serializable{
	public Dormitory()
	{
		super("Dormitory");
		this.getAllEnemies().add(new Enemy("Zombieman",30,100));
		this.fillPapers("Window", "");
		this.setDescription("You are now in the dormitory, you must be carefull, there is a zombie next to the WINDOW ! I hope you have a weapon. Oh no, the door behind you just locked.. good luck ! And remember, you can always check the monster's stats using the command MONSTER.");
	}
}
