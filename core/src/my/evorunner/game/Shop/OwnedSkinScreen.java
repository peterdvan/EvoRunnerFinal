package my.evorunner.game.Shop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import my.evorunner.game.EvoRunner;
import my.evorunner.game.Gameplay.AvailableCharacters;

import static my.evorunner.game.Constants.GAMEPLAY_MUSIC;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.EvoRunner.generator;
import static my.evorunner.game.EvoRunner.parameter;
import static my.evorunner.game.EvoRunner.savedData;

/**
 * Created by Matthew on 2/4/2018.
 *
 * Class that shows the player the characters they owned. Needs work
 * to show based on saved data and such.
 *
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0:
 * Added the samurai not owned sprites.
 */

public class OwnedSkinScreen implements Screen, InputProcessor{
    private Stage stage;
    private Stage buttonStage;
    private SpriteBatch batch;
    private EvoRunner parent;

    private BitmapFont font;
    private TextButton.TextButtonStyle wordFont;

    private AvailableCharacters list;
    ArrayList<String> characterList;
    ArrayList<TextureRegion> characterTextureList;
    ArrayList<TextureRegion> ownedCharacterList;
    ArrayList<TextureRegion> notOwnedCharacterList;
    ArrayList<TextureRegion> selectCharacterList;

    public static int CURRENT_SELECTED_CHARACTER_ID;
    private final int FONT_SIZE = 3;
    OrthographicCamera camera;
    ScreenViewport view;

    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTextureRegionDrawable;

    //Constructor, takes in an available characters listIn object
    //for use
    public OwnedSkinScreen(EvoRunner parentIn) {
        this.parent = parentIn;
        font = generator.generateFont(parameter);
        wordFont = new TextButton.TextButtonStyle();
        wordFont.font = font;
        CURRENT_SELECTED_CHARACTER_ID = 2;

        //Initializes variables based on listIn
        list = new AvailableCharacters();
        characterList = list.getList();

        characterTextureList = list.getTextureList(1);
        ownedCharacterList = list.getTextureList(2);
        notOwnedCharacterList = list.getTextureList(3);
        selectCharacterList = list.getTextureList(4);
    }

    public void show() {
        GAMEPLAY_MUSIC.setLooping(true);
        GAMEPLAY_MUSIC.play();
        parent.adHandler.showAds(false);        //Set to true to turn off ads


        camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
        view = new ScreenViewport(camera);
        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(view, batch);
        buttonStage = new Stage();


        //Added code
        myTexture = new Texture(Gdx.files.internal("Environment/parallax-forest.png"));
        myTextureRegion = new TextureRegion(myTexture);
        myTextureRegionDrawable = new TextureRegionDrawable(myTextureRegion);
        Table back = new Table();
        back.setFillParent(true);
        back.background(myTextureRegionDrawable);
        buttonStage.addActor(back);
        //

        InputMultiplexer multi = new InputMultiplexer(buttonStage, stage, this);
        Gdx.input.setInputProcessor(multi);


        //Adds table to stage
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Adds back button to the stage
        TextButton backButton = new TextButton("<=",wordFont);
        backButton.getLabel().setFontScale(3);
        backButton.setPosition(70,SCREEN_HEIGHT * 7 / 8);
        backButton.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dispose();
                parent.changeScreens(EvoRunner.MENU_SCREEN);
            }
        }));

        buttonStage.addActor(backButton);
        addButtons();
    }

    public void addButtons() {
        TextButton healer = new TextButton("Select", wordFont);
        healer.getLabel().setFontScale(FONT_SIZE);
        healer.setPosition(SCREEN_WIDTH/7 + 25, (SCREEN_HEIGHT - (SCREEN_HEIGHT / 3)) - 35);
        healer.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 0).flush();
            }
        }));
        stage.addActor(healer);

        TextButton mage = new TextButton("Select", wordFont);
        mage.getLabel().setFontScale(FONT_SIZE);
        mage.setPosition((SCREEN_WIDTH / 7) + (SCREEN_WIDTH / 3) + 25, (SCREEN_HEIGHT - (SCREEN_HEIGHT / 3)) - 35);
        mage.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 1).flush();
            }
        }));
        stage.addActor(mage);

        TextButton ranger = new TextButton("Select", wordFont);
        ranger.getLabel().setFontScale(FONT_SIZE);
        ranger.setPosition((SCREEN_WIDTH / 7) + (2 * (SCREEN_WIDTH / 3)) + 25, (SCREEN_HEIGHT - (SCREEN_HEIGHT / 3)) - 35);
        ranger.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 2).flush();
            }
        }));
        stage.addActor(ranger);

        TextButton warrior = new TextButton("Select", wordFont);
        warrior.getLabel().setFontScale(FONT_SIZE);
        warrior.setPosition((SCREEN_WIDTH / 7) + 25, (SCREEN_HEIGHT - (2 * (SCREEN_HEIGHT / 3))) - 85);
        warrior.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 3).flush();
            }
        }));
        stage.addActor(warrior);

        TextButton darkknight = new TextButton("Select", wordFont);
        darkknight.getLabel().setFontScale(FONT_SIZE);
        darkknight.setPosition((SCREEN_WIDTH / 7) + (SCREEN_WIDTH / 3) + 25, (SCREEN_HEIGHT - (2 * (SCREEN_HEIGHT / 3))) - 85);
        darkknight.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 4).flush();
            }
        }));
        stage.addActor(darkknight);

        TextButton monk = new TextButton("Select", wordFont);
        monk.getLabel().setFontScale(FONT_SIZE);
        monk.setPosition((SCREEN_WIDTH / 7) + (2 * (SCREEN_WIDTH / 3)) + 25, (SCREEN_HEIGHT - (2 * (SCREEN_HEIGHT / 3))) - 85);
        monk.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 5).flush();
            }
        }));
        stage.addActor(monk);

        TextButton ninja = new TextButton("Select", wordFont);
        ninja.getLabel().setFontScale(FONT_SIZE);
        ninja.setPosition((SCREEN_WIDTH / 7) + 25, (SCREEN_HEIGHT - (3 * (SCREEN_HEIGHT / 3))) - 135);
        ninja.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 6).flush();
            }
        }));
        stage.addActor(ninja);

        TextButton paladin = new TextButton("Select", wordFont);
        paladin.getLabel().setFontScale(FONT_SIZE);
        paladin.setPosition((SCREEN_WIDTH / 7) + (SCREEN_WIDTH / 3) + 25, (SCREEN_HEIGHT - (3 * (SCREEN_HEIGHT / 3))) - 135);
        paladin.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 7).flush();
            }
        }));
        stage.addActor(paladin);

        TextButton samurai = new TextButton("Select", wordFont);
        samurai.getLabel().setFontScale(FONT_SIZE);
        samurai.setPosition((SCREEN_WIDTH / 7) + (2 * (SCREEN_WIDTH / 3)) + 25, (SCREEN_HEIGHT - (3 * (SCREEN_HEIGHT / 3))) - 135);
        samurai.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 8).flush();
            }
        }));
        stage.addActor(samurai);

        TextButton soldier = new TextButton("Select", wordFont);
        soldier.getLabel().setFontScale(FONT_SIZE);
        soldier.setPosition((SCREEN_WIDTH / 7) + 25, (SCREEN_HEIGHT - (4 * (SCREEN_HEIGHT / 3))) - 185);
        soldier.addListener((new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savedData.putInteger("Character", 9).flush();
            }
        }));
        stage.addActor(soldier);
    }

    //Goes through the list and renders all the characters
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        buttonStage.draw();
        batch.begin();
        int height = 1;
        int count = 0;
        int add = 0;
        for (int i = 0; i < ownedCharacterList.size(); i++) {
            add = (height - 1) * 50;
            if (savedData.getBoolean(list.getCharacterName(i), false)) {
                if (savedData.getInteger("Character") == i) {
                    batch.draw(
                            selectCharacterList.get(i),
                            (SCREEN_WIDTH / 9) + (count * (SCREEN_WIDTH / 3)),
                            SCREEN_HEIGHT - ((height) * (SCREEN_HEIGHT) / 3) - add,
                            0,
                            0,
                            SCREEN_WIDTH / 6,
                            SCREEN_HEIGHT / 3,
                            1,
                            1,
                            0);
                } else {
                    batch.draw(
                            ownedCharacterList.get(i),
                            (SCREEN_WIDTH / 9) + (count * (SCREEN_WIDTH / 3)),
                            SCREEN_HEIGHT - ((height) * (SCREEN_HEIGHT) / 3) - add,
                            0,
                            0,
                            SCREEN_WIDTH / 6,
                            SCREEN_HEIGHT / 3,
                            1,
                            1,
                            0);
                }
            } else {
                batch.draw(
                        notOwnedCharacterList.get(i),
                        (SCREEN_WIDTH / 9) + (count * (SCREEN_WIDTH / 3)),
                        SCREEN_HEIGHT - ((height) * (SCREEN_HEIGHT) / 3) - add,
                        0,
                        0,
                        SCREEN_WIDTH / 6,
                        SCREEN_HEIGHT / 3,
                        1,
                        1,
                        0);
            }
            count++;
            if (count == 3) {
                height++;
                count = 0;
            }
        }

        batch.end();
        camera.update();

        stage.draw();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        batch.dispose();
        stage.dispose();
    }

    //Resize method, updates the viewport (for camera)
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void resume() {

    }
    public void pause() {

    }
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        float y = Gdx.input.getDeltaY();
        if (camera.position.y + y > SCREEN_HEIGHT / 2) {
            camera.position.set(camera.position.x, SCREEN_HEIGHT / 2, camera.position.z);
        } else if (camera.position.y + y < -1 * (SCREEN_HEIGHT * 3)) {
            camera.position.set(camera.position.x, -1 * (SCREEN_HEIGHT * 3), camera.position.z);
        } else {
            camera.position.add(0, y, 0);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
