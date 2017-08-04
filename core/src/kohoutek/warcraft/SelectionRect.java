package kohoutek.warcraft;

import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class SelectionRect extends Rectangle {
	
	@Override
	public boolean overlaps(Rectangle r){
		// needed because original class does not support negative dimensions
		return 	Math.min(x, x + width) < Math.max(r.x, r.x + r.width) &&
				Math.max(x, x + width) > Math.min(r.x, r.x + r.width) &&
				Math.min(y, y + height) < Math.max(r.y, r.y + r.height) &&
				Math.max(y, y + height) > Math.min(r.y, r.y + r.height);
	}
}
