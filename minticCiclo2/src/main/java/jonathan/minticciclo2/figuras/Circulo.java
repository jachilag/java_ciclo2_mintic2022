package jonathan.minticciclo2.figuras;

public class Circulo {
    public float radio;
    public String color;
    
    public Circulo(String color, float radio) {
        this.color=color;
        this.radio = radio;
    }

    public float calcularArea() {
        return  (float) (Math.PI*Math.pow(this.radio, 2));
    }
    
    public float calcularPerimetro() {
        return (float) (2*Math.PI*this.radio);
    }

    public void pintar(int x, int y) {//metodo
        System.out.println("soy el circulo pintado en: " +x +", " + y);
    }
}
