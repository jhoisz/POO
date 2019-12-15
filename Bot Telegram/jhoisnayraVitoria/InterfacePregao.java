package jhoisnayraVitoria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface InterfacePregao {

	// Só podemos ter um servico aberto por contratante em um dado momento.
	/* Feito */public int cadastrarServico(String emailContratante, String descricao, double valor, int prazoMaximo,
			int codTipoDeServico) throws SQLException;

	// Lista todos os serviços que atendam aos critérios de pesquisa (>=)
	/* Feito */public ArrayList<Servico> listarServicos(double valor, int prazoMaximo, int tipo, boolean contratado,
			boolean finalizado, int avaliacaoMediaContratante);

	// Verifica que não existe já cadastrado como contratante. Email deve ser
	// único.
	/* Feito */public void cadastrarContratante(String email, String nome, String telefone) throws SQLException;

	// Verifica que não existe já cadastrado como prestador. Email deve ser
	// único.
	/* Feito */public void cadastrarPrestador(String email, String nome, String telefone) throws SQLException;

	// Verifica que prestador e serviço existem.
	/* Feito */public void adicionarServicoPrestador(String email, int tipo) throws SQLException;

	// Verifica que não existe já cadastrado. Código e descrição devem ser
	// únicos.
	/* Feito */public void adicionarTipoDeServico(int cod, String descr) throws SQLException;

	// Cadastra proposta de um prestador para um serviço. Deve verificar
	// compatibilidade entre os tipos de servicós
	// prestados pelo prestador e o serviço solicitado. Valor e prazo devem ser no
	// máximo igual ao previsto.
	/* Feito */public void cadastrarProposta(int codigoServico, String emailPrestador, double valor, int prazo)
			throws SQLException;

	// valor 0 significa todas ou então apenas valores abaixo de um valor.
	// prazo 0 significa todas ou então apenas prazos menores que um máximo.
	// avaliacao 0 significa todas ou então apenas avaliações acima de um limite
	// mínimo.
	// somente as não contratadas e finalizadas
	/* Feito */public ArrayList<Proposta> listarPropostas(int codigoServico, double valor, int prazoMaximo,
			int avaliacaoMediaPrestador) throws SQLException;

	// Marca serviço como contratado liberando para o cadastro de outros serviços
	// pelo contratante
	/* Feito */public void contratarProposta(int codigoServico, String emailPrestador) throws SQLException;

	// Proposta deve ter sido contratada. Marca serviço como finalizado.
	/* Feito */public void finalizarServico(int codigoServico, Date data) throws SQLException;

	// Servico deve ter sido finalizado.
	/* Feito */public void avaliarPrestador(int codigoServico, int nota, String observacoes) throws SQLException;

	// Servico deve ter sido finalizado.
	/* Feito */public void avaliarContratante(int codigoServico, int nota, String observacoes) throws SQLException;
}
