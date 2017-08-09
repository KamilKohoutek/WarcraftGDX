package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

import kohoutek.warcraft.Player;

/**
 * Component that marks an entity as selectable by its owner player
 * @author Kamil Kohoutek
 */

public class SelectableComponent implements Component {
	public Player selectedBy = null;
	
	public SelectableComponent() { }
	
	
	}

