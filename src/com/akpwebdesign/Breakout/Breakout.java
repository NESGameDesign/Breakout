package com.akpwebdesign.Breakout;

import java.net.URL;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.akpwebdesign.Breakout.gameStates.States;

import de.matthiasmann.twl.TWLSlick.TWLStateBasedGame;

public class Breakout extends TWLStateBasedGame {

	public Breakout(String name) throws SlickException {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenu(States.MAIN_MENU));
		this.addState(new Game(States.GAME));
		this.addState(new MapEditor(States.EDITOR));
	}

	@Override
	protected URL getThemeURL() {
		return Breakout.class.getResource("/res/ui/theme.xml");
	}

}
