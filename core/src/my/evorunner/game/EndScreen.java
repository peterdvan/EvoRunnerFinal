package my.evorunner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

/**
 * End screen for when the player dies during the game.
 * Version: 1.0
 * Last edited: Matthew Phan
 *
 * Changelog 1.0
 * Commented out table.setDebug(true) in function setup stage to make the end screen look cleaner
 * Changed variable name label2 to youRun for comprehension
 * Removed declaration of table in function show
 * Changed path  playAgainButton and backsImageButton to Game UI buttons
 */

public class EndScreen implements Screen {
    //Declare variables for use later in the program. Initialization occurs
    //within the set up method
    private TextureRegion region;
    private EvoRunner parent;
    private Stage stage;
    private Table table;
    private TextButton playAgain;
    private TextButton back;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Random random;
    private SpriteBatch batch;

    //EndScreen constructor that takes in EvoRunner class as a parameter
    //Sets the current EndScreens parent to be the one brought in as a
    //parameter
    public EndScreen (EvoRunner parent) {
        this.parent = parent;
        random = new Random();
    }

    //SetupStage method for the end screen
    private void setUpStage(Stage stage) {
        //Declare a new table
        table = new Table();

        //Declares parameter as a free type font with size 60
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 60;
        //Declares a bitmapFont using the parameter declared with FTF
        BitmapFont font24 = EvoRunner.generator.generateFont(parameter);

        TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        textStyle.font = font24;
        textStyle.downFontColor = getRandomColor();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font24;
        Label youRan = new Label("You Ran",labelStyle);
        table.add(youRan);

        //Meters label that holds the number of meters the user ran
        Label meters = new Label("" + parent.getMeters(),labelStyle);
        EvoRunner.savedData.putFloat("HighScore", parent.getMeters()).flush();

        //Adds the number of meters ran to the table
        table.row().pad(youRan.getHeight()/2, 0, 0, 0);     //Adds padding to the table
        table.add(meters);      //Adds the number of meters ran to the table
        stage.addActor(table);      //Adds the table to the stage
        table.row().pad(youRan.getHeight()/2, 0, 0, 0);     //Adds padding to the table

        //Initializes play again as a new button using the play again button png
        playAgain = new TextButton("Play Again",textStyle);
        playAgain.addListener((new ChangeListener() {       //Adds a listener to the play again button

            //If the button is pressed, runs dispose to clean up the end screen
            //Runs change screen and takes the user back to the game play screen
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.GAMEPLAY_SCREEN);
            }
        }));
        table.add(playAgain);       //Adds playAgain to the table

        //Initializes back as a new button using the back image button png
        back = new TextButton("Back",textStyle);
        back.addListener((new ChangeListener() {        //Adds a listener to the back button

            //If the button is pressed, runs dispose to clean up the end screen
            //Runs change screens and takes the user back to the menu screen
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.MENU_SCREEN);
            }
        }));
        table.row().pad(youRan.getHeight()/2, 0, 0, 0);     //Adds some padding under youRan label
        table.add(back);        //Adds the back button to the table
        table.setFillParent(true);      //Sizes the root table so it goes to the center of the screen
        //table.setDebug(true);     //Uncomment to show debug lines in the table
    }

    //Show method. Declares stage as a new stage with
    //runs setUpStage with the newly declared stage
    @Override
    public void show() {
        batch = new SpriteBatch();
        region = new TextureRegion(new Texture(Gdx.files.internal("Environment/DarkHouseTiles.png")));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        parent.adHandler.showAds(true);
        setUpStage(stage);
    }


    //Method for rendering the screen. Changes the color
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);        //Changes the screen color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);    //Clears the screen
        batch.begin();
        batch.draw(region,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        //Calls act using the minimum of the time it took for the last frame or 1/30f (for 30 fps)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    //Disposes the stage for cleaning up
    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
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
