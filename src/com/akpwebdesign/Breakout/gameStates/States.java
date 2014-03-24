package com.akpwebdesign.Breakout.gameStates;

public enum States {
	SPLASH(0),
	MAIN_MENU(1),
	GAME(2),
	EDITOR(3);
	
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
