package fazenda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DAOFazenda {

	public void inserir(Fazenda f) throws SQLException, ClassNotFoundException {
        Connection con;
		con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    String cmd = "insert into fazenda "
	        			+ "(numero, nome) "
	        			+ "values (" + f.getNumero() + ",\'" + f.getNome() + "\')";
 	    System.out.println(cmd);
	    st.execute(cmd);
	    st.close();
	}
	
	public void removerTodos() throws SQLException, ClassNotFoundException {
        Connection con;
		con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    String cmd = "delete from fazenda ";
	    System.out.println(cmd);
	    st.execute(cmd);
	    st.close();
	}
	
	public Fazenda pesquisarPor(int n) {
        Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
        	String cmd = "select * from fazenda where numero = " + n; 
        	System.out.println(cmd);
        	ResultSet rs = st.executeQuery(cmd);
     	    Fazenda f = null;
        	if (rs.next()) {
        	   String nome = rs.getString("nome");
 	    	   f = new Fazenda(n, nome);
 	    	}
       	    st.close();
        	return f;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  
	}
}
