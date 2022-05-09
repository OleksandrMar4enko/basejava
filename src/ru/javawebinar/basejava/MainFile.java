package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
	public static void main(String[] args) {
		String filePath = ".\\.gitignore";

		File file = new File(filePath);
		try {
			System.out.println(file.getCanonicalPath());
		} catch (IOException e) {
			throw new RuntimeException("Error", e);
		}

		File dir = new File("./src/ru/javawebinar/basejava");
//		System.out.println(dir.isDirectory());
		String[] list = dir.list();
//		if (list != null) {
//			for (String name : list) {
//				System.out.println(name);
//			}
//		}
		printAllNamesOfFilesAndDirectories(dir);

		try (FileInputStream fis = new FileInputStream(filePath)) {
			System.out.println(fis.read());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	private static void printAllNamesOfFilesAndDirectories(File dir) {
		File[] files = dir.listFiles();
		for (File entry : files) {
			if (entry.isDirectory()) {
				System.out.println(entry.getName());
				printAllNamesOfFilesAndDirectories(entry);
				continue;
			} else {
				System.out.println(entry.getName());
			}
		}
	}
}
