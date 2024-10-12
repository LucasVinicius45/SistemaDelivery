package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.restaurante.sistemadelivery.model.Endereco;
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
	public void incluirEstabelecimento(Endereco endereco, Estabelecimento estabelecimento) {
	    // Instancia o EnderecoDAOImpl para inserir o endereço
	    EnderecoDAO enderecoDAO = new EnderecoDAOImpl();
	    
	    // Primeiro, insere o endereço
	    Endereco enderecoInserido = enderecoDAO.incluirEndereco(endereco);
	    
	    if (enderecoInserido != null) {
	        // Depois, insere o estabelecimento utilizando o ID do endereço inserido
	        String sqlEstabelecimento = "INSERT INTO ESTABELECIMENTO (ENDERECO_ID, NOME, TEL) VALUES (?, ?, ?)";
	        
	        try (PreparedStatement stmtEstabelecimento = conn.prepareStatement(sqlEstabelecimento)) {
	            stmtEstabelecimento.setLong(1, enderecoInserido.getId()); // Usa o ID do endereço inserido
	            stmtEstabelecimento.setString(2, estabelecimento.getNome());
	            stmtEstabelecimento.setString(3, estabelecimento.getTel());
	            
	            int rowsAffectedEstabelecimento = stmtEstabelecimento.executeUpdate();
	            
	            if (rowsAffectedEstabelecimento > 0) {
	                conn.commit(); // Confirma a transação
	                System.out.println("Estabelecimento de Sushi inserido com sucesso!");
	            } else {
	                System.out.println("Nenhuma linha inserida para o estabelecimento.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            try {
	                conn.rollback(); // Desfaz a transação em caso de erro
	            } catch (SQLException rollbackException) {
	                rollbackException.printStackTrace();
	            }
	        }
	    } else {
	        System.out.println("Falha ao inserir o endereço. O estabelecimento não foi inserido.");
	    }
	}


}
