package com.akpwebdesign.Breakout.entity.brick;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public enum BrickType {
	RED("res/red_brick.png"), BLUE("res/blue_brick.png"), GREEN(
			"res/green_brick.png"), ORANGE("res/orange_brick.png"), PURPLE(
			"res/purple_brick.png");

	private Image image;

	BrickType(String image) {
		try {
			this.setImage(new Image(image));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	private static final List<BrickType> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static BrickType randomType() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

	public BrickType next() {
		int index = (this.ordinal() + 1) % BrickType.values().length;
		return BrickType.values()[index];
	}
}
