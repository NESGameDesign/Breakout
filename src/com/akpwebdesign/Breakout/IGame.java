package com.akpwebdesign.Breakout;

import java.util.List;

import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import com.akpwebdesign.Breakout.entity.Entity;
import com.akpwebdesign.Breakout.entity.Paddle;

public interface IGame {

	public List<Entity> getEntities();

	public GameContainer getGC();

	public Paddle getPaddle();

	public Entity getBall();

	public World getWorld();

	public int getBricksBroken();

	public void setBricksBroken(int bricksBroken);

	public void initPhysics();

	public void addScore(int value);
	
	public void addLife();
	
	public void removeLife();

}
