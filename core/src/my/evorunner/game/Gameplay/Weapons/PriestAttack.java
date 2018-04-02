package my.evorunner.game.Gameplay.Weapons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Enemies.Enemy;
import my.evorunner.game.Gameplay.Assets;

import my.evorunner.game.Constants;

/**
 * Created by Peter on 2/7/2018.
 */

public class PriestAttack extends Weapon {
    public static final float BULLET_MOVE_SPEED = 150;
    private Vector2 position;
    private TextureRegion region;
    private Rectangle bounds;
    public float width;
    public float height;
    private UsedState usedState;
    private long throwStartTime;
    private long deathStart;
    private float throwTimeKeeper;
    private float deathTime;
    public PriestAttack(Vector2 position) {
        this.position = position;
        region = Assets.ourInstance.plusAttackAssets.plus1;
        width = Constants.SCREEN_WIDTH/24;
        height = Constants.SCREEN_HEIGHT/16;
        bounds = new Rectangle(position.x,
                position.y,
                width,
                height);
        usedState = UsedState.AVAILABLE;
        throwStartTime = TimeUtils.nanoTime();
    }


    public void update(float delta, DelayedRemovalArray<Enemy> enemies) {
        position.x += delta * BULLET_MOVE_SPEED;
        throwTimeKeeper = MathUtils.nanoToSec * (TimeUtils.nanoTime() - throwStartTime);
        deathTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - deathStart);
        bounds.set(position.x,
                position.y,
                width,
                height);
        for (Enemy enemy : enemies) {
            Rectangle enemyBound = new Rectangle(
                    enemy.getPositionX(),
                    enemy.getPositionY(),
                    enemy.getWidth(),
                    enemy.getHeight());

            if (enemyBound.overlaps(bounds)) {
                enemy.hitByWeapon();
                usedState = UsedState.USED;
                deathStart = TimeUtils.nanoTime();
            }
        }
        if(usedState == UsedState.AVAILABLE) {
            region = (TextureRegion) Assets.ourInstance.plusAttackAssets.boomerangAnimation.getKeyFrame(throwTimeKeeper);
        } else {
            region = (TextureRegion) Assets.ourInstance.shurikenHitAnimationAssets.hitAnimation.getKeyFrame(deathTime);
        }
    }

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

    @Override
    public boolean isFinished() {
        if(usedState == UsedState.USED) {
            float timeElapsed = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(deathStart);
            return Assets.ourInstance.shurikenHitAnimationAssets.hitAnimation.isAnimationFinished(timeElapsed);
        }
        return false;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public UsedState getUsedState() {
        return usedState;
    }
}
