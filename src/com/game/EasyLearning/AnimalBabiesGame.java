package com.game.EasyLearning;


import android.util.Log;
import android.widget.Toast;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, BitmapTextureAtlas> atlas = new HashMap<String, BitmapTextureAtlas>();
    private Map<String, TextureRegion> region = new HashMap<String, TextureRegion>();
    private Map<String, Sprite> sprite = new HashMap<String, Sprite>();
    private Map<String, Sound> sound = new HashMap<String, Sound>();
    private ArrayList<Character> letters;

    public AnimalBabiesGame(SceneManager sceneManager, BaseGameActivity activity, Engine engine, Camera camera) {
        this.activity = activity;
        this.camera = camera;
        this.engine = engine;
        this.sceneManager = sceneManager;
        letters = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
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

        for (Character c : letters) {
            String l = "" + c;
            String ll = "_" + c;
            l = l.toLowerCase();
            ll = ll.toLowerCase();
            //atlas.put(l, new BitmapTextureAtlas(context.getTextureManager(), 256, 256));
            atlas.put(ll, new BitmapTextureAtlas(activity.getTextureManager(), 256, 256));

            //region.put(l, BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get(l), context, l + ".png", 0, 0));
            region.put(ll, BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get(ll), activity, ll + ".png", 0, 0));

            //atlas.get(l).load();
            atlas.get(ll).load();
        }

        atlas.put("deer", new BitmapTextureAtlas(activity.getTextureManager(), 1024, 256));
        region.put("deerA", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("deer"), activity, "deer.png", 130, 0));
        region.get("deerA").setTextureSize(128, 128);
        region.put("deerB", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("deer"), activity, "deer.png", 0, 0));
        region.get("deerB").setTextureSize(128, 128);
        atlas.get("deer").load();

        atlas.put("dog", new BitmapTextureAtlas(activity.getTextureManager(), 1024, 256));
        region.put("dogA", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("dog"), activity, "dog.png", 130, 0));
        region.put("dogB", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("dog"), activity, "dog.png", 0, 0));
        region.get("dogB").setTextureSize(128, 128);
        region.get("dogA").setTextureSize(128, 128);
        atlas.get("dog").load();

        atlas.put("panda", new BitmapTextureAtlas(activity.getTextureManager(), 1024, 256));
        region.put("pandaA", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("panda"), activity, "panda.png", 140, 0));
        region.put("pandaB", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("panda"), activity, "panda.png", 0, 0));
        region.get("pandaA").setTextureSize(128, 128);
        region.get("pandaB").setTextureSize(128, 128);
        atlas.get("panda").load();

        atlas.put("lamb", new BitmapTextureAtlas(activity.getTextureManager(), 1024, 256));
        region.put("lambA", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("lamb"), activity, "lamb.png", 130, 0));
        region.put("lambB", BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get("lamb"), activity, "lamb.png", 0, 0));
        region.get("lambA").setTextureSize(128, 128);
        region.get("lambB").setTextureSize(128, 128);
        atlas.get("lamb").load();
    }

    public void loadSounds() {
        SoundFactory.setAssetBasePath("mfx/");
        try {
            sound.put("panda", SoundFactory.createSoundFromAsset(engine.getSoundManager(), activity, "panda.ogg"));
            sound.put("dog", SoundFactory.createSoundFromAsset(engine.getSoundManager(), activity, "dog.ogg"));
            sound.put("deer", SoundFactory.createSoundFromAsset(engine.getSoundManager(), activity, "deer.ogg"));
            sound.put("lamb", SoundFactory.createSoundFromAsset(engine.getSoundManager(), activity, "lamb.ogg"));
        } catch (IOException e) {
            e.printStackTrace();  //TODO
        }
    }

    public Scene levelsScene() {
        homeScene = new Scene();
        Sprite bgSprite = new Sprite(0, 0, gameBgTR, engine.getVertexBufferObjectManager());

        final Sprite lvl1Sprite = new Sprite(50, 130, lvl1TR, engine.getVertexBufferObjectManager()) {
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
                setCurrentScene(AnimalsBabiesScenes.LEVEL2);
                return true;
            }
        };

        Sprite lvl3Sprite = new Sprite(310, 130, lvl3TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.NONE);
                return true;
            }
        };

        Sprite lvl4Sprite = new Sprite(438, 130, lvl4TR, engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                pressedEffec(this);
                setCurrentScene(AnimalsBabiesScenes.NONE);
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

        /*String word = "hello";
        ArrayList<Sprite> wordToSprite = new ArrayList<Sprite>();
        for (int i = 0; i < word.length(); i ++){
            String l = "" + word.charAt(i);
            final String ll = "_" + word.charAt(i);
            Sprite aSprite = new Sprite(i*128, 250, region.get(ll), engine.getVertexBufferObjectManager()){
                @Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                             final float pTouchAreaLocalX,
                                             final float pTouchAreaLocalY) {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                    Log.d("onAreaTouched",String.format(ll+" %fx%f", pSceneTouchEvent.getX(), pSceneTouchEvent.getY()));
                    homeScene.reset();
                    return true;
                }
            };
            aSprite.setSize(128,128);
            wordToSprite.add(aSprite);
            homeScene.attachChild(aSprite);
            homeScene.registerTouchArea(aSprite);
            sprite.put(ll,aSprite);
        }*/
        return homeScene;
    }

    public Scene createLevel1() {
        level1Scene = new Scene();
        Sprite bg = new Sprite(0, 0, gameBgTR, engine.getVertexBufferObjectManager());

        final Sprite deerB = new Sprite(150, 100, region.get("deerB"), engine.getVertexBufferObjectManager());
        Sprite deerA = new Sprite(0, 0, region.get("deerA"), engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if (deerB.collidesWith(this)) {
                    this.setPosition(deerB.getX(), deerB.getY());
                    sound.get("deer").play();
                }
                level1Scene.reset();
                return true;
            }
        };
        deerA.setPosition(camera.getHeight() - 128, (camera.getWidth() - deerA.getWidth()) / 2);

        final Sprite dogB = new Sprite(255, 90, region.get("dogB"), engine.getVertexBufferObjectManager());
        Sprite dogA = new Sprite(0, 0, region.get("dogA"), engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if (dogB.collidesWith(this)) {
                    this.setPosition(dogB.getX(), dogB.getY());
                    sound.get("dog").play();
                }
                level1Scene.reset();
                return true;
            }
        };
        dogA.setPosition(camera.getHeight() - 128, (camera.getWidth() - dogA.getWidth()) / 2);

        final Sprite pandaB = new Sprite(360, 80, region.get("pandaB"), engine.getVertexBufferObjectManager());
        Sprite pandaA = new Sprite(0, 0, region.get("pandaA"), engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if (pandaB.collidesWith(this)) {
                    this.setPosition(pandaB.getX(), pandaB.getY());
                    sound.get("panda").play();
                }
                level1Scene.reset();
                return true;
            }
        };
        pandaA.setPosition((camera.getWidth() - pandaA.getWidth()) / 2, camera.getHeight() - 128);

        final Sprite lambB = new Sprite(490, 100, region.get("lambB"), engine.getVertexBufferObjectManager());
        Sprite lambA = new Sprite(0, 0, region.get("lambA"), engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                if (lambB.collidesWith(this)) {
                    this.setPosition(lambB.getX(), lambB.getY());
                    sound.get("lamb").play();
                }
                level1Scene.reset();
                return true;
            }
        };
        lambA.setPosition((camera.getWidth() - lambA.getWidth()) / 2, camera.getHeight() - 128);

        level1Scene.attachChild(bg);
        level1Scene.attachChild(deerB);
        level1Scene.attachChild(dogB);
        level1Scene.attachChild(pandaB);
        level1Scene.attachChild(lambB);

        level1Scene.attachChild(deerA);
        level1Scene.attachChild(lambA);
        level1Scene.attachChild(dogA);
        level1Scene.attachChild(pandaA);


        level1Scene.registerTouchArea(pandaA);
        level1Scene.registerTouchArea(dogA);
        level1Scene.registerTouchArea(lambA);
        level1Scene.registerTouchArea(deerA);

        return level1Scene;
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
        createLevel1();
    }

    public Scene getHomeScene() {
        return homeScene;
    }

    public enum AnimalsBabiesScenes {
        NONE, LEVEL1, LEVEL2, LEVEL3, LEVEL4
    }
}
