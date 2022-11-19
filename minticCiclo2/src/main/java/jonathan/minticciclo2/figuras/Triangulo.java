package jonathan.minticciclo2.figuras;


public class Triangulo {

    public float base;
    public float altura;
    public float ladoA;
    public float ladoB;
    public float ladoC;
    public byte datosTriangulo;
    public String color;

    public Triangulo(String color, float base, float altura) {
        this.base = base;
        this.altura = altura;
        this.color=color;
        datosTriangulo = 1;
    }

    public Triangulo(String color, float ladoA, float ladoB, float ladoC) {
        this.color= color;
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
        datosTriangulo = 2;
    }

    public float calcularArea() {
        float resultado = 0;
        switch (datosTriangulo) {
            case 1:
                resultado = this.base * this.altura / 2;
                break;
            case 2:
                float p = (this.ladoA + this.ladoB + this.ladoC) / 2; // semiperimetro
                resultado = (float) Math.pow(p * (p - this.ladoA) * (p - this.ladoB) * (p - this.ladoC), 0.5);
                break;
            default:
                break;
        }
        return resultado;
    }

    public float[] calcularPerimetro() {
        float[] resultado = new float[2];
        switch (datosTriangulo) {
            case 1:
                resultado[0] = (float) (2
                        * (Math.pow((Math.pow(this.base / 2, 2) + Math.pow(this.altura, 2)), 0.5)) + this.base);
                resultado[1] = (float) (this.base + this.altura
                        + Math.pow((Math.pow(this.base, 2) + Math.pow(this.altura, 2)), 0.5));
                break;
            case 2:
                resultado[0] = this.ladoA + this.ladoB + this.ladoC;
            default:
                break;
        }
        return resultado;
    }

    public void pintar(int x, int y) {
        System.out.println("soy el triangulo pintado en: " + x + ", " + y);
    }

}
