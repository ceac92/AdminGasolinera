/*
 * @author daMgeL Clase de configuracion creada para abstraer las credenciales
 * de la Db y poder modificarlas sin tener que refactorizar toda la clase de
 * conexion. La utilidad principal es que se puede cambiar el driver de conexion
 * y/o las credenciales sin tener que modificar toda la logica de conexion.
 */
package report;

public class dbConfiguration {

    //private static final String URL = "jdbc:mysql://asmoto-testing.caatgzxcvryv.us-east-1.rds.amazonaws.com";
    private static final String URL = "jdbc:mysql://localhost:3306/bdgasolinera";
    private static final String USER = "root";
    private static final String PASSWORD = "destiny";
    private static final String JDBCDRIVERNAME = "com.mysql.jdbc.Driver";

    public static String getUrl() {
        return URL;
    }
    public static String getUser() {
        return USER;
    }
    public static String getPassword() {
        return PASSWORD;
    }
    public static String getJdbcDriverName() {
        return JDBCDRIVERNAME;
    }

}
