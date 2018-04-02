package my.evorunner.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * File that holds the constant values for the game. Declares values for the screen and some monster
 * values. Takes in multiple .wav files for certain sound effects.
 *
 * Version 1.0
 * Last Edited: Matthew Phan
 * Changelog 1.0
 */

public class Constants {
    //Takes .wav for slime death sound effect
    public static final Sound SLIME_DEATH_SOUND = Gdx.audio.newSound(Gdx.files.internal("Sound Effects/flyswatter2.wav"));

    //Declares bounds_buffer for x and y coordinates for other classes
    public static final float BOUNDS_BUFFER_X = 20;
    public static final float BOUNDS_BUFFER_Y = 20;
    public static final Vector2 KILL_PLANE = new Vector2(0,0);
    public static final Vector2 STARTING_POSITION = new Vector2(100,0);     //Declare a new 2d vector for the starting position of player
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();//791
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight(); //480
    public static final double CHARACTER_HEIGHT_TO_WIDTH_RATIO = 3;

    //Speeds for bat and slime
    public static int LAVA_SLIME_SPEED = SCREEN_WIDTH/10;
    public static int BAT_SPEED = SCREEN_WIDTH/(int)2.63;
    public static float DEFAULT_ENVIRONENT_VELOCITY = SCREEN_WIDTH / 4;      //Speed for the environment at title screen

    //Epsilon declared for compaing float numbers
    public static final float EPSILON = 0.00001f;

    //Default music declared based on .wav files
    public static final Music GAMEPLAY_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("Sound Effects/In The Forest (NES).wav"));
    public static final Music PLAYER_DEATH_SOUND = Gdx.audio.newMusic(Gdx.files.internal("Sound Effects/deathSound.wav"));

    //Character Skin Names
    public static final String CHILD = "Child";
    public static final String SAMURAI = "Child";

    //static pictures
    public static TextureRegion COIN_PICTURE = new TextureRegion(new Texture(Gdx.files.internal("Items/goldCoin5.png")));
}
