package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;

import kohoutek.warcraft.SelectionRect;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.OwnerComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.SelectedComponent;

/**
 * This system manages selecting entities by player
 * @author Kamil Kohoutek
 */
public class SelectionSystem extends IteratingSystem {
	public static final int SELECTED_MAX = 1;
	
	/** reference to the selectionRect instance **/
	private final SelectionRect selectionRect;
	private int selectedCount = 0;
	
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);
	private final ComponentMapper<OwnerComponent> oc = ComponentMapper.getFor(OwnerComponent.class);

	public SelectionSystem(final SelectionRect selectionRect) {
		super(Family.all(RenderableComponent.class, PositionComponent.class, BoundsComponent.class, OwnerComponent.class).get());
		this.selectionRect = selectionRect;
		
		// processing every frame is unnecessary, only needed when player is dragging the mouse
		setProcessing(false);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {		
		final PositionComponent pos = pc.get(entity);
		final BoundsComponent bounds = bc.get(entity);
		final OwnerComponent owner = oc.get(entity);
		
		final Rectangle tmp = new Rectangle(bounds);
		tmp.x += pos.x;
		tmp.y += pos.y;				
		
		if(owner.id == 0 && selectionRect.overlaps(tmp)) {
			 if(!isSelected(entity) && selectedCount < SELECTED_MAX){
				 entity.add(new SelectedComponent());
				 selectedCount++;
			 }
		} else {			
			entity.remove(SelectedComponent.class);
		}
	}

	public void resetSelectedCount() {
		selectedCount = 0;
	}
	
	
	public int getSelectedCount() {
		return selectedCount;
		
	}
	
	private static boolean isSelected(final Entity entity) {
		return entity.getComponent(SelectedComponent.class) != null;
	}

}
