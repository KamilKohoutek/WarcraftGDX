package kohoutek.warcraft.entitystuff.components;

import com.badlogic.ashley.core.Component;

/**
 * Component that marks an entity as selectable by its owner player
 * @author Kamil Kohoutek
 */

public class SelectableComponent implements Component {
	public boolean selected = false;
	
	public SelectableComponent() { }
	
	public SelectableComponent(final boolean selected){
		this.selected = selected;
	}
	
	}

