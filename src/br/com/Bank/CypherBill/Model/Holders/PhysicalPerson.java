package br.com.Bank.CypherBill.Model.Holders;

import br.com.Bank.CypherBill.Model.BankException.CypherBillException;

public class PhysicalPerson extends Holder{
	private String cpf;

	public PhysicalPerson(String name, String cpf, String cep,String adress, String email, String numberCellphone) {
		super(name, cep, adress,email, numberCellphone);
		if(cpf.length() != 11) {
			throw new CypherBillException("Error in creating account: \"Incorrect Data: The CPF digit must have eleven digits.\".");
		}
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "\n__________________________________________\n\n"
				+"Client's DATA:"
				+"\nName: "+super.getName()
				+" - CPF: "+cpf
				+"\nAdress: "+ super.getAdress()
				+" - CEP: "+super.getCep()
				+"\nContact:\n"
				+"Email: "+super.getEmail()
				+" - Phone Number: "+super.getNumberCellphone()
				+"\n__________________________________________\n";
	}

	@Override
	public String dataHolder() {
		return getCpf();
	}
	
}
