package com.stvv.agent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.HashSet;

public class HashsetUtil {
	private static HashSet<String> hashSet = new HashSet<String>();

	public static void startFile() {

	}

	public static void addLine(String line) {
		hashSet.add(line);
	}

	public static void printLines() {
		Iterator<String> iterator = hashSet.iterator();
		StringBuilder line = new StringBuilder();
		while (iterator.hasNext()) {
			line.append(iterator.next());
			if (iterator.hasNext()) {
				line.append("\n");
			}
		}
		writeToFile(line.toString());
		hashSet.clear();
	}

	public static void writeToFile(String line) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("stmt-cov.txt", true))) {
			bw.write(line + " \n");
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
