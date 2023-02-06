package br.com.Bank.CypherBill.Model.Account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;
import br.com.Bank.CypherBill.Model.Holders.Holder;
import br.com.Bank.CypherBill.Model.Holders.PhysicalPerson;

public abstract class Account implements AccountInterest{
	private Holder holder;
	private int agency;
	private int numberAccount;
	private BigDecimal cash = new BigDecimal(0.0);
	
	private String pacth = "S:\\Projects\\Eclipse-projects\\AccountProject\\src\\br\\com\\Bank\\CypherBill\\Model\\Account\\extract.txt";
	
	File source = new File(pacth);
	String sourceFolder = source.getParent();
	boolean checked = new File(sourceFolder + "\\HolderExtract").mkdir();
	private String targetFile;
	
	public Account(Holder holder, int agency, int numberAccount) {
		
		targetFile = sourceFolder + "\\HolderExtract\\"+holder.getName()+".txt";
		
		String agencyCharacters = Integer.toString(agency);
		String numberAccountCharacters = Integer.toString(numberAccount);
		if(holder == null || agencyCharacters.length() != 4 || numberAccountCharacters.length() != 8) {
			throw new CypherBillException("error creating account: \"[Incorrect Data.]\"");
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
			if(holder instanceof PhysicalPerson) {
				bw.write("Name: "+holder.getName()+" - CPF: "+holder.dataHolder()+"\n");
				bw.write("Agency: "+ agency+" - Number Account : "+ numberAccount+"\n\n");
			}else {
				bw.write("Institution Name: "+holder.getName()+" - CNPJ: "+holder.dataHolder()+"\n");
				bw.write("Agency: "+ agency+" - Number Account : "+ numberAccount+"\n\n");
			}
		}catch(Exception e) {
			throw new CypherBillException("[ERROR] error creating account");
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
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile,true))) {
			bw.write(message);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readExtract() {
		try(BufferedReader br = new BufferedReader(new FileReader(targetFile))) {
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
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		if(getHolder() instanceof PhysicalPerson) {
			return "\nAgency: "+ getAgency()
			+" - Number Account : "+ getNumberAccount()
			+"\nName: "+getHolder().getName()
			+" - CPF: "+getHolder().dataHolder()
			+"\n__________________________________________\n";
		}else {
			return "\nAgency: "+ getAgency()
			+" - Number Account : "+ getNumberAccount()
			+"\nName: "+getHolder().getName()
			+" - CNPJ: "+getHolder().dataHolder()
			+"\n__________________________________________\n";
		}
		
	}
	
}
