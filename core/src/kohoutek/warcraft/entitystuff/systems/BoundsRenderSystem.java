package kohoutek.warcraft.entitystuff.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.components.SelectableComponent;


/**
 * System for drawing bounding rectangles of entities, signifying that an entity is selected
 * @author Kamil Kohoutek
 */
public class BoundsRenderSystem extends IteratingSystem {
	
	private final ShapeRenderer renderer;
	
	private final ComponentMapper<PositionComponent> pc = ComponentMapper.getFor(PositionComponent.class);
	private final ComponentMapper<BoundsComponent> bc = ComponentMapper.getFor(BoundsComponent.class);
	private final ComponentMapper<SelectableComponent> sc = ComponentMapper.getFor(SelectableComponent.class);

	@SuppressWarnings("unchecked")
	public BoundsRenderSystem(final ShapeRenderer renderer) {
		super(Family.all(RenderableComponent.class, SelectableComponent.class, PositionComponent.class, BoundsComponent.class).get());
		this.renderer = renderer;
	}
	
	@Override
	public void update(float deltaTime){
		renderer.begin(ShapeType.Line);
		super.update(deltaTime);
		renderer.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final SelectableComponent selectable = sc.get(entity);
		if(!selectable.selected) return;
		
		final PositionComponent pos = pc.get(entity);
		final BoundsComponent bounds = bc.get(entity);
		
		renderer.rect(pos.x + bounds.x, pos.y + bounds.y, bounds.width, bounds.height, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);

	}

}
