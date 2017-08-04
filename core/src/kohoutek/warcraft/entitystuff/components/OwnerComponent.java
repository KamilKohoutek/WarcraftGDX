package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

import kohoutek.warcraft.Player;

public class OwnerComponent implements Component {
	public Player owner;
	
	public OwnerComponent(Player player){
		owner = player;
	}

}
