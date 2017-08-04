package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

public class SpeedComponent implements Component {
	public int speed;
	
	public SpeedComponent(int speed){
		this.speed = speed;
	}

}
