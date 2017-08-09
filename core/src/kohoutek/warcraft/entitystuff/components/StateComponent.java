package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

public class StateComponent implements Component {
	public static enum EntityState { ATTACKING, MOVING, DYING, IDLE }
	
	public EntityState state = EntityState.IDLE;
	
	public StateComponent(final EntityState state){
		this.state = state;
	}
	

}
