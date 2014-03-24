package com.akpwebdesign.Breakout.physics;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import com.akpwebdesign.Breakout.entity.Ball;
import com.akpwebdesign.Breakout.entity.brick.Brick;

public class GameCollisionListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		if (contact.getFixtureA().getBody().getUserData() instanceof Ball
				&& contact.getFixtureB().getBody().getUserData() instanceof Brick) {
			Brick brick = (Brick) contact.getFixtureB().getBody().getUserData();
			brick.setReadyToDestroy(true);
		}

		if (contact.getFixtureB().getBody().getUserData() instanceof Ball
				&& contact.getFixtureA().getBody().getUserData() instanceof Brick) {
			Brick brick = (Brick) contact.getFixtureA().getBody().getUserData();
			brick.setReadyToDestroy(true);
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
