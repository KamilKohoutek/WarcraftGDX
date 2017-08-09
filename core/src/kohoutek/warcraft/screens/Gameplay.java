package kohoutek.warcraft.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import kohoutek.warcraft.Common.Race;
import kohoutek.warcraft.Player;
import kohoutek.warcraft.SelectionRect;
import kohoutek.warcraft.entitystuff.EntityEngine;
import kohoutek.warcraft.entitystuff.components.BoundsComponent;
import kohoutek.warcraft.entitystuff.components.PositionComponent;
import kohoutek.warcraft.entitystuff.components.RenderableComponent;
import kohoutek.warcraft.entitystuff.entities.Footman;


public class Gameplay implements Screen, InputProcessor {
	
	// reference to the assetmanager being used
	private final AssetManager 			am;
	
	// player contorl
	private final SelectionRect			selectionRect;
	private final Vector2				targetPoint;
	
	private TiledMap 					map;
	private OrthographicCamera 			cam;
	private OrthogonalTiledMapRenderer 	mRenderer;
	private ShapeRenderer				sRenderer;
	private EntityEngine 				entityEngine;
	
	private final Player[] players = new Player[] {new Player(Race.HUMAN), new Player(Race.ORC)};

	public Gameplay(AssetManager am){
		this.am = am;
		
		selectionRect = new SelectionRect();		
		targetPoint = new Vector2();
	}
	
	@Override
	public void show() {					
		map 			= am.get("../core/assets/map1.tmx", TiledMap.class);
		mRenderer 		= new OrthogonalTiledMapRenderer(map);
		sRenderer 		= new ShapeRenderer();
		cam 			= new OrthographicCamera();
		entityEngine	= new EntityEngine( mRenderer.getBatch(), 
											selectionRect, 
											sRenderer, 
											targetPoint, 
											am, 
											players );
		
		Gdx.input.setInputProcessor(this);
	}
	

	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		System.out.println("Entities active: " + entityEngine.getEntities().size());
		
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
		
		// spawn footmen, java stronk! testing allocations	
		for(int i = 0; i < 4; i++) {
			entityEngine.addEntity(new Footman(MathUtils.random(64, 1800), MathUtils.random(64,1750),am,players[0]));
		}

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
		dispose();		
	}

	@Override
	public void dispose() {
		map.dispose();
		mRenderer.dispose();
		sRenderer.dispose();
		//entityEngine.removeAllEntities();
	}
	
	
	/** INPUT PROCESSING **/
	
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {	
		Vector3 screenToWorld 	= cam.unproject(new Vector3(screenX, screenY, 0));
		if(button == Buttons.LEFT){		
			selectionRect.x 		= screenToWorld.x;
			selectionRect.y 		= screenToWorld.y;		
			selectionRect.width 	= 0;
			selectionRect.height 	= 0;
			
			entityEngine.ss.setProcessing(true);
		} else if (button == Buttons.RIGHT){
			targetPoint.x = screenToWorld.x;
			targetPoint.y = screenToWorld.y;
			
			entityEngine.ts.setProcessing(true);
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
			
			entityEngine.ss.setProcessing(false);
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {	
		selectionRect.width += Gdx.input.getDeltaX();
		selectionRect.height -= Gdx.input.getDeltaY();
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
