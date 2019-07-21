package edu.dkravchuk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.dkravchuk.classes.ATM;
import edu.dkravchuk.classes.Account;
import edu.dkravchuk.classes.DataManager;

public class Application {
	private static ATM atm;
	private static DataManager dm = new DataManager();

	public static void main(String[] args) {
		atm = getATM();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your card number");
		String num = sc.next();

		System.out.println("Please enter pin-code for the card: " + num);
		
		int pin = sc.nextInt();
		System.out.println("PIN: " + pin);
		
		
		sc.close();

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
}
