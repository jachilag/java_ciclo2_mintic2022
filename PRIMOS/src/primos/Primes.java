package primos;

import java.util.ArrayList;

public abstract class Primes {
    //ATRIBUTOS
    protected Integer num;
    protected ArrayList<Integer> listOutput;

    //CONSTRUCTOR
    public Primes(){listOutput = new ArrayList<>();}

    //METODOS
    public boolean isPrime(Integer numero){
        boolean salida = true;
        int contador = 0;
        for (int i = 1; i <= numero; i++) {
            if (contador < 2){
                contador += (numero%i ==0)?1:0;
            } else {
                salida = false;
                break;
            }
        }
        return salida;
    }

    public boolean itIsPrime(){return isPrime(num);}

    public Integer listSize(){return listOutput.size();}

    //METODOS ABSTRACTOS
    public abstract ArrayList<Integer> listPrimes();
    public abstract Integer lastPrime();

    //GETTERS Y SETTERS
    public Integer getNum() {return num;}

    public void setNum(Integer num) {this.num = num;}


}
