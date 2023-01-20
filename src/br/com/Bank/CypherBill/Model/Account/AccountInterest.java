package br.com.Bank.CypherBill.Model.Account;

import java.math.BigDecimal;

public interface AccountInterest {
	
	public default BigDecimal interestOnTrasaction(BigDecimal amount, double interest) {
		interest /= 100;
		BigDecimal fees = new BigDecimal(interest);
		return amount.multiply(fees);
	}
	
	//public void yield(BigDecimal cash);
	
}
