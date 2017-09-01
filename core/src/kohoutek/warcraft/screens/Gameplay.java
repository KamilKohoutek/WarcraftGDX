package kohoutek.warcraft.screens;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import kohoutek.warcraft.Race;
import kohoutek.warcraft.Player;
import kohoutek.warcraft.SelectionRect;
import kohoutek.warcraft.entitystuff.components.OwnerComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.entities.Footman;
import kohoutek.warcraft.entitystuff.entities.HumanFarm;
import kohoutek.warcraft.entitystuff.systems.AnimationOrientationSystem;
import kohoutek.warcraft.entitystuff.systems.AnimationRenderSystem;
import kohoutek.warcraft.entitystuff.systems.BoundsRenderSystem;
import kohoutek.warcraft.entitystuff.systems.MovementSystem;
import kohoutek.warcraft.entitystuff.systems.SelectionSystem;
import kohoutek.warcraft.entitystuff.systems.TargetSystem;


public class Gameplay implements Screen, InputProcessor {
	
	// reference to the assetmanager being used
	private final AssetManager 			am;
	
	// player contorl
	private final SelectionRect			selectionRect;
	private final Vector2				targetPoint;	

	private String						mapName;
	private TiledMap 					map;
	private OrthographicCamera 			cam;
	private OrthogonalTiledMapRenderer 	mRenderer;
	private ShapeRenderer				sRenderer;
	private Engine 						entityEngine;
	
	private SelectionSystem				ss;
	private TargetSystem				ts;
	
	private final Player[] players;

	private Gameplay(final AssetManager am) {
		this.am = am;
		
		cam 			= new OrthographicCamera();
		sRenderer 		= new ShapeRenderer();
		entityEngine 	= new Engine();
		selectionRect 	= new SelectionRect();
		targetPoint 	= new Vector2();	
		players 		= new Player[]{new Player(Race.HUMAN, 0), new Player(Race.ORC, 1)};

	}

	public Gameplay(final AssetManager am, final String mapName) {
		this(am);	
		this.mapName = mapName;
				
		map 			= new TmxMapLoader().load("../core/assets/" + mapName + ".tmx");
		mRenderer 		= new OrthogonalTiledMapRenderer(map);
		
		ss 				= new SelectionSystem(selectionRect);
		ts 				= new TargetSystem(targetPoint);
		
		entityEngine.addSystem(new AnimationRenderSystem(mRenderer.getBatch()));
		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new AnimationOrientationSystem());
		entityEngine.addSystem(ss);
		entityEngine.addSystem(ts);
		entityEngine.addSystem(new BoundsRenderSystem(sRenderer));

		
	}
	
	public Gameplay(final AssetManager am, final ObjectInputStream stream) {
		this(am);
		
		Object[] objects = null;
		try {
			objects = (Object[])stream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			// restore object states
			
		}
	}
	
	@Override
	public void show() {						
		for(int i = 0; i < 4; i++) {
			entityEngine.addEntity(new Footman(MathUtils.random(64, 500), MathUtils.random(64,500),am,players[0]));
		}
		
		Gdx.input.setInputProcessor(this);
	}
	

	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// controlling the camera with mouse	
		if(Gdx.input.getX() >= Gdx.graphics.getWidth() - 40){		
			cam.translate(24, 0);
		}
		if(Gdx.input.getX()  <= 40){
			cam.translate(-24, 0);
		}
		if(Gdx.input.getY() <= 40){
			cam.translate(0, 24);
		}
		if(Gdx.input.getY() >= Gdx.graphics.getHeight() - 40){
			cam.translate(0, -24);
		}
		
		// keep camera within map bounds
		cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth/2, 64*32 - cam.viewportWidth/2);
		cam.position.y = MathUtils.clamp(cam.position.y, cam.viewportHeight/2, 64*32 - cam.viewportHeight/2);	
		cam.update();

		// draw map
		mRenderer.setView(cam);
		mRenderer.render();	
		
		sRenderer.setProjectionMatrix(cam.combined);
		
		// update & draw entites
		entityEngine.update(delta);		
		

		// draw selection rectangle
		sRenderer.begin(ShapeType.Line);
		sRenderer.rect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
		sRenderer.end();
	}
	

	@Override
	public void resize(int width, int height) {
		cam.viewportHeight = height;
		cam.viewportWidth = width;
		cam.update();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
	
	}

	@Override
	public void hide() {
		//dispose();		
	}

	@Override
	public void dispose() {
		map.dispose();
		mRenderer.dispose();
		sRenderer.dispose();
	}
	
	
	/*************** INPUT PROCESSING ***************/
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.F5:
			// TODO save game
			
			ObjectOutputStream o = null;
			try {
				o = new ObjectOutputStream(new FileOutputStream("C:/Users/Kamil/Desktop/out.txt", false));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				o.writeObject(((Footman)(entityEngine.getEntities().get(1))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {			
		final Vector3 screenToWorld = cam.unproject(new Vector3(screenX, screenY, 0));	
		final int selectedCount 	= ss.countSelected();
		
		switch(button){
		case Buttons.LEFT:
			if(selectedCount == 0) {
				selectionRect.x 		= screenToWorld.x;
				selectionRect.y 		= screenToWorld.y;		
				selectionRect.width 	= 0;
				selectionRect.height 	= 0;		
				ss.setProcessing(true);
			} else {
				ss.clearSelected();		
			}
			break;			
		case Buttons.RIGHT:
			targetPoint.x = screenToWorld.x;
			targetPoint.y = screenToWorld.y;
			
			// after defining target coords, this must be called
			ts.prepare(selectedCount);
			
			break;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT){			
			selectionRect.x 		= 0;
			selectionRect.y 		= 0;
			selectionRect.width 	= 0;
			selectionRect.height 	= 0;
			
			ss.setProcessing(false);
			ss.clearSelected();
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {	
		if(selectionRect.x + selectionRect.y > 0) {
			selectionRect.width += Gdx.input.getDeltaX();
			selectionRect.height -= Gdx.input.getDeltaY();
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
