package jonathan.minticciclo2;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static jonathan.minticciclo2.funProp.Input;


public abstract class  primeraSemana {

    //=======================================================
    //volumen solidos esfera y cono
    public static double volumenEsfera(double radio){
        return ((4/3)*PI*pow(radio, 3));
    }
    public static double volumenCono(double h, double radio){
        return (PI*pow(radio, 2)*h)/3;
    }
    public static double volumenSolido(double radio1, double radio2, double h){
        return volumenEsfera(radio1)+volumenCono(radio2,h);
    }
    public static void programaVolumen(){
        System.out.println("\nEjercicio del volumen del solido: \n");
        double radio1 = Double.valueOf(Input("ingrese radio de esfera: "));
        double radio2 = Double.valueOf(Input("ingrese radio de cono: "));
        double h = Double.valueOf(Input("ingrese altura de cono: "));
        System.out.println("\nEl volumen del solido es: " + volumenSolido(radio1,radio2,h));
    }
    
    //=======================================================
    //ejercicios PRIMERA SEMANA
    public static String mayor(){
        String resultado;
        int a = Integer.valueOf("Ingrese PRIMER numero entero");
        int b = Integer.valueOf("Ingrese SEGUNDO numero entero");

        resultado = "el mayor es " + a;
        if(b>a){
            resultado =  "el mayor es " + b;
        } else if (a==b){
            resultado = a + " es igual a "+b;
        }
        return resultado;
    }
    public static String conducir(){
        String salida;
        String name = Input("ingrese su nombre: ").toUpperCase();
        byte edad = Byte.valueOf(Input("ingrese su edad: "));
        
        System.out.println("bienvenido "+name);
        if( edad >0 && edad<18 || edad>80){
            salida = "no tienes edad para conducir";
        }else if(edad>=18 && edad<=80){
            salida = "Ya tienes edad para conducir";
        } else {
            salida = "has ingresado una edad incorrecta";
        }      
        return salida;
    }
}
