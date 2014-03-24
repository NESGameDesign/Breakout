package com.akpwebdesign.Breakout.entity;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import com.akpwebdesign.Breakout.IGame;

public class Paddle extends Entity {

	public Paddle(Image image, IGame game) {
		super(image, game);
		this.setY(560.0f);
		initPhysics();
	}

	public void update(Input input) {
		super.update(input);

		this.setX(input.getMouseX() - (this.getImageWidth() / 2));

		if (this.getX() < 0) {
			this.setX(0);
		}

		if (this.getX() + this.getImageWidth() > this.getGame().getGC()
				.getWidth()) {
			this.setX(this.getGame().getGC().getWidth() - this.getImageWidth());
		}

		this.setAngle((float) Math.toDegrees(this.getBody().getAngle()));
		this.getBody().setTransform(new Vec2(this.getX(), this.getY()),
				this.getBody().getAngle());
	}

	@Override
	public void initPhysics() {

		super.initPhysics();

		// body definition
		BodyDef bd = new BodyDef();
		bd.position.set(this.getX() - (this.getImageWidth() / 2), this.getY()
				- (this.getImageHeight() / 2));
		bd.type = BodyType.STATIC;

		// define shape of the body.
		PolygonShape ps = new PolygonShape();
		Vec2[] verts = { new Vec2(5, 3), new Vec2(12, 0), new Vec2(118, 0),
				new Vec2(124, 27), new Vec2(10, 32), new Vec2(5, 29),
				new Vec2(0, 22), new Vec2(0, 9) };
		for (Vec2 vert : verts) {
			vert.mulLocal(this.getScale());
			vert.mulLocal(this.getScale());
		}
		ps.set(verts, 8);

		PolygonShape ps2 = new PolygonShape();
		Vec2[] verts2 = { new Vec2(124, 27), new Vec2(118, 0),
				new Vec2(124, 4), new Vec2(128, 10), new Vec2(128, 22),
				new Vec2(10, 32), new Vec2(124, 27), new Vec2(118, 32) };
		for (Vec2 vert : verts2) {
			vert.mulLocal(this.getScale());
			vert.mulLocal(this.getScale());
		}
		ps2.set(verts2, 8);

		// define fixture of the body.
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 1f;
		fd.friction = 0f;
		fd.restitution = 1f;

		// create the body and add fixture to it
		this.setBody(this.getGame().getWorld().createBody(bd));
		this.getBody().createFixture(fd);

		fd.shape = ps2;
		this.getBody().createFixture(fd);
	}
}
