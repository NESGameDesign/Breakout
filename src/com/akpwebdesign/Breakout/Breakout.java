package com.akpwebdesign.Breakout;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.akpwebdesign.Breakout.gameStates.States;

public class Breakout extends StateBasedGame {

	public Breakout(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenu(States.MAIN_MENU));
		this.addState(new PauseScreenState(States.PAUSE_MENU));
		this.addState(new Game(States.GAME));
		this.addState(new MapEditor(States.EDITOR));
	}

}
