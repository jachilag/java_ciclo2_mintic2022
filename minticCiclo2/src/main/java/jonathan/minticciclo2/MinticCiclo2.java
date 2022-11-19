package jonathan.minticciclo2;

import static jonathan.minticciclo2.funProp.*;

import jonathan.minticciclo2.figuras.*;
import jonathan.minticciclo2.animales.*;

public class MinticCiclo2 {

    public static void main(String[] args) {

        System.out.println(repetNtimes("\n", 2));
        System.out.println(repetNtimes("=", 50));
        //pruebaMatrices2();
        usoSwitchCase();
        System.out.println(repetNtimes("=", 50));
        System.out.println(repetNtimes("\n", 2));
    }


    public static void usoSwitchCase() {

        System.out.println("elija una opcion: \n"+
                            "1. vivir\n"+
                            "2. morir\n"+
                            "3. sufrir\n"+
                            "4. escapar\n"+
                            "0. salir\n"
                            );

        int dato = Integer.valueOf(Input("seleccione una opcion: "));

        // if (dato == 1) {
        //     System.out.println("dame tu dinero");
        // } else if(dato==2){
        //     System.out.println("mueres, igual tomo tu dinero");
        // } else if(dato==3){
        //     System.out.println("luchas pero , igual tomo tu dinero");
        // } else if(dato==4){
        //     System.out.println("escapas, te alcanzo, igual tomo tu dinero");
        // } else if(dato==0){
        //     System.out.println("fin del juego .... x)");
        // } else{
        //     System.out.println("errro");
        // }


        //swith case
        switch (dato) {
            case 1:
            System.out.println("dame tu dinero");
                break;
            case 2:
            System.out.println("mueres, igual tomo tu dinero");
                break;
            case 3:
            System.out.println("luchas pero , igual tomo tu dinero");
                break;
            case 4:
            System.out.println("escapas, te alcanzo, igual tomo tu dinero");
                break;
            case 0:
            System.out.println("fin del juego .... x)");
            break;
            default:
            System.out.println("error");
                break;
        }

        
    }
















    // ==========================================================================
    // implementacion ejemplo animales
    public static void AppAnimal() {
        Animal animal1 = new Animal();
        System.out.println(animal1.showInfo());
        // lectura de datos privados en la clase animal mediante getter
        // System.out.println("este es el peso: "+ animal1.getPeso());
        // System.out.println("este es la altura : "+ animal1.getAltura());
        // System.out.println("este es el color: "+ animal1.getColor());

        // cambio de los atributos mediante settes
        // animal1.setAltura(1.7d);
        // animal1.setPeso(40.2);
        // animal1.setColor("fosforescente");
        // System.out.println(animal1.showInfo());

        Animal animal2 = new Animal(20.3d, 1.12d, "Verde Esmeralda");
        System.out.println(animal2.showInfo());
        System.out.println(repetNtimes("-", 50));

        Reptil reptil1 = new Reptil("tortuga", true, 40);
        System.out.println(reptil1.showInfo());
        System.out.println(repetNtimes("-", 50));

        Ave ave1 = new Ave("golondrina", true, true);
        System.out.println(ave1.showInfo());
        System.out.println(repetNtimes("-", 50));

        Mamifero mamifero = new Mamifero("canguro", 9, true);
        System.out.println(mamifero.showInfo());
        System.out.println(repetNtimes("-", 50));

        Mamifero mamifero2 = new Mamifero("canguro", 9, true);
        System.out.println(mamifero2.showInfo());
        System.out.println(repetNtimes("-", 50));

    }

    // ==========================================================================
    // prueba matrices:
    public static void pruebaMatrices2() {
        // int matriz1[][] = { { 2, 3, -1 }, { 0, 2, 4 }, { -2, 5, 6 } };
        // printArray(matriz1);
        // System.out.println(determinante(matriz1));

        // int matriz2[][] = { { 8, 2, -1, -4 }, { 3, 5, -3, 11 }, { 24, 6, 1, -12 }, {
        // 2, 2, 7, -1 } };
        // printArray(matriz2);
        // System.out.println(repetNtimes("-", 50));
        // printArray(inverseMatrix(matriz2));

        String[] entrada = Input("ingrese vector: ").replace("[", "").replace("]", "").split(",");
        printArray(entrada);
    }

    public static int[] uniqueValues(int[] vector) {
        int cont1 = 1;
        // determinar cuantos unicos hay
        for (int i = 0; i < vector.length; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (vector[i] == vector[j]) {
                    break;
                }
                cont1 += (j == i - 1) ? 1 : 0;
            }
        }

        System.out.println(cont1);
        //creacion de vector salida con los valores unicos
        int[] salida = new int[cont1];
        salida[0] = vector[0];
        int cont2 = 0;
        for (int i = 0; i < vector.length; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (vector[i] == vector[j]) {
                    break;
                }
                cont2 += (j == i - 1) ? 1 : 0;
                if (j == i - 1) {
                    salida[cont2] = vector[i];
                }
            }
        }

        return salida;
    }

    // ==========================================================================
    // implementacion del ejemplo de las figuras geometricas
    public static void AppFigura() {
        // instansiacion de los objetos
        Circulo circulo = new Circulo("amarillo", 3);
        Triangulo triangulo1 = new Triangulo("rojo", 20, 30);
        Triangulo triangulo2 = new Triangulo("verde", 125, 160, 225);
        Cuadrado cuadrado = new Cuadrado("azul", 5);

        // triangulos
        System.out.println("TRIANGULO con datos de base y altura:");
        System.out
                .println("color: " + triangulo1.color + " base: " + triangulo1.base + " altura: "
                        + triangulo1.altura);
        System.out.println("area: " + triangulo1.calcularArea() + "   "
                + "perimetro: " + triangulo1.calcularPerimetro()[0] + " - "
                + triangulo1.calcularPerimetro()[1]);
        System.out.println(repetNtimes("-", 50));

        System.out.println("TRIANGULO con datos de sus tres lados:");
        System.out.println("color: " + triangulo2.color + " ladoA: " + triangulo2.ladoA + " ladoB: "
                + triangulo2.ladoB
                + " ladoC: " + triangulo2.ladoC);
        System.out.println("area: " + triangulo2.calcularArea() + "   "
                + "perimetro: " + triangulo2.calcularPerimetro()[0] + " - "
                + triangulo2.calcularPerimetro()[1]);
        System.out.println(repetNtimes("-", 50));

        // cuadrado
        System.out.println("CUADRADO:");
        System.out.println("color: " + cuadrado.color + " lado: " + cuadrado.lado);
        System.out.println("area: " + cuadrado.calcularArea() + "   "
                + "perimetro: " + cuadrado.calcularPerimetro());
        System.out.println(repetNtimes("-", 50));

        // circulo
        System.out.println("CIRCULO:");
        System.out.println("color: " + circulo.color + " radio: " + circulo.radio);
        System.out.println("area: " + circulo.calcularArea() + "   "
                + "perimetro: " + circulo.calcularPerimetro());
        System.out.println(repetNtimes("-", 50));
    }

    // ==========================================================================
    // prueba de matrices funciones propias
    public static void pruebaMatrices() {
        int[][] Vedades2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        String[][] saludo = {{"hola", "jonathan", "como"}, {"hola", "eris", "como"},
                {"hola", "montse", "como"}};
        // printMat(Vedades);
        int[] edades = {5, 2, 3, 8, 1};
        // String[] edades2 = toStr(edades);
        // printArray(saludo);
        System.out.println(averageArray(toFloat(edades)));
        printArray(Vedades2[1]);
        printArray(column(Vedades2, 0));
        printArray(saludo);
    }

} // fin Main