package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * 
 * @author Kamil Kohoutek
 */

@SuppressWarnings("serial")
public class TargetPointComponent extends Vector2 implements Component {
	
	public TargetPointComponent(float x, float y){
		super(x, y);
	}
	
	

}
