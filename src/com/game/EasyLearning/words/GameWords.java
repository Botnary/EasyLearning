package com.game.EasyLearning.words;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: botnary
 * Date: 13-06-09
 * Time: 5:01 PM
 */
public class GameWords {
    private BaseGameActivity context;
    private Map<String, BitmapTextureAtlas> atlas = new HashMap<String, BitmapTextureAtlas>();
    private Map<String, TextureRegion> region = new HashMap<String, TextureRegion>();
    private Map<String, Sprite> sprite = new HashMap<String, Sprite>();

    private ArrayList<Character> letters;

    public GameWords(BaseGameActivity context) {
        this.context = context;
        letters = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
    }

    public void loadResources() {
        for (Character c : letters) {
            String l = "" + c;
            String ll = "_" + c;
            l = l.toLowerCase();
            ll = ll.toLowerCase();
            //atlas.put(l, new BitmapTextureAtlas(context.getTextureManager(), 256, 256));
            atlas.put(ll, new BitmapTextureAtlas(context.getTextureManager(), 256, 256));

            //region.put(l, BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get(l), context, l + ".png", 0, 0));
            region.put(ll, BitmapTextureAtlasTextureRegionFactory.createFromAsset(atlas.get(ll), context, ll + ".png", 0, 0));

            //atlas.get(l).load();
            atlas.get(ll).load();
        }
    }

    public void populateScene(String word, Engine mEngine, Scene scene){
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i ++){
            String l = "" + word.charAt(i);
            final String ll = "_" + word.charAt(i);
            /*sprite.put(l,new Sprite(i*10, 10, region.get(l), mEngine.getVertexBufferObjectManager()){
                @Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                             final float pTouchAreaLocalX,
                                             final float pTouchAreaLocalY) {
                    this.setPosition(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                    System.out.println(String.format("%fx%f", pSceneTouchEvent.getX(), pSceneTouchEvent.getY()));
                    return true;
                }
            });*/
            Sprite aSprite = new Sprite(i*128, 150, region.get(ll), mEngine.getVertexBufferObjectManager()){
                @Override
                public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                             final float pTouchAreaLocalX,
                                             final float pTouchAreaLocalY) {
                    this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                    System.out.println(String.format(ll+" %fx%f", pSceneTouchEvent.getX(), pSceneTouchEvent.getY()));
                    return true;
                }
            };
            //sprite.put(ll,aSprite);
            //scene.attachChild(sprite.get(l));
            scene.attachChild(aSprite);

            //scene.registerTouchArea(sprite.get(l));
            scene.registerTouchArea(aSprite);

            sprite.put(ll,aSprite);
        }

        Sprite ss = new Sprite(128, 10, region.get("_g"), mEngine.getVertexBufferObjectManager()){
            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent,
                                         final float pTouchAreaLocalX,
                                         final float pTouchAreaLocalY) {
                this.setRotation(30f);
                this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
                System.out.println(String.format("tregion %fx%f", pSceneTouchEvent.getX(), pSceneTouchEvent.getY()));
                return true;
            }
        };
        scene.attachChild(ss);
        scene.registerTouchArea(ss);
        scene.setTouchAreaBindingOnActionMoveEnabled(true);
        System.out.println(String.format("scene %s", scene.hasOnAreaTouchListener()));
    }
}
