package Characters;
import java.io.*;

public class Enemy implements Serializable{
	private int HP;
	private final int DEG;
	private final String NAME;
	
	public Enemy(String name, int deg, int hp)
	{
		this.NAME = name;
		this.DEG = deg;
		this.HP = hp;
	}
	
	public int getEnemyHp()
	{
		return this.HP;
	}
	
	public int getEnemyDeg()
	{
		return this.DEG;
	}
	
	public String getEnemyName()
	{
		return this.NAME;
	}
	
	public void setEnemyLife(int life)
	{
		this.HP += life;
	}
}
