package my.evorunner.game.Gameplay.Skill_Buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

/**
 * Created by Peter on 2/4/2018.
 */

public abstract class AbilityButton {
    private Texture buttonPicture;
    protected ImageButton abilityButton;
    public AbilityButton() {

    }
    public abstract ImageButton getImageButton();
    public abstract void cooldownChecker();
}
