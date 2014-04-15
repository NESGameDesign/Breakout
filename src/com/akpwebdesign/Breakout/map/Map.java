package com.akpwebdesign.Breakout.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.akpwebdesign.Breakout.Coordinate;

public class Map implements Serializable {

	/**
	 * Serial Version.
	 */
	private static final long serialVersionUID = -7541355888374577208L;

	private ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

	public Map(Map map) {
		this.setCoordinates(map.getCoordinates());
	}

	public Map(ArrayList<Coordinate> coordinates) {
		this.setCoordinates(coordinates);
	}

	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public static Map loadMap(String url) {
		try {
			FileInputStream fileIn = new FileInputStream(url);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Map m = (Map) in.readObject();
			in.close();
			fileIn.close();
			return m;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map loadMap(InputStream is) {
		try {
			ObjectInputStream in = new ObjectInputStream(is);
			Map m = (Map) in.readObject();
			in.close();
			is.close();
			return m;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveMap(Map map, String url) {
		try {
			FileOutputStream fileOut = new FileOutputStream(url);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}
