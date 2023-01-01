package Allcommands;
import java.io.Serializable;

import java.util.HashMap;


public class Help implements Serializable{

	static protected HashMap<String,String> allCmds = new HashMap<>();
	
	public Help()
	{
		
	}
	

	static{
		allCmds.put("Go","the command GO is followed by the name of the neighbor location the player wants to go.\n\tExample: Go Bathroom\n");
		allCmds.put("Help","indicates the set of availaible commands.\n\tExample: Help. \n\tYou can actually add "+
				"another argument to display informations of a particular command Example: Help Go");
		allCmds.put("Quit","quit the Game.\n\tExample: Quit\n");
		allCmds.put("Look","displays a description of the the current location and all items\n");
		
		allCmds.put("Take","argument : adds (if possible) the argument to the hero’s items.\n\tExample: Take coat\n");
		allCmds.put("Equip","uses the object you have typed. \n\tExample: Equip gun, will equip your player with the gun\n");
		
		
		allCmds.put("Check_bag", "\n\tShows all items inside your bag, but only items not hints.\n\tExample : Check_bag\n");
		
		allCmds.put("Throw", "\n\tAllow you to throw away an item from your bag to your current location.\n\tExample : Throw gun\n");
		
		allCmds.put("My_stats", "\n\tAllows you to see your current stats like your name, life points, damage, and the volume of your bag.\n Example : My_stats\n");
		allCmds.put("Holding", "\n\tShows all items in your hands.\n\tExample : Holding\n");
		allCmds.put("Disarm", "\n\tAllows you to put your equiped weapon into your bag\n\tExample : Disarm\n");
		
		allCmds.put("Attack", "\n\tAllows you to attack all enemies in your current location.\n\tExample : Attack\n");
		
		allCmds.put("Monster", "\n\tShows all monsters including their stats present in your current room\n\tExample : Monster\n");
		allCmds.put("See_rooms", "\n\tShows all adjacent rooms\n\tExample : See_rooms\n");
		allCmds.put("Pack", "\n\tAllows you to pack the item in your hand into your bag.\n\tExample : Pack\n");
		allCmds.put("Check", "\n\tAllows you to search furniture.\n\tExample : Check desk\n");
		allCmds.put("Clues", "\n\tAllows you to see all clues that you have gathered...\n\tExample : Clues\n");
		allCmds.put("Save", "\n\tAllows you to save the game ...\n\tExample : Save\n");
		allCmds.put("Load", "\n\tAllows you to load a previous save...\n\tExample : Load\n");
		
	}
	
	static public HashMap<String,String> getCmds()
	{
		return allCmds;
	}
	
	static public void printCommands()
	{
		for(String i: allCmds.keySet())
			System.out.println(String.format("%s : %s", i, allCmds.get(i)));
	}
	
	static public void printACommandHelp(String c)
	{
		if (allCmds.containsKey(c))
		{
			System.out.println(allCmds.get(c));
		}
		
	}
}
