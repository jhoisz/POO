package jhoisnayraVitoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOTipoDeServico {
	public void inserirTipoDeServico(TipoDeServico tipo) throws SQLException {
        Connection con;
		con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    String cmd = "insert into tipoDeServico(codigo,descricao) values ("+tipo.getCodTipoDeServico()+",'"+tipo.getDescr()+"');";
	    st.execute(cmd);
	    st.close();
	}
	public TipoDeServico buscar(int cod) throws SQLException {
		Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
            
        	String cmd = "select * from tipoDeServico where codigo = "+cod+";"; 
        	
        	ResultSet rs = st.executeQuery(cmd);
     	    TipoDeServico tipo = null;
     	    
        	if (rs.next()) {
 	    	   	tipo = new TipoDeServico(rs.getInt("codigo"),rs.getString("descricao"));
 	    	}
       	    st.close();
       	    
        	return tipo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;  		
	}
	public ArrayList<TipoDeServico> listarTiposDeServicos() throws SQLException {
		ArrayList<TipoDeServico> tipos = new ArrayList<TipoDeServico>();
		Connection con;
        try {
			con = Conexao.getConexao();
            Statement st = con.createStatement();
            
        	String cmd = "select * from tipoDeServico"; 
        	
        	ResultSet rs = st.executeQuery(cmd);
        	
        	while(rs.next()) {
        		TipoDeServico tipo = new TipoDeServico(rs.getInt("codigo"),rs.getString("descricao"));
 	    	   	tipos.add(tipo);
 	    	}
       	    st.close();
       	    
        	return tipos;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;  		
	}
	public void apagar() throws SQLException{
		Connection con;
		con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "delete from serv_tipo;";
		st.execute(cmd);
		
		cmd = "delete from prest_servPossiveis;";
		st.execute(cmd);
		
		cmd = "delete from tipoDeServico;";
		st.execute(cmd);
		
		st.close();
	}
}
