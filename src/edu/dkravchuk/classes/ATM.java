package edu.dkravchuk.classes;

import java.util.List;

public class ATM {
	private Integer totalAmmount;

	private List<Account> accountList;

	public Integer getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(int totalAmmount) {
		this.totalAmmount = totalAmmount;
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
