package my.evorunner.game.Gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import my.evorunner.game.EvoRunner;
import my.evorunner.game.Gameplay.Enemies.Enemies;
import my.evorunner.game.Gameplay.Obstacles.Obstacles;
import my.evorunner.game.Gameplay.Skill_Buttons.GameTime;

import my.evorunner.game.Gameplay.Skill_Buttons.ButtonTable;

import static my.evorunner.game.Constants.COIN_PICTURE;
import static my.evorunner.game.Constants.DEFAULT_ENVIRONENT_VELOCITY;
import static my.evorunner.game.Constants.GAMEPLAY_MUSIC;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.Constants.STARTING_POSITION;
import static my.evorunner.game.EvoRunner.generator;
import static my.evorunner.game.EvoRunner.parameter;
import static my.evorunner.game.EvoRunner.savedData;


/**
 * Version 1.1
 * Last Edited: Matthew Phan
 * Changelog 1.1
 * Removed multi input processor and replaced it with button table
 * button table now holds a button for jump and attack
 * second render method added to change the player sprite when attacking
 *
 */

public class GameplayScreen implements Screen{
    //UI Elements
    ButtonTable buttonTable;
    public Stage stage;
    public Stage buttonTableStage;

    //Other
    private EvoRunner parent;
    private ScrollingBackground scrollingBackground;
    private SpriteBatch batch;
    private ScreenViewport viewport;
    private Player player;
    private Enemies enemies;
    private Obstacles obstacles;
    private Coins coins;
    private IntroductionNPC introductionNPC;

    private int width;
    private int height;
    private Vector2 position;
    private BitmapFont font;
    private TextButton.TextButtonStyle wordFont;
    private TextButton text;
    private TextButton text2;
    private ImageButton textBubble;

    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTextureRegionDrawable;

    public GameplayScreen(EvoRunner evoParent) {
        this.parent = evoParent;
        scrollingBackground = new ScrollingBackground();
        this.parent.adHandler.showAds(true);
    }

    public float getMeters() {
        return player.getMetersRan();
    }
    @Override
    public void show() {
        DEFAULT_ENVIRONENT_VELOCITY = 200;
        AssetManager am = new AssetManager();
        Assets.ourInstance.init(am);
        batch = new SpriteBatch();

        viewport = new ScreenViewport();
        obstacles = new Obstacles(viewport);

        player = new Player(STARTING_POSITION, savedData.getInteger("Character", 0));


        enemies = new Enemies(viewport);
        parameter.size = 24;
        font = generator.generateFont(parameter);
        coins = new Coins(viewport);

        GAMEPLAY_MUSIC.setLooping(true);
        GAMEPLAY_MUSIC.play();
        setUpUI();

        width = SCREEN_WIDTH / 18;
        height = SCREEN_HEIGHT / 10;

        font = generator.generateFont(parameter);
        wordFont = new TextButton.TextButtonStyle();
        wordFont.font = font;

        position = new Vector2(SCREEN_WIDTH * 1.5f,0);

        text = new TextButton("The last guy to escape ran " ,wordFont);
        text2 = new TextButton(savedData.getFloat("HighScore", 0) + " meters. Can you escape?", wordFont);
        text.getLabel().setFontScale(1);
        text2.getLabel().setFontScale(1);

        myTexture = new Texture(Gdx.files.internal("Items/TextBubble.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTextureRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        textBubble = new ImageButton(myTextureRegionDrawable); //Set the button up
    }

    private void setUpUI() {
        stage = new Stage();
        buttonTableStage = new Stage();
        buttonTable = new ButtonTable(player, buttonTableStage);
        Gdx.input.setInputProcessor(buttonTableStage);
        introductionNPC = new IntroductionNPC();
    }
    @Override
    public void hide() {
        GAMEPLAY_MUSIC.stop();
    }

    @Override
    public void render(float delta) {
        position.x -= DEFAULT_ENVIRONENT_VELOCITY * delta;
        text.setPosition(position.x + (width / 2) + 5,position.y + height + 20);
        text2.setPosition(position.x + (width / 2) + 5,position.y + height + 5);
        textBubble.setPosition(position.x + (width / 2), position.y + height);
        stage.addActor(textBubble);
        stage.addActor(text);
        stage.addActor(text2);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateComponents(delta);

        GameTime.updateCurrentTime(Gdx.graphics.getDeltaTime());

        batch.begin();
        scrollingBackground.render(batch);
        obstacles.render(batch);
        coins.render(batch);
        enemies.render(batch);
        player.render(batch);
        drawInformation(batch);
        introductionNPC.render(batch,delta);
        batch.end();
        buttonTableStage.draw();

        stage.draw();
    }

    public void render() {
        batch.begin();
        scrollingBackground.render(batch);
        obstacles.render(batch);
        coins.render(batch);
        enemies.render(batch);
        player.render(batch);
        drawInformation(batch);
        batch.end();
        buttonTableStage.draw();
        stage.draw();
    }

    private void updateComponents(float delta) {
        endGame();
        increaseDifficulty();
        scrollingBackground.update(delta,player);
        enemies.update(delta,player);
        coins.update(delta, obstacles.getObstacleList());

        obstacles.update(delta,player);
        player.update(delta,obstacles.getObstacleList(),coins.getCoinsList(),enemies.getEnemiesList());
      //  buttonTable.update();
    }
    @Override
    public void resize(int width, int height) {
        scrollingBackground.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        batch.dispose();
    }

    private void endGame () {
        if(player.checkLives() <= 0 ) {
            if (player.isFinished()) {
                dispose();
                parent.changeScreens(EvoRunner.END_SCREEN);
                DEFAULT_ENVIRONENT_VELOCITY = 200;
            }
        }
    }
    private void drawInformation(SpriteBatch batch) {
        font.draw(batch, player.getMetersRan() + "m",0,SCREEN_HEIGHT - parent.adHandler.getBannerHeight());
        batch.draw(
                COIN_PICTURE.getTexture(),
                SCREEN_WIDTH - SCREEN_WIDTH/7 - SCREEN_WIDTH/12,
                SCREEN_HEIGHT - SCREEN_HEIGHT/14 - parent.adHandler.getBannerHeight(),
                0,
                0,
                SCREEN_WIDTH/12,
                SCREEN_HEIGHT/7,
                1,
                1,
                0,
                COIN_PICTURE.getRegionX(),
                COIN_PICTURE.getRegionY(),
                COIN_PICTURE.getRegionWidth(),
                COIN_PICTURE.getRegionHeight(),
                false,
                false);
        font.draw(batch, Integer.toString(player.getCoins()),SCREEN_WIDTH - SCREEN_WIDTH/7,SCREEN_HEIGHT - parent.adHandler.getBannerHeight());
    }

    private void increaseDifficulty() {
        if(player.getMetersRan() % 500 == 0 &&  player.getMetersRan() <= 3000) {
            enemies.generateMonster();
        }
        if(player.getMetersRan() == 500 || player.getMetersRan() == 1500 ) {
            enemies.addBat();
        }
    }
}
