package primos.hija;
import primos.Primos;
import java.util.ArrayList;

public class PruebaPrimos extends Primos {
    public PruebaPrimos(Integer numero) {
        super();
        setNumero(numero);
    }
    /*@return ArrayList Devuelve la lista de numeros primos*/
    public ArrayList<Integer> listaPrimos() {
        if(isPrimo()==true){
            for(int i = 1; i<=numero; i++){
                if (primos(i)==true){
                    listaSalida.add(i);
                }
            }
            return listaSalida;
        }else{
            for(int i = 1; i<=numero; i++){
                if (primos(i)==true){
                    listaSalida.add(i);
                }
            }
            return listaSalida;
        }
    }
    /*@return Integer que seá el primo más cercano de la lista salida*/
    public Integer MasCercano() {
        return listaSalida.get(listaSalida.size()-1);
    }
}
