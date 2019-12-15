package main;

import java.util.Scanner;

import professores.Estatuario;

public class Questao4 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		final int MAX = 2;
		Estatuario[] est = new Estatuario[MAX];
		String matricula, nome, endereco;
		int cargaHoraria;
		double valorDaHora;
		float trienio, rioprev;
		
		for(int i=0; i<MAX; i++) {
			System.out.print("Digite a matricula: ");
			matricula = sc.nextLine();
			
			System.out.print("Digite o nome: ");
			nome = sc.nextLine();
			System.out.print("Digite o endereco: ");
			endereco = sc.nextLine();
			System.out.print("Digite a carga horaria: ");
			cargaHoraria = sc.nextInt();
			sc.nextLine();
			System.out.print("Digite o valor da hora: ");
			valorDaHora = sc.nextDouble();
			System.out.print("Digite o valor do trienio: ");
			trienio = sc.nextFloat();
			System.out.print("Digite o valor da previdencia: ");
			rioprev = sc.nextFloat();
			sc.nextLine();
			est[i] = new Estatuario(matricula, nome, cargaHoraria, valorDaHora, trienio, rioprev);
		}
		
		for(int i=0; i<MAX; i++) {
			System.out.println("Estatuario "+(i+1)+":"+ est[i]);
		}
	}
}