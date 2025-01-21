import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class UsuarioDAO {
    public void createUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setBoolean(6, usuario.isAdministrador());

            stmt.executeUpdate();
        }
    }

    public Usuario getUsuarioById(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getBoolean("administrador")
                );
            }
        }
        return null;
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("email"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getBoolean("administrador")
                ));
            }
        }
        return usuarios;
    }

    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome = ?, endereco = ?, email = ?, login = ?, senha = ?, administrador = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setBoolean(6, usuario.isAdministrador());
            stmt.setInt(7, usuario.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteUsuario(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
