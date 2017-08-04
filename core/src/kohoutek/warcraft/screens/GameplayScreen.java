package kohoutek.warcraft.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

import kohoutek.warcraft.entitystuff.EntityManager;

public class GameplayScreen implements Screen, InputProcessor {
	
	// reference to the assetmanager being used
	private final AssetManager 			am;
	
	private TiledMap 					map;
	private OrthographicCamera 			cam;
	private OrthogonalTiledMapRenderer 	renderer;
	private EntityManager 				em;

	public GameplayScreen(AssetManager am){
		this.am = am;
	}
	
	@Override
	public void show() {					
		map = am.get("../core/assets/map1.tmx", TiledMap.class);
		renderer = new OrthogonalTiledMapRenderer(map, 1);
		cam = new OrthographicCamera();
		em = new EntityManager(new Engine(), renderer.getBatch(), am);
		
		Gdx.input.setInputProcessor(this);

	}
	

	@Override
	public void render(float delta) {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
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

		renderer.setView(cam);
		renderer.render();
	
		em.update(delta);
		
	}
	

	@Override
	public void resize(int width, int height) {
		cam.viewportHeight = height;
		cam.viewportWidth = width;
		cam.update();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();		
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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
