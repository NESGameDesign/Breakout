package com.akpwebdesign.Breakout;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.akpwebdesign.Breakout.gameStates.States;

public class PauseScreenState extends BasicGameState {

	private int state;
	private GameContainer gc;
	private StateBasedGame sbg;

	public PauseScreenState(States state) {
		this.state = state.getStateID();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.gc = gc;
		this.sbg = sbg;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
	    g.drawString("Paused", 50, 50);
	 
	    g.drawString("1. Resume Game", 50, 100);
	    g.drawString("2. Quit Game", 50, 120);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i)
			throws SlickException {

	}

	@Override
	public int getID() {
		return state;
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE || key == Input.KEY_1) {
			unPause();
		}
		
		if (key == Input.KEY_2) {
			quitGame();
		}
	}

	private void quitGame() {
		try {
			sbg.getState(States.GAME.getStateID()).init(this.gc, this.sbg);
		} catch (SlickException e) {
			e.printStackTrace();
		}
        sbg.enterState(States.MAIN_MENU.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));		
	}

	private void unPause() {
		sbg.enterState(States.GAME.getStateID());
	}

}
