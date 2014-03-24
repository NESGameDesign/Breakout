package com.akpwebdesign.Breakout.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PhysicsUtils {
	// This method creates a wall.
	public static void addWall(float posX, float posY, float length,
			double angle, World world) {
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(1, length);

		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1.0f;
		fd.friction = 0.0f;
		fd.restitution = 1.0f;

		BodyDef bd = new BodyDef();
		bd.position.set(posX, posY);
		bd.angle = (float) angle;

		world.createBody(bd).createFixture(fd);
	}

	public static void addBall(float posX, float posY, float radius, World world) {
		CircleShape cs = new CircleShape();
		cs.m_radius = radius;

		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 1.0f;
		fd.friction = 0.0f;
		fd.restitution = 1.0f;

		BodyDef bd = new BodyDef();
		bd.position.set(posX, posY);

		world.createBody(bd).createFixture(fd);
	}
}
