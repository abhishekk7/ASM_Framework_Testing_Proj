package com.stvv.agent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class HashsetUtil {
	private static HashSet<String> hashSet = new HashSet<String>();
	private static Map<String, HashSet<String>> map = new HashMap<>();
	private static String className;

	public static void startClass(String text) {
		className = text;
	}

	public static void addLine(String line) {
		hashSet.add(line);
	}

	public static void printLines() {
		StringBuilder line = new StringBuilder();
		String coverage;
		for (String key : map.keySet()) {
			line.append(key + "\n");
			coverage = map.get(key).toString().replace('[', ' ').replace(']', ' ').replace(',', ' ');
			line.append(coverage);
		}
		writeToFile(line.toString());
	}

	public static void writeToFile(String line) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("stmt-cov.txt", true))) {
			bw.write(line + " \n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToMap() {
		map.put(className, hashSet);
		hashSet = new HashSet<String>();
	}
}
