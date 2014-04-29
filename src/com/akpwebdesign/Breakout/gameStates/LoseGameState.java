package com.akpwebdesign.Breakout.gameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.akpwebdesign.Breakout.GameLevel;

public class LoseGameState extends BasicGameState {
	
	private GameContainer gc;
	private StateBasedGame game;
	private int state;
	
	public LoseGameState(States state) {
		this.state = state.getStateID();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		this.gc = arg0;
		this.game = arg1;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.red);
	    g.drawString("You Lost!", 50, 50);
	 
	    g.setColor(Color.white);
	    g.drawString("1. Play Again", 50, 100);
	    g.drawString("2. Main Menu", 50, 120);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return state;
	}
	
	public void keyReleased(int key, char c) {
	    switch(key) {
	    case Input.KEY_1:
	    	((GameGameState)game.getState(States.GAME.getStateID())).setLevel(GameLevel.LVL1);
	    	((GameGameState)game.getState(States.GAME.getStateID())).fullClearState();
	    	try {game.getState(States.GAME.getStateID()).init(gc, game);}
	    	catch (SlickException e) {e.printStackTrace();}
	        game.enterState(States.GAME.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        break;
	    case Input.KEY_ESCAPE:
	    case Input.KEY_2:
	    	((GameGameState)game.getState(States.GAME.getStateID())).addLife(5);
	        game.enterState(States.MAIN_MENU.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        break;
	    default:
	        break;
	    }
	}

}
