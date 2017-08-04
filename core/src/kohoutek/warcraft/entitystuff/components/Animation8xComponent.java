package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation8xComponent implements Component {
	
	// indices of animation orientations
	public static final short E		= 0;		// east
	public static final short NE 	= 1;		// north-east
	public static final short N 	= 2;		// north
	public static final short NW 	= 3;		// north-west
	public static final short W 	= 4;		// west
	public static final short SW 	= 5;		// south-west
	public static final short S 	= 6;		// south
	public static final short SE 	= 7;		// south east
	
	public final Array<Animation<TextureRegion>> anims;

	public Animation8xComponent(final Array<Animation<TextureRegion>> anims8) {
		anims = anims8;
	}


}
