package edu.dkravchuk.classes;

public class Validator {

	public Integer validateCardId(String id, ATM atm) {
		Integer accountIndex = null;
		for (Account ac : atm.getAccountList()) {
			if (ac.getCardId().equals(id)) {
				System.out.println("Card number validation accomplished");
				accountIndex = atm.getAccountList().indexOf(ac);
				break;
			}
		}
		return accountIndex;

	}

	public boolean validatePin(String pinCode, Account a) {
		int pin = Integer.parseInt(pinCode);
		if (pin == a.getPassword()) {
			System.out.println("PIN correct");
		} else {
			return false;
		}
		return true;
	}

	public Integer validateAmmount(String s) {
		Integer ammount = null;

		try {
			ammount = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return null;
		}

		return ammount;
	}

}
