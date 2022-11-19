package primos.hija;
import primos.Primes;
import java.util.ArrayList;

public class testPrimes extends Primes {

    //CONSTRUCTOR
    public testPrimes(Integer numero) {
        super();
        setNum(numero);
    }

    //IMPLEMENTACION DE METODOS ABSTRACTOS DE LA CLASE PADRE "PRIMES"
    public ArrayList<Integer> listPrimes() {
        for (int i = 1; i <= num; i++) {
            if(isPrime(i)){listOutput.add(i);}
        }
        return listOutput;
    }

    public Integer lastPrime(){return listOutput.get(listOutput.size()-1);}

}
