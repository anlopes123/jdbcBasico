import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.Class.*;

public class ConnectionFactory {

    private ConnectionFactory(){
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("org.firebirdsql.jdbc.FBDriver");

        Connection connection = null;
        try(InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String driver = prop.getProperty("jdbc.driver");
            String databaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            StringBuilder sb =   new StringBuilder("jdbc")
                    .append(":")
                    .append(driver)
                    .append(':')
                    .append(databaseAddress)
                    .append(':')
                    .append(dataBaseName);
            String connectionUrl = sb.toString();
            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e ){
                System.out.println("FALHA ao tetar criar conexão");
                e.printStackTrace();
                throw new RuntimeException();
            }

        } catch (IOException e ) {
            System.out.println("FALHA ao tentar carregar arquivo de propriedades");
            e.printStackTrace();
        }
        return  connection;

    }


}
