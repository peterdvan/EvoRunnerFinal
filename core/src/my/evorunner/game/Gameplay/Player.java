package my.evorunner.game.Gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Enemies.Enemy;
import my.evorunner.game.Gameplay.Obstacles.Obstacle;
import my.evorunner.game.Gameplay.Weapons.Weapons;

import my.evorunner.game.Constants;
import my.evorunner.game.EvoRunner;

import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.Gameplay.Coin.CoinState.COLLECTING;

/**
 * Player file
 *
 * Version 1.3
 * Last Edited: Matthew Phan
 * Changelog 1.3
 * Increased Max jump duration to .3f from .2f to help players jump over slimes
 * Increased Jump velocity from 250 to 350 to help players jump over slimes
 *
 *
 * Changelog 1.2
 * Commented out case JUMPING to remove double jump on button press
 * Note: might need to increase jump velo because default jump is smaller now
 * modified player render method to account for if the player attacks
 */

public class Player {
    Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;
    private Jumpstate jumpstate;
    private long startingJumpTime;

    private Vector2 spawnLocation;
    private float metersRan;
    int lives;
    public TextureRegion region;
    public Rectangle bounds;

    private int coinsCollected;

    long walkingStartTime;
    private long deathStartTime;
    private float deathTime;

    private LifeState lifeState;

    public static float HALF_EVO_RUNNER_WIDTH;
    public static float HALF_EVO_RUNNER_HEIGHT;

    //Values for jumping.
    public static final float GRAVITY = SCREEN_WIDTH * 1.25f;
    public static final float MAX_JUMP_DURATION = .2f;
    public static final float JUMP_VELOCITY = SCREEN_HEIGHT * .70f;


    //Identifiers
    private Animation firstWalkingAnimation;
    private Animation secondWalkingAnimation;
    private Animation thirdWalkingAnimation;

    private boolean firstEvo;
    private boolean secondEvo;
    private boolean thirdEvo;

    private int characterID;
    private boolean evolutionFlag;
    //Weapon
    private Weapons weapons;

    public Player() {

    }
    //Added attack animations to constructor
    public Player(Vector2 spawnLocation,int characterID) {

        AvailableCharacters currentChar = new AvailableCharacters();
        firstWalkingAnimation = currentChar.getFirstWalk(characterID);
        secondWalkingAnimation = currentChar.getSecondWalk(characterID);
        thirdWalkingAnimation = currentChar.getThirdWalk(characterID);

        evolutionFlag = false;
        this.spawnLocation = spawnLocation;
        this.position = new Vector2();
        this.velocity = new Vector2();
        lastFramePosition = new Vector2();
        metersRan = 0;
        coinsCollected = 0;
        lives = 2;      //Lives starts at 2 and loses one in initialize. Perhaps just start at 1?
        lifeState = LifeState.ALIVE;
        region = currentChar.getCharacter(characterID);
        walkingStartTime = TimeUtils.nanoTime();
        HALF_EVO_RUNNER_WIDTH = Constants.SCREEN_WIDTH/50;
        HALF_EVO_RUNNER_HEIGHT = (int)(SCREEN_HEIGHT/50 * Constants.CHARACTER_HEIGHT_TO_WIDTH_RATIO);
        bounds = new Rectangle(
                position.x,
                position.y,
                HALF_EVO_RUNNER_WIDTH * 2,
                HALF_EVO_RUNNER_HEIGHT * 2);

        this.characterID = characterID;
        weapons = new Weapons();
        init();
    }



    public void init() {
        lives -= 1;
        position.set(spawnLocation);

        lastFramePosition.set(spawnLocation);

        velocity.setZero();

        jumpstate = Jumpstate.FALLING;
    }
    public void gotHit() {
        if(lifeState != LifeState.DEAD) {
            lifeState = LifeState.DEAD;
            deathStartTime = TimeUtils.nanoTime();
            Constants.DEFAULT_ENVIRONENT_VELOCITY = 0;
            Constants.PLAYER_DEATH_SOUND.play();
            Constants.GAMEPLAY_MUSIC.stop();
            EvoRunner.savedData.putInteger("coins", EvoRunner.savedData.getInteger("coins") + coinsCollected).flush();
            lives -= 1;
        }
    }
    public boolean isFinished() {
        float timeElapsed = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(deathStartTime);
        return Assets.ourInstance.playerDeathAnimationAssets.deathAnimation.isAnimationFinished(timeElapsed);
    }
    public void render(SpriteBatch batch) {
        float walkTimeKeeper = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkingStartTime);
        if(lifeState == LifeState.ALIVE) {
            if(metersRan <= 250) {
                region = (TextureRegion) firstWalkingAnimation.getKeyFrame(walkTimeKeeper);
            } else if (metersRan <= 350) {
                region = (TextureRegion)Assets.ourInstance.evolutionAssets.evolutionAnimation.getKeyFrame(walkTimeKeeper);
            }else if (metersRan <= 1000) {
                Constants.DEFAULT_ENVIRONENT_VELOCITY = 300;
                region = (TextureRegion) secondWalkingAnimation.getKeyFrame(walkTimeKeeper);
            } else if (metersRan <= 1050) {
                region = (TextureRegion)Assets.ourInstance.evolutionAssets.evolutionAnimation.getKeyFrame(walkTimeKeeper);
            }else if (metersRan > 1200){
                Constants.DEFAULT_ENVIRONENT_VELOCITY = 400;
                region = (TextureRegion) thirdWalkingAnimation.getKeyFrame(walkTimeKeeper);
            }

        } else {
            deathTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - deathStartTime);
            region = (TextureRegion) Assets.ourInstance.playerDeathAnimationAssets.deathAnimation.getKeyFrame(deathTime);
        }
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                Constants.SCREEN_WIDTH/24,
                SCREEN_HEIGHT/10,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
        weapons.render(batch);
    }

    //Changes the player sprite to an attacking one and sets attack to true
    public void attack() {
        weapons.addWeapon(this);
    }

    public void update(float delta, DelayedRemovalArray<Obstacle> obstacles, DelayedRemovalArray<Coin> coins, DelayedRemovalArray<Enemy> enemies) {
        velocity.y -= delta * GRAVITY;
        if (position.y < 0) {
            velocity.y = 0;
            position.y = 0;
            jumpstate = Jumpstate.GROUNDED;
        }
        if(lifeState == LifeState.ALIVE) {
            metersRan += .5;
            lastFramePosition.set(position);
            position.mulAdd(velocity, delta);

            for (Obstacle obstacle : obstacles) {
                if (landedOnPlatform(obstacle)) {
                    position.y = obstacle.getPositionY() + obstacle.getHeight();
                    velocity.y = 0;
                    jumpstate = Jumpstate.GROUNDED;
                }
            }

            for (Coin coin : coins) {
                if (bounds.overlaps(coin.getBounds()) && coin.getCoinState() != COLLECTING) {
                    coin.setCoinstate(COLLECTING);
                    coin.setCoinCollectionStartTime(TimeUtils.nanoTime());
                    coinCollected();
                }
            }

            bounds.set(position.x, position.y, region.getRegionWidth(), region.getRegionHeight());

        }
        weapons.update(delta,enemies);
    }

    //Jump method
    //Removed jumping case so that player doesn't jump more when its pressed
    public void jump() {
        switch (jumpstate) {
            case FALLING:
                break;
            case JUMPING:
                continueJump();
                break;
            case GROUNDED:
                startJump();
                break;
        }

    }
    private boolean landedOnPlatform(Obstacle obstacle) {
        float obstacleHeight = obstacle.getHeight() + obstacle.getPositionY();
        if(lastFramePosition.y  >= obstacleHeight && position.y <  obstacleHeight) {
            float leftFoot = position.x;
            float rightFoot = position.x + HALF_EVO_RUNNER_WIDTH;
            if(obstacle.getPositionX() < leftFoot && obstacle.getPositionX() + obstacle.getWidth() > leftFoot) {
                return true;
            } else if (obstacle.getPositionX() < rightFoot && obstacle.getPositionX() + obstacle.getWidth() > rightFoot) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }




    private void startJump() {
        startingJumpTime = TimeUtils.nanoTime();
        jumpstate = Jumpstate.JUMPING;
        continueJump();
    }
    private void continueJump() {
        if(jumpstate == Jumpstate.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startingJumpTime);
            if(jumpDuration < MAX_JUMP_DURATION) {
                velocity.y = JUMP_VELOCITY;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (jumpstate == Jumpstate.JUMPING) {
            jumpstate = Jumpstate.FALLING;
        }
    }

    //Change back to ++ for normal coins
    public void coinCollected() {
        coinsCollected += 1;
    }

    public float getMetersRan() {
        return metersRan;
    }

    public int getCoins() {
        return coinsCollected;
    }

    public float getPositionX() {
        return position.x;
    }
    public float getPositionY() {
        return position.y;
    }

    public float getRegionWidth() {
        return region.getRegionWidth();
    }

    public float getRegionHeight() {
        return region.getRegionHeight();
    }

    public int getCharacterID() {
        return characterID;
    }
    public int checkLives() {
        return lives;
    }

    public boolean isPlayerAlive() {
        if(lifeState == LifeState.ALIVE) {
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    enum Jumpstate {
        JUMPING,FALLING,GROUNDED
    }

    enum LifeState {
        ALIVE,DEAD
    }
}
