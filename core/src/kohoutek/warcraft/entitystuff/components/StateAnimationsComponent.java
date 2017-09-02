package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StateAnimationsComponent implements Component {
	public final Array<Animation<TextureRegion>> movement;
	public final Array<Animation<TextureRegion>> attack;
	public final Animation<TextureRegion> death;


	public StateAnimationsComponent(final Array<Animation<TextureRegion>> movement, 
									final Array<Animation<TextureRegion>>  attack,
									final Animation<TextureRegion>  death
									) 
	{				
		this.movement = movement;
		this.attack = attack;
		this.death = death;

	}

}
