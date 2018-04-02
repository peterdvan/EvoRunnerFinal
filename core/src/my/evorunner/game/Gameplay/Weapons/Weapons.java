package my.evorunner.game.Gameplay.Weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import my.evorunner.game.Gameplay.Enemies.Enemy;
import my.evorunner.game.Gameplay.Player;

import java.util.Random;

import my.evorunner.game.Constants;

import static my.evorunner.game.Gameplay.Player.HALF_EVO_RUNNER_WIDTH;

/**
 * Created by Peter on 2/3/2018.
 */

public class Weapons {
    DelayedRemovalArray<Weapon> weaponList;
    Random random;
    public Weapons() {
        random = new Random();
        weaponList = new DelayedRemovalArray<Weapon>();

    }

    public void update(float delta,DelayedRemovalArray<Enemy> enemyList) {
        for(Weapon weapon : weaponList) {
            weapon.update(delta,enemyList);
        }
        for(int i = 0; i < weaponList.size; i++) {
            //if user can see star or star is used delete it
            if(weaponList.get(i).getPosition().x > Constants.SCREEN_WIDTH * 1.2f || weaponList.get(i).isFinished()) {
                weaponList.removeIndex(i);
            }
        }
        // enemyList.begin();
        // enemyList.end();
    }


    public void render(SpriteBatch batch) {
        for(Weapon weapon : weaponList) {
            weapon.render(batch);
        }
    }

    public void addNinjaStar(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new NinjaStar(startPosition));
    }

    public void addFireBall(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new MageAttack(startPosition));
    }
    public void addArrow(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new RangerAttack(startPosition));
    }
    public void addPriestAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new PriestAttack(startPosition));
    }
    public void addDarkKnightAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new DarkKnightAttack(startPosition));
    }
    public void addSamuraiAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new SamuraiAttack(startPosition));
    }
    public void addSoldierAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new SoldierAttack(startPosition));
    }
    public void addBoomerangAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new MonkAttack(startPosition));
    }
    public void addWarriorAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new WarriorAttack(startPosition));
    }
    public void addPaladinAttack(Player player) {
        Vector2 startPosition = new Vector2(player.getPositionX() + HALF_EVO_RUNNER_WIDTH * 2,player.getPositionY());
        weaponList.add(new PaladinAttack(startPosition));
    }
    public void addWeapon(Player player) {
        switch (player.getCharacterID()) {
            case 1:
                addFireBall(player);
                break;
            case 2:
                addArrow(player);
                break;
            case 0:
                addPriestAttack(player);
                break;
            case 4:
                addDarkKnightAttack(player);
                break;
            case 8:
                addSamuraiAttack(player);
                break;
            case 9:
                addSoldierAttack(player);
                break;
            case 5:
                addBoomerangAttack(player);
                break;
            case 3:
                addWarriorAttack(player);
                break;
            case 6:
                addNinjaStar(player);
                break;
            case 7:
                addPaladinAttack(player);
                break;
            default:
             //   addNinjaStar(player);
               // addFireBall(player);
               // addArrow(player);
               // addDarkKnightAttack(player);
              //  addSamuraiAttack(player);
                //addWarriorAttack(player);
               // addSoldierAttack(player);
               // addHealerAttack(player);
                addPriestAttack(player);
        }
    }
}
