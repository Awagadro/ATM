package edu.dkravchuk.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataManager {

	public void printFile(ATM atm) {
		try {
			File file = new File("data.txt");
			// If dataFile doesn't exist, create one
			if (!file.exists())
				file.createNewFile();

			String data = atm.toString();
			FileWriter fw;
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Initial data saved");
	}

	public ATM readFile() {
		ATM atm = new ATM();
		StringBuffer sb = new StringBuffer();

		try {
			File file = new File("data.txt");

			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				sb.append(st);
			}
//			System.out.println(sb);
			br.close();
		} catch (IOException e) {
			System.out.println("Data file is missing!");
			
			return null;
		}

		System.out.println("Data file uploaded");
		return atm;

	}

}
