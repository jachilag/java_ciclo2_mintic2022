import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        prueba0();
        prueba1();
        prueba2();
        prueba3();
        prueba4();
        prueba5();

    }

    public static void prueba0() {
        ArrayList<Cliente> t3 = new ArrayList<>();
        t3.add(new Cliente("valeria Di", "10367876345", 9653, "03/07/2022", "0004"));
        t3.add(new Cliente("Johan Doe", "1037645345", 3918, "03/07/2022", "0005"));
        t3.add(new Cliente("Maurice Doe", "98765234", 6048, "03/07/2022", "0006"));
        t3.add(new Cliente("Matthew Doe", "1036789453", 5840, "03/07/2022", "0007"));
        t3.add(new Cliente("Agustina Doe", "10003456", 3940, "03/07/2022", "0008"));
        t3.add(new Cliente("Agustina Doe", "10003456", 3840, "03/07/2022", "0009"));
        t3.add(new Cliente("Milena Doe", "20003456", 3696, "03/07/2022", "0010"));
        t3.add(new Cliente("Carla Di", "103789762", 2432, "03/07/2022", "0011"));

        imprimirArray(Solution.reportes(t3));
    }

    public static void prueba1() {
        ArrayList<Cliente> tienda = new ArrayList<>();
        tienda.add(new Cliente("Mateo", "1037543456", 6700, "01/07/2022", "0001"));
        imprimirArray(Solution.reportes(tienda));

    }

    public static void prueba2() {
        ArrayList<Cliente> tienda = new ArrayList<>();
        tienda.add(new Cliente("Luz Di", "32675123", 100, "02/07/2022", "0002"));
        tienda.add(new Cliente("John Doe", "81200306", 2300, "02/07/2022", "0003"));
        imprimirArray(Solution.reportes(tienda));
    }

    public static void prueba3() {
        ArrayList<Cliente> tienda = new ArrayList<>();
        tienda.add(new Cliente("Valeria Di", "10367876345", 9653, "03/07/2022", "0004"));
        tienda.add(new Cliente("Johan Doe", "1037645345", 3918, "03/07/2022", "0005"));
        tienda.add(new Cliente("Maurice Doe", "98765234", 6048, "03/07/2022", "0006"));
        tienda.add(new Cliente("Matthew Doe", "1036789453", 5840, "03/07/2022", "0007"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 3840, "03/07/2022", "0008"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 3840, "03/07/2022", "0009"));
        tienda.add(new Cliente("Milena Doe", "20003456", 3696, "03/07/2022", "0010"));
        tienda.add(new Cliente("Carla Di", "103789762", 2432, "03/07/2022", "0011"));
        imprimirArray(Solution.reportes(tienda));
    }

    public static void prueba4() {
        ArrayList<Cliente> tienda = new ArrayList<>();
        tienda.add(new Cliente("Valeria Di", "10367876345", 3240, "04/07/2022", "0012"));
        tienda.add(new Cliente("Johan Doe", "1037645345", 9048, "04/07/2022", "0012"));
        tienda.add(new Cliente("Maurice Doe", "98765234", 2310, "04/07/2022", "0013"));
        tienda.add(new Cliente("Matthew Doe", "1036789453", 4680, "04/07/2022", "0014"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 3968, "04/07/2022", "0015"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 6000, "04/07/2022", "0016"));
        tienda.add(new Cliente("Milena Doe", "20003456", 13120, "04/07/2022", "0017"));
        tienda.add(new Cliente("Carla Di", "103789762", 5980, "04/07/2022", "0018"));
        tienda.add(new Cliente("Luz Di", "32675123", 7240, "04/07/2022", "0019"));
        tienda.add(new Cliente("John Doe", "81200306", 29900, "04/07/2022", "0020"));
        tienda.add(new Cliente("Mateo", "1037543456", 193600, "04/07/2022", "0021"));
        imprimirArray(Solution.reportes(tienda));
    }

    public static void prueba5() {
        ArrayList<Cliente> tienda = new ArrayList<>();
        tienda.add(new Cliente("Valeria Di", "10367876345", 2700, "05/07/2022", "0022"));
        tienda.add(new Cliente("Johan Doe", "1037645345", 9500, "05/07/2022", "0022"));
        tienda.add(new Cliente("Maurice Doe", "98765234", 300, "05/07/2022", "0022"));
        tienda.add(new Cliente("Matthew Doe", "1036789453", 15000, "05/07/2022", "0022"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 1800, "05/07/2022", "0022"));
        tienda.add(new Cliente("Agustina Doe", "10003456", 10000, "05/07/2022", "0022"));
        tienda.add(new Cliente("Milena Doe", "20003456", 400, "05/07/2022", "0022"));
        tienda.add(new Cliente("Carla Di", "103789762", 3000, "05/07/2022", "0022"));
        tienda.add(new Cliente("Luz Di", "32675123", 400, "05/07/2022", "0022"));
        imprimirArray(Solution.reportes(tienda));
    }

    public static void imprimirArray(Object[] vector) {
        String texto = "[";
        for (int i = 0; i < vector.length; i++) {
            texto += (i==vector.length-1)?vector[i]:vector[i] + ", ";
        }
        System.out.println(texto+"]");
    }
}
