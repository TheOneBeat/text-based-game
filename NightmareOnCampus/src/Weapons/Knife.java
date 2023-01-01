package Weapons;
import java.io.*;

public class Knife extends Weapon implements Serializable{
	
	public Knife(String name, int vol, int ptdeg, int percent)
	{
		super(name,vol,ptdeg, percent);
	}
}
