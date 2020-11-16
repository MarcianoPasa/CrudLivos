package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BancoDeDados {
    
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                
                String user = "postgres";
                String password = "123";
                
                Class.forName("org.postgresql.Driver");
                
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/livrosDB",user, password);
                
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
    
}
