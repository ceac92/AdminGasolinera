package report;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author daMgel
 */
public class dbManager {

    private static final String CLASSNAME = dbManager.class.getSimpleName();

    public static Connection OpenConection() {

        Connection cnn = null;
        try {
            Class.forName(dbConfiguration.getJdbcDriverName());
            cnn = DriverManager.getConnection(dbConfiguration.getUrl(), dbConfiguration.getUser(), dbConfiguration.getPassword());
            System.out.println("\"Conectado a: \" + dbConfiguration.getUrl()");

        } catch (SQLException ex) {
            System.err.println(CLASSNAME + "> Error al establecer conexion:  \n" + ex.getMessage());
        } finally {
            return cnn;
        }
    }

    public static void CloseConnection(Connection CurrentConection) {
        try {
            if (CurrentConection != null) {
                CurrentConection.close();
                if (CurrentConection.isClosed() == true) {
                   // Utilidades.Logs(CLASSNAME + "> Conexion cerrada correctamente!");
                } else {
                    System.out.println(CLASSNAME + "> No hay conexion abierta!");
                }
              //  Utilidades.Logs(LINE);
            }
        } catch (SQLException se) {
            System.out.println(CLASSNAME + "> Error al cerrar la conexion! " + se.getMessage());
        }
    }
    public static void main(String[] args) {
        OpenConection();
    }

}
