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
 * Created by Peter on 2/8/2018.
 */

public class WarriorAttack extends Weapon {
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
    public WarriorAttack(Vector2 position) {
        this.position = position;
        region = Assets.ourInstance.redSwordSlashAssets.SWORD_0;
        width = Constants.SCREEN_WIDTH/12;
        height = Constants.SCREEN_HEIGHT/8;
        bounds = new Rectangle(position.x,
                position.y,
                width,
                height);
        usedState = UsedState.AVAILABLE;
        throwStartTime = TimeUtils.nanoTime();
    }


    public void update(float delta, DelayedRemovalArray<Enemy> enemies) {
        throwTimeKeeper = MathUtils.nanoToSec * (TimeUtils.nanoTime() - throwStartTime);
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
            region = (TextureRegion) Assets.ourInstance.redSwordSlashAssets.slashAnimation.getKeyFrame(throwTimeKeeper);
    }

    public void render(SpriteBatch batch) {
        if(region != null) {
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
    }

    @Override
    public boolean isFinished() {
            return Assets.ourInstance.redSwordSlashAssets.slashAnimation.isAnimationFinished(throwTimeKeeper);
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public UsedState getUsedState() {
        return usedState;
    }
}
