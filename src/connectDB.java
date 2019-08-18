
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Segocode
 */
public class connectDB {

    private String driver;
    private Connection conexion;
    private String sentenciaSQL;
    private ResultSet cursor;

    public connectDB(String file) {
        this.driver = "jdbc:sqlite:" + file;
    }

    public Connection setConexion() throws SQLException {
        conexion = DriverManager.getConnection(driver);
        return conexion;
    }

    public void setSentencia(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public ResultSet ejecutarConsulta() throws SQLException {
        Statement s = conexion.createStatement();
        cursor = s.executeQuery(sentenciaSQL);
        return cursor;
    }

    public void cerrarConexion() throws SQLException {
        conexion.close();
    }


}
