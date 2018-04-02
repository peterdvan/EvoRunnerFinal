package my.evorunner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import my.evorunner.game.Gameplay.ScrollingBackground;

import java.util.Random;

import static my.evorunner.game.Constants.GAMEPLAY_MUSIC;
import static my.evorunner.game.EvoRunner.generator;
import static my.evorunner.game.EvoRunner.parameter;

/**
 * MenuScreen class
 *
 * Version 1.1
 * Last Edited: Matthew Phan
 * Changelog 1.1
 * Added characters button
 *
 * Changelog 1.0
 * Changed variable "parentt" in menu screen parameter to parentIn for comprehension
 * Changed variable menuFont to menuButton for comprehension
 * Changed path for title.png to Game UI buttons
 */

public class MenuScreen implements Screen {
    //Declare private variables for later use
    private EvoRunner parent;
    private SpriteBatch batch;
    private Image title;
    private BitmapFont font;
    private Stage stage;
    private ScrollingBackground scrollingBackground;
    private TextButton.TextButtonStyle menuButton;
    private Random random;
    //MenuScreen constructor that takes in an EvoRunner
    public MenuScreen( EvoRunner parentIn) {
        this.parent = parentIn;     //Declares parents as the evorunner brought in
        scrollingBackground = new ScrollingBackground();    //Declares a new scrolling background
        font = generator.generateFont(parameter);       //Sets font to newly generated font with parameter
        menuButton = new TextButton.TextButtonStyle();        //Sets menuButton as a new text button
        font.getData().setScale(5);         //Sets the scale of font. Increase to increase font size
        menuButton.font = font;         //Sets the menuButtons font as the newly changed font scale
        menuButton.fontColor = Color.WHITE;
        random = new Random();
    }

    //Show method for the menu screen
    @Override
    public void show() {
        menuButton.downFontColor = getRandomColor();
        GAMEPLAY_MUSIC.setLooping(true);
        GAMEPLAY_MUSIC.play();
        parent.adHandler.showAds(false);
        batch = new SpriteBatch();
        //Declares batch as a new spriteBatch for drawing textures

        //Initializes title using title.png
        title = new Image(new Texture(Gdx.files.internal("Game UI buttons/Title.png")));

        stage = new Stage(new ScreenViewport());        //Initializes stage using a new screenViewport

        final Table table = new Table();      //Initializes table
        table.setFillParent(true);      //Runs setFillParent to match the table with the stage
        stage.addActor(table);          //Adds table to the stage as an actor

        //Declares text buttons using menuButton
        TextButton start = new TextButton("Start", menuButton);
        TextButton lootbox = new TextButton("Loot Box", menuButton);
        TextButton Characters = new TextButton("Characters", menuButton);

        //Adds a listener to the start button. Changes to gameplay screen if start
        //is pressed
        start.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GAMEPLAY_MUSIC.stop();
                dispose();
                parent.adHandler.showAds(true);
                parent.changeScreens(EvoRunner.GAMEPLAY_SCREEN);
            }
        }));

        //Adds a listener to the lootbox button. Changes to shop screen
        //if pressed
        lootbox.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.SHOP_SCREEN);
            }
        }));

        Characters.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.CHARACTERS_SCREEN);
            }
        });

        //Adds start and loot box to the table
        table.add(start);
        table.row().pad(10, 0, 10, 0);
        table.add(lootbox);
        table.row().pad(10, 0, 10, 0);
        table.add(Characters);
        Gdx.input.setInputProcessor(stage);     //Sets the input processor to the stage (for taking input)
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        scrollingBackground.update(delta,null);
        scrollingBackground.render(batch);
        title.getDrawable().draw(batch,0,Gdx.graphics.getHeight() - 100,Gdx.graphics.getWidth(),100);
        batch.end();
        stage.draw();
    }

    //Resize method, updates the viewport (for camera)
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
        batch.dispose();
        stage.dispose();
    }

    private Color getRandomColor() {
        int colorNumber = random.nextInt(10) + 1;
        Color color;
        switch(colorNumber) {
            case 1:
                color = Color.ORANGE;
                break;
            case 2:
                color = Color.OLIVE;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.BROWN;
                break;
            case 5:
                color = Color.ROYAL;
                break;
            case 6:
                color = Color.FOREST;
                break;
            case 7:
                color = Color.GOLD;
                break;
            case 8:
                color = Color.CYAN;
                break;
            case 9:
                color = Color.MAROON;
                break;
            case 10:
                color = Color.MAGENTA;
                break;
            default:
                color = Color.ORANGE;
        }
        return color;
    }
}
