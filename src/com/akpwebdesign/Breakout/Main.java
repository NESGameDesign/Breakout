package com.akpwebdesign.Breakout;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Breakout"));
			appgc.setDisplayMode(800, 600, false);
			appgc.setShowFPS(false);
			appgc.setTargetFrameRate(120);
			appgc.setShowFPS(true);
			appgc.setMouseGrabbed(true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
