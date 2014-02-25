package com.akpwebdesign.Breakout.entity;

import org.jbox2d.dynamics.Body;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.akpwebdesign.Breakout.Game;

public class Entity {
	private Image image;
	private Image scaledImage;
	private Game game;
	private float x = 0f;
	private float y = 0f;
	private float scale = 1f;
	private Body body;

	public Entity(Image image, Game game) {
		this.image = image;
		this.scaledImage = image.getScaledCopy(scale);
		this.setGame(game);
	}

	public Image getImage() {
		return scaledImage;
	}

	public void setImage(Image image) {
		this.image = image;
		this.scaledImage = image.getScaledCopy(scale);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
		this.setImage(this.image.getScaledCopy(scale));
		this.initPhysics();
	}

	public float getImageWidth() {
		return this.getImage().getWidth();
	}

	public float getImageHeight() {
		return this.getImage().getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public void draw() {
		this.scaledImage.draw(this.getX(), this.getY());
	}

	public void update(Input input) {
		input.poll(this.getGame().getGC().getWidth(), this.getGame().getGC().getHeight());
	}

	public void setAngle(float angle) {
		this.getImage().setRotation(angle);		
	}
	
	public void initPhysics() {
		if(this.getBody() != null)
		{
			this.getGame().getWorld().destroyBody(this.getBody());
		}
	}
}
