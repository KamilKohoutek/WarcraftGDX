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

import kohoutek.warcraft.screens.GameplayScreen;

public class Warcraft extends Game {
	private AssetManager am;
	private GameplayScreen gameplayScr;
	

	@Override
	public void create() {
		am = new AssetManager();
		
		// enqueue assets for loading
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/PEASANT.png", Texture.class);
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/FOOTMAN.png", Texture.class);
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/GRUNT.png", Texture.class);
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/BUILDINGS_O.png", Texture.class);
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/BUILDINGS_H.png", Texture.class);
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/PEASANT.png", Texture.class);		
		am.setLoader(TiledMap.class, new TmxMapLoader());
		am.load("C:/Users/Kamil/Documents/warcraft/warcraft/res/map1.tmx", TiledMap.class);
				
		gameplayScr = new GameplayScreen(am);

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
