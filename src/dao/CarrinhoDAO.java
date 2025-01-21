import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CarrinhoDAO {
    public void addProdutoAoCarrinho(Carrinho carrinho) throws SQLException {
        String sql = "INSERT INTO Carrinho (usuario_id, produto_id, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carrinho.getUsuarioId());
            stmt.setInt(2, carrinho.getProdutoId());
            stmt.setInt(3, carrinho.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public List<Carrinho> getCarrinhoByUsuarioId(int usuarioId) throws SQLException {
        String sql = "SELECT * FROM Carrinho WHERE usuario_id = ?";
        List<Carrinho> carrinho = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                carrinho.add(new Carrinho(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getInt("produto_id"),
                        rs.getInt("quantidade")
                ));
            }
        }
        return carrinho;
    }

    public void updateQuantidadeProdutoCarrinho(int id, int quantidade) throws SQLException {
        String sql = "UPDATE Carrinho SET quantidade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteProdutoCarrinho(int id) throws SQLException {
        String sql = "DELETE FROM Carrinho WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
