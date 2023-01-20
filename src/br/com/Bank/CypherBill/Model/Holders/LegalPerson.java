package br.com.Bank.CypherBill.Model.Holders;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;

public class LegalPerson extends Holder{
	private String cnpj;

	public LegalPerson(String name, String cnpj, String cep, String adress, String email, String numberCellphone) {
		super(name, cep, adress, email, numberCellphone);
		if(cnpj.length() != 14) {
			throw new CypherBillException("Error in creating account: \"Incorrect Data: The CNPJ digit must have fourteen digits.\".");
		}
		this.cnpj = cnpj;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return  "\n__________________________________________\n\n"
				+"Client's DATA:"
				+"\nName: "+super.getName()
				+" - CNPJ: "+cnpj
				+"\nAdress: "+ super.getAdress()
				+" - CEP: "+super.getCep()
				+"\nContact:\n"
				+"Email: "+super.getEmail()
				+" - Phone Number: "+super.getNumberCellphone()
				+"\n__________________________________________\n";
	}
	@Override
	public String dataHolder() {
		return getCnpj();
	}
}
