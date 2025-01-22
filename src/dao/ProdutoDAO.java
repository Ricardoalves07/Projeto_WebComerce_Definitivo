package src.dao;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    // Create (Inserir novo produto)
    public void create(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (categoria, quantidade, preco, descricao, foto) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getCategoria());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());
            stmt.setBytes(5, produto.getFoto());
            stmt.executeUpdate();
        }
    }

    // Read (Consultar produto por ID)
    public Produto read(int id) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("categoria"),
                        rs.getInt("quantidade"),
                        rs.getBigDecimal("preco"),
                        rs.getString("descricao"),
                        rs.getBytes("foto")
                );
            }
        }
        return null;
    }

    // List (Consultar todos os produtos)
    public List<Produto> listAll() throws SQLException {
        String sql = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("categoria"),
                        rs.getInt("quantidade"),
                        rs.getBigDecimal("preco"),
                        rs.getString("descricao"),
                        rs.getBytes("foto")
                ));
            }
        }
        return produtos;
    }

    // Update (Atualizar informações do produto)
    public void update(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET categoria = ?, quantidade = ?, preco = ?, descricao = ?, foto = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getCategoria());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setString(4, produto.getDescricao());
            stmt.setBytes(5, produto.getFoto());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        }
    }

    // Delete (Remover produto)
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
