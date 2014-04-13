package com.akpwebdesign.Breakout.entity;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.akpwebdesign.Breakout.IGame;

public class Ball extends Entity {

	private boolean lockedToPaddle = true;

	public Ball(Image image, IGame game) {
		super(image, game);
		this.setX(-999);
		this.setY(-999);
		this.initPhysics();
	}

	public void update(Input input) {
		super.update(input);

		input.poll(this.getGame().getGC().getWidth(), this.getGame().getGC()
				.getHeight());

		if (this.getY() + this.getImageHeight() + 3 >= this.getGame().getGC()
				.getHeight()) {
			this.lockedToPaddle = true;
			this.getGame().removeLife();
		}

		if (this.lockedToPaddle) {
			this.getBody().setTransform(
					new Vec2(this.getGame().getPaddle().getX()
							+ (this.getGame().getPaddle().getImageWidth() / 2),
							this.getGame().getPaddle().getY()
									- this.getImageWidth() / 2 + 3),
					this.getBody().getAngle());
			this.getBody().setAwake(false);

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				this.lockedToPaddle = false;
				this.getBody().setAwake(true);
				this.getBody().setLinearVelocity(new Vec2(0, 0));
				this.getBody()
						.applyLinearImpulse(
								new Vec2((this.getX() - (this.getGame().getGC()
										.getWidth() / 2)) * 4,
										(500 + (this.getGame()
												.getBricksBroken())) * -2),
								this.getBody().getPosition(), true);
			}
		}
	}

	@Override
	public void initPhysics() {

		super.initPhysics();

		// body definition
		BodyDef bd = new BodyDef();
		bd.position.set(this.getX(), this.getY());
		bd.type = BodyType.DYNAMIC;

		// define shape of the body.
		CircleShape cs = new CircleShape();
		cs.m_radius = (float) this.getImageWidth() / 2;

		// define fixture of the body.
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 1f;
		fd.friction = 0f;
		fd.restitution = 1f;

		// create the body and add fixture to it
		this.setBody(this.getGame().getWorld().createBody(bd));
		this.getBody().createFixture(fd);
		this.getBody().setUserData(this);
	}
}
