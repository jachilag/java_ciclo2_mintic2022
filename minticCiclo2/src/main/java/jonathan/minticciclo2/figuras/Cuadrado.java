package jonathan.minticciclo2.figuras;

public class Cuadrado {

    public float lado;
    public String color;

    public Cuadrado(String color, float lado) {
        this.color=color;
        this.lado = lado;
    }

    public float calcularArea() {
        return (float) Math.pow(this.lado, 2);
    }

    public float calcularPerimetro() {
        return 4*this.lado;
    }

    public void pintar(int x, int y) {
    }

    
}
