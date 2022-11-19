package enmarcar;

/*
 * se soluciona el lidiar con los diferentes tipos de datos, pero hay repetibilidad de 
 * codigo. por lo tanto no es una buena solucion
 */


public class Enmarcar {
    protected int num;
    protected double num2;
    protected String nombres;

    public Enmarcar(int num) {
        this.num = num;
    }

    public Enmarcar(double num2) {
        this.num2 = num2;
    }

    public Enmarcar(String nombres) {
        this.nombres = nombres;
    }

    public void dibujarString(){
        String s = "*  "+nombres+"  *\n";
        String linea = "";
        String mensaje;
        for(int i = 0; i < s.length()-1;i++) {
            linea += "*";
        }
        mensaje = linea + "\n" + s + linea;
        System.out.println(mensaje);
    }
    public void dibujarDouble(){
        String s = "*  "+num2+"  *\n";
        String linea = "";
        String mensaje;
        for(int i = 0; i < s.length()-1;i++) {
            linea += "*";
        }
        mensaje = linea + "\n" + s + linea;
        System.out.println(mensaje);
    }

    public void dibujarInteger(){
        String s = "*  "+num+"  *\n";
        String linea = "";
        String mensaje;
        for(int i = 0; i < s.length()-1;i++) {
            linea += "*";
        }
        mensaje = linea + "\n" + s + linea;
        System.out.println(mensaje);
    }
}
