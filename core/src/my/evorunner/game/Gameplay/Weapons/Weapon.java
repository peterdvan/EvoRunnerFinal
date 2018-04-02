package my.evorunner.game.Gameplay.Weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import my.evorunner.game.Gameplay.Enemies.Enemy;

/**
 * Created by Peter on 2/3/2018.
 */

public abstract class Weapon {
    public abstract void update(float delta,DelayedRemovalArray<Enemy> enemyList);
    public abstract void render(SpriteBatch batch);
    public abstract boolean isFinished();
    public abstract Vector2 getPosition();
    public abstract UsedState getUsedState();
    enum UsedState{
        USED, AVAILABLE
    }
}
