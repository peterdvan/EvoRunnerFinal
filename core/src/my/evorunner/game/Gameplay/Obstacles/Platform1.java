package my.evorunner.game.Gameplay.Obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import my.evorunner.game.Gameplay.Assets;
import my.evorunner.game.Gameplay.Player;

import java.util.Random;

import static my.evorunner.game.Constants.BOUNDS_BUFFER_X;
import static my.evorunner.game.Constants.BOUNDS_BUFFER_Y;
import static my.evorunner.game.Constants.DEFAULT_ENVIRONENT_VELOCITY;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;

/**
 * Created by Peter on 1/11/2018.
 */

public class Platform1 extends Obstacle {
    private Vector2 position;
    private TextureRegion region;
    private Rectangle bounds;
    private Rectangle innerBounds;
    private Random random;
    private int platformHeight;
    private int platformWidth;
    public Platform1(Vector2 position,Random random) {
        this.position = position;
        this.random = random;
        platformHeight = SCREEN_HEIGHT/25;
        platformWidth = SCREEN_WIDTH/5;
        region = Assets.ourInstance.platformAssets.PLATFORM_1;
        bounds = new Rectangle(position.x - BOUNDS_BUFFER_X,
                position.y - BOUNDS_BUFFER_Y,
                platformWidth + BOUNDS_BUFFER_X,
                platformHeight + BOUNDS_BUFFER_X);
        innerBounds = new Rectangle(position.x,position.y,platformWidth,platformHeight);

    }

    public void update(float delta, Player player) {
        if(player.isPlayerAlive()) {
            position.x -= delta * DEFAULT_ENVIRONENT_VELOCITY;
            bounds.set(position.x - BOUNDS_BUFFER_X,
                    position.y - BOUNDS_BUFFER_Y,
                    platformWidth + BOUNDS_BUFFER_X,
                    platformHeight + BOUNDS_BUFFER_Y);
            innerBounds.set(position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                platformWidth,
                platformHeight,
                1,
                1,
                0,
                 region.getRegionX(),
                 region.getRegionY(),
                 region.getRegionWidth(),
                 region.getRegionHeight(),
                 false,
                 false
        );
    }
    public Rectangle getBounds() {
        return  bounds;
    }

    public Rectangle getInnerBounds() {
        return  innerBounds;
    }

    @Override
    public float getPositionX() {
        return position.x;
    }

    @Override
    public float getPositionY() {
        return position.y;
    }

    public void setPosition(int num,int num2) {
        position.x = num;
        position.y = num2;
    }

    public float getWidth() {
        return platformWidth;
    }

    public float getHeight() {
        return platformHeight;
    }


}
