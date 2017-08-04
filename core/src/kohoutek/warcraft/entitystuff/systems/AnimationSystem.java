package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.entitystuff.components.Animation8xComponent;
import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.UnitAnimationComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;

public class AnimationSystem extends IteratingSystem {
	
	private final ComponentMapper<Animation8xComponent> ac = ComponentMapper.getFor(Animation8xComponent.class);
	private final ComponentMapper<TargetPointComponent> tpc = ComponentMapper.getFor(TargetPointComponent.class);
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);


	@SuppressWarnings("unchecked")
	public AnimationSystem() {
		super(Family.all(RenderableComponent.class, Animation8xComponent.class, PositionComponent.class, BoundsComponent.class, TargetPointComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {		
		final TargetPointComponent point = tpc.get(entity);
		final PositionComponent pos = pc.get(entity);
		final Animation8xComponent anim = ac.get(entity);
		final BoundsComponent bounds = bc.get(entity);
		
		Vector2 center = bounds.getCenter(new Vector2());
		center.add(pos);
		int angle = (int)Math.toDegrees(Math.atan2(point.y - center.y, point.x - center.x ));
	   	if (angle < 0) {
    	    angle += 360;
    	}
		
	    int index = 0;
	    if(angle >= 30 && angle <= 60){
	        index = 1;
	    } else if (angle > 60 && angle < 120){
	        index = 2;
	    } else if (angle >= 120 && angle <= 150){
	        index = 3;
	    } else if (angle > 150 && angle < 210){
	        index = 4;
	    } else if (angle >= 210 && angle <= 240){
	        index = 5;
	    } else if (angle > 240 && angle < 300){
	        index = 6;
	    } else if (angle >= 300 && angle <= 330){
	        index = 7;
	    }
	      
	    
	    entity.add(new AnimationComponent(anim.anims.get(index)));
	    entity.remove(Animation8xComponent.class);
	    
	}

}
