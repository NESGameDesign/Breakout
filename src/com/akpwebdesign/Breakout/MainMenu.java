package com.akpwebdesign.Breakout;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.akpwebdesign.Breakout.gameStates.States;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.TWLSlick.BasicTWLGameState;
import de.matthiasmann.twl.TWLSlick.RootPane;
import de.matthiasmann.twl.TWLSlick.TWLStateBasedGame;

public class MainMenu extends BasicTWLGameState {
	private TWLStateBasedGame game;
	private int state;
	private Button playBtn;
	private Button quitBtn;
	private Button editorBtn;
	private Label label;


	public MainMenu(States state) {
		this.state = state.getStateID();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = (TWLStateBasedGame) game;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame arg1, int arg2)
			throws SlickException {
		gc.setMouseGrabbed(false);
	}

	@Override
	public int getID() {
		return state;
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			Main.exit();
			game.getContainer().exit();
			break;
		default:
			break;
		}
	}

	@Override
	protected RootPane createRootPane() {
		RootPane rp = super.createRootPane();
		rp.setTheme("");

		playBtn = new Button("Play Game");
		playBtn.addCallback(new Runnable() {
			public void run() {
				game.enterState(States.GAME.getStateID(),
						new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
		});
		
		quitBtn = new Button("Quit");
		quitBtn.addCallback(new Runnable() {
			public void run() {
				Main.exit();
				game.getContainer().exit();
			}
		});
		
		editorBtn = new Button("Open Map Editor");
		editorBtn.addCallback(new Runnable() {
			public void run() {
				game.enterState(States.EDITOR.getStateID(),
						new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
		});
		
		label = new Label("Breakout");

		rp.add(playBtn);
		rp.add(quitBtn);
		rp.add(editorBtn);
		rp.add(label);
		return rp;
	}

	@Override
	protected void layoutRootPane() {
		playBtn.adjustSize();
		playBtn.setPosition(150, 500);
		
		quitBtn.adjustSize();
		quitBtn.setPosition(400, 500);
		
		editorBtn.adjustSize();
		editorBtn.setPosition(600, 500);
		

		label.adjustSize();
		label.setPosition(250, 300);
	}

}
