package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

import kohoutek.warcraft.Player;

public class OwnerComponent implements Component {
	public int id;
	
	public OwnerComponent(final int id){
		this.id = id;
	}

}
