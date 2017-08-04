package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

public class AttackComponent implements Component {
	public int damage;
	public final int range;
	public float speed;
	
	public AttackComponent(int damage, float speed, int range){
		this.damage = damage;
		this.speed = speed;
		this.range = range;
	}

}
