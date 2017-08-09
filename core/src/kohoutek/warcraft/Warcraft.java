package kohoutek.warcraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import kohoutek.warcraft.screens.Gameplay;

public class Warcraft extends Game {
	private AssetManager am;
	private Gameplay gameplayScr;
	

	@Override
	public void create() {
		am = new AssetManager();
		
		// enqueue assets for loading
		am.load("../core/assets/PEASANT.png", 			Texture.class);
		am.load("../core/assets/FOOTMAN.png", 			Texture.class);
		am.load("../core/assets/GRUNT.png", 			Texture.class);
		am.load("../core/assets/BUILDINGS_O_edit.png", 	Texture.class);
		am.load("../core/assets/BUILDINGS_H_edit.png", 	Texture.class);
		am.load("../core/assets/PEASANT.png", 			Texture.class);		
		am.setLoader(TiledMap.class, new TmxMapLoader());
		am.load("../core/assets/map1.tmx", 				TiledMap.class);
				
		gameplayScr = new Gameplay(am);
	}
	
	@Override
	public void render(){
		if(am.update()) {
			if(getScreen() != gameplayScr) setScreen(gameplayScr);
	    } else {
	    	float progress = am.getProgress();
		    System.out.println((int)(progress * 100) + " %");
	    }		
		super.render();
	}
}
