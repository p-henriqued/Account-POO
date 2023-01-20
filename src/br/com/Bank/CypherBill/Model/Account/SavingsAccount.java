package br.com.Bank.CypherBill.Model.Account;

import java.math.BigDecimal;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;
import br.com.Bank.CypherBill.Model.Holders.Holder;
import br.com.Bank.CypherBill.Model.Holders.PhysicalPerson;

public class SavingsAccount extends Account{
	private String keyAccount;
	private int quantityOfWithdraw = 0;
	private int quantityOfDeposit = 0;

	public SavingsAccount(Holder holder, int agency, int numberAccount, String keyAccount) {
		super(holder, agency, numberAccount);
		if(keyAccount.length() != 4) {
			throw new CypherBillException("Passaword must be four digits long.");
		}
		this.keyAccount = keyAccount;
	}
	
	public String getKeyAccount() {
		return keyAccount;
	}
	
	@Override
	public void setKeyAccount(String keyAccount) {
		if(keyAccount == getKeyAccount() || keyAccount.length() != 4) {
			throw new CypherBillException("The new passaword cannot be the same as the previous one.");
		}
		this.keyAccount = keyAccount;
	}

	@Override
	public void deposit(BigDecimal amount, String key) {
		verifyPassword(key, getKeyAccount());
		if(amount.compareTo(BigDecimal.ZERO) != 1) {
			throw new CypherBillException("There isn't deposit less than $0.1");
		}
		setCash(getCash().add(amount));
		writeExtract("Deposit made from $"+String.format("%.2f", amount));
	}

	@Override
	public void withdraw(BigDecimal amount,String key) {
		verifyPassword(key, getKeyAccount());
		if(amount.compareTo(getCash()) == 1) {
			throw new CypherBillException("Not enough money for withdrawal.");
		}
		setCash(getCash().subtract(amount));
		writeExtract("Withdrawal made from $"+String.format("%.2f", amount));
	}

	@Override
	public void transfer(Account account, BigDecimal amount,String key) {
		verifyPassword(key, getKeyAccount());
		if(account == null || amount.compareTo(getCash()) == 1) {
			throw new CypherBillException("Transfer Failed: Incorrect Data.");
		}
		setCash(getCash().subtract(amount));
		account.setCash(getCash().add(amount));
		super.writeExtract("Transfer made to "+account.getHolder().getName()+" - Account: "+account.getAgency()+"-"+account.getNumberAccount()+" - from $"+String.format("%.2f", amount));
		System.out.println("Transfer made successfully to "+account.getHolder().getName()+" - Account: "+account.getAgency()+"-"+account.getNumberAccount() 
							+ "\nCurrent Balance: $"+String.format("%.2f", getCash())+"\n");
	}

	@Override
	public String toString() {
		if(getHolder() instanceof PhysicalPerson) {
			return "\n__________________________________________\n\n"
					+"Digital savings account Physical Person successfully created!"
					+super.toString();
		}else {
			return "\n__________________________________________\n\n"
					+"Digital Savings account Legal Peerson successfully created!"
					+super.toString();
		}
	}

}
