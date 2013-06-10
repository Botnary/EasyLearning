package com.game.EasyLearning;

import android.hardware.SensorManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.game.EasyLearning.words.GameWords;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;

public class MainGameActivity extends BaseGameActivity {
    private final static int CAMERA_WIDTH = 800;
    private final static int CAMERA_HEIGHT = 480;

    private Camera camera;
    //private GameWords gameWords;

    SceneManager sceneManager;

    private BitmapTextureAtlas bgAtlas, alienAtlas, aAtlas;
    private TextureRegion alienRegion, bgRegion, aRegion;
    protected Scene scene;
    protected PhysicsWorld physicsWorld;

    @Override
    public EngineOptions onCreateEngineOptions() {
        //gameWords = new GameWords(this);
        this.camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.camera);
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        //loadGfx();
        //gameWords.loadResources();
        sceneManager = new SceneManager(this, mEngine, camera);
        sceneManager.loadMenuResources();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        /*this.scene = new Scene();
        //this.scene.setBackground(new Background(0, 125, 58));
        physicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
        this.scene.registerUpdateHandler(physicsWorld);*/

        pOnCreateSceneCallback.onCreateSceneFinished(sceneManager.createMenuScene());
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        /*Sprite bgSprite = new Sprite(0, 0, bgRegion, this.mEngine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                //System.out.println(String.format("%fx%f", pSceneTouchEvent.getX(), pSceneTouchEvent.getY()));
                return true;
            }
        };
        scene.attachChild(bgSprite);

        //createWalls();
        scene.registerTouchArea(bgSprite);
        gameWords.populateScene("hello",mEngine,scene);*/
        mEngine.registerUpdateHandler(new TimerHandler(3f,
                new ITimerCallback() {

                    @Override
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        mEngine.unregisterUpdateHandler(pTimerHandler);
                        // TODO Auto-generated method stub
                        //sceneManager.loadMenuResources();
                        //sceneManager.createMenuScene();
                        //sceneManager.setCurrentScene(SceneManager.AllScenes.MENU);
                    }
                }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    private void loadGfx() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        bgAtlas = new BitmapTextureAtlas(getTextureManager(), 1600, 960);
        bgRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgAtlas, this, "bg.gif", 0, 0);
        bgAtlas.load();

        /*alienAtlas = new BitmapTextureAtlas(getTextureManager(), 256, 256);
        alienRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(alienAtlas, this, "beast.png", 0, 0);
        alienAtlas.load();*/


    }
}
