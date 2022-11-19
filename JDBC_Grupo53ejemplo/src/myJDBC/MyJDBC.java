package myJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    // ATRIBUTOS
    private String urlBase; 
    private String portBase;
    private String dbName;
    private String url;
    private String userName;
    private String userPassword;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    // CONSTRUCTOR
    public MyJDBC(String dataName, String userNameDB, String userPasswordDB) {
        urlBase = "jdbc:mysql://localhost:";    //url de la base de datos
        portBase = "3306";      //puerto de conexion
        dbName = "/" + dataName;    //nombre de la base de datos
        url = urlBase + portBase + dbName;
        userName = userNameDB;
        userPassword = userPasswordDB;
    }

    // METODOS
    public boolean ConnectionMyDB() {
        boolean salida = false;
        try {
            connection = DriverManager.getConnection(url, userName, userPassword);
            statement = connection.createStatement();
            salida = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    public void CREATE(String myQuery) {
        try {
            statement.executeUpdate(myQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void READ(String myQuery) {
        try {
            resultSet = statement.executeQuery(myQuery);
            while (resultSet.next()){
                System.out.print("\n" + resultSet.getString("id") + "\t");
                System.out.print(resultSet.getString("first_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void UPDATE(String myQuery){
        try {
            statement.executeUpdate(myQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DELETE(String myQuery){
        try {
            statement.executeUpdate(myQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
