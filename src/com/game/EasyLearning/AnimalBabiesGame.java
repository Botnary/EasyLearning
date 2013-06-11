package com.game.EasyLearning;


import android.util.Log;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Created with IntelliJ IDEA.
 * User: botnary
 * Date: 13-06-11
 * Time: 2:16 PM
 */
public class AnimalBabiesGame {

    private BaseGameActivity activity;
    private SceneManager sceneManager;
    private Engine engine;
    private Camera camera;
    private BitmapTextureAtlas
            gameBgTA,
            homeBtnTA,
            lvl1TA,
            lvl2TA,
            lvl3TA,
            lvl4TA;
    private ITextureRegion
            gameBgTR,
            homeBtnTR,
            lvl1TR,
            lvl2TR,
            lvl3TR,
            lvl4TR;
    private Scene
            homeScene,
            level1Scene,
            level2Scene,
            level3Scene,
            level4Scene;

    public AnimalBabiesGame(SceneManager sceneManager, BaseGameActivity activity, Engine engine, Camera camera) {
        this.activity = activity;
        this.camera = camera;
        this.engine = engine;
        this.sceneManager = sceneManager;
    }

    public void loadResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        gameBgTA = new BitmapTextureAtlas(this.activity.getTextureManager(), 1600, 960);
        gameBgTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                gameBgTA, this.activity, "bg_blue.gif", 0, 0);
        gameBgTA.load();

        homeBtnTA = new BitmapTextureAtlas(this.activity.getTextureManager(), 182, 192);
        homeBtnTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(homeBtnTA, activity, "btn_home.png", 0, 0);
        homeBtnTA.load();

        lvl1TA = new BitmapTextureAtlas(activity.getTextureManager(), 254, 262);
        lvl1TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lvl1TA, activity, "lvl1.png", 0, 0);
        lvl1TA.load();

        lvl2TA = new BitmapTextureAtlas(activity.getTextureManager(), 254, 262);
        lvl2TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lvl2TA, activity, "lvl2.png", 0, 0);
        lvl2TA.load();

        lvl3TA = new BitmapTextureAtlas(activity.getTextureManager(), 254, 262);
        lvl3TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lvl3TA, activity, "lvl3.png", 0, 0);
        lvl3TA.load();

        lvl4TA = new BitmapTextureAtlas(activity.getTextureManager(), 254, 262);
        lvl4TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(lvl4TA, activity, "lvl4.png", 0, 0);
        lvl4TA.load();

    }

    public Scene levelsScene() {
        homeScene = new Scene();
        Sprite bgSprite = new Sprite(0, 0, gameBgTR, engine.getVertexBufferObjectManager());

        Sprite lvl1Sprite = new Sprite(50, 130, lvl1TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.LEVEL1);
                return true;
            }
        };

        Sprite lvl2Sprite = new Sprite(177, 130, lvl2TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.LEVEL1);
                return true;
            }
        };

        Sprite lvl3Sprite = new Sprite(310, 130, lvl3TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.LEVEL1);
                return true;
            }
        };

        Sprite lvl4Sprite = new Sprite(438, 130, lvl4TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.LEVEL1);
                return true;
            }
        };

        Sprite homeBtnSprite = new Sprite(30, 30, homeBtnTR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                engine.registerUpdateHandler(new TimerHandler(1f,
                        new ITimerCallback() {

                            @Override
                            public void onTimePassed(TimerHandler pTimerHandler) {
                                engine.unregisterUpdateHandler(pTimerHandler);
                                sceneManager.setCurrentScene(SceneManager.AllScenes.MENU);
                            }
                        }));
                return true;
            }
        };

        homeScene.attachChild(bgSprite);

        homeScene.attachChild(lvl1Sprite);
        homeScene.registerTouchArea(lvl1Sprite);

        homeScene.attachChild(lvl2Sprite);
        homeScene.registerTouchArea(lvl2Sprite);

        homeScene.attachChild(lvl3Sprite);
        homeScene.registerTouchArea(lvl3Sprite);

        homeScene.attachChild(lvl4Sprite);
        homeScene.registerTouchArea(lvl4Sprite);

        homeScene.attachChild(homeBtnSprite);
        homeScene.registerTouchArea(homeBtnSprite);

        return homeScene;
    }

    private void setCurrentScene(AnimalsBabiesScenes scene) {
        switch (scene) {
            case LEVEL1:
                engine.setScene(level1Scene);
                break;
            case LEVEL2:
                engine.setScene(level2Scene);
                break;
            case LEVEL3:
                engine.setScene(level3Scene);
                break;
            case LEVEL4:
                engine.setScene(level4Scene);
                break;
            default:
                engine.setScene(homeScene);
                break;
        }
    }

    public void pressedEffec(Sprite sprite) {
        if (sprite.getAlpha() == 0.5F) {
            sprite.setAlpha(1);
        } else {
            sprite.setAlpha(0.5F);
        }
    }

    public void createScenes() {
        levelsScene();
    }

    public Scene getHomeScene() {
        return homeScene;
    }

    public enum AnimalsBabiesScenes {
        LEVEL1, LEVEL2, LEVEL3, LEVEL4
    }
}
