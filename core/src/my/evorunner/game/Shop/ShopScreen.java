package my.evorunner.game.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

import my.evorunner.game.Constants;
import my.evorunner.game.EvoRunner;
import my.evorunner.game.Gameplay.AvailableCharacters;

/**
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0
 * Now using available characters to hold the number for rolling (summoning)
 * modified render width to help the sprites fit better
 */

public class ShopScreen implements Screen {
    private Table table;
    private Stage stage;
    private SpriteBatch batch;
    private EvoRunner parent;
    private Lootbox lootbox;

    private BitmapFont font;
    private TextButton summonButton;
    private TextButton.TextButtonStyle wordFont;
    private TextureRegion coinPicture;
    private TextureRegion unlockedCharacter;
    private AvailableCharacters list;
    ArrayList<String> characterList;
    ArrayList<TextureRegion> characterTextureList;
    private int coinAmount;
    private TextureRegion background;

    public ShopScreen(EvoRunner parent) {
        this.parent = parent;
        font = EvoRunner.generator.generateFont(EvoRunner.parameter);
        wordFont = new TextButton.TextButtonStyle();
        wordFont.font = font;

        list = new AvailableCharacters();
        characterList = list.getList();
        characterTextureList = list.getTextureList(1);
    }

    @Override
    public void show() {
        background = new TextureRegion(new Texture(Gdx.files.internal("Environment/industryBackground.png")));
        coinAmount = EvoRunner.savedData.getInteger("coins");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        coinPicture = new TextureRegion(new Texture(Gdx.files.internal("Items/goldCoin5.png")));
        lootbox = new Lootbox();

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        // table.setDebug(true);

        //Set to a default to prevent issues with render
        unlockedCharacter = new TextureRegion(new Texture(Gdx.files.internal("Items/goldCoin5.png")));
        summonButton = new TextButton("SUMMON (200)",wordFont);
        summonButton.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(coinAmount >= 200) {
                    if(lootbox.isFinished()) {
                        lootbox.resetBox();
                        batch.begin();
                        lootbox.render(batch);
                        batch.end();
                    }
                    lootbox.setStandby(false);
                    EvoRunner.savedData.putInteger("coins", EvoRunner.savedData.getInteger("coins") - 200).flush();   //flush saves
                    coinAmount = EvoRunner.savedData.getInteger("coins");

                    //Gets a random int for a random character
                    //TODO: change later for modifying roll rates
                    int unlocked = list.getRandomCharacter();
                    String characterName = list.getCharacterName(unlocked);
                    unlockedCharacter = characterTextureList.get(unlocked);
                    //Adds the character name to the saved data
                    EvoRunner.savedData.putBoolean(characterName, true).flush();
                }
            }
        }));

        summonButton.setPosition((Gdx.graphics.getWidth()/2) - summonButton.getWidth()/2 ,  50);
        summonButton.getLabel().setFontScale(4);

        TextButton backButton = new TextButton("Back",wordFont);
        backButton.getLabel().setFontScale(4);
        backButton.setPosition(Constants.SCREEN_WIDTH/7, Constants.SCREEN_HEIGHT * 7 / 8);
        backButton.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.MENU_SCREEN);
            }
        }));

        stage.addActor(summonButton);
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(
                unlockedCharacter,
                Constants.SCREEN_WIDTH / 2 - Constants.SCREEN_WIDTH/25,
                Constants.SCREEN_HEIGHT/2 - Constants.SCREEN_HEIGHT/6,
                0,
                0,
                Constants.SCREEN_WIDTH/15,
                Constants.SCREEN_HEIGHT/6,
                1,
                1,
                0);
        lootbox.update();
        batch.draw(
                coinPicture.getTexture(),
                Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/12 - Constants.SCREEN_WIDTH/6,
                Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/7,
                0,
                0,
                Constants.SCREEN_WIDTH/12,
                Constants.SCREEN_HEIGHT/7,
                1,
                1,
                0,
                coinPicture.getRegionX(),
                coinPicture.getRegionY(),
                coinPicture.getRegionWidth(),
                coinPicture.getRegionHeight(),
                false,
                false);
        font.getData().setScale(3);
        font.draw(batch,"" + coinAmount, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/6, Constants.SCREEN_HEIGHT - Constants.SCREEN_HEIGHT/20);
        lootbox.render(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        stage.dispose();
        batch.dispose();
    }
}
