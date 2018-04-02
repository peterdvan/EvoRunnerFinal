package my.evorunner.game.Gameplay.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import my.evorunner.game.Gameplay.Player;

import java.util.Random;


/**
 * Created by Peter on 1/6/2018.
 */

public class Enemies {
    private DelayedRemovalArray<Enemy> enemyList;
    Viewport viewport;
    public static final int NUMBER_OF_ENEMIES = 5;
    Random random;
    public Enemies(Viewport viewport) {
        this.viewport = viewport;
        random = new Random();
        enemyList = new DelayedRemovalArray<Enemy>();
        for(int i = 0; i < NUMBER_OF_ENEMIES; i++) {
             generateMonster();
        }
    }

    public void update(float delta,Player evoRunner) {
        for(Enemy enemy : enemyList) {
            enemy.update(delta,evoRunner);
            enemy.resetPosition(evoRunner);
        }
        for(int i = 0; i < enemyList.size; i++) {
            if(enemyList.get(i).isDead()) {
                enemyList.removeIndex(i);
               generateMonster();
            }

        }
       // enemyList.begin();
       // enemyList.end();
    }

    public void addSlime() {
        enemyList.add(new LavaSlime(new Vector2(random.nextInt(Gdx.graphics.getWidth() * 3) + Gdx.graphics.getWidth(),0),random));
    }

    public void addBat() {
        enemyList.add(new Bat(new Vector2(random.nextInt(Gdx.graphics.getWidth() * 3) + Gdx.graphics.getWidth(),0),random));
    }

    public void addFlower() {
        enemyList.add(new Flower(new Vector2(random.nextInt(Gdx.graphics.getWidth() * 3) + Gdx.graphics.getWidth(),0),random));
    }
    public void addMushroom() {
        enemyList.add(new Mushroom(new Vector2(random.nextInt(Gdx.graphics.getWidth() * 3) + Gdx.graphics.getWidth(),0),random));
    }

    public void generateMonster() {
        int num = random.nextInt(3);
        switch(num){
            case 0:
                addSlime();
                break;
            case 1:
                addFlower();
                break;
            case 2:
                addMushroom();
                break;
        }
    }
    public void render(SpriteBatch batch) {
        for(Enemy enemy : enemyList) {
            enemy.render(batch);
        }
    }

    public DelayedRemovalArray<Enemy> getEnemiesList() {
        return enemyList;
    }
}

