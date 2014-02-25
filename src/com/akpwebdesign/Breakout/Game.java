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
import com.akpwebdesign.Breakout.physics.PhysicsUtils;
import com.akpwebdesign.Breakout.screen.ScreenUtils;
import com.akpwebdesign.Breakout.screen.Slick2DJBox2DDebugDraw;

public class Game extends BasicGame {
	private Input input = new Input(0);
	private Paddle paddle = null;
	private GameContainer gc = null;
	private List<Entity> entities = new ArrayList<Entity>();
	private World world = new World(new Vec2(0.0f, 0.0f));
	private int bricksBroken = 0;
	private boolean debug = true;

	public Game(String gamename) throws SlickException {
		super(gamename);
	}

	public Paddle getPaddle() {
		return paddle;
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
		Ball ball = new Ball(new Image("res/ball.png"), this);
		ball.setScale(0.75f);
		this.gc = gc;
		entities.add(paddle);
		entities.add(ball);
		this.initPhysics();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		input.poll(gc.getWidth(), gc.getHeight());

		float timeStep = 1.0f / 120.0f;
		int velocityIterations = 6;
		int positionIterations = 2;

		for (int x = 0; x < entities.size(); x++) {
			entities.get(x).update(input);

			if (entities.get(x).getBody().m_type == BodyType.DYNAMIC) {
				for (int j = 0; j < 120; ++j) {
					world.step(timeStep, velocityIterations, positionIterations);
					entities.get(x).setX(entities.get(x).getBody().getPosition().x-(entities.get(x).getImageWidth()/2));
					entities.get(x).setY(entities.get(x).getBody().getPosition().y-(entities.get(x).getImageHeight()/2));
				}
			}
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			gc.exit();
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
		
		if(this.debug)
		{
			world.drawDebugData();
		}
	}

	public void initPhysics() {
		PhysicsUtils.addWall(-1, 0, 1, 600, world);
		PhysicsUtils.addWall(801, 0, 1, 600, world);
		PhysicsUtils.addWall(0, 601, 800, 1, world);
		PhysicsUtils.addWall(0, -1, 800, 1, world);
		
		Slick2DJBox2DDebugDraw sDD = new Slick2DJBox2DDebugDraw(gc);
		
		sDD.setFlags(0x0001|0x0002|0x0004|0x0010|0x0020|0x0040|0x0080);
		world.setDebugDraw(sDD);
	}
}
