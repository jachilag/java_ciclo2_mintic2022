package app;

import myJDBC.MyJDBC;

public class App {
    public static void main(String[] args) {
        MyJDBC myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        System.out.println(myJDBC.ConnectionMyDB());
        myJDBC.CREATE("INSERT INTO usuario(id, first_name) VALUES(1, 'Diego')");
    }
}
