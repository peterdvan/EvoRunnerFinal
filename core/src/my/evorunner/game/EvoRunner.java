package my.evorunner.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import my.evorunner.game.Gameplay.GameplayScreen;
import my.evorunner.game.Shop.OwnedSkinScreen;
import my.evorunner.game.Shop.ShopScreen;

/**
 * Main game class (EvoRunner.java)
 *
 * Version 1.1
 * Last Edited: Matthew Phan
 * Changelog 1.1
 * Added owned skin screen
 *
 * Changelog 1.0
 * Changed path slkscr.ttf from Skins to Fonts
 */

public class EvoRunner extends Game{
	//Declare integer constants for the different screens
	public final static int MENU_SCREEN = 0;
	public final static int GAMEPLAY_SCREEN = 1;
	public final static int SETTINGS_SCREEN = 2;
	public final static int END_SCREEN = 3;
	public final static int SHOP_SCREEN = 4;
	public final static int CHARACTERS_SCREEN = 5;

	//Declare screens for menu, game play, app preferences, end screen, and shop
	private MenuScreen menuScreen;
	private GameplayScreen gameplayScreen;
	private AppPreferences appPreferences;
	private EndScreen endScreen;
	private ShopScreen shopScreen;
	private OwnedSkinScreen characterScreen;

	//Declare adHandler
	public AdHandler adHandler;

	//Declare FTF generators for font
	public static FreeTypeFontGenerator generator;
	public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;

	//Declare preferences
	public static Preferences savedData;

	//Declare int for coins
	public static int COINS;

	//EvoRunner constructor with adHandler
	public EvoRunner(AdHandler handler) {
		this.adHandler = handler;
		adHandler.showAds(true);
	}

	//Create function. Runs loadData and initializes the generator, parameter
	//Screens and appPreferences
	@Override
	public void create() {
		loadData();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/slkscr.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		gameplayScreen = new GameplayScreen(this);
		menuScreen = new MenuScreen(this);
		endScreen = new EndScreen(this);
		shopScreen = new ShopScreen(this);
		appPreferences = new AppPreferences();
		characterScreen = new OwnedSkinScreen(this);
		setScreen(menuScreen);		//Sets the screen to the menu
	}

	//Loads the users save data
	private void loadData() {
		savedData = Gdx.app.getPreferences("Saved Data");
		COINS = savedData.getInteger("coins",0);
	}

	//Changes screens based on the integer brought in as parameter
	public void changeScreens(int screen) {
		switch (screen) {
			case MENU_SCREEN:
				this.setScreen(menuScreen);
				break;
			case GAMEPLAY_SCREEN:
				this.setScreen(gameplayScreen);
				break;
			case END_SCREEN:
				this.setScreen(endScreen);
				break;
			case SHOP_SCREEN:
				this.setScreen(shopScreen);
				break;
			case CHARACTERS_SCREEN:
				this.setScreen(characterScreen);
				break;
		}
	}

	public AppPreferences getPreferences() {
		return appPreferences;
	}
	public float getMeters() {
		return  gameplayScreen.getMeters();
	}

	//Uses parent class render function
	@Override
	public void render() {
		super.render();
	}
}
