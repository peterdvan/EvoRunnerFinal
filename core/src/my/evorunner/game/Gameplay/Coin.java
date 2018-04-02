package my.evorunner.game.Gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import my.evorunner.game.Constants;

import static my.evorunner.game.Gameplay.Coin.CoinState.COLLECTED;
import static my.evorunner.game.Gameplay.Coin.CoinState.COLLECTING;
import static my.evorunner.game.Gameplay.Coin.CoinState.NOT_COLLECTED;

/**
 * Created by Peter on 1/26/2018.
 */

public class Coin {
    public final static float COIN_HEIGHT  = Constants.SCREEN_HEIGHT/15;
    public final static float COIN_WIDTH = Constants.SCREEN_WIDTH/25;
    Vector2 position;
    Vector2 lastFramePosition;
    private CoinState coinstate;
    TextureRegion region;
    private long coinCollectionStartTime;
    private Rectangle bounds;
    public final float COLLECTION_TIME = .25F;
    public Coin(Vector2 position) {
        this.position = position;
        lastFramePosition = new Vector2(position.x,position.y);
        coinstate = NOT_COLLECTED;
        region = Assets.ourInstance.coinAssets.COIN_1;
        bounds = new Rectangle(position.x,
                position.y,
                COIN_WIDTH,
                COIN_HEIGHT);
        coinCollectionStartTime = 0;
    }


    public void update(float delta) {
        position.x -= Constants.DEFAULT_ENVIRONENT_VELOCITY * delta;
        bounds.set(position.x,
                position.y,
                COIN_WIDTH,
                COIN_HEIGHT);
        if (coinstate == NOT_COLLECTED) {
            float collectionTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - coinCollectionStartTime);
            region = (TextureRegion) Assets.ourInstance.coinAssets.coinAnimation.getKeyFrame(collectionTime);
        }else if (coinstate == COLLECTING) {
            float collectionTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - coinCollectionStartTime);
            if(collectionTime <= COLLECTION_TIME) {
                region = Assets.ourInstance.coinAssets.COIN_COLLECTED;
            } else {
                coinstate = COLLECTED;
            }
        }


        lastFramePosition.x = position.x;
    }


    public void render(SpriteBatch batch) {
        if(coinstate != COLLECTED) {
            batch.draw(
                    region.getTexture(),
                    position.x,
                    position.y,
                    0,
                    0,
                    COIN_WIDTH,
                    COIN_HEIGHT,
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

    public float getPositionX() {
        return position.x;
    }

    public float getPositionY() {
        return position.y;
    }

    public float getWidth() {
        return COIN_WIDTH;
    }

    public float getHeight() {
        return COIN_HEIGHT;
    }

    public TextureRegion getTextureRegion() {
        return region;
    }


    public CoinState getCoinState() {
        return coinstate;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setCoinstate(CoinState coinstate) {
        this.coinstate = coinstate;
    }

    public void setCoinCollectionStartTime(long num) {
        coinCollectionStartTime = num;
    }
    enum CoinState {
        COLLECTING,COLLECTED,NOT_COLLECTED
    }
}
