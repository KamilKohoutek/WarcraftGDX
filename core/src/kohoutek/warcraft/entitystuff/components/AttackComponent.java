package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class AttackComponent implements Component {
	public final int damage;
	public final int range;
	public final float speed;
	public final Entity victim;
	
	public AttackComponent(int damage, float speed, int range, Entity victim){
		this.damage = damage;
		this.speed = speed;
		this.range = range;
		this.victim = victim;
	}

}
