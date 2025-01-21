import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ProdutoDAO {
    public void createProduto(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produto (descricao, preco, foto, quantidade, categoria_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getDescricao());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setString(3, produto.getFoto());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getCategoriaId());
            stmt.executeUpdate();
        }
    }

    public Produto getProdutoById(int id) throws SQLException {
        String sql = "SELECT * FROM Produto WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getBigDecimal("preco"),
                        rs.getString("foto"),
                        rs.getInt("quantidade"),
                        rs.getInt("categoria_id")
                );
            }
        }
        return null;
    }

    public List<Produto> getAllProdutos() throws SQLException {
        String sql = "SELECT * FROM Produto";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("descricao"),
                        rs.getBigDecimal("preco"),
                        rs.getString("foto"),
                        rs.getInt("quantidade"),
                        rs.getInt("categoria_id")
                ));
            }
        }
        return produtos;
    }

    public void updateProduto(Produto produto) throws SQLException {
        String sql = "UPDATE Produto SET descricao = ?, preco = ?, foto = ?, quantidade = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getDescricao());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setString(3, produto.getFoto());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getCategoriaId());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produto WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
