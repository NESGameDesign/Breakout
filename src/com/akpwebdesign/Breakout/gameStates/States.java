package com.akpwebdesign.Breakout.gameStates;

public enum States {
	SPLASH(0),
	MAIN_MENU(1),
	GAME(2),
	PAUSE_MENU(3),
	EDITOR(4);
	
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
