package my.evorunner.game.Gameplay.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Assets;
import my.evorunner.game.Gameplay.Player;

import java.util.Random;

import static my.evorunner.game.Constants.BOUNDS_BUFFER_X;
import static my.evorunner.game.Constants.BOUNDS_BUFFER_Y;
import static my.evorunner.game.Constants.DEFAULT_ENVIRONENT_VELOCITY;
import static my.evorunner.game.Constants.KILL_PLANE;
import static my.evorunner.game.Constants.LAVA_SLIME_SPEED;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.Constants.SLIME_DEATH_SOUND;

/**
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0
 * Changed slime height from screen height / 16 to screen height / 20
 * Changed slime width from width / 24  to width / 20
 */

public class LavaSlime extends Enemy {

    public float width;
    public float height;
    public TextureRegion region;
    public Vector2 position;
    long walkStartTime;
    long deathStart;
    private LifeState lifeState;
    private float walkTimeKeeper;
    private float deathTime;
    public Rectangle bounds;
    public Rectangle outerBounds;
    private Random random;
    public LavaSlime(Vector2 position,Random random ) {
        this.position = position;
        region = Assets.ourInstance.lavaSlimeAssets.LAVA_SLIME;
        width = SCREEN_WIDTH/30;
        height = SCREEN_HEIGHT/30;
        walkStartTime = TimeUtils.nanoTime();
        lifeState = LifeState.ALIVE;
        this.random = random;
        bounds = new Rectangle(position.x,
                position.y,
                width,
                height);
        outerBounds = new Rectangle(position.x - BOUNDS_BUFFER_X,
                position.y - BOUNDS_BUFFER_Y,
                width + BOUNDS_BUFFER_X,
                height + BOUNDS_BUFFER_X);

    }

    @Override
    public void dispose() {
        SLIME_DEATH_SOUND.dispose();
    }

    @Override
    public void update(float delta, Player evoRunner) {
        position.x -= delta * (DEFAULT_ENVIRONENT_VELOCITY + LAVA_SLIME_SPEED);
        Rectangle evoRunnerBounds = evoRunner.getBounds();

        bounds.set(position.x,
                position.y,
                width,
                height);
        outerBounds.set(position.x - BOUNDS_BUFFER_X,
                position.y - BOUNDS_BUFFER_Y,
                width + BOUNDS_BUFFER_X,
                height + BOUNDS_BUFFER_Y);
        walkTimeKeeper = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
        deathTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - deathStart);
        if(lifeState == LifeState.ALIVE) {
            if(evoRunnerBounds.overlaps(bounds)) {
                hitPlayer(evoRunner);
            }
            region = (TextureRegion) Assets.ourInstance.lavaSlimeAssets.lavaSlimeWalkAnimation.getKeyFrame(walkTimeKeeper);
        } else {
            region = (TextureRegion) Assets.ourInstance.lavaSlimeAssets.lavaSlimeDeathAnimation.getKeyFrame(deathTime);
        }
    }

    private void hitPlayer(Player evoRunner) {
      evoRunner.gotHit();
    }

    public void resetPosition(Player player) {
        if(position.x + width < KILL_PLANE.x) {
            position.x = random.nextInt(Gdx.graphics.getWidth()*5) + Gdx.graphics.getWidth();
        }
    }
    public void hitByWeapon() {
        deathStart = TimeUtils.nanoTime();;
        lifeState = LifeState.DEAD;
        SLIME_DEATH_SOUND.play(.2f);
    }

    @Override
    public LifeState getLifeState() {
        return lifeState;
    }

    @Override
    public void setPosition(int num,int num2) {
        position.x = num;
        position.y = num2;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                width,
                height,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public float getPositionX() {
        return position.x;
    }
    public float getPositionY() {
        return position.y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public TextureRegion getTextureRegion() {
        return region;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public Rectangle getOuterBounds() {
        return outerBounds;
    }

    @Override
    public boolean isDead() {
        if(lifeState == LifeState.DEAD) {
            return Assets.ourInstance.lavaSlimeAssets.lavaSlimeDeathAnimation.isAnimationFinished(deathTime);
        }
        return false;
    }
}
