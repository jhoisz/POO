package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection[] conexoes = new Connection[10];
	private static boolean conectou = false;
	private static int pos = 0;

	Conexao() {
	}

	protected static Connection getConexao() {
		if (pos == 10) {
			pos = 0;
		}
		if (!conectou) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				for (int i = 0; i < 10; i++) {
					conexoes[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/pregao?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "078999");
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
				System.exit(1);
			}
			conectou = true;
		}
		return conexoes[pos++];
	}

	public void apagar() throws SQLException {
		InterfacePregao pregao = new MeuPregao();
		((MeuPregao) pregao).allClear();
	}
}