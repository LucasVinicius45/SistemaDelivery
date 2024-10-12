package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public Endereco incluirEndereco(Endereco endereco) {
		
	    String sql = "INSERT INTO ENDERECO (CIDADE, BAIRRO, CEP) VALUES (?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, endereco.getCidade());
	        stmt.setString(2, endereco.getBairro());
	        stmt.setString(3, endereco.getCep());

	        int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            conn.commit(); // Confirma a transação
	            System.out.println("Endereço inserido com sucesso!");
	            
	            String query = "SELECT id FROM ENDERECO WHERE CEP = ? AND CIDADE = ? AND BAIRRO = ?";
	            try (PreparedStatement stmtSelect = conn.prepareStatement(query)) {
	                stmtSelect.setString(1, endereco.getCep());
	                stmtSelect.setString(2, endereco.getCidade());
	                stmtSelect.setString(3, endereco.getBairro());

	                try (ResultSet rs = stmtSelect.executeQuery()) {
	                    if (rs.next()) {
	                        long id = rs.getLong("id");
	                        endereco.setId(id); // Atualiza o objeto com o ID gerado
	                        System.out.println("ID do endereço: " + id);
	                    }
	                }
	            }

	            return endereco; // Retorna o objeto Endereco atualizado
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
	    
	    return null; // Retorna null caso a inserção falhe
	}



}
