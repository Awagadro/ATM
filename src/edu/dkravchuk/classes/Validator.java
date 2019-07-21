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

	public void validatePin(String id, int pin, ATM atm) {
	}

}
