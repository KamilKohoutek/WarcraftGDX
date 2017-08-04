package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.MotionComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.SpeedComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;

/**
 * This system manages movement of entities
 * @author Kamil Kohoutek
 */
public class MovementSystem extends IteratingSystem {
	
	/* NEVER FORGET: +Y is UP */
	
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);
	private final ComponentMapper<SpeedComponent> sc = ComponentMapper.getFor(SpeedComponent.class);
	private final ComponentMapper<TargetPointComponent> utpc = ComponentMapper.getFor(TargetPointComponent.class);

	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Family.all(MotionComponent.class, PositionComponent.class, BoundsComponent.class, TargetPointComponent.class, SpeedComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final PositionComponent pos = pc.get(entity);
		final BoundsComponent bounds = bc.get(entity);
		final SpeedComponent speed = sc.get(entity);
		final TargetPointComponent point = utpc.get(entity);
		
		final Vector2 center = bounds.getCenter(new Vector2());
		center.add(pos);

		
		if(MathUtils.round(center.x) == MathUtils.round(point.x) && MathUtils.round(center.y) == MathUtils.round(point.y)){
			entity.remove(MotionComponent.class);
		} else {	
			Vector2 direction = new Vector2(point).sub(center).nor();
			if(direction.isZero()) return;
			pos.x += speed.speed * direction.x * deltaTime;
			pos.y += speed.speed * direction.y * deltaTime;
		}

		
	}

}
