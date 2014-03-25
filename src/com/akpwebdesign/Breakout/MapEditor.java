package com.akpwebdesign.Breakout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import com.akpwebdesign.Breakout.entity.Entity;
import com.akpwebdesign.Breakout.entity.Paddle;
import com.akpwebdesign.Breakout.entity.brick.Brick;
import com.akpwebdesign.Breakout.entity.brick.BrickType;
import com.akpwebdesign.Breakout.gameStates.States;
import com.akpwebdesign.Breakout.map.Map;

import de.matthiasmann.twl.TWLSlick.BasicTWLGameState;

public class MapEditor extends BasicTWLGameState implements IGame {
	private Input input = new Input(0);
	private GameContainer gc = null;
	private List<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
	private Brick followMouseBrick = null;
	private int gridHeight = 0;
	private int gridWidth = 0;
	private BrickType type = null;
	private int state;
	private StateBasedGame game;

	public MapEditor(States state) {
		this.state = state.getStateID();
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public GameContainer getGC() {
		return gc;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {		
		this.game = game;
		this.gc = gc;
		this.followMouseBrick = new Brick(BrickType.RED, this);
		this.followMouseBrick.getImage().setAlpha(0.5f);
		this.setGridHeight((int) (this.followMouseBrick.getImageHeight() + 1));
		this.setGridWidth((int) (this.followMouseBrick.getImageWidth() + 1));
		type = BrickType.RED;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		input.poll(gc.getWidth(), gc.getHeight());

		float x = (float) (Math.floor((input.getMouseX() / this.gridWidth)) * gridWidth);
		float y = (float) Math.floor((input.getMouseY() / this.gridHeight))
				* gridHeight;

		this.followMouseBrick.setX(x);
		this.followMouseBrick.setY(y);
		gc.setMouseGrabbed(false);
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			this.exit();
		}

		if (key == Input.KEY_S) {
			this.save();
		}

		if (key == Input.KEY_L) {
			this.load();
		}

		if (key == Input.KEY_C) {
			this.clear();
		}

		if (key == Input.KEY_T) {
			this.type = this.type.next();
			this.followMouseBrick.setType(type);
			this.followMouseBrick.getImage().setAlpha(0.5f);
		}
	}

	private void load() {
		JFileChooser example = new JFileChooser();

		example.showOpenDialog(null);
		coords = Map.loadMap(example.getSelectedFile().getAbsolutePath())
				.getCoordinates();

		this.getEntities().clear();

		for (Coordinate coord : coords) {
			Brick brick;
			try {
				brick = new Brick(coord.getBrickType(), this);
				brick.setX(coord.getX());
				brick.setY(coord.getY());
				this.getEntities().add(brick);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).draw();
		}
		this.followMouseBrick.draw();
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (button == 0) {
			float x2 = (float) (Math.floor((x / this.gridWidth)) * gridWidth);
			float y2 = (float) Math.floor((y / this.gridHeight)) * gridHeight;
			boolean add = true;

			for (Coordinate coord : this.coords) {
				if (coord.getX() == x2 && coord.getY() == y2) {
					add = false;
				}
			}
			if (add) {
				try {
					Brick brick = new Brick(type, this);
					brick.setX(x2);
					brick.setY(y2);
					this.getEntities().add(brick);
					this.coords.add(new Coordinate(x2, y2, true, type));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
		if (button == 1) {
			float x2 = (float) (Math.floor((x / this.gridWidth)) * gridWidth);
			float y2 = (float) Math.floor((y / this.gridHeight)) * gridHeight;

			Iterator<Coordinate> coordinateIterator = this.coords.iterator();
			while (coordinateIterator.hasNext()) {
				Coordinate coord = coordinateIterator.next();
				if (coord.getX() == x2 && coord.getY() == y2) {
					coordinateIterator.remove(); // Removes the 'current' item
				}
			}

			Iterator<Entity> entityIterator = this.getEntities().iterator();
			while (entityIterator.hasNext()) {
				Entity brick = entityIterator.next();
				if (brick.getX() == x2 && brick.getY() == y2) {
					entityIterator.remove(); // Removes the 'current' item
				}
			}
		}
	}

	private void save() {
		JFileChooser example = new JFileChooser();

		example.showOpenDialog(null);
		Map.saveMap(new Map(coords), example.getSelectedFile()
				.getAbsolutePath());
	}

	private void clear() {
		this.getEntities().clear();
		this.coords.clear();
	}

	private void exit() {
		game.enterState(States.MAIN_MENU.getStateID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	}

	@Override
	public Paddle getPaddle() {
		return null;
	}

	@Override
	public Entity getBall() {
		return null;
	}

	@Override
	public World getWorld() {
		return null;
	}

	@Override
	public int getBricksBroken() {
		return 0;
	}

	@Override
	public void setBricksBroken(int bricksBroken) {
	}

	@Override
	public void initPhysics() {
	}

	@Override
	public void addScore(int value) {
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	@Override
	public int getID() {
		return state;
	}
}
