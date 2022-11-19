package jonathan.retos;

import java.util.Scanner;

public class Retos {

    public static void main(String[] args) {
        reto1();
    }

    public static String Input(String text) {
        String dato;
        Scanner scan1 = new Scanner(System.in);
        System.out.print(text);
        dato = scan1.nextLine();
        return dato;
    }

    public static void reto1() {
        int distanciaGoku;
        int distanciaEsfera;
        int tiempoVuelo;
        String nombreEsfera;

        distanciaGoku = Integer.valueOf(Input("Ingrese distancia a la casa de goku: "));
        distanciaEsfera = 2 * distanciaGoku + 4;
        tiempoVuelo = (distanciaGoku + distanciaEsfera) / 5;

        if (tiempoVuelo >= 0 && tiempoVuelo <= 20) {
            nombreEsfera = "uno";
        } else if (tiempoVuelo >= 21 && tiempoVuelo <= 30) {
            nombreEsfera = "dos";
        } else if (tiempoVuelo >= 31 && tiempoVuelo <= 50) {
            nombreEsfera = "tres";
        } else if (tiempoVuelo >= 51) {
            nombreEsfera = "cuatro";
        } else {
            nombreEsfera = "";
        }

        System.out.println(distanciaGoku + " " + distanciaEsfera + " "
                + tiempoVuelo + "\n" + nombreEsfera);
    }
}
