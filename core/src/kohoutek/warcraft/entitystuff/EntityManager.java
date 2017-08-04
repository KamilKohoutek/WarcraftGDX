package kohoutek.warcraft.entitystuff;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.MotionComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.SelectionComponent;
import kohoutek.warcraft.entitystuff.components.SpeedComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;
import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.systems.AnimationSystem;
import kohoutek.warcraft.entitystuff.systems.RenderSystem;
import kohoutek.warcraft.entitystuff.systems.MovementSystem;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;

public class EntityManager {
	
	private final Engine engine;
	private final RenderSystem rs;
	private final MovementSystem ums;
	private final AnimationSystem as;
	
	public EntityManager(final Engine engine, final Batch batch, final AssetManager am){
		this.engine = engine;
		rs = new RenderSystem(batch);
		ums = new MovementSystem();
		as = new AnimationSystem();
		
		engine.addSystem(rs);
		engine.addSystem(ums);
		engine.addSystem(as);
		
		/*
		Entity e = new Entity();
	
		
		Texture sheet = am.get("C:/Users/Kamil/Documents/warcraft/warcraft/res/FOOTMAN.png");
		TextureRegion a = new TextureRegion(sheet, 2 * 48, 0, 48, 48);
		TextureRegion b = new TextureRegion(sheet, 2 * 48, 48, 48, 48);
		TextureRegion c = new TextureRegion(sheet, 2 * 48, 2 * 48, 48, 48);	
		Animation<TextureRegion> anim = new Animation<TextureRegion>(0.25f, new TextureRegion[]{a,b,c});
		anim.setPlayMode(PlayMode.LOOP_PINGPONG);
		
		e.add(new RenderableComponent())
			.add(new MotionComponent())
			.add(new PositionComponent(400,300))
			.add(new AnimationComponent(anim))
			.add(new SpeedComponent(64))
			.add(new TargetPointComponent(600, 300))
			.add(new SelectionComponent());
		
		engine.addEntity(e);*/
		
		engine.addEntity(new Footman(400,300,am));
		


	}
	
	public final void update(float delta){
		engine.update(delta);
	}


}
