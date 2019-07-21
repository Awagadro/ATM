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
	private static final int DEPOSIT_LIMIT = 1000000;

	public static void main(String[] args) {
		atm = getATM();
		StringBuffer sb = new StringBuffer();
		Integer acIndex = null;
		Boolean isCorrectPin = false;

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
		int attemptCount = 1;
		do {
			if (attemptCount > 3) {
				System.out.println("You've reached the limit of available attempts to enter PIN");
				break;
			}
			sb.delete(0, sb.length());
			sb.append(getResponse());
			isCorrectPin = val.validatePin(sb.toString(), atm.getAccountList().get(acIndex));
			if (isCorrectPin == false) {
				System.out.println("Please enter a valid PIN code");
				attemptCount++;
			} else {
				break;
			}
		} while (isCorrectPin == false);

		if (isCorrectPin == true) {
			System.out.printf(
					"Welcome, your balance is: %s. Please choose operation: make a deposit (d), withdraw money (w), exit (any other key)",
					atm.getAccountList().get(acIndex).getAccountBalance());
			System.out.println();

			sb.delete(0, sb.length());
			sb.append(getResponse());
			if (sb.toString().equals("d")) {
				deposit(atm, acIndex);
			}
			if (sb.toString().equals("w")) {
				withdraw(atm, acIndex);
			} else {
				return;
			}
		}
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
		atm.setTotalAmmount(20000000);

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

	private static void deposit(ATM atm, Integer acIndex) {
		System.out.println("Please enter the transfer amount for deposit");
		StringBuffer sb = new StringBuffer();
		Integer depoAmmount = null;
		do {
			sb.delete(0, sb.length());
			sb.append(getResponse());
			depoAmmount = val.validateAmmount(sb.toString());
			if ((depoAmmount == null) || (depoAmmount > DEPOSIT_LIMIT)) {
				System.out.println("You should enter a summ under " + DEPOSIT_LIMIT);
			} else {
				atm.setTotalAmmount(atm.getTotalAmmount() + depoAmmount);
				int temp = atm.getAccountList().get(acIndex).getAccountBalance();
				atm.getAccountList().get(acIndex).setAccountBalance(temp + depoAmmount);
				System.out.println("Operation success. Now your balance is: "
						+ atm.getAccountList().get(acIndex).getAccountBalance());
				dm.printFile(atm);

				break;
			}
		} while ((depoAmmount == null) || (depoAmmount > DEPOSIT_LIMIT));

	}

	private static void withdraw(ATM atm, Integer acIndex) {
		System.out.println("Please enter the transfer amount for withdraw");
		StringBuffer sb = new StringBuffer();
		Integer wAmmount = null;
		final int withdrawLimit = Math.min(atm.getTotalAmmount(),
				atm.getAccountList().get(acIndex).getAccountBalance());
		do {
			sb.delete(0, sb.length());
			sb.append(getResponse());
			wAmmount = val.validateAmmount(sb.toString());
			if ((wAmmount == null) || (wAmmount > withdrawLimit)) {
				System.out.println("You should enter a summ under " + withdrawLimit);
			} else {
				atm.setTotalAmmount(atm.getTotalAmmount() - wAmmount);
				int temp = atm.getAccountList().get(acIndex).getAccountBalance();
				atm.getAccountList().get(acIndex).setAccountBalance(temp - wAmmount);
				System.out.println("Operation success. Now your balance is: "
						+ atm.getAccountList().get(acIndex).getAccountBalance());
				dm.printFile(atm);

				break;
			}
		} while ((wAmmount == null) || (wAmmount > withdrawLimit));

	}

}
