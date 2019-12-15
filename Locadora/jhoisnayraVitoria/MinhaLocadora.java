package jhoisnayraVitoria;

import java.util.ArrayList;
import java.util.Date;

public class MinhaLocadora extends Locadora {
	ArrayList<Aluguel> registro = new ArrayList<Aluguel>();
	
	@Override
	public ArrayList<Veiculo> pesquisarMoto(int cilindrada){
		ArrayList<Veiculo> motos = new ArrayList<Veiculo>();
		for(Veiculo v: veiculos) {
			if(v instanceof Moto) {
				if(((Moto)v).getCilindrada()>= cilindrada) {
					motos.add(v);
				}
			}
		}
		return motos;
	}
	@Override
	public ArrayList<Veiculo> pesquisarOnibus(int quantPessoas){
		ArrayList<Veiculo> onibus = new ArrayList<Veiculo>();
		for(Veiculo v: veiculos) {
			if(v instanceof Onibus) {
				if(((Onibus)v).getQuantPessoas() >= quantPessoas) {
					onibus.add(v);
				}
			}
		}
		return onibus;
	}
	
	
	@Override
	public boolean inserir(Veiculo v) {
		Veiculo veiculo = pesquisar(v.getPlaca());
		//Mudei aki
		if(veiculo == null) { 
			veiculos.add(v);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
		ArrayList<Veiculo> carros = new ArrayList<Veiculo>();
		for(Veiculo v: veiculos) {
			if(v instanceof Carro) {
				if(((Carro)v).getTipo() == tipoCarro) {
					carros.add(v);
				}
			}
		}
		return carros;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCaminhao(int carga) {
		ArrayList<Veiculo> caminhoes = new ArrayList<Veiculo>();
		for(Veiculo v: veiculos) {
			if(v instanceof Caminhao) {
				if(((Caminhao)v).getCapacidade() >= carga) {
					caminhoes.add(v);
				}
			}
		}
		return caminhoes;
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		Veiculo v = pesquisar(placa);		
		if(pesquisar(v.getPlaca()) != null) {
			return v.aluguel(dias);
		}
		return -1;
	}

	public Aluguel pesquisarAluguel(String placa) {
		for(Aluguel a: alugueis) {
			if(a.getVeiculo().getPlaca().equals(placa)) {
				return a;
			}
		}
		return null;
	}
	
	@Override
	public boolean registrarAluguel(String placa, Date data, int dias, int cpf) {
		Veiculo v = pesquisar(placa);
		
		if(v != null) {
			if(pesquisarAluguel(placa)!=null || pesquisarCliente(cpf)== null) {
				return false;
			}else{
				Aluguel a = new Aluguel();
				a.setCpf(cpf);
				a.setDias(dias);
				a.setInicio(data);
				a.setVeiculo(v);
				a.setValor(v.aluguel(dias));
				v.aluguel(dias);
				alugueis.add(a);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean registrarDevolucao(String placa) {
		Aluguel a = pesquisarAluguel(placa);
		
		if(a!=null) {
			alugueis.remove(a);
			registro.add(a);
			return true;
		}

		return false;
	}

	@Override
	public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
		for(Veiculo v: veiculos) {
			switch (tipo) {
				case 0:
					 v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
					break;
				case 1:
					 if(v instanceof Moto){
						 v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
					 }
					break;
				case 2:
					 if(v instanceof Carro){
						 v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
					 }
					break;
				case 3:
					
					 if(v instanceof Caminhao){
						 v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
					 }
					break;
				case 4:
					 if(v instanceof Onibus){
						 v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
					 }
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void aumentarDiaria(int tipo, double taxaAumento) {
			for(Veiculo v: veiculos) {
			switch (tipo) {
				case 0:
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
					break;
				case 1:
					 if(v instanceof Moto){
						 v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
					 }
					break;
				case 2:
					 if(v instanceof Carro){
						 v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
					 }
					break;
				case 3:
					 if(v instanceof Caminhao){
						 v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
					 }
					break;
				case 4:
					 if(v instanceof Onibus){
						 v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
					 }
					break;
				default:
					break;
			}
		}
		
		
	}

	@Override
	public double faturamentoTotal(int tipo, Date inicio, Date fim) {
		double total = 0;
		for(Aluguel a : registro) {
			switch(tipo) {
				case 0:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						total+=a.getValor();
					}
					break;
				case 1:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Moto) {
							System.out.println(a.getValor());
							total+=a.getValor();
						}
					}
					break;
				case 2:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Carro) {
							total+=a.getValor();
						}
					}
					break;
				case 3:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Caminhao) {
							total+=a.getValor();
						}
					}
					break;
				case 4:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Onibus) {
							total+=a.getValor();
						}
					}
					break;
			}
		}
		return total;
	}

	@Override
	public int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
		int total = 0;
		for(Aluguel a : registro) {
			switch(tipo) {
				case 0:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						total+=a.getDias();
					}
					break;
				case 1:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Moto) {
							total+=a.getDias();
						}
					}
					break;
				case 2:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Carro) {
							total+=a.getDias();
						}
					}
					break;
				case 3:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Caminhao) {
							total+=a.getDias();
						}
					}
					break;
				case 4:
					if(inicio.compareTo(a.getInicio())<= 0 && fim.compareTo(a.getInicio())>=0) {
						if(a.getVeiculo() instanceof Onibus) {
							total+=a.getDias();
						}
					}
					break;
			}
		}
		return total;
	}
}