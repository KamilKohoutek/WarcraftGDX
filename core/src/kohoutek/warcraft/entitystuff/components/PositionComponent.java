package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

@SuppressWarnings("serial")
public class PositionComponent extends Vector2 implements Component {

	public PositionComponent(float x, float y){
		super(x, y);
	}
	

}
