package my.evorunner.game.Gameplay.Skill_Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import my.evorunner.game.Constants;
import my.evorunner.game.Gameplay.Player;

/**
 * Created by Peter on 2/4/2018.
 */

public class DragonAttackButton extends AbilityButton {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton attackButton;

    boolean onCoolDown = false;

    private final float cooldown;
    private float cooldownTriggerTime = -Float.MAX_VALUE;

    public DragonAttackButton(final Player player) {
        cooldown = 1f;
            myTexture = new Texture(Gdx.files.internal("Items/AttackButton.png"));
            myTextureRegion = new TextureRegion(myTexture);
            myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
            attackButton = new ImageButton(myTexRegionDrawable); //Set the button up
            attackButton.addListener(new EventListener() {
                @Override
                public boolean handle(Event event) {
                    if (!isOnCooldown()) {
                        player.attack();
                        setCooldownTriggerTime(GameTime.getCurrentTime());
                        return true;
                    } else {
                        return true;
                    }
                }
            });

    }

    public boolean isOnCooldown() {
        return getRemainingCooldownTime() - Constants.EPSILON > 0;
    }

    public float getCooldownTriggerTime() {
        return cooldownTriggerTime;
    }

    public void setCooldownTriggerTime(float input) {
        cooldownTriggerTime = input;
    }

    public float getRemainingCooldownTime() {
        return Math.max(0, cooldown - (GameTime.getCurrentTime() - cooldownTriggerTime));
    }

    public float getRemainingCooldownPercentage() {
        if ((cooldown - (GameTime.getCurrentTime() - cooldownTriggerTime)) <= 0) {
            return 0.0f;
        }

        return (cooldown - (GameTime.getCurrentTime() - cooldownTriggerTime)) / cooldown;
    }

    public ImageButton getImageButton() {
        return attackButton;
    }

    @Override
    public void cooldownChecker() {
        if (isOnCooldown()) {
            onCoolDown = false;
        }
    }

}
