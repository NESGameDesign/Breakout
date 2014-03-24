package com.akpwebdesign.Breakout;

import java.util.List;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.akpwebdesign.Breakout.entity.Entity;
import com.akpwebdesign.Breakout.entity.Paddle;

public interface IGame {

	public List<Entity> getEntities();

	public GameContainer getGC();

	public void init(GameContainer gc) throws SlickException;

	public void update(GameContainer gc, int i) throws SlickException;

	public void render(GameContainer gc, Graphics g) throws SlickException;

	public Paddle getPaddle();

	public Entity getBall();

	public World getWorld();

	public int getBricksBroken();

	public void setBricksBroken(int bricksBroken);

	public void initPhysics();

	public void addScore(int value);

}
