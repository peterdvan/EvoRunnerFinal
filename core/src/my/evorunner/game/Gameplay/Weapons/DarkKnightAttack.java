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
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;

/**
 * Created by Peter on 2/9/2018.
 */

public class DarkKnightAttack  extends  Weapon {
    private Vector2 position;
    private TextureRegion region;
    private Rectangle bounds;
    public float width;
    public float height;
    private UsedState usedState;
    private long throwStartTime;
    private long deathStart;
    private float throwTimeKeeper;
    public DarkKnightAttack(Vector2 position) {
        this.position = position;
        region = Assets.ourInstance.purpleSwordSlashAssets.SWORD_0;
        width = SCREEN_WIDTH/12;
        height = SCREEN_HEIGHT/8;
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
                deathStart = TimeUtils.nanoTime();
            }
        }
            region = (TextureRegion) Assets.ourInstance.purpleSwordSlashAssets.slashAnimation.getKeyFrame(throwTimeKeeper);
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
            return Assets.ourInstance.purpleSwordSlashAssets.slashAnimation.isAnimationFinished(throwTimeKeeper);

    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public UsedState getUsedState() {
        return usedState;
    }
}
