package kohoutek.warcraft;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Unit extends Sprite {
	public Animation<TextureRegion> tr;
	public Texture sheet;
	private float stateTime = 0;
	
	public Unit(){
		sheet = new Texture("C:/Users/Kamil/Documents/warcraft/warcraft/res/FOOTMAN.png");
		setTexture(sheet);
		setPosition(100, 100);
		setBounds(0, 0, 48, 48);

		TextureRegion a = new TextureRegion(sheet, 2 * 48, 0, 48, 48);
		TextureRegion b = new TextureRegion(sheet, 2 * 48, 48, 48, 48);
		TextureRegion c = new TextureRegion(sheet, 2 * 48, 2 * 48, 48, 48);
		//TextureRegion[][] tmp = TextureRegion.split(sheet, 48, 48);
		
		//final ArrayList<TextureRegion> tmpList = new ArrayList<TextureRegion>();
		//for(TextureRegion[] aTr : tmp){
		//	for(TextureRegion tr : aTr){
		//		tmpList.add(tr);
		//	}
		//}
		//TextureRegion[] frames = tmpList.toArray(new TextureRegion[tmpList.size()]);
		
		tr = new Animation<TextureRegion>(0.25f, new TextureRegion[]{a,b,c});
		tr.setPlayMode(PlayMode.LOOP_PINGPONG);
		setRegion(tr.getKeyFrames()[0]);
	}

	
	public void update(){
		stateTime += Gdx.graphics.getDeltaTime();
		setRegion(tr.getKeyFrame(stateTime, true));
	}
	
	
	
	

}
