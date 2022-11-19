import java.util.ArrayList;
import java.util.Arrays;


public class App {
    public static void main(String[] args) {
        ArrayList<Integer> faltantes = new ArrayList<>(Arrays.asList(1,3,6,8));
        ArrayList<Integer> categorias = new ArrayList<> (Arrays.asList(1,2,5,5,5,1,2,5,5,5));
        int categoria = 5;
        ArrayList<Integer> museoA = new ArrayList<> (Arrays.asList(3,5,7,10,15,16));
        ArrayList<Integer> museoB = new ArrayList<> (Arrays.asList(4,10,5,8));

        ArrayList<Integer> vector = new  ArrayList<>(Arrays.asList(1, 2, 5, 5, 5, 1, 2, 5, 5, 5));
        System.out.println(clases(vector));
        System.out.println(meFaltanDeLaClase(faltantes, categorias, categoria));      
        System.out.println(noTengo(museoA, museoB));  
        System.out.println(puedoCambiar(museoA, museoB));
    }


    public static ArrayList<Integer> clases(ArrayList<Integer> vector) {
        ArrayList<Integer> salida = new ArrayList<>();
        for (int i = 0; i < vector.size(); i++) {
            if (vector.indexOf(vector.get(i)) == i) {
                salida.add(vector.get(i));
            }
        }
        return salida;
       
    }

    public static ArrayList<Integer> meFaltanDeLaClase(ArrayList<Integer> faltantes, ArrayList<Integer> categorias, int categoria) {
        ArrayList<Integer> salida = new ArrayList<>();
        for (Integer i : faltantes) {
            if (categorias.get(i) == categoria) {
                salida.add(i);
            }
        }
        return salida;
    }

    public static ArrayList<Integer> noTengo(ArrayList<Integer> museoA, ArrayList<Integer> museoB) {  
        ArrayList<Integer> salida = new ArrayList<>();
        for (int i = 0; i < museoA.size(); i++) {
            if (museoB.indexOf(museoA.get(i)) == -1) {
                salida.add(museoA.get(i));
            }
        }
        return salida;
    }

    public static Integer puedoCambiar(ArrayList<Integer> museoA, ArrayList<Integer> museoB) {
        int faltantesB = noTengo(museoA, museoB).size();
        int faltantesA = noTengo(museoB, museoA).size();
        return Math.min(faltantesA, faltantesB);
    }
}
