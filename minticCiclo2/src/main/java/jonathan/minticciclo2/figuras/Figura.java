package jonathan.minticciclo2.figuras;

public class Figura {
    public String color;    //atributo
    

    public Figura() {//constructor
        this.color = "rojo";
    }

    public float calcularPerimetro() {//metodo
        System.out.println("imprime un perimetro: ");
        return 0;
    }
    
    public float calcularArea() {//metodo
        System.out.println("imprime un area: ");
        return 0;
    }

    public void pintar(int x, int y) {//metodo
        System.out.println("soy la figura pintada en la posicion: " + x +", " + y);
    }
}
