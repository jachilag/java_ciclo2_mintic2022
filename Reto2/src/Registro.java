import java.util.ArrayList;
import java.util.Scanner;

public class Registro {
    public static ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();

    public static void main(String[] args) {
        procesarComando(); 
    }

    public static void procesarComando() {
        boolean salir = false;
        Scanner scan1 = new Scanner(System.in);
        do {
            String[] entrada2 = scan1.nextLine().split("&");
            int opcion = Integer.valueOf(entrada2[0]);

            switch (opcion) {
                case 1: // agregar un estudiante
                    agregarEstudiante(entrada2);
                    break;
                case 2: // mostrar estudiantes
                    listarEstudiantes();
                    break;
                case 3: // salir de la ejecucion del programa
                    salir = true;
                    break;
                default:
                    System.out.println("ha ingresado un dato incorrecto");
                    break;
            }
        } while (!salir);
    }

    public static void agregarEstudiante(String[] entrada) {
        if (entrada[1].equalsIgnoreCase("Pregrado")) {
            Pregrado estudiante1 = new Pregrado(entrada[2], entrada[3], entrada[4], entrada[5], entrada[6]);
            listaEstudiantes.add(estudiante1);
        } else {
            Posgrado estudiante2 = new Posgrado(entrada[2], entrada[3], entrada[4], entrada[5], entrada[6]);
            listaEstudiantes.add(estudiante2);
        }
    }

    public static void listarEstudiantes() {
        System.out.println("***Listado de estudiantes***");

        for (Estudiante estudiante: listaEstudiantes) {
            System.out.println(estudiante.toString());
        }
    }
}
/* 1&Posgrado&Maria Sarmiento&25&Maestria en economia&Ninguna&Investigacion
 * 1&Pregrado&Luis Parra&21&Medicina&Raizal&15
 * 1&Pregrado&Martha Casas&19&Derecho&Ninguna&42
 * 1&Posgrado&Luz Salas&23&Maestria en ingenieria&Ninguna&Profundizacion
 * 2
 * 3
 */