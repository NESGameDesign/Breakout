package com.akpwebdesign.Breakout;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Breakout extends StateBasedGame {

	public Breakout(String name) {
		super(name);
		this.addState(new MainMenu(0));
		this.addState(new Game(1));
		this.addState(new MapEditor(2));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

	}

}
