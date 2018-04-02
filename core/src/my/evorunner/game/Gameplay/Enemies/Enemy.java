package my.evorunner.game.Gameplay.Enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import my.evorunner.game.Gameplay.Player;

/**
 * Created by Peter on 7/26/2017.
 */

public abstract class Enemy {
    public abstract void dispose();
    public abstract void update(float delta,Player evoRunner);
    public abstract void render(SpriteBatch batch);
    public abstract float getPositionX();
    public abstract float getPositionY();
    public abstract float getWidth();
    public abstract float getHeight();
    public abstract void hitByWeapon();
    public abstract LifeState getLifeState();
    public abstract void setPosition(int x,int y);
    public abstract void resetPosition(Player player);
    public abstract Rectangle getBounds();
    public abstract Rectangle getOuterBounds();
    public abstract boolean isDead();
    enum LifeState{
        DEAD,ALIVE
    }
}
