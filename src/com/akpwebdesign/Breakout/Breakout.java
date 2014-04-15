package com.akpwebdesign.Breakout;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.akpwebdesign.Breakout.gameStates.GameGameState;
import com.akpwebdesign.Breakout.gameStates.LoseGameState;
import com.akpwebdesign.Breakout.gameStates.MainMenuState;
import com.akpwebdesign.Breakout.gameStates.MapEditorGameState;
import com.akpwebdesign.Breakout.gameStates.PauseScreenState;
import com.akpwebdesign.Breakout.gameStates.States;

public class Breakout extends StateBasedGame {

	public Breakout(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new MainMenuState(States.MAIN_MENU));
		this.addState(new PauseScreenState(States.PAUSE_MENU));
		this.addState(new GameGameState(States.GAME));
		this.addState(new LoseGameState(States.LOSE_GAME));
		this.addState(new MapEditorGameState(States.EDITOR));
	}

}
