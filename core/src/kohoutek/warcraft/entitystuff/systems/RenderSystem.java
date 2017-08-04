package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.ScaleComponent;

public class RenderSystem extends IteratingSystem {
	
	private final Batch batch;
	private final ComponentMapper<AnimationComponent> ac = ComponentMapper.getFor(AnimationComponent.class);
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	
	@SuppressWarnings("unchecked")
	public RenderSystem(Batch batch) {
		super(Family.all(RenderableComponent.class, AnimationComponent.class, PositionComponent.class).get());
		this.batch = batch;
		
	}
	
    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final AnimationComponent anim = ac.get(entity);
		final PositionComponent pos = pc.get(entity);
		
		TextureRegion frame = anim.animation.getKeyFrame(anim.stateTime);
		
		final ScaleComponent sc = entity.getComponent(ScaleComponent.class);
		float scx = 1;
		float scy = 1;
		if(sc != null){
			scx = sc.x;
			scy = sc.y;
		}	
		batch.draw(frame, pos.x, pos.y, frame.getRegionWidth() * scx, frame.getRegionHeight() * scy);
		
		anim.stateTime += deltaTime;	
	}

}
