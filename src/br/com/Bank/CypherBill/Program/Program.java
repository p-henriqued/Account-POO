package br.com.Bank.CypherBill.Program;

import java.math.BigDecimal;

import br.com.Bank.CypherBill.Model.Account.Account;
import br.com.Bank.CypherBill.Model.Account.CurrentAccount;
import br.com.Bank.CypherBill.Model.Account.SavingsAccount;
import br.com.Bank.CypherBill.Model.Holders.*;

public class Program {

	public static void main(String[] args) {
		
		//PhysicalPerson(String name, String cpf, String cep,String adress, String email, String numberCellphone)
		Holder h1 = new PhysicalPerson("Jonas Feliz", "12345678901", "08420370", "Rua turusbango, 21", "jonas@gmail.com", "11975724760");
		System.out.println(h1);

		Holder h2 = new LegalPerson("Mercadin do Japa", "12345678901234", "08420370", "Rua turusbango, 989", "mercadojapa@MercadoJapa.com", "11983458209");
		System.out.println(h2);
		
		
//		 BigDecimal amountDeposit = new BigDecimal(1000.00);
//	     BigDecimal amountWithdraw = new BigDecimal(100);
//	     BigDecimal amount = new BigDecimal(100);
	     
	     //Holder holder, int agency, int numberAccount, String keyAccount)
	     Account savingsC = new CurrentAccount(h1,1234, 13980872,"123456");
//	     savingsC.cleanExtract();
	     Account mercadinC = new SavingsAccount(h2,1234, 98620152,"4321");
	     
	     System.out.println(mercadinC);
	     System.out.println(savingsC);
	     
//	     savingsC.deposit(amountDeposit, "123456");
//	     mercadinC.deposit(amountDeposit, "4321");
//	     
//	     savingsC.withdraw(amountWithdraw, "123456");//1
//	     savingsC.withdraw(amountWithdraw, "123456");//2
//	     savingsC.withdraw(amountWithdraw, "123456");//3
//	     savingsC.withdraw(amountWithdraw, "123456");//4
//	     System.out.println(String.format("%.2f", savingsC.getCash()));
//	     savingsC.withdraw(amountWithdraw, "123456");//5
//	     savingsC.withdraw(amountWithdraw, "123456");//6
	     //savingsC.transfer(mercadinC, amount, "123456");
	    
	     savingsC.readExtract();
	     mercadinC.readExtract();
	     
	}

}
