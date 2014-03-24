package com.akpwebdesign.Breakout.entity.brick;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.akpwebdesign.Breakout.IGame;
import com.akpwebdesign.Breakout.entity.Entity;

public class Brick extends Entity {

	private BrickType type;
	private boolean isVisible = true;
	private boolean isReadyToDestroy = false;

	public Brick(BrickType type, IGame game) throws SlickException {
		super(type.getImage(), game);
		this.setScale(0.75f);
		this.initPhysics();
	}

	public BrickType getType() {
		return type;
	}

	public void setType(BrickType type) {
		this.type = type;
		this.setImage(type.getImage());
		this.setScale(0.75f);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public void update(Input input) {
		super.update(input);

		this.setAngle((float) Math.toDegrees(this.getBody().getAngle()));
		this.getBody()
				.setTransform(
						new Vec2(this.getX() + this.getImageWidth() / 2,
								this.getY() + this.getImageHeight() / 2),
						this.getBody().getAngle());

		if (this.isReadyToDestroy()) {
			this.destroy();
			this.getGame()
					.setBricksBroken(this.getGame().getBricksBroken() + 1);
		}

	}

	public void destroy() {
		if (this.getGame().getEntities().contains(this)) {
			this.getGame().getEntities().remove(this);
		}
		this.getBody().setUserData(null);
		// this.getGame().getWorld().destroyBody(this.getBody());
		this.getBody().setActive(false);
		this.setBody(null);
		this.setVisible(false);

		this.getGame()
				.getBall()
				.getBody()
				.setLinearVelocity(
						this.getGame().getBall().getBody().getLinearVelocity()
								.mulLocal((float) 1.001));

		this.getGame().addScore(10);
	}

	@Override
	public void initPhysics() {
		if (this.getGame().getWorld() != null) {
			super.initPhysics();

			// body definition
			BodyDef bd = new BodyDef();
			bd.position.set(this.getX(), this.getY());
			bd.type = BodyType.STATIC;

			// define shape of the body.
			PolygonShape ps = new PolygonShape();
			ps.setAsBox(getImageWidth() / 2, getImageHeight() / 2);

			// define fixture of the body.
			FixtureDef fd = new FixtureDef();
			fd.shape = ps;
			fd.density = 1f;
			fd.friction = 0f;
			fd.restitution = 1f;

			// create the body and add fixture to it
			this.setBody(this.getGame().getWorld().createBody(bd));
			this.getBody().createFixture(fd);
			this.getBody().setUserData(this);
		}
	}

	@Override
	public void draw() {
		if (this.isVisible()) {
			this.getImage().draw(this.getX(), this.getY());
		}
	}

	public boolean isReadyToDestroy() {
		return isReadyToDestroy;
	}

	public void setReadyToDestroy(boolean isReadyToDestroy) {
		this.isReadyToDestroy = isReadyToDestroy;
	}

}
