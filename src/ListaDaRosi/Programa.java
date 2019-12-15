package ListaDaRosi;

import javax.swing.JOptionPane;

public class Programa {

	public static void main(String[] args) {
		/*QUESTAO 7
		int x = 0;
		System.out.println(x);
		
		
		Conta c = null;
		System.out.println(c);
		c.alteraSaldo(20);
		System.out.println(c.getSaldo());
		 */
	
		
		
		/*QUESTAO 5
		 
		
		Conta minhaConta = new Conta();
		Cliente c = new Cliente("Antonio", "Santana", "068.310.273-77");
		
		minhaConta.alteraCliente(c);
		c = null;
		minhaConta.alteraCliente(new Cliente("Maria", "Lopes", "48846659269"));
		*/
		
		/*
		QUESTAO 4 
		 
		 
		Conta c1 = new Conta();
		c1.alteraSaldo(100);
		Conta c2;
		c2 = c1;
				
		if(c1==c2) {
			System.out.println("Contas iguais! ");
		} else {
			System.out.println("Contas diferentes! ");
		}
		*/
		
		/*
		QUESTAO 3 
		
		Conta minhaConta = new Conta();
		minhaConta.alteraSaldo(1000);
		JOptionPane.showMessageDialog(null, "MINHA CONTA: \n");
		JOptionPane.showMessageDialog(null, minhaConta.getSaldo());
		
		
		Conta meuSonho = new Conta();
		meuSonho.alteraSaldo(1500000);
		JOptionPane.showMessageDialog(null, "MEU SONHO: \n");
		JOptionPane.showMessageDialog(null, meuSonho.getSaldo());
		
		Conta minhaRealidade = minhaConta;
		JOptionPane.showMessageDialog(null, "MINHA REALIDADE: \n");
		JOptionPane.showMessageDialog(null, minhaRealidade.getSaldo());
		
		meuSonho = new Conta();
		JOptionPane.showMessageDialog(null, "MEU SONHO: \n");
		JOptionPane.showMessageDialog(null, meuSonho.getSaldo());
		*/
		
		/*
		QUESTAO 2
		
		int op;
		
		do {
		op = Integer.parseInt(JOptionPane.showInputDialog("MENU: \n1 - Alterar saldo\n2 - Verifcar saldo\n0 - Sair")); 
		switch(op){
			case 1: 
				conta.alteraSaldo(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo saldo: ")));
				break;
			case 2:
				JOptionPane.showMessageDialog(null, conta.getSaldo());
				break;
			case 0:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opcao invalida!\n");
				break;
		}
		}while(op!=0); */
	}
}
