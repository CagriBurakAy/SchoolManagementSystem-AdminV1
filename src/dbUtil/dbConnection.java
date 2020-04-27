package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
    private static final String USERNAME="dbuser";
    private static final String PASSWORD="dbpassword";
    private static final String CONN="jdbc:mysql://localhost/login";
    private static final String SQCONN="jdbc:sqlite:school.sqlite";
    public static Connection getConnection() throws Exception{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN);
        }catch (ClassNotFoundException ex){

            ex.printStackTrace();
        }
        return null;
    }
}
