package AllTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Allcommands.Command;
import Clothes.*;
import Game.University;
import Items.Key;
import Weapons.*;


class UniversityTest {
	
	private University theUniv =  new University("kratos"); ;
	private Coat cloth1 = new Coat();
	private Weapon weapon1 = new Ironbar("Hammer", 20, 25, 70);
	private Weapon weapon2 = new Knife("Katana", 25, 20, 50);
	private Key key1 = new Key("keyone", 5);
	
	@Test
	void bagEmpty()
	{
		Command.take(theUniv.getPlayer(), "");
		assertTrue(theUniv.getPlayer().getPlayerBag().getBag().isEmpty());
	}
	
	@Test
	void bagNotEmpty()
	{
		theUniv.getPlayer().getPlayerBag().getBag().add(weapon1);
		assertFalse(theUniv.getPlayer().getPlayerBag().getBag().isEmpty());
		assertEquals(weapon1.getSurname(), theUniv.getPlayer().getPlayerBag().getBag().get(0).getSurname());
	}
	
	
	@Test
	void UseItemNotCloth()
	{
		theUniv.getPlayer().useItem("sercs");
		assertNull(theUniv.getPlayer().getPlayerCurrentWeapon());
		assertNull(theUniv.getPlayer().getPlayerCurrentItem());
	}
	
	@Test
	void ThrowItemFromBagToLocation()
	{
		theUniv.getPlayer().getPlayerBag().getBag().add(weapon1);
		int nbBag = theUniv.getPlayer().getPlayerBag().getBag().size();
		int nbLoc = theUniv.getPlayer().getLocation().getLocationItems().size();
		Command.Throw(theUniv.getPlayer(), weapon1.getSurname());
		assertEquals(nbBag-1,theUniv.getPlayer().getPlayerBag().getBag().size());
		assertEquals(nbLoc+1,theUniv.getPlayer().getLocation().getLocationItems().size());
	}
	
	@Test
	void UseItemClothCoatWeaponKatana()
	{
		int nbVolBag = theUniv.getPlayer().getPlayerBag().getVolumeBag() + cloth1.getVolume();
		theUniv.getPlayer().getLocation().addItem(cloth1);
		Command.take(theUniv.getPlayer(), cloth1.getSurname());
		assertTrue(theUniv.getPlayer().getPlayerBag().getBag().contains(cloth1));
		
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		int nbLife = theUniv.getPlayer().getNbLife()+cloth1.getAmountHp();
		
		Command.use(theUniv.getPlayer(), cloth1.getSurname());
		assertEquals(nbLife,theUniv.getPlayer().getNbLife());
		assertFalse(theUniv.getPlayer().getPlayerBag().getBag().contains(cloth1));
		
		nbVolBag -= cloth1.getVolume();
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		theUniv.getPlayer().getLocation().addItem(weapon2);
		Command.take(theUniv.getPlayer(), weapon2.getSurname());
		assertTrue(theUniv.getPlayer().getPlayerBag().getBag().contains(weapon2));
		
		nbVolBag += weapon2.getVolume();
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		
		Command.use(theUniv.getPlayer(), weapon2.getSurname());
		assertEquals(weapon2.getSurname(),theUniv.getPlayer().getPlayerCurrentWeapon().getSurname());
		
		nbVolBag -= weapon2.getVolume();
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
	}
	
	@Test 
	void noWeapnoItem()
	{
		theUniv.getPlayer().getLocation().addItem(key1);
		int nbVolBag = theUniv.getPlayer().getPlayerBag().getVolumeBag() + key1.getVolume();
		
		Command.take(theUniv.getPlayer(), key1.getSurname());
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		Command.use(theUniv.getPlayer(), key1.getSurname());
		assertEquals(key1.getSurname(),theUniv.getPlayer().getPlayerCurrentItem().getSurname());
		
		nbVolBag -= key1.getVolume();
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		theUniv.getPlayer().getLocation().addItem(weapon2);
		Command.take(theUniv.getPlayer(), weapon2.getSurname());
		
		nbVolBag += weapon2.getVolume();
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		Command.use(theUniv.getPlayer(), weapon2.getSurname());
		
		nbVolBag -= weapon2.getVolume();
		assertEquals(weapon2.getSurname(),theUniv.getPlayer().getPlayerCurrentWeapon().getSurname());
		assertEquals(nbVolBag,theUniv.getPlayer().getPlayerBag().getVolumeBag());
		
		
		Command.noWeap(theUniv.getPlayer());
		Command.noItems(theUniv.getPlayer());
		assertNull(theUniv.getPlayer().getPlayerCurrentWeapon());
		assertNull(theUniv.getPlayer().getPlayerCurrentItem());
		
	}
	
	
	
	



}
