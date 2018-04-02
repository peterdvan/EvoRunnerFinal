package my.evorunner.game.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Assets;

import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;

/**
 * Created by Peter on 1/29/2018.
 */

public class Lootbox {
    private long startTime;
    private TextureRegion region;
    private boolean standby;
    private long openingTime;
    public float width;
    public float height;

    public Lootbox() {
        startTime = TimeUtils.nanoTime();
        openingTime = -1;
        standby = true;
        region = Assets.ourInstance.lootBoxAssets.LOOTBOX_OPENING1;
        width = SCREEN_WIDTH/3;
        height = SCREEN_HEIGHT/2;
    }

    public void render(SpriteBatch batch) {
        update();
        if(region != null) {
            batch.draw(
                    region.getTexture(),
                    SCREEN_WIDTH/2 - width/2,
                    SCREEN_HEIGHT/2 - height/2,
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

    public void update() {
        if(standby) {
            float animationTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startTime);
            region = (TextureRegion) Assets.ourInstance.lootBoxAssets.STANDBY_LOOTBOX.getKeyFrame(animationTime);
        } else {
            if(openingTime == -1) {
                openingTime = TimeUtils.nanoTime();
            }
            float animationTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - openingTime);
            region = (TextureRegion) Assets.ourInstance.lootBoxAssets.LOOTBOX_OPENING.getKeyFrame(animationTime);
        }
    }
    public void setStandby(boolean bool) {
        standby = bool;
    }
    public int getPositionY() {
        return Gdx.graphics.getHeight() - region.getRegionHeight() - 20;
    }

    public boolean isFinished() {
        if(!standby) {
            float timeElapsed = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(startTime);
            return Assets.ourInstance.lootBoxAssets.LOOTBOX_OPENING.isAnimationFinished(timeElapsed);
        }
        return false;
    }
    public void resetBox() {
        openingTime = -1;
    }

}
