package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.SpeedComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent.EntityState;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;

/**
 * This system manages movement of entities
 * @author Kamil Kohoutek
 */
public class MovementSystem extends IteratingSystem {
	
	private final ComponentMapper<StateComponent> 		stc 	= ComponentMapper.getFor(StateComponent.class);
	private final ComponentMapper<PositionComponent> 	pc 		= ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> 		bc 		= ComponentMapper.getFor(BoundsComponent.class);
	private final ComponentMapper<SpeedComponent> 		sc 		= ComponentMapper.getFor(SpeedComponent.class);
	private final ComponentMapper<TargetPointComponent> utpc 	= ComponentMapper.getFor(TargetPointComponent.class);
	
	private final ComponentMapper<AnimationComponent> 	ac = ComponentMapper.getFor(AnimationComponent.class);

	public MovementSystem() {
		super(Family.all(	StateComponent.class,
							PositionComponent.class, 
							BoundsComponent.class, 
							TargetPointComponent.class, 
							SpeedComponent.class
						).get()
		);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final StateComponent state = stc.get(entity);
		
		// if the entity is not meant to move, processing is pointless
		if(state.state != EntityState.MOVING) return;
		
		final PositionComponent 	pos 	= pc.get(entity);
		final BoundsComponent 		bounds 	= bc.get(entity);
		final SpeedComponent		speed 	= sc.get(entity);
		final TargetPointComponent 	point 	= utpc.get(entity);
		// may be null
		final AnimationComponent	anim 	= ac.get(entity);
		
		// get absolute coordinates of bounding rectangle's center
		final Vector2 center = bounds.getCenter(new Vector2());
		center.add(pos);
	
		// approx. if entity has reached its target
		if(center.epsilonEquals(point, 1f)){			
			if(anim != null) {
				// if entity has animation component
				state.state = EntityState.IDLE;
				anim.animation.setPlayMode(PlayMode.REVERSED);	// stops the animation
			}			
		} else {
			// the movement
			final Vector2 direction = new Vector2(point).sub(center).nor();
			if(direction.isZero()) return;
			pos.x += speed.speed * direction.x * deltaTime;
			pos.y += speed.speed * direction.y * deltaTime;
			
			state.state = EntityState.MOVING;
		}	
	}
}
