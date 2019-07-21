package edu.dkravchuk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.dkravchuk.classes.ATM;
import edu.dkravchuk.classes.Account;
import edu.dkravchuk.classes.DataManager;
import edu.dkravchuk.classes.Validator;

public class Application {
	private static ATM atm;
	private static DataManager dm = new DataManager();
	private static Validator val = new Validator();

	public static void main(String[] args) {
		atm = getATM();
		StringBuffer sb = new StringBuffer();
		Integer acIndex = null;
		
		
		System.out.println("Please enter your card number");
		do {
			sb.delete(0, sb.length());
			sb.append(getResponse());
			acIndex = val.validateCardId(sb.toString(), atm);
			if (acIndex == null) {
				System.out.println("Please enter a valid card number");
			} else {
				break;
			}
		} while (acIndex == null);

		System.out.println("Please enter pin-code for the card: " + atm.getAccountList().get(acIndex).getCardId());

//		resp = getResponse();
//		System.out.println("PIN: " + resp);

	}

	private static ATM getATM() {
		atm = dm.readFile();
		if (atm == null) {
			atm = setInitialData();
		}
		return atm;
	}

	private static ATM setInitialData() {
		ATM atm = new ATM();
		ATM.setTotalAmmount(20000000);

		List<Account> accountList = new ArrayList<>();
		// create account1
		Account account1 = new Account();
		account1.setCardId("1111-1111-1111-1111");
		account1.setPassword(1111);
		account1.setAccountBalance(10000);
		accountList.add(account1);

		// create account2
		Account account2 = new Account();
		account2.setCardId("2222-2222-2222-2222");
		account2.setPassword(2222);
		account2.setAccountBalance(20000);
		accountList.add(account2);

		// create account3
		Account account3 = new Account();
		account3.setCardId("3333-3333-3333-3333");
		account3.setPassword(3333);
		account3.setAccountBalance(30000);
		accountList.add(account3);

		// create account4
		Account account4 = new Account();
		account4.setCardId("4444-4444-4444-4444");
		account4.setPassword(4444);
		account4.setAccountBalance(40000);
		accountList.add(account4);

		atm.setAccountList(accountList);

		dm.printFile(atm);
		return atm;
	}

	private static String getResponse() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		try {
			s = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
}
