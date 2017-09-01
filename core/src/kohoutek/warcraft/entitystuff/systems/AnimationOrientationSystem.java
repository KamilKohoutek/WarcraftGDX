package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;

import kohoutek.warcraft.entitystuff.components.Animation8xComponent;
import kohoutek.warcraft.entitystuff.components.AnimationComponent;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.TargetPointComponent;

/**
 * This system ensures that entities with 8x animations are rendered with correct animations based on their orientation
 * @author Kamil Kohoutek
 */
public class AnimationOrientationSystem extends IteratingSystem {
	
	private final ComponentMapper<Animation8xComponent> ac = ComponentMapper.getFor(Animation8xComponent.class);
	private final ComponentMapper<TargetPointComponent> tpc = ComponentMapper.getFor(TargetPointComponent.class);
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);


	public AnimationOrientationSystem() {
		super(Family.all(RenderableComponent.class, 
						 Animation8xComponent.class, 
						 PositionComponent.class, 
						 BoundsComponent.class, 
						 TargetPointComponent.class
						).get()
		);
		//setProcessing(false);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {		
		final TargetPointComponent 	point 	= tpc.get(entity);
		final PositionComponent 	pos 	= pc.get(entity);
		final Animation8xComponent 	anim 	= ac.get(entity);
		final BoundsComponent 		bounds 	= bc.get(entity);
		
		//get absolute coordinates of bounding rectangle's center
		final Vector2 center = new Vector2();
		bounds.getCenter(center);		
		center.add(pos);
		
		// calculate facing angle
		int angle = (int)Math.toDegrees(Math.atan2(point.y - center.y, point.x - center.x));
		// ensure 0-360 deg range
    	angle += (angle < 0) ? 360 : 0;
		
	   	// determine index of animation based on that angle
	    int i = 0;
	    if(angle >= 30 && angle <= 60){
	        i = 1;
	    } else if (angle > 60 && angle < 120){
	        i = 2;
	    } else if (angle >= 120 && angle <= 150){
	        i = 3;
	    } else if (angle > 150 && angle < 210){
	        i = 4;
	    } else if (angle >= 210 && angle <= 240){
	        i = 5;
	    } else if (angle > 240 && angle < 300){
	        i = 6;
	    } else if (angle >= 300 && angle <= 330){
	        i = 7;
	    }
		
	    anim.anims.get(i).setPlayMode(PlayMode.LOOP_PINGPONG);
	    entity.add(new AnimationComponent(anim.anims.get(i)));
	    entity.remove(Animation8xComponent.class);
	}

}
