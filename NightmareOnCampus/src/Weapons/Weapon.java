package Weapons;

import Items.Item;
import java.io.*;

public class Weapon extends Item implements Serializable{
	private final int Degats;
	private final int percents;
	
	public Weapon(String name, int vol, int ptdeg, int percent) {
		super(name, vol);
		this.Degats = ptdeg; 
		this.percents = percent; 
	}

	public int getDegats() {
		return this.Degats;
	}

	public int getpercent() {
		// TODO Auto-generated method stub
		return this.percents;
	}
	
}
