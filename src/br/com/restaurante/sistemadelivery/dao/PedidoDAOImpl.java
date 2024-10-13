package br.com.restaurante.sistemadelivery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// TODO Auto-generated method stub

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
		
	}


