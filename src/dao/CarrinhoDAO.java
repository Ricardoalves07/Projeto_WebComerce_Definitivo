package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
    private Connection connection;

    public CarrinhoDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Carrinho carrinho) throws SQLException {
        String sql = "INSERT INTO Carrinho (compra_id, produto_id, quantidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, carrinho.getCompraId());
            stmt.setInt(2, carrinho.getProdutoId());
            stmt.setInt(3, carrinho.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public List<Carrinho> listByCompraId(int compraId) throws SQLException {
        String sql = "SELECT * FROM Carrinho WHERE compra_id = ?";
        List<Carrinho> carrinhos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, compraId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                carrinhos.add(new Carrinho(
                        rs.getInt("compra_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade")
                ));
            }
        }
        return carrinhos;
    }

    public void deleteByCompraId(int compraId) throws SQLException {
        String sql = "DELETE FROM Carrinho WHERE compra_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, compraId);
            stmt.executeUpdate();
        }
    }
}
