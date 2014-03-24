package com.akpwebdesign.Breakout.dll;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class DLL {
	public static void loadJarDlls(HashMap<String, InputStream> src)
			throws IOException {
		File dest = new File(System.getProperty("java.io.tmpdir") + "breakout");

		for (Map.Entry<String, InputStream> entry : src.entrySet()) {
			System.out.println("Copying '" + entry.getKey() + "' to '"
					+ dest.getAbsolutePath() + "'.");
			FileUtils.copyInputStreamToFile(entry.getValue(),
					new File(dest.getAbsolutePath(), entry.getKey()));
		}

		System.setProperty("org.lwjgl.librarypath", dest.getAbsolutePath());
	}

	public static HashMap<String, InputStream> getFileMap() {

		ArrayList<String> files = new ArrayList<String>();
		files.add("jinput-dx8.dll");
		files.add("jinput-dx8_64.dll");
		files.add("jinput-raw.dll");
		files.add("jinput-raw_64.dll");
		files.add("lwjgl.dll");
		files.add("lwjgl64.dll");
		files.add("OpenAL32.dll");
		files.add("OpenAL64.dll");

		HashMap<String, InputStream> map = new HashMap<String, InputStream>();

		for (String s : files) {
			map.put(s, DLL.class.getResourceAsStream("/lib/natives/" + s));
		}

		return map;
	}

	public static void deleteDLLDir() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				FileUtils.deleteQuietly(new File(System
						.getProperty("org.lwjgl.librarypath")));
			}
		});
	}
}
