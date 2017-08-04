package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

public class ScaleComponent implements Component {
	public float x;
	public float y;
	
	public ScaleComponent(float x, float y){
		this.x = x;
		this.y = y;
	}

}
