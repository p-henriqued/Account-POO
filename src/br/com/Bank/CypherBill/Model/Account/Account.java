package br.com.Bank.CypherBill.Model.Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;
import br.com.Bank.CypherBill.Model.Holders.Holder;

public abstract class Account implements AccountInterest{
	private Holder holder;
	private int agency;
	private int numberAccount;
	private BigDecimal cash = new BigDecimal(0.0);
	
	private String pacth = "AccountProject\\src\\br\\com\\Bank\\CypherBill\\Model\\Account\\extract.txt";
	
	public Account(Holder holder, int agency, int numberAccount) {
		String agencyCharacters = Integer.toString(agency);
		String numberAccountCharacters = Integer.toString(numberAccount);
		if(holder == null || agencyCharacters.length() != 4 || numberAccountCharacters.length() != 8) {
			throw new CypherBillException("error creating account: \"[Incorrect Data.]\"");
		}
		this.holder = holder;
		this.agency = agency;
		this.numberAccount = numberAccount;
	}
	
	public Holder getHolder() {
		return holder;
	}
	public int getAgency() {
		return agency;
	}
	public void setAgency(int agency) {
		this.agency = agency;
	}
	public int getNumberAccount() {
		return numberAccount;
	}
	public BigDecimal getCash() {
		return cash;
	}
	protected void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public abstract void setKeyAccount(String keyAccount);
	public abstract void deposit(BigDecimal amount,String key);
	public abstract void withdraw(BigDecimal amount,String key);
	public abstract void transfer(Account account, BigDecimal amount,String key);

	
	protected void verifyPassword(String key, String keyAccount) {
		if(!key.equals(keyAccount)) {
			throw new CypherBillException("Incorrect password.");
		}
	}
	
	protected void writeExtract(String message) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(pacth,true))) {
			bw.write(message);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readExtract() {
		try(BufferedReader br = new BufferedReader(new FileReader(pacth))) {
			String reader = br.readLine();
			while(reader != null) {
				System.out.println(reader);
				reader = br.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cleanExtract() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(pacth))) {
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "\nAgency: "+ getAgency()
				+" - Number Account : "+ getNumberAccount()
				+"\nName: "+getHolder().getName()
				+" - CPF: "+getHolder().dataHolder()
				+"\n__________________________________________\n";
	}
	
}
