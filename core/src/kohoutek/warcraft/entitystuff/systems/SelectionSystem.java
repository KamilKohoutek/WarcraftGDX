package kohoutek.warcraft.entitystuff.systems;

import java.util.ArrayList;

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
import kohoutek.warcraft.entitystuff.components.SelectableComponent;

/**
 * This system manages selecting entities by player
 * @author Kamil Kohoutek
 */
public class SelectionSystem extends IteratingSystem {
	
	/** reference to the selectionRect instance **/
	private final SelectionRect selectionRect;
	
	private final ComponentMapper<SelectableComponent> sc = ComponentMapper.getFor(SelectableComponent.class);
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);
	private final ComponentMapper<OwnerComponent> oc = ComponentMapper.getFor(OwnerComponent.class);
	
	/** currently selected **/
	private ArrayList<Entity> alreadySelected = new ArrayList<Entity>();

	public SelectionSystem(final SelectionRect selectionRect) {
		super(Family.all(SelectableComponent.class, RenderableComponent.class, PositionComponent.class, BoundsComponent.class, OwnerComponent.class).get());
		this.selectionRect = selectionRect;
		
		// processing every frame is unnecessary, only needed when player is dragging the mouse
		setProcessing(false);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {		
		final SelectableComponent selectable = sc.get(entity);
		final PositionComponent pos = pc.get(entity);
		final BoundsComponent bounds = bc.get(entity);
		final OwnerComponent owner = oc.get(entity);
		
		final Rectangle tmp = new Rectangle(bounds);
		tmp.x += pos.x;
		tmp.y += pos.y;		
		
		
		if(selectionRect.overlaps(tmp)) {
			selectable.selectedBy = owner.owner;
			
			if(!alreadySelected.contains(entity)) {
				alreadySelected.add(entity);
			}
		} else {
			selectable.selectedBy = null;
		}
	}
	
	public void reset() {
		alreadySelected.clear();
		//setProcessing(false);
	}
	
	public int countSelected() {
		return alreadySelected.size();
		
	}

}
