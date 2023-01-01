package Clothes;

import Items.Item;
import java.io.*;

public abstract class Cloth extends Item implements Serializable{
	private final int amount_hp;
	
	public Cloth(String Name, int volume, int hp)
	{
		super(Name, volume);
		this.amount_hp = hp;
	}
	
	
	public int getAmountHp()
	{
		return this.amount_hp;
	}
	
}
