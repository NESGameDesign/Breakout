package com.akpwebdesign.Breakout.gameStates;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.akpwebdesign.Breakout.Code;
import com.akpwebdesign.Breakout.Coordinate;
import com.akpwebdesign.Breakout.GameLevel;
import com.akpwebdesign.Breakout.IGame;
import com.akpwebdesign.Breakout.entity.Ball;
import com.akpwebdesign.Breakout.entity.Entity;
import com.akpwebdesign.Breakout.entity.Paddle;
import com.akpwebdesign.Breakout.entity.brick.Brick;
import com.akpwebdesign.Breakout.map.Map;
import com.akpwebdesign.Breakout.physics.GameCollisionListener;
import com.akpwebdesign.Breakout.physics.PhysicsUtils;
import com.akpwebdesign.Breakout.screen.ScreenUtils;
import com.akpwebdesign.Breakout.screen.Slick2DJBox2DDebugDraw;

public class GameGameState extends BasicGameState implements IGame {
	private Input input = new Input(0);
	private Paddle paddle = null;
	private Ball ball = null;
	private GameContainer gc = null;
	private List<Entity> entities = new ArrayList<Entity>();
	private World world = new World(new Vec2(0.0f, 0.0f));
	private int bricksBroken = 0;
	private int bricks = 0;
	private int score = 0;
	private boolean debug = false;
	private int targetFPS = 120;
	private int timeStep = (1000 / targetFPS);
	private int physicsIterations = 200;
	private int state;
	private StateBasedGame game;
	private int lives = 5;
	private Code code = new Code();
	private Map map;
	private ArrayList<GameLevel> gameLevelList = new ArrayList<GameLevel>();
	private GameLevel currentLevel;
	private boolean codeFound;
	
	public GameGameState(States state) {
		this.state = state.getStateID();
		this.setLevel(GameLevel.LVL1);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		this.clearState();
		this.game = game;
		this.gc = gc;
		
		paddle = new Paddle(new Image("res/paddle.png"), this);
		paddle.setScale(0.75f);
		ball = new Ball(new Image("res/ball.png"), this);
		ball.setScale(0.6f);
		entities.add(paddle);
		entities.add(ball);
		
		Brick brick = null;
		
		this.setMap(new Map(Map.loadMap(this.getClass().getResourceAsStream(
				"/res/maps/"+currentLevel.getLevelName()))));

		for (Coordinate coord : this.getMap().getCoordinates()) {
			if (coord.isBrick()) {
				brick = new Brick(coord.getBrickType(), this);
				brick.setX(coord.getX());
				brick.setY(coord.getY());
				entities.add(brick);
				this.bricks++;
			}
		}

		this.initPhysics();
		
		this.gameLevelList.add(GameLevel.LVL1);
		this.gameLevelList.add(GameLevel.LVL2);
		this.gameLevelList.add(GameLevel.LVL3);
		this.gameLevelList.add(GameLevel.LVL4);
		this.gameLevelList.add(GameLevel.LVL5);
		this.gameLevelList.add(GameLevel.LVL6);
		this.gameLevelList.add(GameLevel.LVL7);
		this.gameLevelList.add(GameLevel.LVL8);
		this.gameLevelList.add(GameLevel.LVL9);
		this.gameLevelList.add(GameLevel.LVL10);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {

		input.poll(gc.getWidth(), gc.getHeight());

		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).update(input);
		}

		for (int x = 0; x < entities.size(); x++) {
			if (entities.get(x).getBody().m_type == BodyType.DYNAMIC) {
				world.step(timeStep, physicsIterations, physicsIterations);
				entities.get(x).setX(
						entities.get(x).getBody().getPosition().x
								- (entities.get(x).getImageWidth() / 2));
				entities.get(x).setY(
						entities.get(x).getBody().getPosition().y
								- (entities.get(x).getImageHeight() / 2));
				entities.get(x).setAngle(
						(float) Math.toDegrees(entities.get(x).getBody()
								.getAngle()));
			}
		}
		
		if (this.lives <= 0) {
			this.loseGame();
		}
		
		if (this.bricks == this.bricksBroken) {
			this.changeLevel();
		}
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).draw();
		}

		g.drawString("Score: " + this.score, 100, 9);
		g.drawString("Lives: " + this.lives, 300, 9);

		if (this.debug) {
			world.drawDebugData();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			pause();
		}

		if (key == Input.KEY_F12) {
			ScreenUtils.takeScreenshot();
		}

		if (key == Input.KEY_D) {
			this.debug = !this.debug;
		}
		
		if (key == Input.KEY_C) {
			this.changeLevel();
		}
		
		if(code.checkCode(key)) {
			this.codeFound = true;
		}
		
		if(this.codeFound && this.currentLevel != GameLevel.CAZIF) {
			this.setLevel(GameLevel.CAZIF);
			GameContainer gc = this.getGC();
			StateBasedGame game = this.game;
			this.clearState();
			
			try {this.init(gc, game);} 
			catch (SlickException e) {e.printStackTrace();}
			
			this.lives = Integer.MAX_VALUE;
			this.bricksBroken += 5000;
			
			this.codeFound = false;
		}
	}

	public void initPhysics() {
		PhysicsUtils.addWall(-1, 0, 600, Math.toRadians(0), world);
		PhysicsUtils.addWall(880, 0, 600, Math.toRadians(0), world);
		PhysicsUtils.addWall(0, 601, 880, Math.toRadians(90), world);
		PhysicsUtils.addWall(0, -1, 880, Math.toRadians(90), world);

		Slick2DJBox2DDebugDraw sDD = new Slick2DJBox2DDebugDraw(gc);
		GameCollisionListener gcl = new GameCollisionListener();

		sDD.setFlags(0x0001 | 0x0004);
		world.setDebugDraw(sDD);
		world.setContactListener(gcl);
	}

	private void pause() {
		game.enterState(States.PAUSE_MENU.getStateID());
	}
	
	private void loseGame() {
		game.enterState(States.LOSE_GAME.getStateID());
	}

	public void addScore(int value) {
		this.score += value;
	}

	@Override
	public int getID() {
		return this.state;
	}
	

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public GameContainer getGC() {
		return gc;
	}

	public World getWorld() {
		return world;
	}

	public int getBricksBroken() {
		return bricksBroken;
	}

	public void setBricksBroken(int bricksBroken) {
		this.bricksBroken = bricksBroken;
	}
	
	public void removeLife() {
		this.lives--;
	}
	
	public void addLife() {
		this.lives++;
	}

	private void clearState() {
		this.gc = null;
		this.game = null;
		this.paddle = null;
		this.ball = null;
		entities.clear();
		this.world = new World(new Vec2(0.0f, 0.0f));
		this.debug = false;
		this.bricksBroken = 0;
		this.bricks = 0;
		this.gameLevelList.clear();
	}
	
	public void fullClearState() {
		clearState();
		this.score = 0;
		this.lives = 5;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setLevel(GameLevel level) {
		this.currentLevel = level;
	}
	
	public void resetLives() {
		this.lives = 5;
	}
	
	private void changeLevel() {
		boolean change = false;
		for(GameLevel lvl : this.gameLevelList) {
			if(change) {
				change = false;
				((LevelChangeState)game.getState(States.LEVEL_CHANGE.getStateID())).setLevel(lvl);
				game.enterState(States.LEVEL_CHANGE.getStateID());
				return;
			}
			if(lvl == this.currentLevel) {
				change = true;
			}
		}
		game.enterState(States.WIN_GAME.getStateID());
	}

	@Override
	public void addLife(int lives) {
		this.lives += lives;		
	}
}
