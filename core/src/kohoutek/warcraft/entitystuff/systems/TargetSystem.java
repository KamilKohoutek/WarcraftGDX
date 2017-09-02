package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.entitystuff.components.Animation8xComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.SelectedComponent;
import kohoutek.warcraft.entitystuff.components.StateAnimationsComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;
import kohoutek.warcraft.entitystuff.components.StateComponent.EntityState;

/**
 * TargetSystem.java
 * @author Kamil Kohoutek
 */
public class TargetSystem extends IteratingSystem {
	
	/** reference to the targetPoint being used **/
	private final Vector2 targetPoint;
	
	/** deviation from targetPoint when 2+ entities are moving towards the same point**/
	private int gap = 0;
	private int mul = 40; // multiplier
	
	private final ComponentMapper<StateAnimationsComponent> sac = ComponentMapper.getFor(StateAnimationsComponent.class);
	private final ComponentMapper<StateComponent> sc = ComponentMapper.getFor(StateComponent.class);

	public TargetSystem(final Vector2 targetPoint) {
		super(Family.all(RenderableComponent.class, SelectedComponent.class, PositionComponent.class, BoundsComponent.class).get());
		this.targetPoint = targetPoint;
		
		// should only be processed when user right-clicks
		setProcessing(false);

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {	
		// these may be null
		final StateAnimationsComponent stateAnims = sac.get(entity);
		final StateComponent state = sc.get(entity);
			
		gap -= (gap >= mul) ? mul : 0;
		entity.add(new TargetPointComponent(targetPoint.x + gap, targetPoint.y));	

		if(stateAnims != null) {
			entity.add(new Animation8xComponent(stateAnims.movement));
		}
		if(state != null) {
			state.state = EntityState.MOVING;
		} else {
			entity.add(new StateComponent(EntityState.MOVING));
		}
	
				
		setProcessing(false);			
	}
	
	/**
	 * Call this after assigning targetPoint coords
	 * @param selectedCount
	 */
	public void prepare(final int selectedCount) {
		gap = selectedCount * mul;
		setProcessing(true);
	}

}
