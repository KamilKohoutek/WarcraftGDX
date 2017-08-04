package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

public class HealthComponent implements Component {
	public int maxHP;
	public int hp;
	
	public HealthComponent(int maxHP, int hp){
		this.maxHP = maxHP;
		this.hp = hp;
	}

}
