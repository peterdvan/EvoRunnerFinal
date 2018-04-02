package my.evorunner.game.Gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import my.evorunner.game.Gameplay.Obstacles.Obstacle;

import java.util.Random;

import static my.evorunner.game.Constants.KILL_PLANE;
import static my.evorunner.game.Constants.SCREEN_WIDTH;
import static my.evorunner.game.Gameplay.Coin.COIN_HEIGHT;

/**
 * Created by Peter on 1/26/2018.
 */

public class Coins {

    DelayedRemovalArray<Coin> coinsList;
    Viewport viewport;
    public static final int COIN_PER_SEC = 1;
    Random random;
    public Coins(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        coinsList = new DelayedRemovalArray<Coin>();
        random = new Random();
    }

    public void update(float delta, DelayedRemovalArray<Obstacle> obstacleList) {
        if(MathUtils.random() < delta * COIN_PER_SEC) {

            Vector2 newCoinPosition = generatePosition(obstacleList);
            if(newCoinPosition == null) {
                return;
            }
            Coin coin = new Coin(newCoinPosition);
            int coinCase = random.nextInt(5) + 1;
            switch (coinCase) {
                case 1:
                    generateSetOfFourCoins(newCoinPosition);
                    break;
                default:
                    coinsList.add(coin);
            }

        }
        for(Coin coin: coinsList) {
            coin.update(delta);
        }
        coinsList.begin();
        for(int i = 0; i < coinsList.size; i++) {
            if(coinsList.get(i).position.x < KILL_PLANE.x - coinsList.get(i).getWidth() ||
                    coinsList.get(i).getCoinState() == Coin.CoinState.COLLECTED) {
                coinsList.removeIndex(i);
            }
        }
        coinsList.end();
    }

    public void render(SpriteBatch batch) {
        for(Coin coin : coinsList) {
            coin.render(batch);
        }
    }

    public DelayedRemovalArray<Coin> getCoinsList() {
        return  coinsList;
    }

    private void generateSetOfFourCoins(Vector2 position) {
        int num = random.nextInt(10) + 1;
        if(num <= 5) {
            for(int i = 0; i < 2; i++) {
                float x = position.x + COIN_HEIGHT * i;
                for(int j = 0; j < 2;j++) {
                    float y = position.y + COIN_HEIGHT * j;
                    Vector2 newCoinPosition = new Vector2(Gdx.graphics.getWidth() + x, y);
                    Coin coin = new Coin(newCoinPosition);
                    coinsList.add(coin);
                }
            }
        }
    }

    //Uses obstacle Position to place coins
    private Vector2 generatePosition(DelayedRemovalArray<Obstacle> obstacleList) {
        int obstacleIndex = random.nextInt(obstacleList.size);
        int counter = 0;
        while (obstacleList.get(obstacleIndex).getPositionX() <= SCREEN_WIDTH) {
            counter++;
            obstacleIndex = random.nextInt(obstacleList.size);
            if(counter == 5) {
                counter = 0;
                return null;
            }
        }
        float y = obstacleList.get(obstacleIndex).getPositionY() + obstacleList.get(obstacleIndex).getHeight() * 2;
        float x = obstacleList.get(obstacleIndex).getPositionX() + obstacleList.get(obstacleIndex).getWidth() / 2;
        return new Vector2(x,y);

    }

}

