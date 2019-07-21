package edu.dkravchuk.classes;

import java.util.List;

public class ATM {
	private static int totalAmmount;
	private static int availableAmmount;
	private static final int DEPOSIT_LIMIT = 1000000;
	private List<Account> accountList;

	public static int getTotalAmmount() {
		return totalAmmount;
	}

	public static void setTotalAmmount(int totalAmmount) {
		ATM.totalAmmount = totalAmmount;
	}

	public static int getAvailableAmmount() {
		return availableAmmount;
	}

	public static void setAvailableAmmount(int availableAmmount) {
		ATM.availableAmmount = availableAmmount;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return totalAmmount + printAccounts();
	}

	private String printAccounts() {
		StringBuilder sb = new StringBuilder();
		for (Account account : accountList) {
			sb.append(account.toString());
		}
		String accountList = sb.toString();

		return accountList;

	}
}
