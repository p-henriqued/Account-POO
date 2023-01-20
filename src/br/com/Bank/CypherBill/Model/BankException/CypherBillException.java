package br.com.Bank.CypherBill.Model.BankException;

public class CypherBillException extends RuntimeException{

	/**
	 * This class is intended to handle exceptions for Cypher Bill Bank
	 */
	private static final long serialVersionUID = 1L;

	public CypherBillException(String message) {
		super("[ERROR]:  "+message);
	}

}
