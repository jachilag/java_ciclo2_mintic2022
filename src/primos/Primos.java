package primos;

import java.util.ArrayList;

public abstract class Primos {
    protected Integer numero;
    protected ArrayList<Integer> listaSalida;
    public Primos(){
        listaSalida = new ArrayList<>();
    }

    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public boolean primos(Integer numero){
        boolean salida = true;
        int contador = 0;
        for(int i=1; i <= numero;i++){
            if(contador<2){
                if(numero%i==0){
                    contador++;
                }
            }else if (contador>=2) {
                salida = false;
                break;
            }
        }
        return salida;
    }

    public boolean isPrimo(){
        return primos(numero);
    }

    public abstract ArrayList<Integer> listaPrimos();
    public abstract Integer MasCercano();
    public Integer ListaSize(){
        return listaSalida.size();
    }
}
