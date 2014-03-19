package com.akpwebdesign.Breakout;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.akpwebdesign.Breakout.dll.DLL;

public class Main {

	public static void main(String[] args) {
		try {
			DLL.loadJarDlls(DLL.getFileMap());
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Game("Breakout"));
			appgc.setDisplayMode(879, 600, false);
			appgc.setShowFPS(false);
			appgc.setTargetFrameRate(120);
			appgc.setShowFPS(true);
			appgc.setMouseGrabbed(true);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void exit() {
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
		}
		DLL.deleteDLLDir();
	}
}
