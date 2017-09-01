package kohoutek.warcraft;

import kohoutek.warcraft.Race;

public class Player implements java.io.Serializable {
	private static final long serialVersionUID = -5942280595435701348L;
	
	public final Race race;
	public final int id;
	
	public int gold = 0;
	public int lumber = 0;
	public int food = 0;
	
	public Player(final Race race, final int id){
		this.race = race;
		this.id = id;
	}
}
