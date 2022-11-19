import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class App {
    public static void main(String[] args)  {
        Menu();
    }


    //menu para elegir cual opcion se desea ver su funcionamiento
    public static void Menu() {
        LinkedList<Integer> lista0 = new LinkedList<> (Arrays.asList(12,20,50,50,50,10,25,56,57,58)); //lista con valores predefinidos
        LinkedList<Integer> lista1 = new LinkedList<> (Arrays.asList(56,57,58,60)); //lista con valores predefinidos
        LinkedList<Integer> lista2 = new LinkedList<> (); //lista vacia
        LinkedList<Integer> lista3 = new LinkedList<> (); //lista vacia
        Boolean salir = false;

        do {
            System.out.println("\n1. Eliminar repetidos (valores predefinidos en el codigo)"+
                                "\n2. Eliminar repetidos (valores ingresados por usuario)"+
                                "\n3. Buscar valores comunes (valores predefinidos en el codigo)"+
                                "\n4. Buscar valores comunes(valores ingresados por usuario)"+
                                "\n0. SALIR"+
                                "\n");
        
            int opcion = Integer.valueOf(Input("Elija una opcion: "));
            System.out.println("===========================================");
            switch (opcion) {
                case 0:
                    salir = true;
                    break;

                case 1:
                    System.out.println("lista predefinida: \n" + lista0);
                    System.out.println("lista resultado: \n" + duplicados(lista0));
                    break;
                    
                case 2:
                    lista2 = listaUsuario();
                    System.out.println("lista del usuario: \n" + lista2);
                    System.out.println("lista resultado: \n" + duplicados(lista2));
                    break;
                    
                case 3:
                    System.out.println("lista predefinida 1: \n" + lista0);
                    System.out.println("lista predefinida 2: \n" + lista1);
                    System.out.println("lista comunes entre las dos listas: \n" + coincidentes(lista0, lista1));
                    break;
                    
                case 4:
                    System.out.println("ingrese datos para la lista 1:");
                    lista2 = listaUsuario();
                    System.out.println("ingrese datos para la lista 2:");
                    lista3 = listaUsuario();
                    System.out.println("lista 1 del usuario: \n" + lista2);
                    System.out.println("lista 2 del usuario: \n" + lista3);
                    System.out.println("lista resultado: \n" + coincidentes(lista2, lista3));
                    break;

                default:
                    break;
            }
            System.out.println("===========================================");
        } while (!salir);
    }



    //obtiene los valores coincidentes  entre dos linkedList
    public static LinkedList<Integer> coincidentes(LinkedList<Integer> listaA, LinkedList<Integer> listaB){
            LinkedList<Integer> salida = new LinkedList<>(); //se crea lista que contendra todos los datos que son coincidentes

            for (int i = 0; i < listaA.size(); i++) { //recorre la primer lista
                for (int j = 0; j < listaB.size(); j++) { //recorre la segunda lista
                    if (listaA.get(i) == listaB.get(j)  ) { //compara si el elemento de la posicion "i" de la listaA es igual al elemento de la posicion "j"  de la listaB
                        salida.add(listaB.get(j)); //si se cumple la condicion del if, entonces agrega el valor a la lista de salida
                        //salida.add(listaA.get(i)); // esta opcion tambien es valida, pero no las dos opciones al mismo tiempo
                        break;  //opcional: para que si se cumple la condicion termine ciclo for de listaB y continue con el siguiente elemento del for de listaA
                    }
                }
            }
            return duplicados(salida); // elimina valores duplicados 
        }

    //elimina duplicados con funciones. En el archivo linkedListas se realizó con genericidad
    public static LinkedList<Integer> duplicados(LinkedList<Integer> lista) {
        LinkedList<Integer> salida = new LinkedList<>();

        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (lista.get(i)==lista.get(j)) {break;}
                if (j==i-1) {salida.add(lista.get(i));}
            }
        }
        return salida;
    }

    //genera una lista con los datos ingresados por el usuario. los datos deben ser de tipo entero para este ejemplo
    public static LinkedList<Integer> listaUsuario() {
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




    // ==========================================================================
    //FUNCIONES PROPIAS. NO SON PARTE OBLIGATORIA DE COMO RESOLVER EL PROBLEMA SIN EMBARGO 
    //SON UTILES PARA COSAS MUY ESPECIFICAS

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