package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.restaurante.sistemadelivery.model.Cliente;
import br.com.restaurante.sistemadelivery.model.Estabelecimento;
import br.com.restaurante.sistemadelivery.model.Motoboy;
import br.com.restaurante.sistemadelivery.model.Pedido;
import br.com.restaurante.sistemadelivery.util.ConexaoBD;

public class PedidoDAOImpl implements PedidoDAO {
	
	private Connection conn = null;
	
	public PedidoDAOImpl() {
		ConexaoBD.getInstance();
		conn = ConexaoBD.getConnection();
		
		try {
			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void criarTabelaPedido() {
		String tabelaQuery = "SELECT TABLE_NAME FROM USER_TABLES WHERE TABLE_NAME = 'PEDIDO'";
	    String sql = "CREATE TABLE PEDIDO (" +
	                 "id NUMBER NOT NULL, " +
	                 "email_cliente VARCHAR2(30) NOT NULL, " +
	                 "nome_estabelecimento VARCHAR2(30) NOT NULL, " +
	                 "id_motoboy NUMBER NOT NULL, " +
	                 "CONSTRAINT pk_pedido_id PRIMARY KEY (id), " +
	                 "FOREIGN KEY (email_cliente) REFERENCES CLIENTE(email), " +
	                 "FOREIGN KEY (nome_estabelecimento) REFERENCES ESTABELECIMENTO(nome), " +
	                 "FOREIGN KEY (id_motoboy) REFERENCES MOTOBOY(id))";

	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(tabelaQuery);

	        // Verifica se a tabela já existe
	        if (!rs.next()) {
	            stmt.executeUpdate(sql);
	            conn.commit();  // Confirma a criação da tabela
	            System.out.println("Tabela PEDIDO criada com sucesso.");
	        } else {
	            System.out.println("A tabela PEDIDO já existe.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Erro ao criar a tabela PEDIDO: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	}

	@Override
	public void incluirPedido(Pedido pedido) {
		String sql = "INSERT INTO PEDIDO (EMAIL_CLIENTE, NOME_ESTABELECIMENTO, ID_MOTOBOY) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"ID"})) { // O "new String[]{"ID"}" faz referência ao campo de chave primária
            stmt.setString(1, pedido.getCliente().getEmail());
            stmt.setString(2, pedido.getEstabelecimento().getNome());
            stmt.setLong(3, pedido.getMotoboy().getId());

           

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                	pedido.setId(generatedKeys.getInt(1));
                }
                conn.commit();
                System.out.println("Pedido inserido com sucesso! ID: " + pedido.getId());
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
	@Override
	public void criarSequencePedido() {
		String sequenceQuery = "SELECT SEQUENCE_NAME FROM ALL_SEQUENCES WHERE SEQUENCE_NAME = 'PEDIDO_SEQ'";
	    String sql = "CREATE SEQUENCE pedido_seq START WITH 1 INCREMENT BY 1 NOCACHE";
	    
	    try (Statement stmt = conn.createStatement(); 
		         ResultSet rs = stmt.executeQuery(sequenceQuery)) {
		        
		       
		        if (!rs.next()) { 
		            stmt.executeUpdate(sql);
		            System.out.println("Sequence PEDIDO criada");
		        } else {
		            System.out.println("A sequence PEDIDO já existe");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		
	}
	@Override
	public void criarTriggerPedido() {
		String sql = "CREATE OR REPLACE TRIGGER pedido_id_trigger " +
                "BEFORE INSERT ON PEDIDO " +
                "FOR EACH ROW " +
                "BEGIN " +
                "  :NEW.ID := pedido_seq.NEXTVAL; " +
                "END;";

		try (Statement stmt = conn.createStatement()) {
	       stmt.executeUpdate(sql);
	       System.out.println("Trigger 'pedido_id_trigger' compilado com sucesso.");
		} catch (SQLException e) {
	       e.printStackTrace();
		}
		
 }
		
}


