package my.evorunner.game.Gameplay;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static my.evorunner.game.EvoRunner.savedData;

/**
 * Database like class that holds all the possible animations, textures, and save data for
 * characters. ArrayLists are declared for walking animations, attack animations, their standing
 * sprites, owned and not owned sprites for the Characters screen, as well as room for adding more.
 *
 * The constructor initializes all arrays and runs the load methods.
 * load available characters loads all the walking and attack animations.
 * It also adds the characters texture region to the list.
 * The loadCharacters method adds the character save data to the lists as well as adding their sprites
 * for showing owned and not owned.
 *
 * Below are also a list of getters and setters that are used throughout the program.
 * Make sure that if you add a character, you add it to both the lists in load available characters
 * and load characters functions.
 *
 *
 * Version 1.1
 * Last Edited: Matthew Phan
 * Changelog 1.1
 * Added samurai sprites and animations. Removed dragon animations.
 */

public class AvailableCharacters {
    //Texture Region and String array list are linked together.
    //The string for texture region will be in the corresponding index
    private ArrayList<TextureRegion> availableCharactersList;
    private ArrayList<String> availableCharactersStringList;
    private HashMap<String,Boolean> availableCharacterMap;

    private ArrayList<Animation> firstWalkingAnimation;
    private ArrayList<Animation> secondWalkingAnimation;
    private ArrayList<Animation> thirdWalkingAnimation;

    private ArrayList<Animation> firstAttackAnimation;
    private ArrayList<Animation> secondAttackAnimation;
    private ArrayList<Animation> thirdAttackAnimation;

    private ArrayList<TextureRegion> ownedCharacters;
    private ArrayList<TextureRegion> notOwnedCharacters;
    private ArrayList<TextureRegion> selectCharacters;

    private Random random;

    //Constructor. Declares the arrayList and HashMap
    public AvailableCharacters() {
        //Declare a new asset manager for retrieving assets
        AssetManager am = new AssetManager();
        Assets.ourInstance.init(am);

        //Initialize lists and map
        availableCharactersList = new ArrayList<TextureRegion>();
        availableCharactersStringList = new ArrayList<String>();
        availableCharacterMap = new HashMap<String, Boolean>();

        firstWalkingAnimation = new ArrayList<Animation>();
        secondWalkingAnimation = new ArrayList<Animation>();
        thirdWalkingAnimation = new ArrayList<Animation>();

        firstAttackAnimation = new ArrayList<Animation>();
        secondAttackAnimation = new ArrayList<Animation>();
        thirdAttackAnimation = new ArrayList<Animation>();

        ownedCharacters = new ArrayList<TextureRegion>();
        notOwnedCharacters = new ArrayList<TextureRegion>();
        selectCharacters = new ArrayList<TextureRegion>();

        //Loads the assets
        loadAvailableCharacters();
        loadCharacters();

        //Declare a new random
        random = new Random();
    }

    //IMPORTANT: adding a character to the list means you must also add the string
    //to the list in loadCharacters as well as an entry in the hash map
    public void loadAvailableCharacters() {
        //Adds characters to the array list
        availableCharactersList.add(Assets.ourInstance.characterAssets.healerStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.healerEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.healerEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.healerEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.healerSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.mageStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.mageEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.mageEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.mageEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.mageSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.rangerStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.rangerEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.rangerEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.rangerEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.rangerSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.warriorStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.warriorEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.warriorEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.warriorEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.warriorSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.darkknightStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.darkknightEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.darkknightEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.darkknightEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.darkknightSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.monkStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.monkEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.monkEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.monkEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.monkSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.ninjaStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.ninjaEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.ninjaEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.ninjaEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.ninjaSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.paladinStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.paladinEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.paladinEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.paladinEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.paladinSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.samuraiStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.samuraiEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.samuraiEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.samuraiEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.samuraiSelect);

        availableCharactersList.add(Assets.ourInstance.characterAssets.soldierStanding);
        firstWalkingAnimation.add(Assets.ourInstance.characterAssets.soldierEvo1Animation);
        secondWalkingAnimation.add(Assets.ourInstance.characterAssets.soldierEvo2Animation);
        thirdWalkingAnimation.add(Assets.ourInstance.characterAssets.soldierEvo3Animation);
        selectCharacters.add(Assets.ourInstance.characterAssets.soldierSelect);
    }

    //Function that puts characters into the lists
    public void loadCharacters() {
        //Adds string to map and list. Map will hold whether or not the character owns the string
        availableCharacterMap.put("0", savedData.getBoolean("0", false));
        availableCharactersStringList.add("0");
        ownedCharacters.add(Assets.ourInstance.characterAssets.healerOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.healerNotOwned);


        availableCharacterMap.put("1", savedData.getBoolean("1", false));
        availableCharactersStringList.add("1");
        ownedCharacters.add(Assets.ourInstance.characterAssets.mageOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.mageNotOwned);

        availableCharacterMap.put("2", savedData.getBoolean("2", false));
        availableCharactersStringList.add("2");
        ownedCharacters.add(Assets.ourInstance.characterAssets.rangerOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.rangerNotOwned);

        availableCharacterMap.put("3", savedData.getBoolean("3", false));
        availableCharactersStringList.add("3");
        ownedCharacters.add(Assets.ourInstance.characterAssets.warriorOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.warriorNotOwned);

        availableCharacterMap.put("4", savedData.getBoolean("4", false));
        availableCharactersStringList.add("4");
        ownedCharacters.add(Assets.ourInstance.characterAssets.darkknightOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.darkknightNotOwned);

        availableCharacterMap.put("5", savedData.getBoolean("5", false));
        availableCharactersStringList.add("5");
        ownedCharacters.add(Assets.ourInstance.characterAssets.monkOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.monkNotOwned);

        availableCharacterMap.put("6", savedData.getBoolean("6", false));
        availableCharactersStringList.add("6");
        ownedCharacters.add(Assets.ourInstance.characterAssets.ninjaOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.ninjaNotOwned);

        availableCharacterMap.put("7", savedData.getBoolean("7", false));
        availableCharactersStringList.add("7");
        ownedCharacters.add(Assets.ourInstance.characterAssets.paladinOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.paladinNotOwned);

        availableCharacterMap.put("8", savedData.getBoolean("8", false));
        availableCharactersStringList.add("8");
        ownedCharacters.add(Assets.ourInstance.characterAssets.samuraiOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.samuraiNotOwned);

        availableCharacterMap.put("9", savedData.getBoolean("9", false));
        availableCharactersStringList.add("9");
        ownedCharacters.add(Assets.ourInstance.characterAssets.soldierOwned);
        notOwnedCharacters.add(Assets.ourInstance.characterAssets.soldierNotOwned);
    }

    //Returns the texture region given an index
    public TextureRegion getCharacter(int arrayIndex) {
        return availableCharactersList.get(arrayIndex);
    }

    //Returns the character string given an inex
    public String getCharacterName(int arrayIndex) {
        return availableCharactersStringList.get(arrayIndex);
    }


    public Animation getFirstWalk(int index) {
        return firstWalkingAnimation.get(index);
    }

    public Animation getSecondWalk(int index) {
        return secondWalkingAnimation.get(index);
    }

    public Animation getThirdWalk(int index) {
        return thirdWalkingAnimation.get(index);
    }

    public int getRandomCharacter() {
        return random.nextInt(availableCharactersStringList.size());
    }

    public ArrayList<String> getList() {
        return availableCharactersStringList;
    }

    public ArrayList<TextureRegion> getTextureList(int input) {
        if (input == 1) {
            return availableCharactersList;
        } else if (input == 2) {
            return ownedCharacters;
        } else if (input == 3){
            return notOwnedCharacters;
        } else {
            //Keep this here to make it easier to add to the return list
            return selectCharacters;
        }
    }

}

