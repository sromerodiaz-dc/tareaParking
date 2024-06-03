import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO {
    private final Database database;

    public VehiculoDAO(Database database) {
        this.database = database;
    }

    public void insertVehiculo(String tipo, boolean ocupado) {
        String SQL = "INSERT INTO Vehiculos(tipo, ocupado) VALUES(?, ?)";
        executeUpdate(SQL, tipo, ocupado);
    }

    public void updateVehiculo(int id, boolean ocupado) {
        String SQL = "UPDATE Vehiculos SET ocupado = ? WHERE id = ?";
        executeUpdate(SQL, ocupado, id);
    }

    public void deleteVehiculo(int id) {
        String SQL = "DELETE FROM Vehiculos WHERE id = ?";
        executeUpdate(SQL, id);
    }

    public void selectAllVehiculos() {
        String SQL = "SELECT id, tipo, ocupado FROM Vehiculos";
        executeQuery(SQL);
    }

    private void executeUpdate(String SQL, Object... params) {
        try (PreparedStatement pstmt = database.connection.prepareStatement(SQL)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Error al ejecutar la sentencia", e);
        }
    }

    private void executeQuery(String SQL) {
        try (PreparedStatement pstmt = database.connection.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("tipo") + "\t" +
                        rs.getBoolean("ocupado"));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al ejecutar la consulta", e);
        }
    }
}
