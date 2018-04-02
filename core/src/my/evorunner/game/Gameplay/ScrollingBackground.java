package my.evorunner.game.Gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import my.evorunner.game.Constants;

/**
 * Version 1.0
 * Last Edited: Matthew Phan
 * changelog 1.0
 * Changed texture for background image to test
 */

public class ScrollingBackground {
    public static float MAX_ENVIRONENT_VELOCITY = 200;
    public static final float ACCELERATION = 2;
    public static final float MAX_VELOCITY = 150;
    TextureRegion image;
    float x1,x2;
    float imageScale;

    public ScrollingBackground() {
        image = new TextureRegion(new Texture(Gdx.files.internal("Environment/parallax-forest.png")));   //Change to "Environment/parallax-forest.png" for original background
        x1 = 0;
        x2 = Gdx.graphics.getWidth();
        imageScale = 0;
    }

    public void update(float delta,Player player) {
        if(player == null || player.isPlayerAlive()) {
            x1 -= Constants.DEFAULT_ENVIRONENT_VELOCITY * delta;
            x2 -= Constants.DEFAULT_ENVIRONENT_VELOCITY * delta;
            float pastPoint = x1 + Gdx.graphics.getWidth();
            float past2 = x2 + Gdx.graphics.getWidth();
            if (pastPoint < 0) {
                x1 = x2 + Gdx.graphics.getWidth();
            }
            if (past2 < 0) {
                x2 = x1 + Gdx.graphics.getWidth();
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(image,x1,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(image,x2,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    public void resize(int width,int height) {
        imageScale = height / image.getRegionHeight();
    }

}
