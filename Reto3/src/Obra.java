import java.util.ArrayList;

public class Obra {
    public static ArrayList<Integer> clases(ArrayList<Integer> categorias) {
        ArrayList<Integer> salida = new ArrayList<>();
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.indexOf(categorias.get(i)) == i) {
                salida.add(categorias.get(i));
            }
        }
        return salida;
    }

    public static ArrayList<Integer> meFaltanDeLaClase(ArrayList<Integer> faltantes, ArrayList<Integer> categorias,int categoria) {
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