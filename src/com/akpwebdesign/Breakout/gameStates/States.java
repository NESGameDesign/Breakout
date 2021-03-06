package com.akpwebdesign.Breakout.gameStates;

public enum States {
	SPLASH(0),
	MAIN_MENU(1),
	GAME(2),
	PAUSE_MENU(3),
	LOSE_GAME(4),
	WIN_GAME(5),
	EDITOR(6),
	LEVEL_CHANGE(7);
	
	private int stateID;
	
	States(int stateID) {
		this.setStateID(stateID);
	}

	public int getStateID() {
		return stateID;
	}

	private void setStateID(int stateID) {
		this.stateID = stateID;
	}
}
