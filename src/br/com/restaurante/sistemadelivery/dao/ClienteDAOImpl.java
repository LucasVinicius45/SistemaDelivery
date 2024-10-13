package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Endereco;
import br.com.restaurante.sistemadelivery.util.ConexaoBD;

public class ClienteDAOImpl implements ClienteDAO {

	private Connection conn = null;

	public ClienteDAOImpl() {
		ConexaoBD.getInstance();
		conn = ConexaoBD.getConnection();
		
		try {
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void criarTabelaCliente() {
		// TODO Auto-generated method stub

	}

	@Override
	public void incluirCliente(Endereco endereco, String nome, String email) {
		 EnderecoDAO enderecoDAO = new EnderecoDAOImpl();
		    
		   
		    Endereco enderecoInserido = enderecoDAO.incluirEndereco(endereco);
		    
		    if (enderecoInserido != null) {
		        
		        String sqlCliente = "INSERT INTO CLIENTE (ENDERECO_ID, NOME, EMAIL) VALUES (?, ?, ?)";
		        
		        try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente)) {
		        	stmtCliente.setLong(1, enderecoInserido.getId()); // Usa o ID do endereço inserido
		        	stmtCliente.setString(2, nome);
		        	stmtCliente.setString(3, email);
		            
		            int rowsAffectedEstabelecimento = stmtCliente.executeUpdate();
		            
		            if (rowsAffectedEstabelecimento > 0) {
		                conn.commit(); // Confirma a transação
		                System.out.println("Cliente inserido com sucesso!");
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

