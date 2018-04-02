package my.evorunner.game.Gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import static my.evorunner.game.Constants.DEFAULT_ENVIRONENT_VELOCITY;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;

/**
 * Created by Peter on 2/11/2018.
 */

public class IntroductionNPC {
    private TextureRegion region;
    private Vector2 position;
    private int width;
    private int height;

    TextButton text;

    public IntroductionNPC() {
        region = Assets.ourInstance.npcAssets.INTRODUCTION_NPC;
        position = new Vector2(SCREEN_WIDTH * 1.5f,0);
        width = SCREEN_WIDTH / 18;
        height = SCREEN_HEIGHT / 10;
    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch,float delta) {


        position.x -= DEFAULT_ENVIRONENT_VELOCITY * delta;
        if (position.x + width > 0) {
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
}
