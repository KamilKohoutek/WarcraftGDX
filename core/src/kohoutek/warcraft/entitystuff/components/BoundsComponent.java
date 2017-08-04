package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class BoundsComponent extends Rectangle implements Component {
	
	public BoundsComponent(float x, float y, float width, float height){
		super(x ,y, width, height);
	}

}
