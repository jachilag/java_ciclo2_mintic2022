import java.util.LinkedList;
import java.util.Scanner;

public class LinkedListas<T> {
    protected LinkedList<T> obj;
    protected LinkedList<T> obj2;

    //CONSTRUCTOR
    public LinkedListas(LinkedList<T> obj) {
        this.obj = obj;
    }

    //METODOS
    public LinkedList<T> eliminarDuplicados(){
        LinkedList<T> salida = new LinkedList<>();
        for (int i = 0; i < obj.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (obj.get(i)==obj.get(j)) {break;}
                if (j==i-1) {salida.add(obj.get(i));}
            }
        }
        return salida;
    }
    
    public LinkedList<T> coincidentes(LinkedList<T> listaB){
        LinkedList<T> salida = new LinkedList<>();

        for (int i = 0; i < obj.size(); i++) {
            for (int j = 0; j < listaB.size(); j++) {
                if (listaB.get(j) == obj.get(i)) {
                    salida.add(obj.get(i));
                    break;
                }
            }
        }

        LinkedListas<T> salida2 =new LinkedListas<>(salida);
        return salida2.eliminarDuplicados();
    }

    //genera una lista con los datos ingresados por el usuario. los datos deben ser de tipo entero para este ejemplo
    //POR EL MOMENTO SOLO LA HE PODIDO HACER CON DATOS TIPO INTEGER Y NO PARA CASOS GENERALES
    public LinkedList<Integer> listaUsuario() {
        LinkedList<Integer> salida = new LinkedList<>(); //se crea lista que contendra todos los datos que ingrese el usuario
        int cont = 1;
        boolean salir = false;
        System.out.println("PARA SALIR DIGITE UNA LETRA");

        do {
            String valor = Input("ingrese dato " + cont + " de tipo entero: ");
            if (soloNumeros(valor)) {
                salida.add(Integer.valueOf(valor));
                cont++;
            } else if(soloLetras(valor)){
                salir = true;
            }
        } while (!salir);
        System.out.println("------------------------------------------");
        return salida;
    }


    //GETTER'S Y SETTER'S
    public LinkedList<T> getObj() {
        return obj;
    }

    public void setObj(LinkedList<T> obj) {
        this.obj = obj;
    }


    
    
    // ==========================================================================
    //FUNCIONES PROPIAS. SON PARTE DE COMO RESOLVI EL PROBLEMA PERO PUEDEN PLANTEARSE OTRAS ALTERNATIVAS

    // metodo para recibir datos y almacenarlos en una variable tipo string.
    //no usar en la plataforma
    public static String Input(String text) {
        String dato = "";
        Scanner scan1 = new Scanner(System.in);
        System.out.print(text);
        dato = scan1.nextLine();
        return dato;
    }

    //metodo para saber si una cadena contiene solo letras. retorna boleano
    public static boolean soloLetras(String text) {
        int unicode;
        int iter = 0;
        boolean flag;

        do {
            unicode = text.charAt(iter);
            flag = ((unicode >= 65 && unicode <= 90) || (unicode >= 97 && unicode <= 122));
            iter++;
        } while (flag && iter < text.length());
        return flag;
    }

    //metodo para saber si una cadena contiene solo numeros. retorna boleano
    public static boolean soloNumeros(String text) {
        int unicode;
        int iter = 0;
        boolean flag;

        do {
            unicode = text.charAt(iter);
            flag = (unicode >= 48 && unicode <= 57);
            iter++;
        } while (flag && iter < text.length());
        return flag;
    }
}


    
