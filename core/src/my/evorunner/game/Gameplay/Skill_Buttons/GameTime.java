package my.evorunner.game.Gameplay.Skill_Buttons;

/**
 * Created by Matthew on 2/10/2018.
 */

public class GameTime {
    private static float currentTime = 0;

    public static float getCurrentTime() {
        return currentTime;
    }

    public static void updateCurrentTime(float addValue) {
        currentTime += addValue;
    }

}
