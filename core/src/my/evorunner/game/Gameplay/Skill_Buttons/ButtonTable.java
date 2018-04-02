package my.evorunner.game.Gameplay.Skill_Buttons;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import my.evorunner.game.Gameplay.Player;


/**
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0
 * Added attack button and jump button
 */
public class ButtonTable {
    private Stage stage;
    private Player player;
    private Table table;
    private AbilityButton abilityButton;

    public ButtonTable(final Player player, Stage stage) {
        table = new Table();
        this.player = player;
        //Creates jump button and adds a listener
        JumpButton jumpButton = new JumpButton(player);

        //Creates attack button and adds a listener
        abilityButton = selectAbilityForCharacter();

        //Adds buttons to the table and adds it to the stage
        table.add(jumpButton.getImageButton());
        table.row();
        table.add(abilityButton.getImageButton());
        table.setFillParent(true);
        table.right();
        //table.debug(); //Uncomment to turn on table lines
        stage.addActor(table);
    }

        // Will Add different abilities to the screen depending on character skin
        private AbilityButton selectAbilityForCharacter() {
            int characterID = player.getCharacterID();
            AbilityButton abilityButton;
            switch(characterID) {
                case 1:
                    abilityButton = new DragonAttackButton(player);
                    break;
                //case 2:
                    //abilityButton = new ShurikenButton(player);
                    //break;
                default:
                    abilityButton = new DragonAttackButton(player);
            }
            return abilityButton;
        }
    public void update() {
        abilityButton.cooldownChecker();
    }

}

