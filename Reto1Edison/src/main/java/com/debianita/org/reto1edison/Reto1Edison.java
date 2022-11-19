package com.debianita.org.reto1edison;
import java.util.Scanner;

public class Reto1Edison {
    public static void main(String[] args) {
        int[] compra1 = {2700, 9500,300,15000,1800,10000,400,3000,400};
        int[] compra2 = {6700};
        
        imprimirArray(reporte(compra1));
        System.out.println("\nSegundo ejemplo");
        imprimirArray(reporte(compra2));

    }
    
    
    public static void imprimirArray(int[] arreglo){
        for (int i : arreglo) {
            System.out.print(i+", ");
        }
    }
    
    public static int[] reporte(int[] compra){
        int n = compra.length;
        int[] salida = new int[3];
        salida[0] = costoTotal(compra);
        salida[1] = menor(compra);
        salida[2] = mayor(compra);
        return salida;
    }
    
    public static int menor(int[] compra){
        int indice = 0;
        
        for (int i = 0; i < compra.length; i++) {
            if (compra[i] < compra[indice]) {
                indice=i;
            }
        }
        return compra[indice];
    }

    public static int mayor(int[] compra){
        int indice = 0;
        
        for (int i = 0; i < compra.length; i++) {
            if (compra[i] > compra[indice]) {
                indice=i;
            }
        }
        return compra[indice];
    }
    
    public static int costoTotal(int[] compra){
        int suma = 0;
        
        for (int i = 0; i < compra.length; i++) {
            suma += compra[i];
        }
        return suma;
    }
}
