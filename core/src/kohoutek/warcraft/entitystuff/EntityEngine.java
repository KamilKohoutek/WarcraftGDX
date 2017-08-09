package kohoutek.warcraft.entitystuff;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.Player;
import kohoutek.warcraft.SelectionRect;
import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.systems.AnimationOrientationSystem;
import kohoutek.warcraft.entitystuff.systems.BoundsRenderSystem;
import kohoutek.warcraft.entitystuff.systems.MovementSystem;
import kohoutek.warcraft.entitystuff.systems.AnimationRenderSystem;
import kohoutek.warcraft.entitystuff.systems.SelectionSystem;
import kohoutek.warcraft.entitystuff.systems.TargetSystem;

public class EntityEngine extends Engine {
	public final AnimationRenderSystem 		ars;
	public final BoundsRenderSystem 		brs;
	public final MovementSystem 			ms;
	public final AnimationOrientationSystem aos;
	public final SelectionSystem 			ss;
	public final TargetSystem				ts;
	
	public EntityEngine(final Batch batch, 
						final SelectionRect selectionRect, 
						final ShapeRenderer sRenderer, 
						final Vector2 targetPoint, 
						final AssetManager am,
						final Player[] players){
		
		ars = new AnimationRenderSystem(batch);
		brs = new BoundsRenderSystem(sRenderer);
		ms = new MovementSystem();
		aos = new AnimationOrientationSystem();
		ss 	= new SelectionSystem(selectionRect);
		ts = new TargetSystem(targetPoint);
		
		addSystem(aos);
		addSystem(ars);
		addSystem(ms);
		addSystem(ss);
		addSystem(ts);
		addSystem(brs);
		
		/*
		for(int i = 0; i < 4096; i++) {
			addEntity(new Footman(MathUtils.random(64, 1800), MathUtils.random(64,1750),am,players[0]));
		}*/
		
	}
}
