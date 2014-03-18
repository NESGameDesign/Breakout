package com.akpwebdesign.Breakout;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.akpwebdesign.Breakout.entity.Ball;
import com.akpwebdesign.Breakout.entity.Entity;
import com.akpwebdesign.Breakout.entity.Paddle;
import com.akpwebdesign.Breakout.entity.brick.Brick;
import com.akpwebdesign.Breakout.entity.brick.BrickType;
import com.akpwebdesign.Breakout.physics.GameCollisionListener;
import com.akpwebdesign.Breakout.physics.PhysicsUtils;
import com.akpwebdesign.Breakout.screen.ScreenUtils;
import com.akpwebdesign.Breakout.screen.Slick2DJBox2DDebugDraw;

public class Game extends BasicGame implements IGame {
	private Input input = new Input(0);
	private Paddle paddle = null;
	private Ball ball = null;
	private GameContainer gc = null;
	private List<Entity> entities = new ArrayList<Entity>();
	private World world = new World(new Vec2(0.0f, 0.0f));
	private int bricksBroken = 0;
	private int score = 0;
	private boolean debug = false;
	private boolean exitRequested = false;
	private int targetFPS = 120;
	private int timeStep = (1000 / targetFPS);
	private int physicsIterations = 200;
	
	public Game(String gamename) throws SlickException {
		super(gamename);
		
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

	@Override
	public void init(GameContainer gc) throws SlickException {
		paddle = new Paddle(new Image("res/paddle.png"), this);
		paddle.setScale(0.75f);
		ball = new Ball(new Image("res/ball.png"), this);
		ball.setScale(0.6f);
		this.gc = gc;
		entities.add(paddle);
		entities.add(ball);
		
		Brick brick = null;
		
		//TODO: implement maps. :D
		
		for(int x = 0; x < 16; x++) {
			for(int i = 15; i < (800-49); )
			{
				brick = new Brick(BrickType.randomType(), this);				
				brick.setScale((float) 0.75);
				brick.setX(i);
				brick.setY((brick.getImageHeight()+2)*x+30);
				entities.add(brick);
				i = (int) (i + brick.getImageWidth()+1);
			}
		}
		
		this.initPhysics();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(this.exitRequested)
			exit();
		
		input.poll(gc.getWidth(), gc.getHeight());

		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).update(input);
		}
		
		for (int x = 0; x < entities.size(); x++) {
			if (entities.get(x).getBody().m_type == BodyType.DYNAMIC) {
				world.step(timeStep, physicsIterations, physicsIterations);
				entities.get(x).setX(entities.get(x).getBody().getPosition().x-(entities.get(x).getImageWidth()/2));
				entities.get(x).setY(entities.get(x).getBody().getPosition().y-(entities.get(x).getImageHeight()/2));
				entities.get(x).setAngle((float) Math.toDegrees(entities.get(x).getBody().getAngle()));
			}
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			this.exitRequested = true;
		}

		if (input.isKeyDown(Input.KEY_F12)) {
			ScreenUtils.takeScreenshot();
		}
		
		if (input.isKeyDown(Input.KEY_D)) {
			this.debug = !this.debug;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).draw();
		}
		
		g.drawString("Score: "+this.score, 100, 9);
		
		if(this.debug)
		{
			world.drawDebugData();
		}
	}

	public void initPhysics() {
		PhysicsUtils.addWall(-1, 0, 600, Math.toRadians(0), world);
		PhysicsUtils.addWall(801, 0, 600, Math.toRadians(0), world);
		PhysicsUtils.addWall(0, 601, 800, Math.toRadians(90), world);
		PhysicsUtils.addWall(0, -1, 800, Math.toRadians(90), world);
		
		Slick2DJBox2DDebugDraw sDD = new Slick2DJBox2DDebugDraw(gc);
		GameCollisionListener gcl = new GameCollisionListener();
		
		sDD.setFlags(0x0001|0x0004);
		world.setDebugDraw(sDD);
		world.setContactListener(gcl);
	}

	private void exit() {
		gc.exit();
		Main.exit();
	}
	
	public void addScore(int value) {
		this.score += value;
	}
}
