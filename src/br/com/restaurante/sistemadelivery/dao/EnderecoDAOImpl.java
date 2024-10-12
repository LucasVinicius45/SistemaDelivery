package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.util.ConexaoBD;

public class EnderecoDAOImpl implements EnderecoDAO {
	
	private Connection conn = null;
	
	public EnderecoDAOImpl() {
		ConexaoBD.getInstance();
		conn = ConexaoBD.getConnection();
		
		try {
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void criarTabelaEndereco() {
		// TODO Auto-generated method stub

	}

	@Override
	public void incluirEndereco(Endereco endereco) {
		String sql = "INSERT INTO ENDERECO (CIDADE, BAIRRO, CEP) VALUES (?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) {
        	stmt.setString(1, endereco.getCidade());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(2, endereco.getCep());
            
            
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); // Confirma a transação
                System.out.println("Endereco inserido!");
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
