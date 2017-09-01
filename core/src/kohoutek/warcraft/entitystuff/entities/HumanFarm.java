package kohoutek.warcraft.entitystuff.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import kohoutek.warcraft.Player;
import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.HealthComponent;
import kohoutek.warcraft.entitystuff.components.OwnerComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.ScaleComponent;


public class HumanFarm extends Entity {
	public static final int BUILD_TIME 		= 30;
	public static final int COST_GOLD 		= 500;
	public static final int COST_LUMBER 	= 300;	
	public static final int HP			 	= 800;
	// TODO build reward
	
	private static final TextureRegion[] REGIONS = new TextureRegion[4];
	
	public HumanFarm(final int x, final int y, final AssetManager am, final Player owner, final boolean finishedOnSpawn) {
		super();
		
		final Animation<TextureRegion> anim = new Animation<TextureRegion>(BUILD_TIME/4f, REGIONS);
		anim.setPlayMode(PlayMode.NORMAL);	
		
		add(new AnimationComponent(anim));
		add(new PositionComponent(x, y));
		add(new BoundsComponent(24, 24, 48, 48));
		add(new ScaleComponent(2, 2));
		add(new RenderableComponent());
		add(new OwnerComponent(owner.id));
		add(new HealthComponent(HP, finishedOnSpawn ? HP : 0));	
	}
	
	public static void initRegions(final AssetManager am) {
		final Texture sheet = am.get("../core/assets/BUILDINGS_H_edit.png");
		final TextureRegion[][] tiles = TextureRegion.split(sheet, 48, 48);
		
		REGIONS[0] = tiles[0][0];
		REGIONS[1] = tiles[0][1];
		REGIONS[2] = tiles[1][1];
		REGIONS[3] = tiles[1][0];
	}

}
