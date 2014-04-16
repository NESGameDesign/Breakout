package com.akpwebdesign.Breakout;

public enum GameLevel {
	LVL1("lvl1.map"),
	LVL2("lvl2.map"),
	LVL3("lvl3.map"),
	LVL4("lvl4.map"),
	LVL5("lvl5.map"),
	LVL6("lvl6.map"),
	LVL7("lvl7.map"),
	LVL8("lvl8.map"),
	LVL9("lvl9.map"),
	LVL10("lvl10.map"),
	CAZIF("cazif.map");
	
	private String levelName;
	
	GameLevel(String levelName) {
		this.setLevelName(levelName);
	}

	public String getLevelName() {
		return levelName;
	}

	private void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
