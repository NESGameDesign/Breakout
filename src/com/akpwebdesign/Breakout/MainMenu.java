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

public class MainMenu extends BasicGameState {
	private StateBasedGame game; // stored for later use
	private int state;
	
	public MainMenu(int state) {
		this.state = state;
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
	    g.drawString("2. High Scores [NYI]", 50, 120);
	    g.drawString("3. Quit", 50, 140);
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
	        game.enterState(1);
	        break;
	    case Input.KEY_2:
	        // TODO: Implement high scores
	        break;
	    case Input.KEY_ESCAPE:
	    case Input.KEY_3:
	    	Main.exit();
	        game.getContainer().exit();
	        break;
	    case Input.KEY_M:
	    	game.enterState(2);
	    default:
	        break;
	    }
	}

}
