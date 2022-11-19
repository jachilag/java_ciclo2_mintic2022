import java.util.ArrayList;
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

        //generacion de los objetos de la clase "linkedListas" para cualquier tipo de dato en los diamantes
        LinkedListas<Integer> listaA = new LinkedListas<>(lista0);
        LinkedListas<Integer> listaB = new LinkedListas<>(lista1);
        LinkedListas<Integer> listaC = new LinkedListas<>(lista2);
        LinkedListas<Integer> listaD = new LinkedListas<>(lista3);

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
                    System.out.println("lista predefinida: \n" + listaA.getObj());
                    System.out.println("lista resultado: \n" + listaA.eliminarDuplicados());
                    break;
                    
                case 2:
                    listaC.setObj(listaC.listaUsuario());
                    System.out.println("lista del usuario: \n" + listaC.getObj());
                    System.out.println("lista resultado: \n" + listaC.eliminarDuplicados());
                    break;
                    
                case 3:
                    System.out.println("lista predefinida 1: \n" + listaA.getObj());
                    System.out.println("lista predefinida 2: \n" + listaB.getObj());
                    System.out.println("lista comunes entre las dos listas: \n" + listaA.coincidentes(listaB.getObj()));
                    break;
                    
                case 4:
                    System.out.println("ingrese datos para la lista 1:");
                    listaC.setObj(listaC.listaUsuario());
                    System.out.println("ingrese datos para la lista 2:");
                    listaD.setObj(listaD.listaUsuario());
                    System.out.println("lista 1 del usuario: \n" + listaC.getObj());
                    System.out.println("lista 2 del usuario: \n" + listaD.getObj());
                    System.out.println("lista resultado: \n" + listaC.coincidentes(listaD.getObj()));
                    break;

                default:
                    break;
            }
            System.out.println("===========================================");
        } while (!salir);
    }



    // metodo para recibir datos y almacenarlos en una variable tipo string.
    //no usar en la plataforma
    public static String Input(String text) {
        String dato = "";
        Scanner scan1 = new Scanner(System.in);
        System.out.print(text);
        dato = scan1.nextLine();
        return dato;
    }
}