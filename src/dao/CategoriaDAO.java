import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class CategoriaDAO {
    public void createCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categoria (nome) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        }
    }

    public Categoria getCategoriaById(int id) throws SQLException {
        String sql = "SELECT * FROM Categoria WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(rs.getInt("id"), rs.getString("nome"));
            }
        }
        return null;
    }

    public List<Categoria> getAllCategorias() throws SQLException {
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
            }
        }
        return categorias;
    }

    public void updateCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categoria SET nome = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCategoria(int id) throws SQLException {
        String sql = "DELETE FROM Categoria WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
