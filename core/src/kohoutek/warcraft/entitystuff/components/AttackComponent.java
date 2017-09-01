package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class AttackComponent implements Component {
	public final int damage;
	public final int range;
	public final float speed;
	
	public AttackComponent(int damage, float speed, int range){
		this.damage = damage;
		this.speed = speed;
		this.range = range;
	}

}
