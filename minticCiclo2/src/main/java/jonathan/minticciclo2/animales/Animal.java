package jonathan.minticciclo2.animales;

public class Animal {
    private double peso;
    private double altura;
    private String color;

    public Animal() {
        this.peso = 30.4d;
        this.altura = 1.3d;
        this.color = "verde clarito";
        
    }


    public Animal(double peso, double altura, String color) {
        this.peso = peso;
        this.altura = altura;
        this.color = color;
    }

    public double getPeso() {
        return this.peso;
    }

    public double getAltura() {
        return this.altura;
    }

    public String getColor() {
        return this.color;
    }


    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String showInfo() {
        return "ANIMAL: {" + "peso: "+ this.peso+", altura: "+this.altura+ ", color: "+this.color+"}";
    }
}
