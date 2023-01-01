package AllPlayers;

import AllLocations.Location;
import Allcommands.Command;

import java.io.*;

import Characters.Enemy;
import Clothes.Jacket;
import Clothes.Cloth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import Items.*;
import Weapons.Weapon;

public class Player implements Serializable {
	private int nbLife;
	private int degats;
	private String name;
	private final int NB_LIFE = 400;
	private Location current_loc = null;
	private Weapon currentWeapon=null;
	private Bag mybag = new Bag(0);
	private Key current_item;
	private ArrayList<String> hints = new ArrayList<>();
	static int count = 0;
	
	public Player(String Name)
	{
		this.name = Name;
		this.degats = 20;
		this.nbLife = 230;

	}
	
	public void present() {
		System.out.println("\n      Your description");
		if (this.currentWeapon!=null)
			System.out.println(String.format("\n\t your name : %s \n \t your pv : %d \n \t damage points: %d \n \t current volume of your bag %d/%d  \n", this.name, this.nbLife, this.degats+this.currentWeapon.getDegats(), this.getPlayerBag().getVolumeBag(),this.getPlayerBag().getVolumeMaxBag()));
		else
			System.out.println(String.format("\n\t your name : %s \n \t your pv : %d \n \t damage points: %d \n \t current volume of your bag %d/%d  \n", this.name, this.nbLife, this.degats, this.getPlayerBag().getVolumeBag(),this.getPlayerBag().getVolumeMaxBag()));
	}
	
	public void changeLocation(Location newLocation) {
		this.current_loc = newLocation;
	}
	
	public ArrayList<String> getHint(){//retourne la liste des hint que possede le player...
		return this.hints;
	}
	
	public void printPlayerHints()
	{
		if (this.getHint().isEmpty())
		{
			System.out.println("\n\tYou haven't found any hints yet...\n\n\t You should hurry...\n");
		}
		else
		{
			for(String value : this.getHint())
				System.out.print("\t > "+value);
			System.out.println("");
			
		}
	}
	
	public boolean playerHaveAllHints()//permet de savoir si le joueur a tte les hints pour resoudre l'enigme
	{
		if (this.hints.size()==1 && count==0)
			{
				System.out.println(String.format("On the paper there is written %s, I think it's a clue to get me out of here, I'll put it in my bag for later, there are probably more...",this.getHint().get(0)));
				count+=1;
			}
		return (this.hints.size()==11)?true:false;
	}
	
	public Location getLocation()
	{
		return this.current_loc;
	}
	
	public Key getPlayerCurrentItem()//retourne la cle que possède le joueur...
	{
		return this.current_item;
	}
	
	public int getNbDegats()//retourne son nombre de degats du joueur...
	{
		return this.degats;
	}

	
	public Weapon getPlayerCurrentWeapon()//retourne la current weapon du joueur...
	{
		return this.currentWeapon;
	}
	
	public void useItem(String v) {// egal à changeWeapon or useFood...
		Iterator <Item> it = this.getPlayerBag().getBag().iterator();//cree un iterator sue la liste d'item dans le bag du player...
		Item current_it = null;
		int count = 0;
		List<String> items = this.getPlayerBag().getBag().stream().map(x -> x.getSurname()).collect(Collectors.toList());
		if (items.contains(v))// si l'item v est present dans la liste d'item du bag
		{
			while(it.hasNext())
			{
				current_it = it.next();
				if (current_it.getSurname().equals(v) && (current_it instanceof Weapon))// si l'item est un weapon
				{
					if (this.currentWeapon!=null)// si le joueur a un weapon dejà dans sa main
						this.getPlayerBag().getBag().add(this.currentWeapon);// alors on rajoute l'ancien weapon dans son bag
					this.currentWeapon = (Weapon)current_it;// on met arme le joueur de la nouvelle weapon
					count += 1;
					System.out.println(String.format("\t you're now armed with %s (%s) \n",v,current_it.getClass().getSimpleName()));//l'arme et la classe de l'arme...
					break;
				}
				
				else if (current_it.getSurname().equals(v) && current_it instanceof Cloth)// si l'item est de type cloth
				{
					Cloth item = ((Cloth) current_it);
					if (this.getNbLife()+item.getAmountHp() <= NB_LIFE)// si le hp du joueur + l'incrementation est inferieur au hp max du joueur
					{
						this.setNbLife(item.getAmountHp()); // alors on augmente la vie du joueur par rapport au hp du cloth
						count += 1;
						System.out.println(String.format("\t%s worn, life increased, see it with the command 'My_stats' \n", v));
						break;
					}
				}
				else if (current_it.getSurname().equals(v) && current_it instanceof Key)//si l'item est une cle
				{
					if (this.current_item!=null)//si le joueur a dejà une cle dans sa main
						this.getPlayerBag().getBag().add(this.current_item);//alors on rajoute cette cle au bag
					
					this.current_item = (Key)current_it;//et on met equippe le joueur de la nouvelle
					count += 1;
					System.out.println(String.format("\tYou're equipped with a %s (%s)\n", v, current_it.getClass().getSimpleName()));
					break;
				}
				
			}
			if (count ==1)// si on a equipe le joueur ou arme le joueur d'une arme 
			{
				this.getPlayerBag().removeItemBag(current_it);//on le remove de la liste d'item du bag
				this.getPlayerBag().setAddNegVolumeBag(-current_it.getVolume());//et on augmente le volume du sac en question en fonction des item...
			}

		}
		else
			System.out.println("\n\tThat item is not in your Bag \n");//l'item que vous vous voulez utiliser n'est pas present dans votre bag
				
	}
	
	public void noWeapon()//permet de desarmer le joueur
	{
		if (this.getPlayerCurrentWeapon() == null)// si le joueur n'a pas d'arme dans la main
			System.out.println("\n\tYou have no weapon, you can check it with the command 'Holding'\n ");
		else//sinon
		{
			this.getPlayerBag().getBag().add((Item)this.currentWeapon);//on rajoute l'item dans le bag du player
			this.getPlayerBag().setAddNegVolumeBag(this.currentWeapon.getVolume());//on augemnte le volume du bag par rapport au volume du weapon ajouté
			System.out.println("\n\tWeapon packed away \n");
			this.currentWeapon = null;// et on dit que le joueur n'a plus d'arme, en mettant à null...
		}
	}
	
	public void noItem()//pareil que pour la fonction noWeapon sauf que c'est avec les clés...
	{
		if (this.getPlayerCurrentItem() == null)
			System.out.println("\n\tYou have no item, you can check it with the command 'Holding'\n ");
		else
		{
			this.getPlayerBag().getBag().add(this.current_item);
			this.getPlayerBag().setAddNegVolumeBag(this.current_item.getVolume());
			System.out.println("\n\tItem packed away\n");
			this.current_item = null;
		}
	}
	
	public int getNbLife()
	{
		return this.nbLife;
	}
	
	public void setNbLife(int life)
	{
		this.nbLife += life;
	}
	
	public void setCurrentItem(Item i)//on change la current_item du joueur par la nouvelle cle...
	{
		this.current_item = (Key) i;
	}
	
	public Bag getPlayerBag()//on renvoie le bag du joueur
	{
		return this.mybag;
	}
	
	public void addItemToBag(String v)//on ajoute l'item dans le bag
	{
		this.getPlayerBag().addItem(this,v);
	}
	
	public void removeItemFromBag(String v)//on remove l'item du bag mais en ne prenant en paramètre que le nom de l'item
	{
		this.getPlayerBag().removeItemBagS(this, v);
	}
	
	public void printItem()//on affiche les item que hold le joueur...
	{
		if (this.getPlayerCurrentWeapon()!= null)
			System.out.println(String.format("\n\tYour current weapon : %s\n", this.getPlayerCurrentWeapon().getSurname()));
		else
			System.out.println("\n\tYou don't have a weapon in your hands\n");
		
		if (this.getPlayerCurrentItem()!= null)
			System.out.println(String.format("\n\tYour current item : %s\n", this.getPlayerCurrentItem().getSurname()));
		else
			System.out.println("\n\t You don't have an item in your hands \n");
	}
	
	public void attackEnemy(Player p, Location loc)//on attaque les monstres de la current_location du joueur 
	{
		if (loc.getAllEnemies().size()==0)//s'il n'y a pas d'enemies...
		{
			System.out.println("\n\tThere is no enemies to kill");
			return;
		}
		else//sinon...
		{
			Iterator <Enemy> it = loc.getAllEnemies().iterator();//on cree un iterator sur la liste d'enemies de la location
			while(it.hasNext())
			{
				Enemy e = it.next();
				while(this.getNbLife()>0 && e.getEnemyHp()>0)//tant que le joueur est en vie, ou l'enemie courant en en vie
				{
					if (Math.random()>0.5){//l'enemie a 50% de chance de nous toucher avec son attaque...
						this.setNbLife(-e.getEnemyDeg());
						System.out.println("Oh no ! you just got hit by the monster !! You just took " + e.getEnemyDeg() + "damage");
				}
						
					if (this.getPlayerCurrentWeapon() != null)//si le joueur a une arme...
					{
						int max = 10; 
				        int min = 1; 
				        int range = (max - min) + 1;     
				     
				        // Générer un nombre aléatoire entre une plage spécifique  
				        int nbr = (int)(Math.random() * range) + min;
				        if ((nbr*(this.getPlayerCurrentWeapon().getpercent())/100)>2)// si la valeur aleatoire * le percent de toucher de l'arme est sup à 2...  
						{
							e.setEnemyLife(-p.getNbDegats());//on attaque...
							System.out.println("Nice hit !! You just gave " + p.getNbDegats() + " damage");
						}
					}
					else{//si on a pas d'arme
						e.setEnemyLife(-(p.getNbDegats()+2));//On fait moins de degats, en occurence :  le nb_degat du player - 2; 
						System.out.println("Nice hit !! You just gave " + p.getNbDegats() + " damage");
					}
				}
				
				if (e.getEnemyHp()<=0)
				{
					it.remove();
			        int max = 10; 
			        int min = 1; 
			        int range = (max - min) + 1;     
			     
			        // Générer un nombre aléatoire entre une plage spécifique  
			        int nbr = (int)(Math.random() * range) + min;
			        if (nbr > 6)
			        	p.getLocation().getLocationItems().add(new Jacket());

					
				}
				
				if (p.getNbLife()<=0)//si le joueur a un nb negatif de points de vie alors...
					break;
			}
		}
		
		
		if (loc.getAllEnemies().isEmpty() && p.getNbLife()>0)//si la liste d'enemies de la location est vide et que le joueur est tjrs vivant, alors 
		{
			loc.setQuitLocation(true);//on peut donc quitter la location
			System.out.println("\n\tYou have managed to kill all the monsters in the room \n");
		}
		else//sinon on est dead...
			System.out.println("\n\tTHE MONSTERS GOT THE BETTER OF YOU... \n\t YOU'RE DEAD");//if (loc.getAllEnemies()!=null && this.getNbLife()<=0)
		
		
	}
	
	
	public void draw(String v) {// cette fonction permet au joueur de pull un meuble v ...
		Set<String> items = this.getLocation().getPapers().keySet();//on cree un set base sur les key du dictionnaire papers de la location du joueur...
		if (items.contains(v))//si le meuble v que l'on veut pull est present...
		{
			if (this.getLocation().getPapers().get(v).isEmpty())// si sa value d'après le dictionnaire papers est vide alors...
			{
				System.out.println("\n\tThere is nothing to see here\n");
				return;
			}
			else//sinon
			{
				this.getHint().add(this.getLocation().getPapers().get(v));//on le rajoute à la liste de hint dont disposes le joueur
				this.getLocation().getPapers().replace(v, "");// on met à null ce meuble 
				System.out.println("\n\tYou found a hint, go check it with the command 'CLUES' \n");
			}
		}
		else if (v=="")//si le joueur n'a pas entre d'argument pour le mueble à pull alors
			System.out.println("\n\tThe command pull must be followed by a argument \n");
		else//alors le nom du meuble entré n'est pas present dans la current_location du joueur et dans ce cas...
			System.out.println("\n\tNo clues here..\n");
	}
	
	
	
	
}
