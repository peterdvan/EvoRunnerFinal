package my.evorunner.game.Gameplay.Skill_Buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import my.evorunner.game.Gameplay.Player;

/**
 * Created by Peter on 2/4/2018.
 */

public class JumpButton {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton jumpButton;
    public JumpButton(final Player player) {
        myTexture = new Texture(Gdx.files.internal("Items/JumpButton.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        jumpButton = new ImageButton(myTexRegionDrawable); //Set the button up
        jumpButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                player.jump();
                return true;
            }
        });
    }

    public ImageButton getImageButton() {
        return jumpButton;
    }
}
