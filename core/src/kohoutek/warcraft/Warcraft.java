package kohoutek.warcraft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.entities.HumanFarm;
import kohoutek.warcraft.screens.Gameplay;
import kohoutek.warcraft.screens.MainMenu;

public class Warcraft extends Game {
	public AssetManager am;
	private Gameplay gameplayScr;
	private MainMenu menuScr;


	@Override
	public void create() {
		am = new AssetManager();

		// enqueue assets for loading
		am.load("../core/assets/menu.jpg", 				Texture.class);
		am.load("../core/assets/PEASANT.png", 			Texture.class);
		am.load("../core/assets/FOOTMAN.png", 			Texture.class);
		am.load("../core/assets/GRUNT.png", 			Texture.class);
		am.load("../core/assets/BUILDINGS_O_edit.png", 	Texture.class);
		am.load("../core/assets/BUILDINGS_H_edit.png", 	Texture.class);
		am.load("../core/assets/PEASANT.png", 			Texture.class);								
		am.finishLoading();
		
		// init animation data
		Footman.initRegions(am);
		HumanFarm.initRegions(am);
		// TODO more
		
		gameplayScr = new Gameplay("map1");
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
