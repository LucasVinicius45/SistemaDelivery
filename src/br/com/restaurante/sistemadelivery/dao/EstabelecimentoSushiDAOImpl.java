package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.util.ConexaoBD;

public class EstabelecimentoSushiDAOImpl implements EstabelecimentoDAO {
	
	private Connection conn = null;

	public EstabelecimentoSushiDAOImpl() {
		ConexaoBD.getInstance();
		conn = ConexaoBD.getConnection();
		
		try {
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void criarTabelaEstabelecimento() {
		// TODO Auto-generated method stub

	}

	@Override
	public void incluirEstabelecimento(Estabelecimento estabelecimento) {
		String sql = "INSERT INTO ESTABELECIMENTO (NOME, TEL) VALUES (?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setString(1, estabelecimento.getNome());
            stmt.setString(2, estabelecimento.getTel());
            
            
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); // Confirma a transação
                System.out.println("Estabelecimento de Sushi inserido!");
            } else {
                System.out.println("Nenhuma linha inserida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback(); // Desfaz a transação em caso de erro
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        }
	}

}
