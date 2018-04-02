package my.evorunner.game.Gameplay.Obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import my.evorunner.game.Gameplay.Player;

/**
 * Created by Peter on 1/2/2018.
 */

public abstract class Obstacle {



    public abstract void render(SpriteBatch batch);
    public abstract void update(float delta,Player player );

    public abstract Rectangle getBounds();
    public abstract Rectangle getInnerBounds();
    public abstract float getPositionX();
    public abstract float getPositionY();
    public abstract float getWidth();
    public abstract float getHeight();
    public  abstract void setPosition(int x,int y);
}
