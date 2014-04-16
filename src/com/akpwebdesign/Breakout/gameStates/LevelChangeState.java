package com.akpwebdesign.Breakout.gameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.akpwebdesign.Breakout.GameLevel;

public class LevelChangeState extends BasicGameState {

	private GameContainer gc;
	private StateBasedGame game;
	private int state;
	private GameLevel lvl;
	
	public LevelChangeState(States state) {
		this.state = state.getStateID();
	}
	 
	@Override
	public void init(GameContainer container, StateBasedGame game)
	        throws SlickException {
	    this.game = game;
	    this.gc = container;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
	    g.drawString("Loading...", 50, 50);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		((GameGameState)arg1.getState(States.GAME.getStateID())).setLevel(this.lvl);
		((GameGameState)arg1.getState(States.GAME.getStateID())).init(arg0, arg1);
		arg1.enterState(States.GAME.getStateID());
	}

	@Override
	public int getID() {
		return state;
	}
	
	public void setLevel(GameLevel lvl) {
		this.lvl = lvl;
	}

}
