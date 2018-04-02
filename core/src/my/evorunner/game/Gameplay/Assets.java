package my.evorunner.game.Gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/**
 * Assets java file for the game. Holds the assets for all the characters, monsters,
 * and obstacles
 *
 * Version 1.1
 * Last Edited: Matthew Phan
 * Changelog 1.1
 * Added animations for the rest of the sprites
 * Added a dragon asset with animations using the blue egg
 * Put variable names together to make the code look cleaner
 */

//Assets class implements Disposable and AssetErrorListener
public class Assets implements Disposable,AssetErrorListener {
    //static final variables
    public static final String TAG = Assets.class.getName();
    public static final Assets ourInstance = new Assets();
    public static final String TEXTURE_ATLAS = "Assets/EvoRunnerAssets.atlas";
    public static final String TEXTURE_DRAGON = "Assets/Dragon.atlas";
    public static final String TEXTURE_CHARACTER = "Assets/Characters.atlas";
    public static final String EVOLUTION_ATLAS = "Assets/Evolution.atlas";
    public static final float COIN_LOOP_DURATION = 0.1f;

    //Declare assets for the different objects
    private AssetManager assetManager;
    public CoinAssets coinAssets;
    public EvoRunnerAssets evoRunnerAssets;
    public NinjaStarAssets ninjaStarAssets;
    public LavaSlimeAssets lavaSlimeAssets;
    public ShurikenHitAnimationAssets shurikenHitAnimationAssets;
    public PlatformAssets platformAssets;
    public BatAssets batAssets;
    public PlayerDeathAnimationAssets playerDeathAnimationAssets;
    public LootBoxAssets lootBoxAssets;
    public DragonAssets dragonAssets;
    public CharacterAssets characterAssets;
    public ArrowAssets arrowAssets;
    public FireballAssets fireballAssets;
    public IceballAssets iceballAssets;
    public BoomerangAssets boomerangAssets;
    public BulletPulseAssets bulletPulseAssets;
    public PlusAttackAssets plusAttackAssets;
    public RedSwordSlashAssets redSwordSlashAssets;
    public PurpleSwordSlashAssets purpleSwordSlashAssets;
    public SamuraiSwordSlashAssets samuraiSwordSlashAssets;
    public PaladinAssets paladinAssets;
    public FlowerAssets flowerAssets;
    public MushroomAssets mushroomAssets;
    public FlowerDeathAssets flowerDeathAssets;
    public MushroomDeathAssets mushroomDeathAssets;
    public BatDeathAssets batDeathAssets;
    public NPCAssets npcAssets;
    public EvolutionAssets evolutionAssets;
    //Returns the Assets ourInstance
    public static Assets getInstance() {
        return ourInstance;
    }

    //Private default constructor for Assets
    private Assets() {}

    //Initialize method that takes in an assetmanager
    public void init(AssetManager assetManager) {
        //Sets the current asset manager as the one brought in via parameter
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);        //Sets error listener on the current instance
        assetManager.load(TEXTURE_ATLAS, TextureAtlas.class);   //loads the texture atlas and the class
        assetManager.load(TEXTURE_DRAGON, TextureAtlas.class);
        assetManager.load(TEXTURE_CHARACTER, TextureAtlas.class);
        assetManager.load(EVOLUTION_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();       //Makes sure the asset manager is done loading
        //Declares textureAtlas atlas by pulling from assetManager
        TextureAtlas atlas = assetManager.get(TEXTURE_ATLAS);
        TextureAtlas dragon = assetManager.get(TEXTURE_DRAGON);
        TextureAtlas character = assetManager.get(TEXTURE_CHARACTER);
        TextureAtlas evolution = assetManager.get(EVOLUTION_ATLAS);

        //Declare assets for different classes
        paladinAssets = new PaladinAssets(atlas);
        samuraiSwordSlashAssets = new SamuraiSwordSlashAssets(atlas);
        iceballAssets = new IceballAssets(atlas);
        boomerangAssets = new BoomerangAssets(atlas);
        bulletPulseAssets = new BulletPulseAssets(atlas);
        plusAttackAssets = new PlusAttackAssets(atlas);
        redSwordSlashAssets = new RedSwordSlashAssets(atlas);
        purpleSwordSlashAssets = new PurpleSwordSlashAssets(atlas);
        coinAssets = new CoinAssets(atlas);
        evoRunnerAssets = new EvoRunnerAssets(atlas);
        ninjaStarAssets = new NinjaStarAssets(atlas);
        lavaSlimeAssets = new LavaSlimeAssets(atlas);
        shurikenHitAnimationAssets = new ShurikenHitAnimationAssets(atlas);
        playerDeathAnimationAssets = new PlayerDeathAnimationAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        batAssets = new BatAssets(atlas);
        lootBoxAssets = new LootBoxAssets(atlas);
        dragonAssets = new DragonAssets(dragon);
        characterAssets = new CharacterAssets(character);
        arrowAssets = new ArrowAssets(atlas);
        fireballAssets = new FireballAssets(atlas);
        evolutionAssets = new EvolutionAssets(evolution);
        npcAssets = new NPCAssets(atlas);
        flowerDeathAssets = new FlowerDeathAssets(atlas);
        mushroomDeathAssets = new MushroomDeathAssets(atlas);
        batDeathAssets = new BatDeathAssets(atlas);
        flowerAssets = new FlowerAssets(atlas);
        mushroomAssets = new MushroomAssets(atlas);
        samuraiSwordSlashAssets = new SamuraiSwordSlashAssets(atlas);
        iceballAssets = new IceballAssets(atlas);
        boomerangAssets = new BoomerangAssets(atlas);
        bulletPulseAssets = new BulletPulseAssets(atlas);
        plusAttackAssets = new PlusAttackAssets(atlas);
        redSwordSlashAssets = new RedSwordSlashAssets(atlas);
        purpleSwordSlashAssets = new PurpleSwordSlashAssets(atlas);
        coinAssets = new CoinAssets(atlas);
        evoRunnerAssets = new EvoRunnerAssets(atlas);
        ninjaStarAssets = new NinjaStarAssets(atlas);
        lavaSlimeAssets = new LavaSlimeAssets(atlas);
        shurikenHitAnimationAssets = new ShurikenHitAnimationAssets(atlas);
        playerDeathAnimationAssets = new PlayerDeathAnimationAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        batAssets = new BatAssets(atlas);
        lootBoxAssets = new LootBoxAssets(atlas);
        dragonAssets = new DragonAssets(dragon);
        characterAssets = new CharacterAssets(character);
        arrowAssets = new ArrowAssets(atlas);
        fireballAssets = new FireballAssets(atlas);
    }

    //Error function for when an asset cannot be loaded
    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    //Dispose method for the asset class that runs dispose on assetManager
    @Override
    public void dispose() {
        assetManager.dispose();
    }

    //EvoRunnerAssets class that holds the assets for all the things in EvoRunner
    public class EvoRunnerAssets {
        public static final String EVO_RUNNER = "EvoRunner";

        //Declare final TextureAtlas.AtlasRegion variables
        public final TextureAtlas.AtlasRegion evoRunner;

        //Base evo runner textures
        public final TextureAtlas.AtlasRegion BASE_EVO_RUNNER_STANDING;
        public final TextureAtlas.AtlasRegion BASE_EVO_RUNNER_RFF;
        public final TextureAtlas.AtlasRegion BASE_EVO_RUNNER_LFF;

        //Base child runner textures
        public final TextureAtlas.AtlasRegion CHILD_EVO_RUNNER_STANDING;
        public final TextureAtlas.AtlasRegion CHILD_EVO_RUNNER_RFF;
        public final TextureAtlas.AtlasRegion CHILD_EVO_RUNNER_LFF;

        //Base Samurai runner textures
        public final TextureAtlas.AtlasRegion SAMURAI_EVO_RUNNER_STANDING;
        public final TextureAtlas.AtlasRegion SAMURAI_EVO_RUNNER_RFF;
        public final TextureAtlas.AtlasRegion SAMURAI_EVO_RUNNER_LFF;

        //Base female textures
        public final TextureAtlas.AtlasRegion BASE_FEMALE_STANDING;
        public final TextureAtlas.AtlasRegion BASE_FEMALE_LFF;
        public final TextureAtlas.AtlasRegion BASE_FEMALE_RFF;

        //Base female2 textures
        public final TextureAtlas.AtlasRegion BASE_FEMALE2_STANDING;
        public final TextureAtlas.AtlasRegion BASE_FEMALE2_LFF;
        public final TextureAtlas.AtlasRegion BASE_FEMALE2_RFF;

        //Base mage female textures
        public final TextureAtlas.AtlasRegion MAGE_FEMALE_STANDING;
        public final TextureAtlas.AtlasRegion MAGE_FEMALE_LFF;
        public final TextureAtlas.AtlasRegion MAGE_FEMALE_RFF;

        //Base ranger female textures
        public final TextureAtlas.AtlasRegion RANGER_FEMALE_STANDING;
        public final TextureAtlas.AtlasRegion RANGER_FEMALE_LFF;
        public final TextureAtlas.AtlasRegion RANGER_FEMALE_RFF;

        //Base male textures
        public final TextureAtlas.AtlasRegion BASE_MALE_LFF;
        public final TextureAtlas.AtlasRegion BASE_MALE_RFF;
        public final TextureAtlas.AtlasRegion BASE_MALE_STANDING;

        //Base mage male textures
        public final TextureAtlas.AtlasRegion MAGE_MALE_STANDING;
        public final TextureAtlas.AtlasRegion MAGE_MALE_LFF;
        public final TextureAtlas.AtlasRegion MAGE_MALE_RFF;

        //Base healer female textures
        public final TextureAtlas.AtlasRegion HEALER_FEMALE_STANDING;
        public final TextureAtlas.AtlasRegion HEALER_FEMALE_LFF;
        public final TextureAtlas.AtlasRegion HEALER_FEMALE_RFF;

        //Declare animation variables for character movement
        public final Animation walkAnimation, childWalkAnimation, samuraiWalkAnimation,
                firstEvolutionkAnimation, baseMaleWalkingAnimation, healerFemaleWalkingAnimation,
                mageMaleWalkingAnimation, baseFemaleWalkingAnimation, baseFemale2WalkingAnimation, mageFemaleWalkingAnimation
                ,rangerFemaleWalkingAnimation;

        //Set times for walking and evolution
        public static final float WALKING_ROTATION = 0.2f;
        public static final float EVOLUTION_TIME= .05f;

        //EvoRunnerAssets constructor that takes a texture atlas
        public EvoRunnerAssets(TextureAtlas atlas) {
            evoRunner = atlas.findRegion(EVO_RUNNER);   //Finds the region for evo runner




            BASE_FEMALE_STANDING = atlas.findRegion("Base_Female_Standing");
            BASE_FEMALE_LFF = atlas.findRegion("Base_Female_LFF");
            BASE_FEMALE_RFF = atlas.findRegion("Base_Female_RFF");
            Array<TextureAtlas.AtlasRegion> baseFemaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            baseFemaleWalkingFrames.add(BASE_FEMALE_LFF);
            baseFemaleWalkingFrames.add(BASE_FEMALE_STANDING);
            baseFemaleWalkingFrames.add(BASE_FEMALE_RFF);
            baseFemaleWalkingAnimation = new Animation(WALKING_ROTATION, baseFemaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            BASE_FEMALE2_STANDING = atlas.findRegion("Base_Female2_Standing");
            BASE_FEMALE2_LFF = atlas.findRegion("Base_Female2_LFF");
            BASE_FEMALE2_RFF = atlas.findRegion("Base_Female2_RFF");
            Array<TextureAtlas.AtlasRegion> baseFemale2WalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            baseFemale2WalkingFrames.add(BASE_FEMALE2_LFF);
            baseFemale2WalkingFrames.add(BASE_FEMALE2_STANDING);
            baseFemale2WalkingFrames.add(BASE_FEMALE2_RFF);
            baseFemale2WalkingAnimation = new Animation(WALKING_ROTATION, baseFemale2WalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            MAGE_FEMALE_STANDING = atlas.findRegion("Mage_Female_Standing");
            MAGE_FEMALE_LFF = atlas.findRegion("Mage_Female_LFF");
            MAGE_FEMALE_RFF = atlas.findRegion("Mage_Female_RFF");
            Array<TextureAtlas.AtlasRegion> mageFemaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            mageFemaleWalkingFrames.add(MAGE_FEMALE_LFF);
            mageFemaleWalkingFrames.add(MAGE_FEMALE_STANDING);
            mageFemaleWalkingFrames.add(MAGE_FEMALE_RFF);
            mageFemaleWalkingAnimation = new Animation(WALKING_ROTATION, mageFemaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            RANGER_FEMALE_STANDING = atlas.findRegion("Ranger_Female_Standing");
            RANGER_FEMALE_LFF = atlas.findRegion("Ranger_Female_LFF");
            RANGER_FEMALE_RFF = atlas.findRegion("Ranger_Female_RFF");
            Array<TextureAtlas.AtlasRegion> rangerFemaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            rangerFemaleWalkingFrames.add(RANGER_FEMALE_LFF);
            rangerFemaleWalkingFrames.add(RANGER_FEMALE_STANDING);
            rangerFemaleWalkingFrames.add(RANGER_FEMALE_RFF);
            rangerFemaleWalkingAnimation = new Animation(WALKING_ROTATION, rangerFemaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            MAGE_MALE_STANDING = atlas.findRegion("Mage_Male_Standing");
            MAGE_MALE_LFF = atlas.findRegion("Mage_Male_LFF");
            MAGE_MALE_RFF = atlas.findRegion("Mage_Male_RFF");
            Array<TextureAtlas.AtlasRegion> mageMaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            mageMaleWalkingFrames.add(MAGE_MALE_LFF);
            mageMaleWalkingFrames.add(MAGE_MALE_STANDING);
            mageMaleWalkingFrames.add(MAGE_MALE_RFF);
            mageMaleWalkingAnimation = new Animation(WALKING_ROTATION, mageMaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            HEALER_FEMALE_STANDING = atlas.findRegion("Healer_Female_Standing");
            HEALER_FEMALE_LFF = atlas.findRegion("Healer_Female_LFF");
            HEALER_FEMALE_RFF = atlas.findRegion("Healer_Female_RFF");
            Array<TextureAtlas.AtlasRegion> healerFemaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            healerFemaleWalkingFrames.add(HEALER_FEMALE_LFF);
            healerFemaleWalkingFrames.add(HEALER_FEMALE_STANDING);
            healerFemaleWalkingFrames.add(HEALER_FEMALE_RFF);
            healerFemaleWalkingAnimation = new Animation(WALKING_ROTATION, healerFemaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            BASE_MALE_LFF = atlas.findRegion("Base_Male_LFF");
            BASE_MALE_RFF = atlas.findRegion("Base_Male_RFF");
            BASE_MALE_STANDING = atlas.findRegion("Base_Male_Standing");
            Array<TextureAtlas.AtlasRegion> baseMaleWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            baseMaleWalkingFrames.add(BASE_MALE_LFF);
            baseMaleWalkingFrames.add(BASE_MALE_STANDING);
            baseMaleWalkingFrames.add(BASE_MALE_RFF);
            baseMaleWalkingAnimation = new Animation(WALKING_ROTATION, baseMaleWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            BASE_EVO_RUNNER_STANDING = atlas.findRegion("BaseEvo_Standing");
            BASE_EVO_RUNNER_RFF = atlas.findRegion("BaseEvo_RFF");
            BASE_EVO_RUNNER_LFF = atlas.findRegion("BaseEvo_LFF");
            Array<TextureAtlas.AtlasRegion> walkingFrames = new Array<TextureAtlas.AtlasRegion>();
            walkingFrames.add(BASE_EVO_RUNNER_LFF);
            walkingFrames.add(BASE_EVO_RUNNER_STANDING);
            walkingFrames.add(BASE_EVO_RUNNER_RFF);
            walkAnimation = new Animation(WALKING_ROTATION, walkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            CHILD_EVO_RUNNER_STANDING = atlas.findRegion("ChildEvo_Standing");
            CHILD_EVO_RUNNER_RFF = atlas.findRegion("ChildEvo_RFF");
            CHILD_EVO_RUNNER_LFF = atlas.findRegion("EvoChild_LFF");
            Array<TextureAtlas.AtlasRegion> childWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            childWalkingFrames.add(CHILD_EVO_RUNNER_LFF);
            childWalkingFrames.add(CHILD_EVO_RUNNER_STANDING);
            childWalkingFrames.add(CHILD_EVO_RUNNER_RFF);
            childWalkAnimation = new Animation(WALKING_ROTATION, childWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            SAMURAI_EVO_RUNNER_STANDING = atlas.findRegion("SamuraiEvo_Standing");
            SAMURAI_EVO_RUNNER_RFF = atlas.findRegion("SamuraiEvo_RFF");
            SAMURAI_EVO_RUNNER_LFF = atlas.findRegion("SamuraiEvo_LFF");
            Array<TextureAtlas.AtlasRegion> samuraiWalkingFrames = new Array<TextureAtlas.AtlasRegion>();
            samuraiWalkingFrames.add(SAMURAI_EVO_RUNNER_LFF);
            samuraiWalkingFrames.add(SAMURAI_EVO_RUNNER_STANDING);
            samuraiWalkingFrames.add(SAMURAI_EVO_RUNNER_RFF);
            samuraiWalkAnimation = new Animation(WALKING_ROTATION, samuraiWalkingFrames, Animation.PlayMode.LOOP_PINGPONG);

            Array<TextureAtlas.AtlasRegion> firstEvolutionFrames = new Array<TextureAtlas.AtlasRegion>();
            String[] evoball = {"EvoBall5", "EvoBall6",
                    "EvoBall7", "EvoBall8", "EvoBall8_One", "EvoBall9", "EvoBall8_One", "EvoBall9_One",
                    "EvoBall10", "EvoBall11"};
            for (int i = 0; i < 10; i++) {
                firstEvolutionFrames.add(atlas.findRegion(evoball[i]));
            }
            firstEvolutionkAnimation = new Animation(EVOLUTION_TIME, firstEvolutionFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class NPCAssets {
        public final TextureAtlas.AtlasRegion INTRODUCTION_NPC;
        public NPCAssets(TextureAtlas atlas){
            INTRODUCTION_NPC = atlas.findRegion("Intro_NPC");
        }

    }
    public class EvolutionAssets {
        public final TextureAtlas.AtlasRegion EVO2,EVO3,EVO4,EVO5,EVO6;
        public final Animation evolutionAnimation;
        public static final float EVOLUTION_TIME = 0.10f;
        public EvolutionAssets(TextureAtlas evolution) {
            EVO2 = evolution.findRegion("Evo2");
            EVO3 = evolution.findRegion("Evo3");
            EVO4 = evolution.findRegion("Evo4");
            EVO5 = evolution.findRegion("Evo5");
            EVO6 = evolution.findRegion("Evo6");
            Array<TextureAtlas.AtlasRegion> evoFrames = new Array<TextureAtlas.AtlasRegion>();
            evoFrames.add(EVO2);
            evoFrames.add(EVO2);
            evoFrames.add(EVO2);
            evoFrames.add(EVO3);
            evoFrames.add(EVO3);
            evoFrames.add(EVO3);
            evoFrames.add(EVO4);
            evoFrames.add(EVO4);
            evoFrames.add(EVO4);
            evoFrames.add(EVO5);
            evoFrames.add(EVO5);
            evoFrames.add(EVO5);
            evoFrames.add(EVO6);
            evoFrames.add(EVO6);
            evoFrames.add(EVO6);
            evoFrames.add(EVO5);
            evoFrames.add(EVO5);
            evoFrames.add(EVO5);
            evoFrames.add(EVO4);
            evoFrames.add(EVO4);
            evoFrames.add(EVO4);
            evoFrames.add(EVO3);
            evoFrames.add(EVO3);
            evoFrames.add(EVO3);
            evoFrames.add(EVO2);
            evoFrames.add(EVO2);
            evoFrames.add(EVO2);
            evolutionAnimation = new Animation(EVOLUTION_TIME, evoFrames, Animation.PlayMode.LOOP_PINGPONG);
        }
    }
    public class CharacterAssets {
        //FOR ANIMATIONS
        public final Animation healerEvo1Animation;
        public final Animation healerEvo2Animation;
        public final Animation healerEvo3Animation;

        public final Animation rangerEvo1Animation;
        public final Animation rangerEvo2Animation;
        public final Animation rangerEvo3Animation;

        public final Animation mageEvo1Animation;
        public final Animation mageEvo2Animation;
        public final Animation mageEvo3Animation;

        public final Animation warriorEvo1Animation;
        public final Animation warriorEvo2Animation;
        public final Animation warriorEvo3Animation;

        public final Animation darkknightEvo1Animation;
        public final Animation darkknightEvo2Animation;
        public final Animation darkknightEvo3Animation;

        public final Animation monkEvo1Animation;
        public final Animation monkEvo2Animation;
        public final Animation monkEvo3Animation;

        public final Animation ninjaEvo1Animation;
        public final Animation ninjaEvo2Animation;
        public final Animation ninjaEvo3Animation;

        public final Animation paladinEvo1Animation;
        public final Animation paladinEvo2Animation;
        public final Animation paladinEvo3Animation;

        public final Animation samuraiEvo1Animation;
        public final Animation samuraiEvo2Animation;
        public final Animation samuraiEvo3Animation;

        public final Animation soldierEvo1Animation;
        public final Animation soldierEvo2Animation;
        public final Animation soldierEvo3Animation;

        //FOR TEXTURES
        public final TextureAtlas.AtlasRegion healerStanding;
        public final TextureAtlas.AtlasRegion rangerStanding;
        public final TextureAtlas.AtlasRegion mageStanding;
        public final TextureAtlas.AtlasRegion warriorStanding;
        public final TextureAtlas.AtlasRegion darkknightStanding;
        public final TextureAtlas.AtlasRegion monkStanding;
        public final TextureAtlas.AtlasRegion ninjaStanding;
        public final TextureAtlas.AtlasRegion paladinStanding;
        public final TextureAtlas.AtlasRegion samuraiStanding;
        public final TextureAtlas.AtlasRegion soldierStanding;

        public final TextureAtlas.AtlasRegion healerOwned;
        public final TextureAtlas.AtlasRegion healerNotOwned;
        public final TextureAtlas.AtlasRegion rangerOwned;
        public final TextureAtlas.AtlasRegion rangerNotOwned;
        public final TextureAtlas.AtlasRegion mageOwned;
        public final TextureAtlas.AtlasRegion mageNotOwned;
        public final TextureAtlas.AtlasRegion warriorOwned;
        public final TextureAtlas.AtlasRegion warriorNotOwned;
        public final TextureAtlas.AtlasRegion darkknightOwned;
        public final TextureAtlas.AtlasRegion darkknightNotOwned;
        public final TextureAtlas.AtlasRegion monkOwned;
        public final TextureAtlas.AtlasRegion monkNotOwned;
        public final TextureAtlas.AtlasRegion ninjaOwned;
        public final TextureAtlas.AtlasRegion ninjaNotOwned;
        public final TextureAtlas.AtlasRegion paladinOwned;
        public final TextureAtlas.AtlasRegion paladinNotOwned;
        public final TextureAtlas.AtlasRegion samuraiOwned;
        public final TextureAtlas.AtlasRegion samuraiNotOwned;
        public final TextureAtlas.AtlasRegion soldierOwned;
        public final TextureAtlas.AtlasRegion soldierNotOwned;

        public final TextureAtlas.AtlasRegion healerSelect;
        public final TextureAtlas.AtlasRegion rangerSelect;
        public final TextureAtlas.AtlasRegion mageSelect;
        public final TextureAtlas.AtlasRegion warriorSelect;
        public final TextureAtlas.AtlasRegion darkknightSelect;
        public final TextureAtlas.AtlasRegion monkSelect;
        public final TextureAtlas.AtlasRegion ninjaSelect;
        public final TextureAtlas.AtlasRegion paladinSelect;
        public final TextureAtlas.AtlasRegion samuraiSelect;
        public final TextureAtlas.AtlasRegion soldierSelect;

        public static final float WALKING_ROTATION = 0.2f;

        public CharacterAssets(TextureAtlas character) {
            healerStanding = character.findRegion("HealerEvo3");
            healerOwned = character.findRegion("HealerEvo3O");
            healerNotOwned = character.findRegion("HealerEvo3NO");
            healerSelect = character.findRegion("HealerEvo3Owned");
            String[] healer1 = {"HealerEvo1LFF", "HealerEvo1", "HealerEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> healer1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                healer1Frames.add(character.findRegion(healer1[i]));
            }
            healerEvo1Animation = new Animation(WALKING_ROTATION, healer1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] healer2 = {"HealerEvo2LFF", "HealerEvo2", "HealerEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> healer2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                healer2Frames.add(character.findRegion(healer2[i]));
            }
            healerEvo2Animation = new Animation(WALKING_ROTATION, healer2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] healer3 = {"HealerEvo3LFF", "HealerEvo3", "HealerEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> healer3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                healer3Frames.add(character.findRegion(healer3[i]));
            }
            healerEvo3Animation = new Animation(WALKING_ROTATION, healer3Frames, Animation.PlayMode.LOOP_PINGPONG);

            rangerStanding = character.findRegion("RangerEvo3");
            rangerOwned = character.findRegion("RangerEvo3O");
            rangerNotOwned = character.findRegion("RangerEvo3NO");
            rangerSelect = character.findRegion("RangerEvo3Owned");
            String[] ranger1 = {"RangerEvo1LFF", "RangerEvo1", "RangerEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> ranger1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ranger1Frames.add(character.findRegion(ranger1[i]));
            }
            rangerEvo1Animation = new Animation(WALKING_ROTATION, ranger1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] ranger2 = {"RangerEvo2LFF", "RangerEvo2", "RangerEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> ranger2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ranger2Frames.add(character.findRegion(ranger2[i]));
            }
            rangerEvo2Animation = new Animation(WALKING_ROTATION, ranger2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] ranger3 = {"RangerEvo3LFF", "RangerEvo3", "RangerEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> ranger3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ranger3Frames.add(character.findRegion(ranger3[i]));
            }
            rangerEvo3Animation = new Animation(WALKING_ROTATION, ranger3Frames, Animation.PlayMode.LOOP_PINGPONG);

            mageStanding = character.findRegion("MageEvo3");
            mageOwned = character.findRegion("MageEvo3O");
            mageNotOwned = character.findRegion("MageEvo3NO");
            mageSelect = character.findRegion("MageEvo3Owned");
            String[] mage1 = {"MageEvo1LFF", "MageEvo1", "MageEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> mage1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                mage1Frames.add(character.findRegion(mage1[i]));
            }
            mageEvo1Animation = new Animation(WALKING_ROTATION, mage1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] mage2 = {"MageEvo2LFF", "MageEvo2", "MageEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> mage2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                mage2Frames.add(character.findRegion(mage2[i]));
            }
            mageEvo2Animation = new Animation(WALKING_ROTATION, mage2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] mage3 = {"MageEvo3LFF", "MageEvo3", "MageEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> mage3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                mage3Frames.add(character.findRegion(mage3[i]));
            }
            mageEvo3Animation = new Animation(WALKING_ROTATION, mage3Frames, Animation.PlayMode.LOOP_PINGPONG);

            warriorStanding = character.findRegion("WarriorEvo3");
            warriorOwned = character.findRegion("WarriorEvo3O");
            warriorNotOwned = character.findRegion("WarriorEvo3NO");
            warriorSelect = character.findRegion("WarriorEvo3Owned");
            String[] warrior1 = {"WarriorEvo1LFF", "WarriorEvo1", "WarriorEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> warrior1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                warrior1Frames.add(character.findRegion(warrior1[i]));
            }
            warriorEvo1Animation = new Animation(WALKING_ROTATION, warrior1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] warrior2 = {"WarriorEvo2LFF", "WarriorEvo2", "WarriorEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> warrior2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                warrior2Frames.add(character.findRegion(warrior2[i]));
            }
            warriorEvo2Animation = new Animation(WALKING_ROTATION, warrior2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] warrior3 = {"WarriorEvo3LFF", "WarriorEvo3", "WarriorEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> warrior3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                warrior3Frames.add(character.findRegion(warrior3[i]));
            }
            warriorEvo3Animation = new Animation(WALKING_ROTATION, warrior3Frames, Animation.PlayMode.LOOP_PINGPONG);

            darkknightStanding = character.findRegion("DarkKnightEvo3");
            darkknightOwned = character.findRegion("DarkKnightEvo3O");
            darkknightNotOwned = character.findRegion("DarkKnightEvo3NO");
            darkknightSelect = character.findRegion("DarkKnightEvo3Owned");
            String[] darkknight1 = {"DarkKnightEvo1LFF", "DarkKnightEvo1", "DarkKnightEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> darkknight1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                darkknight1Frames.add(character.findRegion(darkknight1[i]));
            }
            darkknightEvo1Animation = new Animation(WALKING_ROTATION, darkknight1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] darkknight2 = {"DarkKnightEvo2LFF", "DarkKnightEvo2", "DarkKnightEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> darkknight2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                darkknight2Frames.add(character.findRegion(darkknight2[i]));
            }
            darkknightEvo2Animation = new Animation(WALKING_ROTATION, darkknight2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] darkknight3 = {"DarkKnightEvo3LFF", "DarkKnightEvo3", "DarkKnightEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> darkknight3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                darkknight3Frames.add(character.findRegion(darkknight3[i]));
            }
            darkknightEvo3Animation = new Animation(WALKING_ROTATION, darkknight3Frames, Animation.PlayMode.LOOP_PINGPONG);

            monkStanding = character.findRegion("MonkEvo3");
            monkOwned = character.findRegion("MonkEvo3O");
            monkNotOwned = character.findRegion("MonkEvo3NO");
            monkSelect = character.findRegion("MonkEvo3Owned");
            String[] monk1 = {"MonkEvo1LFF", "MonkEvo1", "MonkEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> monk1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                monk1Frames.add(character.findRegion(monk1[i]));
            }
            monkEvo1Animation = new Animation(WALKING_ROTATION, monk1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] monk2 = {"MonkEvo2LFF", "MonkEvo2", "MonkEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> monk2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                monk2Frames.add(character.findRegion(monk2[i]));
            }
            monkEvo2Animation = new Animation(WALKING_ROTATION, monk2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] monk3 = {"MonkEvo3LFF", "MonkEvo3", "MonkEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> monk3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                monk3Frames.add(character.findRegion(monk3[i]));
            }
            monkEvo3Animation = new Animation(WALKING_ROTATION, monk3Frames, Animation.PlayMode.LOOP_PINGPONG);

            ninjaStanding = character.findRegion("NinjaEvo3");
            ninjaOwned = character.findRegion("NinjaEvo3O");
            ninjaNotOwned = character.findRegion("NinjaEvo3NO");
            ninjaSelect = character.findRegion("NinjaEvo3Owned");
            String[] ninja1 = {"NinjaEvo1LFF", "NinjaEvo1", "NinjaEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> ninja1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ninja1Frames.add(character.findRegion(ninja1[i]));
            }
            ninjaEvo1Animation = new Animation(WALKING_ROTATION, ninja1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] ninja2 = {"NinjaEvo2LFF", "NinjaEvo2", "NinjaEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> ninja2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ninja2Frames.add(character.findRegion(ninja2[i]));
            }
            ninjaEvo2Animation = new Animation(WALKING_ROTATION, ninja2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] ninja3 = {"NinjaEvo3LFF", "NinjaEvo3", "NinjaEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> ninja3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                ninja3Frames.add(character.findRegion(ninja3[i]));
            }
            ninjaEvo3Animation = new Animation(WALKING_ROTATION, ninja3Frames, Animation.PlayMode.LOOP_PINGPONG);

            paladinStanding = character.findRegion("PaladinEvo3");
            paladinOwned = character.findRegion("PaladinEvo3O");
            paladinNotOwned = character.findRegion("PaladinEvo3NO");
            paladinSelect = character.findRegion("PaladinEvo3Owned");
            String[] paladin1 = {"PaladinEvo1LFF", "PaladinEvo1", "PaladinEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> paladin1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                paladin1Frames.add(character.findRegion(paladin1[i]));
            }
            paladinEvo1Animation = new Animation(WALKING_ROTATION, paladin1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] paladin2 = {"PaladinEvo2LFF", "PaladinEvo2", "PaladinEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> paladin2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                paladin2Frames.add(character.findRegion(paladin2[i]));
            }
            paladinEvo2Animation = new Animation(WALKING_ROTATION, paladin2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] paladin3 = {"PaladinEvo3LFF", "PaladinEvo3", "PaladinEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> paladin3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                paladin3Frames.add(character.findRegion(paladin3[i]));
            }
            paladinEvo3Animation = new Animation(WALKING_ROTATION, paladin3Frames, Animation.PlayMode.LOOP_PINGPONG);

            samuraiStanding = character.findRegion("SamuraiEvo3");
            samuraiOwned = character.findRegion("SamuraiEvo3O");
            samuraiNotOwned = character.findRegion("SamuraiEvo3NO");
            samuraiSelect = character.findRegion("SamuraiEvo3Owned");
            String[] samurai1 = {"SamuraiEvo1LFF", "SamuraiEvo1", "SamuraiEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> samurai1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                samurai1Frames.add(character.findRegion(samurai1[i]));
            }
            samuraiEvo1Animation = new Animation(WALKING_ROTATION, samurai1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] samurai2 = {"SamuraiEvo2LFF", "SamuraiEvo2", "SamuraiEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> samurai2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                samurai2Frames.add(character.findRegion(samurai2[i]));
            }
            samuraiEvo2Animation = new Animation(WALKING_ROTATION, samurai2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] samurai3 = {"SamuraiEvo3LFF", "SamuraiEvo3", "SamuraiEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> samurai3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                samurai3Frames.add(character.findRegion(samurai3[i]));
            }
            samuraiEvo3Animation = new Animation(WALKING_ROTATION, samurai3Frames, Animation.PlayMode.LOOP_PINGPONG);

            soldierStanding = character.findRegion("SoldierEvo3");
            soldierOwned = character.findRegion("SoldierEvo3O");
            soldierNotOwned = character.findRegion("SoldierEvo3NO");
            soldierSelect = character.findRegion("SoldierEvo3Owned");
            String[] soldier1 = {"SoldierEvo1LFF", "SoldierEvo1", "SoldierEvo1RFF"};
            Array<TextureAtlas.AtlasRegion> soldier1Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                soldier1Frames.add(character.findRegion(soldier1[i]));
            }
            soldierEvo1Animation = new Animation(WALKING_ROTATION, soldier1Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] soldier2 = {"SoldierEvo2LFF", "SoldierEvo2", "SoldierEvo2RFF"};
            Array<TextureAtlas.AtlasRegion> soldier2Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                soldier2Frames.add(character.findRegion(soldier2[i]));
            }
            soldierEvo2Animation = new Animation(WALKING_ROTATION, soldier2Frames, Animation.PlayMode.LOOP_PINGPONG);

            String[] soldier3 = {"SoldierEvo3LFF", "SoldierEvo3", "SoldierEvo3RFF"};
            Array<TextureAtlas.AtlasRegion> soldier3Frames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                soldier3Frames.add(character.findRegion(soldier3[i]));
            }
            soldierEvo3Animation = new Animation(WALKING_ROTATION, soldier3Frames, Animation.PlayMode.LOOP_PINGPONG);
        }
    }

    public class DragonAssets {

        public final Animation babyDragonAnimation;
        public final Animation babyDragonAttackAnimation;
        public final TextureAtlas.AtlasRegion babyDragonStanding;

        public final Animation childDragonAnimation;
        public final Animation childDragonAttackAnimation;
        public final TextureAtlas.AtlasRegion childDragonStanding;

        public static final float WALKING_ROTATION = 0.2f;

        public DragonAssets(TextureAtlas dragon) {

            String[] babyDefault = {"babyDragonLeftWing", "babyDragon", "babyDragonRightWing"};
            babyDragonStanding = dragon.findRegion("babyDragon");
            Array<TextureAtlas.AtlasRegion> babyDragonFrames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                babyDragonFrames.add(dragon.findRegion(babyDefault[i]));
            }
            babyDragonAnimation = new Animation(WALKING_ROTATION, babyDragonFrames, Animation.PlayMode.LOOP_PINGPONG);

            String[] babyAttack = {"dragonAttack"};
            Array<TextureAtlas.AtlasRegion> babyDragonAttack = new Array<TextureAtlas.AtlasRegion>();
            babyDragonAttack.add(dragon.findRegion(babyAttack[0]));
            babyDragonAttackAnimation = new Animation(WALKING_ROTATION, babyDragonAttack, Animation.PlayMode.NORMAL);

            String[] childDefault = {"childDragonLeft", "childDragon", "childDragonRight"};
            childDragonStanding = dragon.findRegion("childDragon");
            Array<TextureAtlas.AtlasRegion> childDragonFrames = new Array<TextureAtlas.AtlasRegion>();
            for (int i = 0; i < 3; i++) {
                childDragonFrames.add(dragon.findRegion(childDefault[i]));
            }
            childDragonAnimation = new Animation(WALKING_ROTATION, childDragonFrames, Animation.PlayMode.LOOP_PINGPONG);

            String[] childAttack = {"childDragonAttack"};
            Array<TextureAtlas.AtlasRegion> childDragonAttack = new Array<TextureAtlas.AtlasRegion>();
            childDragonAttack.add(dragon.findRegion(childAttack[0]));
            childDragonAttackAnimation = new Animation(WALKING_ROTATION, childDragonAttack, Animation.PlayMode.NORMAL);
        }

    }

    public class BatAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion BAT_1, BAT_2, BAT_3,  BAT_4;
        public final Animation batAnimation;
        public BatAssets(TextureAtlas atlas) {
            BAT_1 = atlas.findRegion("bat1");
            BAT_2 = atlas.findRegion("bat2");
            BAT_3 = atlas.findRegion("bat3");
            BAT_4 = atlas.findRegion("bat4");
            Array<TextureAtlas.AtlasRegion> batFrames = new Array<TextureAtlas.AtlasRegion>();
            batFrames.add(BAT_1);
            batFrames.add(BAT_2);
            batFrames.add(BAT_3);
            batFrames.add(BAT_4);
            batAnimation = new Animation(BAT_LOOP_DURATION,batFrames, Animation.PlayMode.LOOP);
        }
    }

    public class BatDeathAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion BAT_1, BAT_2;
        public final Animation batAnimation;
        public BatDeathAssets(TextureAtlas atlas) {
            BAT_1 = atlas.findRegion("batDeath1");
            BAT_2 = atlas.findRegion("batDeath2");
            Array<TextureAtlas.AtlasRegion> batFrames = new Array<TextureAtlas.AtlasRegion>();
            batFrames.add(BAT_1);
            batFrames.add(BAT_2);
            batAnimation = new Animation(BAT_LOOP_DURATION,batFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class MushroomAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion mushroom0, mushroom1, mushroom2;
        public final Animation mushroomAnimation;
        public MushroomAssets(TextureAtlas atlas) {
            mushroom0 = atlas.findRegion("mushroom1");
            mushroom1 = atlas.findRegion("mushroom2");
            mushroom2 = atlas.findRegion("mushroom3");
            Array<TextureAtlas.AtlasRegion> mushroomFrames = new Array<TextureAtlas.AtlasRegion>();
            mushroomFrames.add(mushroom0);
            mushroomFrames.add(mushroom1);
            mushroomFrames.add(mushroom2);
            mushroomAnimation = new Animation(BAT_LOOP_DURATION,mushroomFrames, Animation.PlayMode.LOOP);
        }
    }
    public class MushroomDeathAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion mushroom0, mushroom1;
        public final Animation mushroomAnimation;
        public MushroomDeathAssets(TextureAtlas atlas) {
            mushroom0 = atlas.findRegion("mushroomDeath");
            mushroom1 = atlas.findRegion("mushroomDeath2");
            Array<TextureAtlas.AtlasRegion> mushroomFrames = new Array<TextureAtlas.AtlasRegion>();
            mushroomFrames.add(mushroom0);
            mushroomFrames.add(mushroom1);
            mushroomAnimation = new Animation(BAT_LOOP_DURATION,mushroomFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class FlowerAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion flower1, flower2, flower3;
        public final Animation flowerAnimation;
        public FlowerAssets(TextureAtlas atlas) {
            flower1 = atlas.findRegion("flower1");
            flower2 = atlas.findRegion("flower2");
            flower3 = atlas.findRegion("flower3");
            Array<TextureAtlas.AtlasRegion> flowerFrames = new Array<TextureAtlas.AtlasRegion>();
            flowerFrames.add(flower1);
            flowerFrames.add(flower2);
            flowerFrames.add(flower3);
            flowerAnimation = new Animation(BAT_LOOP_DURATION,flowerFrames, Animation.PlayMode.LOOP);
        }
    }

    public class FlowerDeathAssets {
        public static final float BAT_LOOP_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion flower1, flower2, flower3;
        public final Animation flowerAnimation;
        public FlowerDeathAssets(TextureAtlas atlas) {
            flower1 = atlas.findRegion("flowerDeath1");
            flower2 = atlas.findRegion("flowerDeath2");
            flower3 = atlas.findRegion("flowerDeath3");
            Array<TextureAtlas.AtlasRegion> flowerFrames = new Array<TextureAtlas.AtlasRegion>();
            flowerFrames.add(flower1);
            flowerFrames.add(flower2);
            flowerFrames.add(flower3);
            flowerAnimation = new Animation(BAT_LOOP_DURATION,flowerFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class LootBoxAssets {
        public static final float LOOTBOX_OPENING_DURATION = 0.15f;
        public static final float LOOTBOX_STANDBY_DURATION = 0.2f;
        public final TextureAtlas.AtlasRegion LOOTBOX_OPENING0, LOOTBOX_OPENING1, LOOTBOX_OPENING2,
        LOOTBOX_OPENING3, LOOTBOX_OPENING4, LOOTBOX_OPENING5, LOOTBOX_OPENING6, LOOTBOX_OPENING7,
                LOOTBOX_OPENING8, LOOTBOX_OPENING9, LOOTBOX_OPENING10, LOOTBOX_OPENING11, LOOTBOX_OPENING12,
        LOOTBOX_OPENING13, LOOTBOX_OPENING14, LOOTBOX_OPENING15, LOOTBOX_OPENING16, LOOTBOX_OPENING17;

        public final Animation STANDBY_LOOTBOX;
        public final Animation LOOTBOX_OPENING;
        public LootBoxAssets(TextureAtlas atlas) {
            LOOTBOX_OPENING0 = atlas.findRegion("burning_crate00");
            LOOTBOX_OPENING1 = atlas.findRegion("burning_crate01");
            LOOTBOX_OPENING2 = atlas.findRegion("burning_crate02");
            LOOTBOX_OPENING3 = atlas.findRegion("burning_crate03");
            LOOTBOX_OPENING4 = atlas.findRegion("burning_crate04");
            LOOTBOX_OPENING5 = atlas.findRegion("burning_crate05");
            LOOTBOX_OPENING6 = atlas.findRegion("burning_crate06");
            LOOTBOX_OPENING7 = atlas.findRegion("burning_crate07");
            LOOTBOX_OPENING8 = atlas.findRegion("burning_crate08");
            LOOTBOX_OPENING9 = atlas.findRegion("burning_crate09");
            LOOTBOX_OPENING10 = atlas.findRegion("burning_crate10");
            LOOTBOX_OPENING11 = atlas.findRegion("burning_crate11");
            LOOTBOX_OPENING12 = atlas.findRegion("burning_crate12");
            LOOTBOX_OPENING13 = atlas.findRegion("burning_crate13");
            LOOTBOX_OPENING14 = atlas.findRegion("burning_crate14");
            LOOTBOX_OPENING15 = atlas.findRegion("burning_crate15");
            LOOTBOX_OPENING16 = atlas.findRegion("burning_crate16");
            LOOTBOX_OPENING17 = atlas.findRegion("burning_crate17");
            Array<TextureAtlas.AtlasRegion> standByFrames = new Array<TextureAtlas.AtlasRegion>();
            standByFrames.add(LOOTBOX_OPENING0);
            standByFrames.add(LOOTBOX_OPENING1);
            Array<TextureAtlas.AtlasRegion> openingFrames = new Array<TextureAtlas.AtlasRegion>();
            openingFrames.add(LOOTBOX_OPENING0);
            openingFrames.add(LOOTBOX_OPENING1);
            openingFrames.add(LOOTBOX_OPENING2);
            openingFrames.add(LOOTBOX_OPENING3);
            openingFrames.add(LOOTBOX_OPENING4);
            openingFrames.add(LOOTBOX_OPENING5);
            openingFrames.add(LOOTBOX_OPENING6);
            openingFrames.add(LOOTBOX_OPENING7);
            openingFrames.add(LOOTBOX_OPENING8);
            openingFrames.add(LOOTBOX_OPENING9);
            openingFrames.add(LOOTBOX_OPENING10);
            openingFrames.add(LOOTBOX_OPENING11);
            openingFrames.add(LOOTBOX_OPENING12);
            openingFrames.add(LOOTBOX_OPENING13);
            openingFrames.add(LOOTBOX_OPENING14);
            openingFrames.add(LOOTBOX_OPENING15);
            openingFrames.add(LOOTBOX_OPENING16);
            openingFrames.add(LOOTBOX_OPENING17);
            STANDBY_LOOTBOX = new Animation(LOOTBOX_STANDBY_DURATION,standByFrames, Animation.PlayMode.LOOP_PINGPONG);
            LOOTBOX_OPENING = new Animation(LOOTBOX_OPENING_DURATION,openingFrames, Animation.PlayMode.NORMAL);
        }

    }


    public class CoinAssets {
        public final TextureAtlas.AtlasRegion COIN_1, COIN_2, COIN_3, COIN_4, COIN_5,
                COIN_6, COIN_7, COIN_8, COIN_9, COIN_COLLECTED;
        public final Animation coinAnimation;
        public CoinAssets(TextureAtlas atlas) {
            COIN_1 = atlas.findRegion("goldCoin1");
            COIN_2 = atlas.findRegion("goldCoin2");
            COIN_3 = atlas.findRegion("goldCoin3");
            COIN_4 = atlas.findRegion("goldCoin4");
            COIN_5 = atlas.findRegion("goldCoin5");
            COIN_6 = atlas.findRegion("goldCoin6");
            COIN_7 = atlas.findRegion("goldCoin7");
            COIN_8 = atlas.findRegion("goldCoin8");
            COIN_9 = atlas.findRegion("goldCoin9");
            COIN_COLLECTED = atlas.findRegion("CoinCollected");
            Array<TextureAtlas.AtlasRegion> coinFrames = new Array<TextureAtlas.AtlasRegion>();
            coinFrames.add(atlas.findRegion("goldCoin1"));
            coinFrames.add(atlas.findRegion("goldCoin2"));
            coinFrames.add(atlas.findRegion("goldCoin3"));
            coinFrames.add(atlas.findRegion("goldCoin4"));
            coinFrames.add(atlas.findRegion("goldCoin5"));
            coinFrames.add(atlas.findRegion("goldCoin6"));
            coinFrames.add(atlas.findRegion("goldCoin7"));
            coinFrames.add(atlas.findRegion("goldCoin8"));
            coinFrames.add(atlas.findRegion("goldCoin9"));
            coinAnimation = new Animation(COIN_LOOP_DURATION, coinFrames, Animation.PlayMode.LOOP);
        }

    }

    public class PlatformAssets {
        public final TextureAtlas.AtlasRegion PLATFORM_1, PLATFORM_2, PLATFORM_3;
        public PlatformAssets(TextureAtlas atlas) {
            PLATFORM_1 = atlas.findRegion("Platform1");
            PLATFORM_2 = atlas.findRegion("Platform2");
            PLATFORM_3 = atlas.findRegion("Platform3");
        }
    }

    public class NinjaStarAssets {
        public final TextureAtlas.AtlasRegion NINJA_STAR_90, NINJA_STAR_30,
                NINJA_STAR_45, NINJA_STAR_60;
        public final Animation ninjaStarAnimation;
        public static final float NINJA_STAR_ROTATION = 0.10f;
        public NinjaStarAssets(TextureAtlas atlas) {
            NINJA_STAR_90 = atlas.findRegion("NinjaStar");
            NINJA_STAR_30 = atlas.findRegion("NinjaStar_Rotated_One");
            NINJA_STAR_45 = atlas.findRegion("NinjaStar_Rotated_Two");
            NINJA_STAR_60 = atlas.findRegion("NinjaStar_Rotated_Three");
            Array<TextureAtlas.AtlasRegion> ninjaStarFrames = new Array<TextureAtlas.AtlasRegion>();
            ninjaStarFrames.add(NINJA_STAR_90);
            ninjaStarFrames.add(NINJA_STAR_30);
            ninjaStarFrames.add(NINJA_STAR_45);
            ninjaStarFrames.add(NINJA_STAR_60);
            ninjaStarAnimation = new Animation(NINJA_STAR_ROTATION,ninjaStarFrames, Animation.PlayMode.LOOP);
        }
    }

    public class FireballAssets {
        public final TextureAtlas.AtlasRegion FIREBALL_0, FIREBALL_1,
                FIREBALL_2, FIREBALL_3,FIREBALL_4;
        public final Animation fireballAnimation;
        public static final float FIREBALL_ROTATION = 0.10f;
        public FireballAssets(TextureAtlas atlas) {
            FIREBALL_0 = atlas.findRegion("fireball1");
            FIREBALL_1 = atlas.findRegion("fireball2");
            FIREBALL_2 = atlas.findRegion("fireball3");
            FIREBALL_3 = atlas.findRegion("fireball4");
            FIREBALL_4 = atlas.findRegion("fireball5");
            Array<TextureAtlas.AtlasRegion> fireballFrames = new Array<TextureAtlas.AtlasRegion>();
            fireballFrames.add(FIREBALL_0);
            fireballFrames.add(FIREBALL_1);
            fireballFrames.add(FIREBALL_2);
            fireballFrames.add(FIREBALL_3);
            fireballFrames.add(FIREBALL_4);
            fireballAnimation = new Animation(FIREBALL_ROTATION,fireballFrames, Animation.PlayMode.LOOP);
        }
    }
    public class IceballAssets {
        public final TextureAtlas.AtlasRegion FIREBALL_0, FIREBALL_1,
                FIREBALL_2, FIREBALL_3,FIREBALL_4;
        public final Animation fireballAnimation;
        public static final float FIREBALL_ROTATION = 0.10f;
        public IceballAssets(TextureAtlas atlas) {
            FIREBALL_0 = atlas.findRegion("iceball1");
            FIREBALL_1 = atlas.findRegion("iceball2");
            FIREBALL_2 = atlas.findRegion("iceball3");
            FIREBALL_3 = atlas.findRegion("iceball4");
            FIREBALL_4 = atlas.findRegion("iceball5");
            Array<TextureAtlas.AtlasRegion> fireballFrames = new Array<TextureAtlas.AtlasRegion>();
            fireballFrames.add(FIREBALL_0);
            fireballFrames.add(FIREBALL_1);
            fireballFrames.add(FIREBALL_2);
            fireballFrames.add(FIREBALL_3);
            fireballFrames.add(FIREBALL_4);
            fireballAnimation = new Animation(FIREBALL_ROTATION,fireballFrames, Animation.PlayMode.LOOP);
        }
    }
    public class ArrowAssets {
        public final TextureAtlas.AtlasRegion ARROW_0, ARROW_1,
                ARROW_2;
        public final Animation arrowRotationAnimation;
        public static final float ARROW_ROTATION = 0.10f;
        public ArrowAssets(TextureAtlas atlas) {
            ARROW_0 = atlas.findRegion("arrow1");
            ARROW_1 = atlas.findRegion("arrow2");
            ARROW_2 = atlas.findRegion("arrow3");

            Array<TextureAtlas.AtlasRegion> arrowFrames = new Array<TextureAtlas.AtlasRegion>();
            arrowFrames.add(ARROW_0);
            arrowFrames.add(ARROW_1);
            arrowFrames.add(ARROW_2);
            arrowRotationAnimation = new Animation(ARROW_ROTATION,arrowFrames, Animation.PlayMode.LOOP);
        }
    }
    public class SamuraiSwordSlashAssets {
        public final TextureAtlas.AtlasRegion SWORD_0, SWORD_1,
                SWORD_2,SWORD_3,SWORD_4;
        public final Animation slashAnimation;
        public static final float SLASH_TIME = 0.10f;

        public SamuraiSwordSlashAssets(TextureAtlas atlas) {
            SWORD_0 = atlas.findRegion("slash1");
            SWORD_1 = atlas.findRegion("slash2");
            SWORD_2 = atlas.findRegion("slash3");
            SWORD_3 = atlas.findRegion("slash4");
            SWORD_4 = atlas.findRegion("slash5");
            Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
            slashFrames.add(SWORD_0);
            slashFrames.add(SWORD_1);
            slashFrames.add(SWORD_2);
            slashFrames.add(SWORD_3);
            slashFrames.add(SWORD_4);

            slashAnimation = new Animation(SLASH_TIME, slashFrames, Animation.PlayMode.NORMAL);
        }
    }
    public class RedSwordSlashAssets {
        public final TextureAtlas.AtlasRegion SWORD_0, SWORD_1,
                SWORD_2;
        public final Animation slashAnimation;
        public static final float SLASH_TIME = 0.10f;

        public RedSwordSlashAssets(TextureAtlas atlas) {
            SWORD_0 = atlas.findRegion("SwordSlash1");
            SWORD_1 = atlas.findRegion("SwordSlash2");
            SWORD_2 = atlas.findRegion("SwordSlash3");

            Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
            slashFrames.add(SWORD_0);
            slashFrames.add(SWORD_1);
            slashFrames.add(SWORD_2);
            slashAnimation = new Animation(SLASH_TIME, slashFrames, Animation.PlayMode.NORMAL);
        }
    }

        public class PurpleSwordSlashAssets {
            public final TextureAtlas.AtlasRegion SWORD_0, SWORD_1,
                    SWORD_2;
            public final Animation slashAnimation;
            public static final float SLASH_TIME = 0.1f;

            public PurpleSwordSlashAssets(TextureAtlas atlas) {
                SWORD_0 = atlas.findRegion("darkSlash1");
                SWORD_1 = atlas.findRegion("darkSlash2");
                SWORD_2 = atlas.findRegion("darkSlash3");

                Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
                slashFrames.add(SWORD_0);
                slashFrames.add(SWORD_1);
                slashFrames.add(SWORD_2);
                slashAnimation = new Animation(SLASH_TIME, slashFrames, Animation.PlayMode.NORMAL);
            }
        }
    public class PaladinAssets {
        public final TextureAtlas.AtlasRegion explosion0, explosion1,
                explosion2, explosion3,explosion4,explosion5,explosion6,explosion7,explosion8;
        public final Animation explosionAnimation;
        public static final float EXPLOSION_TIME = 0.10f;
        public PaladinAssets(TextureAtlas atlas) {
            explosion0 = atlas.findRegion("e0");
            explosion1 = atlas.findRegion("e1");
            explosion2 = atlas.findRegion("e3");
            explosion3 = atlas.findRegion("e4");
            explosion4 = atlas.findRegion("e5");
            explosion5 = atlas.findRegion("e6");
            explosion6 = atlas.findRegion("e7");
            explosion7 = atlas.findRegion("e8");
            explosion8 = atlas.findRegion("e10");
            Array<TextureAtlas.AtlasRegion> explosionFrames = new Array<TextureAtlas.AtlasRegion>();
            explosionFrames.add(explosion1);
            explosionFrames.add(explosion2);
            explosionFrames.add(explosion3);
            explosionFrames.add(explosion4);
            explosionFrames.add(explosion5);
            explosionFrames.add(explosion6);
            explosionFrames.add(explosion7);
            explosionFrames.add(explosion8);
            explosionAnimation = new Animation(EXPLOSION_TIME,explosionFrames, Animation.PlayMode.NORMAL);
        }
    }
    public class BulletPulseAssets {
        public final TextureAtlas.AtlasRegion pulse0;
        public final Animation slashAnimation;
        public static final float SLASH_TIME = 0.10f;

        public BulletPulseAssets(TextureAtlas atlas) {
            pulse0 = atlas.findRegion("SoldierBullet1");


            Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
            slashFrames.add(pulse0);

            slashAnimation = new Animation(SLASH_TIME, slashFrames, Animation.PlayMode.LOOP);
        }
    }
            public class BoomerangAssets {
        public final TextureAtlas.AtlasRegion boomerang0, boomerang1,
                boomerang2, boomerang3, boomerang4, boomerang5, boomerang6, boomerang7;
        public final Animation boomerangAnimation;
        public static final float ROTATION_TIME = 0.10f;

        public BoomerangAssets(TextureAtlas atlas) {
            boomerang0 = atlas.findRegion("redBoomerang0");
            boomerang1 = atlas.findRegion("redBoomerang1");
            boomerang2 = atlas.findRegion("redBoomerang2");
            boomerang3 = atlas.findRegion("redBoomerang3");
            boomerang4 = atlas.findRegion("redBoomerang4");
            boomerang5 = atlas.findRegion("redBoomerang5");
            boomerang6 = atlas.findRegion("redBoomerang6");
            boomerang7 = atlas.findRegion("redBoomerang7");
            Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
            slashFrames.add(boomerang0);
            slashFrames.add(boomerang1);
            slashFrames.add(boomerang2);
            slashFrames.add(boomerang3);
            slashFrames.add(boomerang4);
            slashFrames.add(boomerang5);
            slashFrames.add(boomerang6);
            slashFrames.add(boomerang7);
            boomerangAnimation = new Animation(ROTATION_TIME, slashFrames, Animation.PlayMode.LOOP);
        }

    }
    public class PlusAttackAssets {
        public final TextureAtlas.AtlasRegion plus1, plus2,
                plus3, plus4, plus5, plus6;
        public final Animation boomerangAnimation;
        public static final float ROTATION_TIME = 0.10f;

        public PlusAttackAssets(TextureAtlas atlas) {
            plus1 = atlas.findRegion("healerAttack1");
            plus2 = atlas.findRegion("healerAttack2");
            plus3 = atlas.findRegion("healerAttack3");
            plus4 = atlas.findRegion("healerAttack4");
            plus5 = atlas.findRegion("healerAttack5");
            plus6 = atlas.findRegion("healerAttack6");
            Array<TextureAtlas.AtlasRegion> slashFrames = new Array<TextureAtlas.AtlasRegion>();
            slashFrames.add(plus1);
            slashFrames.add(plus2);
            slashFrames.add(plus3);
            slashFrames.add(plus4);
            slashFrames.add(plus5);
            slashFrames.add(plus6);
            boomerangAnimation = new Animation(ROTATION_TIME, slashFrames, Animation.PlayMode.LOOP);
        }

    }
    public class LavaSlimeAssets {
        public final TextureAtlas.AtlasRegion LAVA_SLIME, LAVA_SLIME_DEATH1,
                LAVA_SLIME_DEATH2, LAVA_SLIME_DEATH3, LAVA_SLIME_Walk1, LAVA_SLIME_Walk2;
        public final Animation lavaSlimeDeathAnimation, lavaSlimeWalkAnimation;
        public final float DEATH_TIME = .2f;
        public final float WALK_TIME = .25f;
        public LavaSlimeAssets(TextureAtlas atlas) {
            LAVA_SLIME = atlas.findRegion("Lava1");
            LAVA_SLIME_DEATH1 = atlas.findRegion("LavaDeath1");
            LAVA_SLIME_DEATH2 = atlas.findRegion("LavaDeath2");
            LAVA_SLIME_DEATH3 = atlas.findRegion("LavaDeath3");
            Array<TextureAtlas.AtlasRegion> lavaSlimeDeathFrames = new Array<TextureAtlas.AtlasRegion>();
            lavaSlimeDeathFrames.add(LAVA_SLIME_DEATH1);
            lavaSlimeDeathFrames.add(LAVA_SLIME_DEATH2);
            lavaSlimeDeathFrames.add(LAVA_SLIME_DEATH3);
            lavaSlimeDeathAnimation = new Animation(DEATH_TIME,lavaSlimeDeathFrames, Animation.PlayMode.NORMAL);
            LAVA_SLIME_Walk1 = atlas.findRegion("LavaMove2");
            LAVA_SLIME_Walk2 = atlas.findRegion("LavaMove3");
            Array<TextureAtlas.AtlasRegion> slimeWalkFrames = new Array<TextureAtlas.AtlasRegion>();
            slimeWalkFrames.add(LAVA_SLIME);
            slimeWalkFrames.add(LAVA_SLIME_Walk1);
            // slimeWalkFrames.add(LAVA_SLIME_Walk2);
            lavaSlimeWalkAnimation = new Animation(WALK_TIME,slimeWalkFrames, Animation.PlayMode.LOOP);
        }
    }

    public class ShurikenHitAnimationAssets {
        public final TextureAtlas.AtlasRegion SMOKE1, SMOKE2, SMOKE3,
                SMOKE4, SMOKE5, SMOKE6, SMOKE7,SMOKE8;
        public final Animation hitAnimation;
        public final float HIT_ANIMATION_TIME = .01f;
        public ShurikenHitAnimationAssets(TextureAtlas atlas) {
            SMOKE1 = atlas.findRegion("Smoke1");
            SMOKE2 = atlas.findRegion("Smoke2");
            SMOKE3 = atlas.findRegion("Smoke3");
            SMOKE4 = atlas.findRegion("Smoke4");
            SMOKE5 = atlas.findRegion("Smoke5");
            SMOKE6 = atlas.findRegion("Smoke6");
            SMOKE7 = atlas.findRegion("Smoke7");
            SMOKE8 = atlas.findRegion("Smoke8");
            Array<TextureAtlas.AtlasRegion> hitAnimationFrames = new Array<TextureAtlas.AtlasRegion>();
            hitAnimationFrames.add(SMOKE1);
            hitAnimationFrames.add(SMOKE2);
            hitAnimationFrames.add(SMOKE3);
            hitAnimationFrames.add(SMOKE4);
            hitAnimationFrames.add(SMOKE5);
            hitAnimationFrames.add(SMOKE6);
            hitAnimationFrames.add(SMOKE7);
            hitAnimationFrames.add(SMOKE8);
            hitAnimation = new Animation(HIT_ANIMATION_TIME,hitAnimationFrames, Animation.PlayMode.NORMAL);
        }
    }

    public class PlayerDeathAnimationAssets {
        public final TextureAtlas.AtlasRegion fire1, fire2, fire3, fire4, fire5, fire6, fire7, fire8,
                fire9, fire10, fire11, fire12, fire13, fire14, fire15;
        public final Animation deathAnimation;
        public final float DEATH_ANIMATION_TIME = .1f;
        public PlayerDeathAnimationAssets(TextureAtlas atlas) {
            fire1 = atlas.findRegion("fire1");
            fire2 = atlas.findRegion("fire2");
            fire3 = atlas.findRegion("fire3");
            fire4 = atlas.findRegion("fire4");
            fire5 = atlas.findRegion("fire5");
            fire6 = atlas.findRegion("fire6");
            fire7 = atlas.findRegion("fire7");
            fire8 = atlas.findRegion("fire8");
            fire9 = atlas.findRegion("fire9");
            fire10 = atlas.findRegion("fire10");
            fire11 = atlas.findRegion("fire11");
            fire12 = atlas.findRegion("fire12");
            fire13 = atlas.findRegion("fire13");
            fire14 = atlas.findRegion("fire14");
            fire15 = atlas.findRegion("fire15");
            Array<TextureAtlas.AtlasRegion> deathAnimationFrames = new Array<TextureAtlas.AtlasRegion>();
            deathAnimationFrames.add(fire1);
            deathAnimationFrames.add(fire2);
            deathAnimationFrames.add(fire3);
            deathAnimationFrames.add(fire4);
            deathAnimationFrames.add(fire5);
            deathAnimationFrames.add(fire6);
            deathAnimationFrames.add(fire7);
            deathAnimationFrames.add(fire8);
            deathAnimationFrames.add(fire9);
            deathAnimationFrames.add(fire10);
            deathAnimationFrames.add(fire12);
            deathAnimationFrames.add(fire13);
            deathAnimationFrames.add(fire14);
            deathAnimationFrames.add(fire15);
            deathAnimation = new Animation(DEATH_ANIMATION_TIME,deathAnimationFrames, Animation.PlayMode.NORMAL);
        }
    }
}
