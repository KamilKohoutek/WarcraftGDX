package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {
	public Animation<TextureRegion> animation;
	public float stateTime 	= 0;
	
	public AnimationComponent(final Animation<TextureRegion> animation){
		this.animation = animation;
	}
	


}
