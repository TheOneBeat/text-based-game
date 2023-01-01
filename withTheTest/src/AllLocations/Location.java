package AllLocations;

import java.util.ArrayList;


import java.io.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Set;

import AllExits.Door;
import Characters.Enemy;
import Items.Item;

public abstract class Location implements Serializable {
	protected String Name;
	protected String description; 
	protected HashMap <String, Door> theLocation = new HashMap<>();
	protected HashMap <String,String> papers = new HashMap<>();
	protected ArrayList<? super Item> allItems = new ArrayList<>();
	protected ArrayList<Enemy> allEnemies = new ArrayList<>();
	protected boolean quitLocation;
	protected boolean isLock;
	
	public Location(String name)
	{
		this.Name = name;
		this.theLocation.put(this.Name, new Door(this));
		this.quitLocation = true;
		this.isLock = false;
	}
	
	public void AddExitLocation(String name, Door d) {
		this.theLocation.put(name, d);
	}
	
	public void setQuitLocation(boolean v)
	{
		this.quitLocation = v;
	}
	
	public void fillPapers(String k, String v)
	{
		this.getPapers().put(k, v);
	}

	
	public HashMap<String,String> getPapers()
	{
		return this.papers;
	}
	
	public void printLocationGoods() {// affiche les biens disponibles dans la current_location
		Set<String> items = this.getPapers().keySet();//on cree un set de furnitures (String) present dans le dictionnaire papers de la location ... 
		if (items.size()==0)//si ce set de clues est vie alors...
			System.out.println("\n\t There is nothing to dig in here \n");
		else//sinon on les affiche
		{
			System.out.println(String.format("\t There is %d Goods to Pull \n\n\t We have:\n ", items.size()));
			
			for(String value:items)
				System.out.print("\t > "+value);
			System.out.println("\n");
		}
	}
	
	public boolean getQuitLocation()
	{
		return this.quitLocation;
	}
	
	public void setIsLock(boolean v)
	{
		this.isLock = v;
	}
	
	public boolean getIsLock()
	{
		return this.isLock;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public void printlocation()
	{
		System.out.println(String.format("%s\n", this.description));
	}
	
	public void presentLoc()
	{
		Set <String>allinkedLocations = this.theLocation.keySet();//creation de set de locations
		if (allinkedLocations.size() > 1)//si la taille de ce set est sup à 1 alors  
		{//on affiche les locations disponibles pour la current_location
			System.out.println("   > all locations that you can currently go now : \n");
			for(String v : allinkedLocations)
			{
				
				if (v != this.getName())//pour l'afficher on verifie si le current v est different du nom de la current location, si c'est c'est le cas on l'affiche...
					System.out.print("\t"+String.format("> %s     ", v));
			}
			System.out.println("\n");
		}
	}
	
	public Set<String> getAllWays(){
		return this.theLocation.keySet();//retourne un set de String de tous les locations disponibles pour une current_location, y compris le nom de la current_location
	}
	
	public HashMap<String, Door> getDict(){
		return this.theLocation;
	}
	
	public void removeItemS(String v)//supprime un item de la location en se basant uniquement sur son nom
	{
		@SuppressWarnings("unchecked")
		Iterator<Item> it = (Iterator<Item>) this.getLocationItems().iterator();
		while(it.hasNext())
		{
			Item current_it = it.next();
			if (current_it.getSurname().equals(v))
			{
				it.remove();
				break;
			}
				
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<? super Item> getLocationItems()//retourne la liste de tous les items confondus disponibles dans la current_location (weapon, key, cloth )
	{
		return this.allItems;
	}
	
	public void addItem(Item v)
	{
		this.getLocationItems().add(v);
	}
	
	public Item getItemfromLocation(String v)//retourne l'item de la location dont le nom correspond au paramètre de la fonction.
	{
		Item current_it = null;
		@SuppressWarnings("unchecked")
		Iterator<Item> it = (Iterator<Item>) this.getLocationItems().iterator();
		while(it.hasNext())
		{
			current_it = it.next();
			if (current_it.getSurname().equals(v))
				break;
		}
		return current_it;
	}
	
	public void printLocationItem()//print tous les items de la location + le nom de leurs classes
	{
		Item current_it = null;
		if (this.getLocationItems().size()!=0)
		{
			System.out.println("   All Items available here : \n");
			@SuppressWarnings("unchecked")
			Iterator<Item> it = (Iterator<Item>) this.getLocationItems().iterator();
			while(it.hasNext())
			{
				current_it = it.next();
				
				System.out.print(String.format("  > %s (%s)", current_it.getSurname(), current_it.getClass().getSimpleName()));
			}
			System.out.println("\n");
		}
		else
			System.out.println("\tThere are no items here\n");
		
	}
	
	public ArrayList<Enemy> getAllEnemies()
	{
		return this.allEnemies;
	}
	
	
	public void showMonsters()
	{
		System.out.println(String.format("There is %d monster(s) in this location \n", this.getAllEnemies().size()));
		
		if (this.getAllEnemies().size()>0)
		{
			for(Enemy e: this.allEnemies)
			{
				System.out.println(String.format("\n   monster's name: %s\t | his hp: %d\t | his deg :%d\t \n",e.getEnemyName(), e.getEnemyHp(),e.getEnemyDeg()));
			}
		}
	}
	
}
