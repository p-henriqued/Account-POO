package br.com.Bank.CypherBill.Model.Holders;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;

public abstract class Holder {
	private String name;
	private String cep;
	private String adress;
	private String email;
	private String numberCellphone;
	
	protected Holder() { }
	public Holder(String name, String cep, String adress, String email, String numberCellphone) {
		if(numberCellphone.length() != 11 || cep.length() != 8) {
			throw new CypherBillException("Error in creating account: \"Incorrect Data.\".");
		}
		this.name = name;
		this.cep = cep;
		this.adress = adress;
		this.email = email;
		this.numberCellphone = numberCellphone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name == getName()) {
			throw new IllegalArgumentException("[ERROR]: Impossible to make the change.");
		}
		this.name = name;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		if(cep == getCep()) {
			throw new IllegalArgumentException("[ERROR]: Impossible to make the change.");
		}
		this.cep = adress;
	}
	public String getAdress() {
		return this.adress;
	}
	public void setAdress(String adress) {
		if(adress == getAdress()) {
			throw new IllegalArgumentException("[ERROR]: Impossible to make the change.");
		}
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email == getEmail()) {
			throw new IllegalArgumentException("[ERROR]: Impossible to make the change.");
		}
		this.email = email;
	}
	public String getNumberCellphone() {
		return numberCellphone;
	}
	public void setNumberCellphone(String numberCellphone) {
		if(numberCellphone == getNumberCellphone()) {
			throw new IllegalArgumentException("[ERROR]: Impossible to make the change.");
		}
		this.numberCellphone = numberCellphone;
	}
	
	@Override
	public abstract String toString();
	public abstract String dataHolder();
	
	
	

}
