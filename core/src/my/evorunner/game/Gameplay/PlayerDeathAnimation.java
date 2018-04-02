package my.evorunner.game.Gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by Peter on 1/26/2018.
 */

public class PlayerDeathAnimation {
    Vector2 position;
    long startTime;

    public PlayerDeathAnimation(Vector2 position) {
        this.position = position;
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch) {
        float animationTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTime);
        TextureRegion region = (TextureRegion) Assets.ourInstance.playerDeathAnimationAssets.deathAnimation.getKeyFrame(animationTime);
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
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

    public boolean isFinished() {
        float timeElapsed = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(startTime);
        return Assets.ourInstance.playerDeathAnimationAssets.deathAnimation.isAnimationFinished(timeElapsed);
    }

}
