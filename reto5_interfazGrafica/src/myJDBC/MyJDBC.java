package myJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
    private ResultSet resultSet1;
    private ArrayList<String[]> arraySalida;

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

    public boolean CREATE(String myQuery) {
        boolean salida = false;
        try {
            statement.executeUpdate(myQuery);
            salida = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    public String READ(String myQuery) {
        String salida = "";
        try {
            resultSet = statement.executeQuery(myQuery);
            int col = columnsTable(resultSet);
            
            while (resultSet.next()){
                for (int i = 1; i <= col; i++) {
                    salida += resultSet.getString(i) + "\t";
                }
                salida += "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }
    
    public ArrayList<String[]> READ_as_ARRAY(String myQuery) {
        try {
            arraySalida = new ArrayList<>();
            resultSet1 = statement.executeQuery(myQuery);
            int col = columnsTable(resultSet1);
            while (resultSet1.next()){
                String[] listaSalida = new String[col];
                for (int i = 1; i <= col; i++) {
                    listaSalida[i-1] = resultSet1.getString(i);
                }
                arraySalida.add(listaSalida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arraySalida;
    }

    public int columnsTable(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData(); 
        return rsmd.getColumnCount();

    }

    public boolean UPDATE(String myQuery){
        return CREATE(myQuery);
    }

    public boolean DELETE(String myQuery){
        return CREATE(myQuery);
    }
}