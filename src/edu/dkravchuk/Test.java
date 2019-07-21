package edu.dkravchuk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.dkravchuk.classes.ATM;
import edu.dkravchuk.classes.Account;

public class Test {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		ATM atm = new ATM();
		{

			try {
				File file = new File("data.txt");
				String st;
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((st = br.readLine()) != null)
					sb.append(st);
				System.out.println(sb);
				br.close();

				String[] tokens = sb.toString().split(" ");
//				for (String t : tokens) {
//					System.out.println(t);
//				}
//				System.out.println((tokens.length - 1) / 3);

				parseDataFromFile(atm, tokens);
				System.out.println(atm);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private static void parseDataFromFile(ATM atm, String[] tokens) {
		ATM.setTotalAmmount(Integer.parseInt((tokens[0])));
		List<Account> accountList = new ArrayList<Account>();
		for (int i = 1; i < tokens.length; i = i + 3) {
			Account ac = new Account();
			ac.setCardId(tokens[i]);
			ac.setPassword(Integer.parseInt((tokens[i + 1])));
			ac.setAccountBalance(Integer.parseInt((tokens[i + 2])));
			accountList.add(ac);
		}
		atm.setAccountList(accountList);
	}

}
