package kohoutek.warcraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.entities.HumanFarm;
import kohoutek.warcraft.screens.Gameplay;

public class Warcraft extends Game {
	private AssetManager am;
	private Gameplay gameplayScr;
	private final String saveName;

	public Warcraft(String saveName) {
		this.saveName = saveName;
	}
	
	public Warcraft() {
		this(null);
	}

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
		//am.setLoader(TiledMap.class, new TmxMapLoader());
		//am.load("../core/assets/map1.tmx", 				TiledMap.class);						
		am.finishLoading();
		
		// init animation data
		Footman.initRegions(am);
		HumanFarm.initRegions(am);
		// TODO more
		
		
		if(saveName != null) {
			ObjectInputStream stream = null;
			try {
				stream = new ObjectInputStream(new FileInputStream("../core/saves/" + saveName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				gameplayScr = new Gameplay(stream);					
			}		
		} else {
			gameplayScr = new Gameplay("map1");
		}
		
		setScreen(gameplayScr);
	}
	
	
	@Override
	public void render(){
		/*
		if(am.update()) {
			if(getScreen() != gameplayScr) setScreen(gameplayScr);
	    } else {
	    	float progress = am.getProgress();
		    System.out.println((int)(progress * 100) + " %");
	    }	*/
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		am.dispose();
	}
	
}
