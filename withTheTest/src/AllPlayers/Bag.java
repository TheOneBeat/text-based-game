package AllPlayers;

import java.util.ArrayList;

import java.io.*;
import java.util.Iterator;

import Items.Item;

public class Bag implements Serializable{
	private ArrayList<Item> thebag = new ArrayList<>();
	private int volumeBag;
	private int DEFAULT_VOLUME = 90;
	
	public Bag(int vol) {
		this.setVolumeBag(vol);
	}
	
	public Bag() {
		this.setVolumeBag(DEFAULT_VOLUME);
	}
	
	public void addItem(Player p,String v)//cette fonction permet de rajouter un item en ne prenant en parametre que le nom de l'item  
	{
		boolean result = false;
		Item current_it = null;
		@SuppressWarnings("unchecked")
		Iterator<Item> it = (Iterator<Item>) p.getLocation().getLocationItems().iterator();
		while(it.hasNext())
		{
			current_it = it.next();
			if (current_it.getSurname().equals(v))
			{
				if ((p.getPlayerBag().getVolumeBag()+current_it.getVolume()) <= DEFAULT_VOLUME)
				{
					p.getPlayerBag().getBag().add(current_it);//on ajoute l'item dans le bag du player
					p.getPlayerBag().setVolumeBag(p.getPlayerBag().getVolumeBag()+current_it.getVolume());//on ajoute le volume du bag par rapport au voulme de l'item à rajouter
					p.getLocation().removeItemS(v);//on remove l'item de la location 
					System.out.println(String.format("\t '%s added to your bag' \n", v));
					result = true;
					break;
				}
				else
				{
					System.out.println("\n there is no more space in your bag");
					break;
				}
			}
		}
		
		if (result==false)//alors l'item n'est pas présent dans le bag ou ne l'est plus...
		{
			System.out.println("\t Sorry, can't add that to your bag");
			System.out.println("\t Make sure it is an available item in the location, with the command 'Look' \n");
		}
	}
	
	public ArrayList<Item> getBag()
	{
		return this.thebag;
	}

	public int getVolumeBag() {
		return volumeBag;
	}
	
	public int getVolumeMaxBag()
	{
		return this.DEFAULT_VOLUME;
	}

	public void setVolumeBag(int volumeBag) {
		this.volumeBag = volumeBag;
	}
	
	public void setAddNegVolumeBag(int volumeBag) {
		this.volumeBag += volumeBag;
	}
	
	public boolean isPresentItemInBag(String v)//permet de savoir si un item dont on connaît le nom est present dans le bag
	{
		boolean result = false;
		Iterator<Item> it = this.getBag().iterator();
		while(it.hasNext())
		{
			Item current_it = it.next();
			if (current_it.getSurname().equals(v))
			{
				result = true;
				break;
			}
		}
		return result;
	}
	
	public void removeItemBagS(Player p, String v)//permet de remove un item du bag du player en ne prenant en compte que le nom de l'item
	{
		
		if (p.getPlayerBag().isPresentItemInBag(v)==true)
		{
			Iterator<Item> it = p.getPlayerBag().getBag().iterator();
			while(it.hasNext())
			{
				Item current_it = it.next();
				if (current_it.getSurname().equals(v))
				{
					p.getLocation().addItem(current_it);
					p.getPlayerBag().setAddNegVolumeBag(-current_it.getVolume());
					it.remove();
					System.out.println(String.format("\n\t '%s throw down'", v));
					break;
				}
			}
			
		}
		else
			System.out.println("\n\t there is no such item in your bag \n\t You can verify with the command 'Mybag' ");
	}
	
	public void removeItemBag(Item v)
	{
		this.getBag().remove(v);
	}
	
	public Item printItemBag(String v)//retourne l'item dont on connaît le nom depuis la liste d'item du bag
	{
		Item current_it = null;
		Iterator<Item> it = this.getBag().iterator();
		while(it.hasNext())
		{
			current_it = it.next();
			if (current_it.getSurname().equals(v))
				break;
				
		}
		return current_it;
	}
	
	public void printItemInTheBag()//print tous les items du bag
	{
		if (this.getBag().size()!=0)
		{
			System.out.println("\n\t In your bag, there is :\n");
			for (Item i: this.getBag())
				System.out.print("> "+ i.getSurname()+"\t ");
			System.out.println("\n");
		}
		else
			System.out.println("\n Your bag is empty\n");
	}
}
