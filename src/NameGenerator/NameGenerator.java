package NameGenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NameGenerator {

	public static ObjectOutputStream outputStream = null;
	public static ObjectInputStream inputStream;

	public static boolean addNickname = true;
	public static boolean addSurname = false;

	public static String namesFile = "src/names.txt";
	public static String nicknameFile = "src/adjectives.txt";
	public static String outputFile = "output.txt";

	public static void main(String[] args) {
		sort(nicknameFile);
		generateNames(outputFile);
	}

	public static void sort(String filename) {
		int i = 0;
		ArrayList<String> adjectives = new ArrayList<String>();
		FileReader file2;
		try {
			file2 = new FileReader(filename);
			BufferedReader buffer2 = new BufferedReader(file2);
			String line = "";
			i = 0;
			while ((line = buffer2.readLine()) != null) {
				adjectives.add(line);
				i++;
			}
			buffer2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(adjectives, String.CASE_INSENSITIVE_ORDER);
		try {
			FileWriter file = new FileWriter(filename);
			BufferedWriter buffer = new BufferedWriter(file);
			for (int v = 0; v < adjectives.size(); v++) {
				buffer.write(adjectives.get(v));
				buffer.newLine();
			}
			buffer.close();
		} catch (IOException e) {
			System.out.println("A write error has occurred.");
		}
	}

	public static void generateNames(String name) {
		inputStream = null;
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> adjectives = new ArrayList<String>();
		ArrayList<String> output = new ArrayList<String>();
		int i = 0;
		try {
			FileReader file = new FileReader(namesFile);
			BufferedReader buffer = new BufferedReader(file);
			String line = "";
			while ((line = buffer.readLine()) != null) {
				names.add(line);
				i++;
			}
			buffer.close();
			FileReader file2 = new FileReader(nicknameFile);
			BufferedReader buffer2 = new BufferedReader(file2);
			line = "";
			i = 0;
			while ((line = buffer2.readLine()) != null) {
				adjectives.add(line);
				i++;
			}
			buffer2.close();
		} catch (IOException e) {
			System.out.println("A read errror has occurred.");
		}
		for (int a = 0; a < names.size(); a++) {
			char c = Character.valueOf(names.get(a).charAt(0));
			for (int b = 0; b < adjectives.size(); b++) {
				char y = Character.valueOf(adjectives.get(b).charAt(0));
				if (y == c) {
					String f = "";
					if (addNickname) {
						f = "\"";
						f = f + adjectives.get(b);
						f = f + "\" ";
					}
					f = f + names.get(a);
					output.add(f);
				}
			}

		}
		System.out.println(output.size() + " names");
		try {
			FileWriter file = new FileWriter(name);
			BufferedWriter buffer = new BufferedWriter(file);
			for (int v = 0; v < output.size(); v++) {
				buffer.write(output.get(v));
				buffer.newLine();
			} // close the BufferedReader object when it is no longer needed
			buffer.close();
		} catch (IOException e) {
			System.out.println("A write error has occurred.");
		}
	}

	public static String[] generateNames(int numberOfNames) {
		// generate a random list of names
		if (!new File(outputFile).exists()) {
			generateNames(outputFile);
		}
		String[] output = new String[numberOfNames];
		Random r = new Random();
		//load possible names
		ArrayList<String> names = new ArrayList<String>();
		try {
			FileReader file = new FileReader(namesFile);
			BufferedReader buffer = new BufferedReader(file);
			String line = "";
			while ((line = buffer.readLine()) != null) {
				names.add(line);
			}
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//get random names
		for (int i = 0; i < output.length; i++) {
			output[i]=names.get(r.nextInt(names.size()));
		}
		return output;
	}

}
