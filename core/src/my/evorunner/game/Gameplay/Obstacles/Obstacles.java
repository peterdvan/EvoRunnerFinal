package my.evorunner.game.Gameplay.Obstacles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import my.evorunner.game.Gameplay.Player;

import java.util.Random;

import static my.evorunner.game.Constants.KILL_PLANE;
import static my.evorunner.game.Gameplay.Player.HALF_EVO_RUNNER_HEIGHT;

/**
 * Created by Peter on 1/9/2018.
 */

public class Obstacles {
    private DelayedRemovalArray<Obstacle> obstacleList;
    Viewport viewport;
    Random random;
    public static final int NUMBER_OF_PLATFORMS = 12;
    public static final int  TYPES_OF_PLATFORMS = 4;
    public static final int  HEIGHT_LEVELS = 2;
    public Obstacles(Viewport viewport) {
        this.viewport = viewport;
        random = new Random();
        init();
    }

    public void init() {
        obstacleList = new DelayedRemovalArray<Obstacle>();
        for(int i = 0;i < NUMBER_OF_PLATFORMS; i++) {
            Obstacle obstacle;
             int type = randomNumber(TYPES_OF_PLATFORMS) + 1;
            switch (type) {
                case 1:
                     obstacle = new Platform1(new Vector2(random.nextInt(Gdx.graphics.getWidth()) + Gdx.graphics.getWidth() ,(int) HALF_EVO_RUNNER_HEIGHT),random);
                    break;
                case 2:
                     obstacle = new Platform2(new Vector2(random.nextInt(Gdx.graphics.getWidth()) + Gdx.graphics.getWidth() ,(int) HALF_EVO_RUNNER_HEIGHT),random);
                    break;
                case 3:
                     obstacle = new Platform3(new Vector2(random.nextInt(Gdx.graphics.getWidth()) + Gdx.graphics.getWidth() ,(int) HALF_EVO_RUNNER_HEIGHT),random);
                    break;
                default:
                     obstacle = new Platform3(new Vector2(random.nextInt(Gdx.graphics.getWidth()) + Gdx.graphics.getWidth() ,(int) HALF_EVO_RUNNER_HEIGHT),random);
            }
            obstacleList.add(obstacle);
        }

    }
    private int randomNumber(int num) {
        Random rand = new Random();
        return rand.nextInt(num);
    }

    public void update(float delta,Player evoRunner) {
        for(Obstacle obstacle : obstacleList) {
            obstacle.update(delta,evoRunner);
            if(obstacle.getPositionX() + obstacle.getWidth()< KILL_PLANE.x) {
                redistributePlatforms(obstacle);
            }
        }
       // obstacleList.begin();
       // obstacleList.end();
    }

    public void render(SpriteBatch batch) {
        for(Obstacle obstacle : obstacleList) {
            obstacle.render(batch);
        }
    }

    public DelayedRemovalArray<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public void redistributePlatforms(Obstacle obstacle) {
        int level = random.nextInt(HEIGHT_LEVELS) + 1;
        obstacle.setPosition(random.nextInt(Gdx.graphics.getWidth()) + Gdx.graphics.getWidth() ,(int)HALF_EVO_RUNNER_HEIGHT * level * 2);
    }
}
