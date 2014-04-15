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

public class MainMenu extends BasicGameState {
	private StateBasedGame game;
	private int state;
	
	public MainMenu(States state) {
		this.state = state.getStateID();
	}
	 
	@Override
	public void init(GameContainer container, StateBasedGame game)
	        throws SlickException {
	    this.game = game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setColor(Color.white);
	    g.drawString("Breakout", 50, 50);
	 
	    g.drawString("1. Play Game", 50, 100);
	    g.drawString("2. Quit", 50, 120);
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
	        game.enterState(States.GAME.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        break;
	    case Input.KEY_ESCAPE:
	    case Input.KEY_2:
	    	Main.exit();
	        game.getContainer().exit();
	        break;
	    case Input.KEY_M:
	    	game.enterState(States.EDITOR.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	    default:
	        break;
	    }
	}

}
