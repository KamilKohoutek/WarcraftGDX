package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.ScaleComponent;

/**
 * System for drawing animations
 * @author Kamil Kohoutek
 */
public class AnimationRenderSystem extends IteratingSystem {
	
	private final Batch batch;
	private final ComponentMapper<AnimationComponent> ac = ComponentMapper.getFor(AnimationComponent.class);
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<ScaleComponent> sc = ComponentMapper.getFor(ScaleComponent.class);
	
	public AnimationRenderSystem(Batch batch) {
		super(Family.all(RenderableComponent.class, AnimationComponent.class, PositionComponent.class, ScaleComponent.class).get());
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
		final TextureRegion frame = anim.animation.getKeyFrame(anim.stateTime);		
		final ScaleComponent scale = sc.get(entity);		

		batch.draw(frame, pos.x, pos.y, frame.getRegionWidth() * scale.x, frame.getRegionHeight() * scale.y);
		
		anim.stateTime += deltaTime;	
	}

}
