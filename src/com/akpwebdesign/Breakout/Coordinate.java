package com.akpwebdesign.Breakout;

import java.io.Serializable;

import com.akpwebdesign.Breakout.entity.brick.BrickType;

public class Coordinate implements Serializable {
	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = 1388107473390825308L;

	private boolean isBrick;
	private float x;
	private float y;
	private BrickType brickType;

	public Coordinate(float x, float y) {
		this(x, y, true, BrickType.RED);
	}

	public Coordinate(float x, float y, boolean isBrick) {
		this(x, y, isBrick, BrickType.RED);
	}

	public Coordinate(float x, float y, boolean isBrick, BrickType brickType) {
		this.setBrick(isBrick);
		this.setX(x);
		this.setY(y);
		this.setBrickType(brickType);
	}

	public boolean isBrick() {
		return isBrick;
	}

	public void setBrick(boolean isBrick) {
		this.isBrick = isBrick;
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

	public BrickType getBrickType() {
		return brickType;
	}

	public void setBrickType(BrickType brickType) {
		this.brickType = brickType;
	}
}
