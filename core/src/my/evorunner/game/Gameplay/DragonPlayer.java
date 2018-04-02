package my.evorunner.game.Gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.TimeUtils;
import my.evorunner.game.Gameplay.Obstacles.Obstacle;

import static my.evorunner.game.Constants.CHARACTER_HEIGHT_TO_WIDTH_RATIO;
import static my.evorunner.game.Constants.DEFAULT_ENVIRONENT_VELOCITY;
import static my.evorunner.game.Constants.GAMEPLAY_MUSIC;
import static my.evorunner.game.Constants.PLAYER_DEATH_SOUND;
import static my.evorunner.game.Constants.SCREEN_HEIGHT;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.EvoRunner.savedData;
import static my.evorunner.game.Gameplay.Coin.CoinState.COLLECTING;

/**
 * Created by Peter on 2/5/2018.
 */

public class DragonPlayer extends Player{
    Vector2 position;
    private Vector2 velocity;
    private Vector2 lastFramePosition;
    private Player.Jumpstate jumpstate;
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

    private Player.LifeState lifeState;

    public static float HALF_EVO_RUNNER_WIDTH;
    public static float HALF_EVO_RUNNER_HEIGHT;

    //Values for jumping.
    public static final float GRAVITY = 1000f;
    public static final float MAX_JUMP_DURATION = .3f;
    public static final float JUMP_VELOCITY = 350;

    //Count and attack used to change the sprite to its attack sprite
    int count = 0;
    boolean attack = false;

    //Identifiers
    private Animation firstWalkingAnimation;
    private Animation secondWalkingAnimation;
    private Animation thirdWalkingAnimation;
    private Animation firstAttackAnimation;
    private Animation secondAttackAnimation;
    private Animation thirdAttackAnimation;
    private int characterID;

    private boolean first;
    private boolean second;
    private boolean third;

    //Added attack animations to constructor
    public DragonPlayer(Vector2 spawnLocation,int characterID,Animation firstWalkingAnimation,
                  Animation secondWalkingAnimation,Animation thirdWalkingAnimation,
                  Animation firstAttackAnimation, Animation secondAttackAnimation,
                  Animation thirdAttackAnimation) {

        this.spawnLocation = spawnLocation;
        this.position = new Vector2();
        this.velocity = new Vector2();
        lastFramePosition = new Vector2();

        metersRan = 0;
        coinsCollected = 0;
        lives = 2;      //Lives starts at 2 and loses one in initialize. Perhaps just start at 1?
        lifeState = Player.LifeState.ALIVE;
        region = Assets.ourInstance.dragonAssets.babyDragonStanding;
        walkingStartTime = TimeUtils.nanoTime();
        HALF_EVO_RUNNER_WIDTH = SCREEN_WIDTH/50;
        HALF_EVO_RUNNER_HEIGHT = (int)(SCREEN_HEIGHT/50 * CHARACTER_HEIGHT_TO_WIDTH_RATIO);
        bounds = new Rectangle(
                position.x,
                position.y,
                HALF_EVO_RUNNER_WIDTH * 2,
                HALF_EVO_RUNNER_HEIGHT * 2);

        this.firstWalkingAnimation = firstWalkingAnimation;
        this.secondWalkingAnimation = secondWalkingAnimation;
        this.thirdWalkingAnimation = thirdWalkingAnimation;

        this.firstAttackAnimation = firstAttackAnimation;
        this.secondAttackAnimation = secondAttackAnimation;
        this.thirdAttackAnimation = thirdAttackAnimation;

        this.characterID = characterID;
        init();
    }



    public void init() {
        lives -= 1;
        position.set(spawnLocation);

        lastFramePosition.set(spawnLocation);

        velocity.setZero();

        jumpstate = Player.Jumpstate.FALLING;
    }
    public void gotHit() {
        if(lifeState != Player.LifeState.DEAD) {
            lifeState = Player.LifeState.DEAD;
            deathStartTime = TimeUtils.nanoTime();
            DEFAULT_ENVIRONENT_VELOCITY = 0;
            PLAYER_DEATH_SOUND.play();
            GAMEPLAY_MUSIC.stop();
            savedData.putInteger("coins",savedData.getInteger("coins") + coinsCollected).flush();
            lives -= 1;
        }
    }
    public boolean isFinished() {
        float timeElapsed = MathUtils.nanoToSec * TimeUtils.timeSinceNanos(deathStartTime);
        return Assets.ourInstance.playerDeathAnimationAssets.deathAnimation.isAnimationFinished(timeElapsed);
    }
    public void render(SpriteBatch batch) {
        float walkTimeKeeper = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkingStartTime);
        if(lifeState == Player.LifeState.ALIVE) {
            //This section checks if the player is currently attacking. If so, it will continue the
            //animation of the dragon opening its mouth
            //Count is used so that the animation lasts long enough to see before switching back to normal
            //Walk animation
            //Increase count == __ to make it last longer
            //TODO: Add attack animations for all sprites and have this work for any sprite
            count++;
            if (attack == true) {
                attack = false;
            }
            if (attack == false && count == 5) {
                if(metersRan <= 100) {
                    region = (TextureRegion) firstWalkingAnimation.getKeyFrame(walkTimeKeeper);
                    first = true;
                    second = false;
                    third = false;

                } else if (metersRan <= 260) {
                    region = (TextureRegion) secondWalkingAnimation.getKeyFrame(walkTimeKeeper);
                    first = false;
                    second = true;
                    third = false;

                } else if (metersRan <= 2000) {
                    region = (TextureRegion) thirdWalkingAnimation.getKeyFrame(walkTimeKeeper);
                    first = false;
                    second = false;
                    third = true;

                } else {

                }
                count = 0;
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
                SCREEN_WIDTH/24,
                SCREEN_HEIGHT/20,
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    //Changes the player sprite to an attacking one and sets attack to true
    public void attack() {
        if (first) {
            region = (TextureRegion) firstAttackAnimation.getKeyFrame(0);
        } else if (second) {
            region = (TextureRegion) secondAttackAnimation.getKeyFrame(0);
        } else if (third) {
            region = (TextureRegion) thirdAttackAnimation.getKeyFrame(0);
        }
        attack = true;
    }

    public void update(float delta, DelayedRemovalArray<Obstacle> obstacles, DelayedRemovalArray<Coin> coins) {
        velocity.y -= delta * GRAVITY;
        if (position.y < 0) {
            velocity.y = 0;
            position.y = 0;
            jumpstate = Player.Jumpstate.GROUNDED;
        }
        if(lifeState == Player.LifeState.ALIVE) {
            metersRan += .5;
            lastFramePosition.set(position);
            position.mulAdd(velocity, delta);

            for (Obstacle obstacle : obstacles) {
                if (landedOnPlatform(obstacle)) {
                    position.y = obstacle.getPositionY() + obstacle.getHeight();
                    velocity.y = 0;
                    jumpstate = Player.Jumpstate.GROUNDED;
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

    }

    //Jump method
    //Removed jumping case so that player doesn't jump more when its pressed
    public void jump() {
        switch (jumpstate) {
            case FALLING:
                break;
            //case JUMPING:
            //continueJump();
            //break;
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
        jumpstate = Player.Jumpstate.JUMPING;
        continueJump();
    }
    private void continueJump() {
        if(jumpstate == Player.Jumpstate.JUMPING) {
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - startingJumpTime);
            if(jumpDuration < MAX_JUMP_DURATION) {
                velocity.y = JUMP_VELOCITY;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (jumpstate == Player.Jumpstate.JUMPING) {
            jumpstate = Player.Jumpstate.FALLING;
        }
    }

    //Change back to ++ for normal coins
    public void coinCollected() {
        coinsCollected += 50;
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
        if(lifeState == Player.LifeState.ALIVE) {
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
