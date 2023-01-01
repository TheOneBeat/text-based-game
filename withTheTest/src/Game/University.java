package Game;
import java.io.*;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

import AllExits.*;
import AllLocations.*;
import AllPlayers.*;

public class University implements Serializable{
	private ArrayList<Location> allLocs = new ArrayList<>();
	private ArrayList<String> hint = new ArrayList<>();
	private Player thePlayer;
	
	public University() {
		
		this.thePlayer = new Player(this.enterName());
		
		
		Location corridor = new Corridor();
		Location closet = new Closet();
		Location defayeoff = new DefayeOffice();
		Location dormitory = new Dormitory();
		Location hiddenRoom = new HiddenRoom();
		Location englishR = new EnglishRoom();
		Location mathematicsR = new MathematicsClassroom();
		Location amphi = new Amphitheater();
		Location skapinoff = new SkapinOffice();
		Location computerlab = new ComputerLab();
		
		corridor.AddExitLocation(amphi.getName(), new Door(amphi));
		corridor.AddExitLocation(englishR.getName(), new Door(englishR));
		corridor.AddExitLocation(mathematicsR.getName(), new Door(mathematicsR));
		corridor.AddExitLocation(computerlab.getName(), new Door(computerlab));
		corridor.AddExitLocation(skapinoff.getName(), new Door(skapinoff));
		corridor.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		englishR.AddExitLocation(corridor.getName(), new Door(corridor));

		amphi.AddExitLocation(corridor.getName(), new Door(corridor));
		
		mathematicsR.AddExitLocation(computerlab.getName(), new Door(computerlab));
		mathematicsR.AddExitLocation(corridor.getName(), new Door(corridor));
		
		computerlab.AddExitLocation(mathematicsR.getName(), new Door(mathematicsR));
		computerlab.AddExitLocation(corridor.getName(), new Door(corridor));
		computerlab.AddExitLocation(closet.getName(), new Door(closet));
		
		closet.AddExitLocation(computerlab.getName(), new Door(computerlab));
		
		dormitory.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		skapinoff.AddExitLocation(corridor.getName(), new Door(corridor));
		skapinoff.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		defayeoff.AddExitLocation(skapinoff.getName(), new Door(skapinoff));
		defayeoff.AddExitLocation(dormitory.getName(), new Door(dormitory));
		defayeoff.AddExitLocation(corridor.getName(), new Door(corridor));
		
		this.hint.add("s");
		this.hint.add("b");
		this.hint.add("w");
		this.hint.add("k");
		this.hint.add("d");
		this.hint.add("j");
		this.hint.add("r");
		this.hint.add("u");
		this.hint.add("h");
		this.hint.add("cesar");
		this.hint.add("3");
		
		
		this.allLocs.add(mathematicsR);
		this.allLocs.add(hiddenRoom);
		this.allLocs.add(skapinoff);
		this.allLocs.add(dormitory);
		this.allLocs.add(closet);
		this.allLocs.add(computerlab);
		this.allLocs.add(mathematicsR);
		this.allLocs.add(amphi);
		this.allLocs.add(englishR);
		this.allLocs.add(defayeoff);
		this.allLocs.add(corridor);
		
		this.insertHints();
		
		
		
		
		this.thePlayer.changeLocation(this.getAlllocs().get(0));
		
		//closet de base...
	}
	
public University(String name) {
		
		this.thePlayer = new Player(name);
		
		
		Location corridor = new Corridor();
		Location closet = new Closet();
		Location defayeoff = new DefayeOffice();
		Location dormitory = new Dormitory();
		Location hiddenRoom = new HiddenRoom();
		Location englishR = new EnglishRoom();
		Location mathematicsR = new MathematicsClassroom();
		Location amphi = new Amphitheater();
		Location skapinoff = new SkapinOffice();
		Location computerlab = new ComputerLab();
		
		corridor.AddExitLocation(amphi.getName(), new Door(amphi));
		corridor.AddExitLocation(englishR.getName(), new Door(englishR));
		corridor.AddExitLocation(mathematicsR.getName(), new Door(mathematicsR));
		corridor.AddExitLocation(computerlab.getName(), new Door(computerlab));
		corridor.AddExitLocation(skapinoff.getName(), new Door(skapinoff));
		corridor.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		englishR.AddExitLocation(corridor.getName(), new Door(corridor));

		amphi.AddExitLocation(corridor.getName(), new Door(corridor));
		
		mathematicsR.AddExitLocation(computerlab.getName(), new Door(computerlab));
		mathematicsR.AddExitLocation(corridor.getName(), new Door(corridor));
		
		computerlab.AddExitLocation(mathematicsR.getName(), new Door(mathematicsR));
		computerlab.AddExitLocation(corridor.getName(), new Door(corridor));
		computerlab.AddExitLocation(closet.getName(), new Door(closet));
		
		closet.AddExitLocation(computerlab.getName(), new Door(computerlab));
		
		dormitory.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		skapinoff.AddExitLocation(corridor.getName(), new Door(corridor));
		skapinoff.AddExitLocation(defayeoff.getName(), new Door(defayeoff));
		
		defayeoff.AddExitLocation(skapinoff.getName(), new Door(skapinoff));
		defayeoff.AddExitLocation(dormitory.getName(), new Door(dormitory));
		defayeoff.AddExitLocation(corridor.getName(), new Door(corridor));
		
		this.hint.add("s");
		this.hint.add("b");
		this.hint.add("w");
		this.hint.add("k");
		this.hint.add("d");
		this.hint.add("j");
		this.hint.add("r");
		this.hint.add("u");
		this.hint.add("h");
		this.hint.add("cesar");
		this.hint.add("3");
		
		
		this.allLocs.add(mathematicsR);
		this.allLocs.add(hiddenRoom);
		this.allLocs.add(skapinoff);
		this.allLocs.add(dormitory);
		this.allLocs.add(closet);
		this.allLocs.add(computerlab);
		this.allLocs.add(mathematicsR);
		this.allLocs.add(amphi);
		this.allLocs.add(englishR);
		this.allLocs.add(defayeoff);
		this.allLocs.add(corridor);
		
		this.insertHints();
		
		
		
		
		this.thePlayer.changeLocation(this.getAlllocs().get(0));
		
		//closet de base...
	}
	
	static {
		System.out.println("\t Welcome to the NIGHTMARE ON CAMPUS my friend\n");

	}
	
	
	public Player getPlayer()
	{
		return this.thePlayer;
	}
	
	
	public ArrayList<Location> getAlllocs()
	{
		return this.allLocs;
	}
	
	public String enterName()
	{
		String name = null;
		while(name==null || name=="")
		{
			
			Scanner thescan = new Scanner(System.in);
			System.out.println("Please enter your name\n");
			name= thescan.nextLine();
		}

		System.out.println("\nHello " + name + "\nYou just woke up in a cold and dark building.\n" +
		"You check your pockets and all you have is a lighter.\n" +
		"You light it up to finally see the room you woke up in. You can't see much however it looks familiar..\n" +
		"Of ourse !! It's the mathematics classroom of your University.\n" +
		"You have absolutely no idea what you are doing here but your survival instincts kick in, you look around you and you find an old abandoned bag.\n" +
		"You take it with you, you never know.\n" +
		"YOU MUST FIND A WAY OUT ! YOUR LIFE DEPENDS ON IT !\n\n\n" +
		"Thanks to your lighter you can kind of see what's around you, use the command LOOK to use it.\n" +  
		"If you ever find an object you would wish to use in the future, you can always take it using the command TAKE followed by the name of the object.\n" + 
		"Sometimes you may stumble upon furniture and want to know what's inside, use the command CHECK followed by the name of the furniture to do so.\n" +
		"To change room simply use the command GO followed by the name of the room you wish to go to.\n" +
		"However, you can only GO to rooms adjacent to yours, use the command SEE_ROOMS to see them.\n" +
		"Use the command MY_STATS to make sure you're not too low on life points.\n" +
		"Remember those items you took ? You can see them by using the command CHECK_BAG.\n\n" +
		
		"It's a lot to take in, use the command HELP to see all the available commands.\n"
		);
	
	
		return name;
	}


	
	public void insertHints()
	{
		Random random = new Random();
		int nb;
		Iterator<String> it = this.hint.iterator();
		while(it.hasNext())
		{
			nb = random.nextInt(this.allLocs.size());
			Set <String>theLocation = this.allLocs.get(nb).getPapers().keySet();
			for(String nameFurniture: theLocation)
			{
				if (this.allLocs.get(nb).getPapers().get(nameFurniture)=="")
				{
					this.allLocs.get(nb).getPapers().replace(nameFurniture,it.next());
					it.remove();
					break;
				}
				
			}
		}
		
	}
	
	
	
	
	
}
