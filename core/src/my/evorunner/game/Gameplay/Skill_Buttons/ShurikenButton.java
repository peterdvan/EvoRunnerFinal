package my.evorunner.game.Gameplay.Skill_Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Player;

/**
 * Created by Peter on 2/4/2018.
 */

public class ShurikenButton extends AbilityButton {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable shurikenButtonOn;
    private TextureRegionDrawable shurikenButtonOff;
    private ImageButton shurikenButton;
    private long cooldown;

    public ShurikenButton(final Player player) {
        myTexture = new Texture(Gdx.files.internal("Items/ninjaStarButton.png"));
        myTextureRegion = new TextureRegion(myTexture);
        shurikenButtonOn = new TextureRegionDrawable(myTextureRegion);
        shurikenButtonOff = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Items/ninjaStarButtonOff.png"))));

        shurikenButton = new ImageButton(shurikenButtonOn);
        shurikenButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                shurikenButton.setDisabled(true);
                shurikenButton.setBackground(shurikenButtonOff);
                cooldown = TimeUtils.millis();
                player.attack();
                return true;
            }
        });
    }

    @Override
    public ImageButton getImageButton() {
        return shurikenButton;
    }

    @Override
    public void cooldownChecker() {
        long timeElapsed = TimeUtils.timeSinceMillis(cooldown);
        if(shurikenButton.isDisabled() && timeElapsed > 1000) {
            shurikenButton.setDisabled(false);
            shurikenButton.setBackground(shurikenButtonOn);
        }
    }

}
