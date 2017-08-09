package kohoutek.warcraft;

import kohoutek.warcraft.Common.Race;

public class Player {
	public final Race race;
	
	public int gold = 0;
	public int wood = 0;
	public int food = 0;
	
	public Player(final Race race){
		this.race = race;
	}
}
