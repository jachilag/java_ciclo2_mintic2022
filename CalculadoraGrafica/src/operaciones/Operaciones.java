package operaciones;

public class Operaciones {
    //atributos
    private double valor1;
    private double valor2;

    //constructor
    public Operaciones(double valor1, double valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    //metodos
    public double multiplicar(){
        return valor1 * valor2;
    }
    public double dividir(){
        return valor1 / valor2;
    }
    public double restar(){
        return valor1 - valor2;
    }
    public double sumar(){
        return valor1 + valor2;
    }


    //getter y setter
    public double getValor1() {
        return valor1;
    }

    public void setValor1(double valor1) {
        this.valor1 = valor1;
    }

    public double getValor2() {
        return valor2;
    }

    public void setValor2(double valor2) {
        this.valor2 = valor2;
    }
}
