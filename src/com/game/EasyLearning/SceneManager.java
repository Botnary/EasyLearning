package com.game.EasyLearning;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: botnary
 * Date: 13-06-10
 * Time: 11:49 AM
 */
public class SceneManager {
    private AllScenes currentScene;
    private BaseGameActivity activity;
    private Engine engine;
    private Camera camera;
    private AnimalBabiesGame animalBabiesGame;
    private BitmapTextureAtlas
            splashTA,
            menuTA1,
            menuTA2,
            menuTA3,
            menuTA4,
            menuBgTA,
            loaderTA,
            texBanana;
    private ITextureRegion
            splashTR,
            menuGame1TR,
            menuGame2TR,
            menuGame3TR,
            menuGame4TR,
            menuBgTR,
            loaderTR;
    private TiledTextureRegion regBanana;
    private Scene
            splashScene,
            menuScene,
            animalBabiesScene,
            matchObjectScene,
            handWritingScene,
            learnAlphabetScene;

    public enum AllScenes {
        SPLASH, MENU, ANIMALS_BABIES, MATCH_OBJECT
    }

    public SceneManager(BaseGameActivity act, Engine eng, Camera cam) {
        // TODO Auto-generated constructor stub
        this.activity = act;
        this.engine = eng;
        this.camera = cam;
        this.animalBabiesGame = new AnimalBabiesGame(this, act, eng, cam);
    }

    public void loadSplashResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        texBanana = new BitmapTextureAtlas(this.activity.getTextureManager(), 256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        regBanana = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(texBanana, this.activity.getAssets(),"spr_banana.png", 0, 0, 4, 2);
        texBanana.load();

        splashTA = new BitmapTextureAtlas(this.activity.getTextureManager(), 1600, 960);
        splashTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTA, activity, "bg_blue.gif", 0, 0);
        splashTA.load();

        loaderTA = new BitmapTextureAtlas(this.activity.getTextureManager(), 132, 132);
        loaderTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loaderTA, activity, "ajax-loader.gif", 0, 0);
        loaderTA.load();
    }

    public void loadAnimalBabiesResources() {
        animalBabiesGame.loadResources();
    }

    public void loadMenuResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuTA1 = new BitmapTextureAtlas(this.activity.getTextureManager(), 594, 132);
        menuTA2 = new BitmapTextureAtlas(this.activity.getTextureManager(), 594, 132);
        menuTA3 = new BitmapTextureAtlas(this.activity.getTextureManager(), 594, 132);
        menuTA4 = new BitmapTextureAtlas(this.activity.getTextureManager(), 594, 132);

        menuBgTA = new BitmapTextureAtlas(this.activity.getTextureManager(), 1600, 960);
        menuBgTR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                menuBgTA, this.activity, "bg_menu.gif", 0, 0);
        menuGame1TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                menuTA1, this.activity, "animal_babies.png", 0, 0);
        menuGame2TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                menuTA2, this.activity, "match_object.png", 0, 0);
        menuGame3TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                menuTA3, this.activity, "hand_writing.png", 0, 0);
        menuGame4TR = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
                menuTA4, this.activity, "learn_alphabet.png", 0, 0);

        menuTA1.load();
        menuTA2.load();
        menuTA3.load();
        menuTA4.load();

        menuBgTA.load();
    }

    public Scene createSplashScene() {
        splashScene = new Scene();
        splashScene.setBackground(new Background(1, 1, 1));

        Sprite bg = new Sprite(0,0,splashTR,engine.getVertexBufferObjectManager());

        Sprite loader = new Sprite(0, 0, loaderTR,
                engine.getVertexBufferObjectManager());
        loader.setPosition((camera.getWidth() - loader.getWidth()) / 2,
                (camera.getHeight() - loader.getHeight()) / 2);

        AnimatedSprite sprBanana = new AnimatedSprite(0,0,regBanana,engine.getVertexBufferObjectManager());
        sprBanana.animate(100);
        sprBanana.setPosition((camera.getWidth() - loader.getWidth()) / 2,
                (camera.getHeight() - loader.getHeight()) / 2);

        splashScene.attachChild(bg);
        splashScene.attachChild(sprBanana);
        //splashScene.attachChild(loader);
        return splashScene;
    }

    public Scene createMenuScene() {
        menuScene = new Scene();
        //menuScene.setBackground(new Background(255, 189, 49));

        Sprite menubg = new Sprite(0, 0, menuBgTR,
                engine.getVertexBufferObjectManager());

        Sprite menu1 = new Sprite(0, 0, menuGame1TR,
                engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                setSpriteAlpha(this);
                engine.registerUpdateHandler(new TimerHandler(1f,
                        new ITimerCallback() {

                            @Override
                            public void onTimePassed(TimerHandler pTimerHandler) {
                                engine.unregisterUpdateHandler(pTimerHandler);
                                setCurrentScene(AllScenes.ANIMALS_BABIES);
                            }
                        }));
                return true;
            }
        };
        menu1.setScale(1F);
        menu1.setPosition((camera.getWidth() - menu1.getWidth()) / 2,
                70);

        Sprite menu2 = new Sprite(0, 0, menuGame2TR,
                engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                setSpriteAlpha(this);
                setCurrentScene(AllScenes.MATCH_OBJECT);
                return true;
            }
        };
        menu2.setPosition((camera.getWidth() - menu2.getWidth()) / 2,
                140);

        Sprite menu3 = new Sprite(0, 0, menuGame3TR,
                engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                setSpriteAlpha(this);
                return true;
            }
        };
        menu3.setPosition((camera.getWidth() - menu3.getWidth()) / 2,
                208);

        Sprite menu4 = new Sprite(0, 0, menuGame4TR,
                engine.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                setSpriteAlpha(this);
                return true;
            }
        };

        menu4.setPosition((camera.getWidth() - menu4.getWidth()) / 2,
                276);


        menuScene.attachChild(menubg);
        menuScene.attachChild(menu1);
        menuScene.attachChild(menu2);
        menuScene.attachChild(menu3);
        menuScene.attachChild(menu4);

        menuScene.registerTouchArea(menu1);
        menuScene.registerTouchArea(menu2);
        menuScene.registerTouchArea(menu3);
        menuScene.registerTouchArea(menu4);
        return menuScene;

    }

    public Scene createAnimalsBabiesScene() {
        animalBabiesGame.createScenes();
        animalBabiesScene = animalBabiesGame.getHomeScene();
        return animalBabiesScene;
    }

    public AllScenes getCurrentScene() {
        return currentScene;
    }

    public void setSpriteAlpha(Sprite sprite) {
        if (sprite.getAlpha() == 0.5F) {
            sprite.setAlpha(1);
        } else {
            sprite.setAlpha(0.5F);
        }
    }

    public void setCurrentScene(AllScenes currentScene) {
        this.currentScene = currentScene;
        switch (currentScene) {
            case SPLASH:
                engine.setScene(splashScene);
                break;
            case MENU:
                engine.setScene(menuScene);
                break;
            case ANIMALS_BABIES:
                engine.setScene(animalBabiesScene);
                break;
            default:
                break;
        }
    }

}
