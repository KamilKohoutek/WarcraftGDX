package kohoutek.warcraft.entitystuff.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import kohoutek.warcraft.entitystuff.components.Animation8xComponent;
import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.AttackComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.HealthComponent;
import kohoutek.warcraft.entitystuff.components.MotionComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.ScaleComponent;
import kohoutek.warcraft.entitystuff.components.SpeedComponent;
import kohoutek.warcraft.entitystuff.components.UnitAnimationComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;
import static kohoutek.warcraft.entitystuff.components.Animation8xComponent.*;

public class Footman extends Entity {
	
	public Footman(int x, int y, final AssetManager am){
		super();

		final PositionComponent pos = new PositionComponent(x, y);
		final Vector2 initialTargetPoint = new Vector2(x + 48, y + 48 + 100);	// for animation
		
		final Array<Animation<TextureRegion>> movement = new Array<Animation<TextureRegion>>(8);
		final Array<Animation<TextureRegion>> attack = new Array<Animation<TextureRegion>>(8);
		final Array<Animation<TextureRegion>> death = new Array<Animation<TextureRegion>>(8);
				
		final Texture sheet = am.get("C:/Users/Kamil/Documents/warcraft/warcraft/res/FOOTMAN.png");
		final TextureRegion[][] tiles = TextureRegion.split(sheet, 48, 48);
				
		
	     //walk animations
		Animation<TextureRegion> anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{tiles[0][2],tiles[1][2],tiles[2][2]});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(E, anim);
		
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{tiles[0][1],tiles[1][1],tiles[2][1]});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(NE, anim);
		
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{tiles[0][0],tiles[1][0],tiles[2][0]});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(N,  anim);
		
		TextureRegion flip1 = new TextureRegion(tiles[0][1]);
		flip1.flip(true, false);
		TextureRegion flip2 = new TextureRegion(tiles[1][1]);
		flip2.flip(true, false);
		TextureRegion flip3 = new TextureRegion(tiles[2][1]);
		flip3.flip(true, false);
		
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{flip1,flip2,flip3});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(NW,  anim);
		
		flip1 = new TextureRegion(tiles[0][2]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][2]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][2]);
		flip3.flip(true, false);
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{flip1,flip2,flip3});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(W,  anim);
		
		flip1 = new TextureRegion(tiles[0][3]);
		flip1.flip(true, false);
		flip2 = new TextureRegion(tiles[1][3]);
		flip2.flip(true, false);
		flip3 = new TextureRegion(tiles[2][3]);
		flip3.flip(true, false);

		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{flip1,flip2,flip3});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(SW,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{tiles[0][4],tiles[1][4],tiles[2][4]});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(S,  anim);
		
		anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{tiles[0][3],tiles[1][3],tiles[2][3]});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		movement.insert(SE,  anim);	
		
		add(pos);
		add(new BoundsComponent(0,0,96,96));
		add(new RenderableComponent());
		add(new HealthComponent(60, 60));
		add(new SpeedComponent(32));
		add(new AttackComponent(2, 1, 0));
		add(new TargetPointComponent(initialTargetPoint.x, initialTargetPoint.y));
		add(new Animation8xComponent(movement));
		add(new ScaleComponent(2 ,2));
	}
}
