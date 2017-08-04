package kohoutek.warcraft.entitystuff;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import kohoutek.warcraft.SelectionRect;
import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.systems.AnimationSystem;
import kohoutek.warcraft.entitystuff.systems.BoundsRenderSystem;
import kohoutek.warcraft.entitystuff.systems.MovementSystem;
import kohoutek.warcraft.entitystuff.systems.AnimationRenderSystem;
import kohoutek.warcraft.entitystuff.systems.SelectionSystem;

public class EntityEngine extends Engine {
	public final AnimationRenderSystem ars;
	public final BoundsRenderSystem brs;
	public final MovementSystem ums;
	public final AnimationSystem as;
	public final SelectionSystem ss;
	
	public EntityEngine(final Batch batch, final SelectionRect selectionRect, final ShapeRenderer sRenderer, final AssetManager am){
		ars = new AnimationRenderSystem(batch);
		brs = new BoundsRenderSystem(sRenderer);
		ums = new MovementSystem();
		as = new AnimationSystem();
		ss = new SelectionSystem(selectionRect);
		
		addSystem(ars);
		addSystem(brs);
		addSystem(ums);
		addSystem(as);
		addSystem(ss);
				
		addEntity(new Footman(400,300,am));
		
	}

}
