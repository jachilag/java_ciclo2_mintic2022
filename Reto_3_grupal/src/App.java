import java.util.ArrayList;
import java.util.Arrays;

public class App {


    public static void main(String[] args) throws Exception {
        ArrayList<Integer> lista = new ArrayList<>(Arrays.asList(1,2,5,5,5,1,2,5,5,5));
        //System.out.println(clases(lista));
        ArrayList<Integer> indices = new ArrayList<>(Arrays.asList(1,3,6,8));
        ArrayList<Integer> categorias = new ArrayList<>(Arrays.asList(1,2,5,5,5,1,2,5,5,5));
        System.out.println(meFaltanDeLaClase(indices, categorias, 2));
        System.out.println(meFaltanDeLaClase2(indices, categorias, 2));
        ArrayList<Integer> museoA = new ArrayList<>(Arrays.asList(3,5,7,10,15,16));
        ArrayList<Integer> museoB = new ArrayList<>(Arrays.asList(4,10,5,8));
        //System.out.println(puedoCambiar(museoB,museoA ));
    }


    //[1,2,5,5,5,1,2,5,5,5]
    public static ArrayList<Integer> clases(ArrayList<Integer> lista) {
        ArrayList<Integer> salida = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.indexOf(lista.get(i))==i) {
                salida.add(lista.get(i));
            }
        }
        return salida;
    }

    //([1,3,6,8], [1,2,5,5,5,1,2,5,5,5], 2)
    public static ArrayList<Integer> meFaltanDeLaClase(ArrayList<Integer> indices, ArrayList<Integer> categorias, int categoria) {
        ArrayList<Integer> salida = new ArrayList<>();

        for (int i = 0; i < indices.size(); i++) {
            if (categorias.get(indices.get(i)) == categoria) {
                salida.add(indices.get(i));
            }
        }
        return salida;
    }
    //([1,3,6,8], [1,2,5,5,5,1,2,5,5,5], 2)
    public static ArrayList<Integer> meFaltanDeLaClase2(ArrayList<Integer> indices, ArrayList<Integer> categorias, int categoria) {
        ArrayList<Integer> salida = new ArrayList<>();
        // recorre elemento a elemento
        for (Integer elemento : indices) {
            if (categorias.get(elemento)==categoria) {//equivalente a python get() == []
                salida.add(elemento);
            }
        }
        return salida;
    }
    
    //([3,5,7,10,15,16],[4,10,5,8])
    public static ArrayList<Integer> noTengo(ArrayList<Integer> museoA, ArrayList<Integer> museoB) {
        ArrayList<Integer> salida = new ArrayList<>();
        
        for (Integer i : museoA) {
            if (!museoB.contains(i)) {
                salida.add(i);
            }
        }
        return salida;
    }

    public static int puedoCambiar(ArrayList<Integer> museoA, ArrayList<Integer> museoB) {
        int intercambiaMuseoA = noTengo(museoA, museoB).size();
        int intercambiaMuseoB = noTengo( museoB, museoA).size();
        return Math.min(intercambiaMuseoA, intercambiaMuseoB);
    }










}
