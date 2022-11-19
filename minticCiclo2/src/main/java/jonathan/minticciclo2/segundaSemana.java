package jonathan.minticciclo2;

import static jonathan.minticciclo2.funProp.*;




public class segundaSemana {
    
    //CALCULADORA:
    public static void menuCalculadora(){
        char opcion;
        int a=0,b=0;
        boolean salir = false;
        
        do {            
            System.out.println("Bienvenido a la calculadora");
            System.out.println("1. Sumar\n2. Resta\n3. Multiplicar\n0. salir ");
            opcion = Input("\nElija una opcion: ").charAt(0);
            System.out.println(opcion);

            if(opcion == '1' || opcion == '2' || opcion == '3'){
                a = Integer.valueOf(Input("\n\nIngrese primer numero: "));
                b = Integer.valueOf(Input("Ingrese segundo numero: "));
            }

            switch (opcion) {
                case '0':
                    salir = true;
                    break;
                case '1':
                    System.out.println("la suma es : "+suma(a,b));
                    break;
                case '2':
                    System.out.println("la resta es : "+resta(a,b));
                    break;
                case '3':
                    System.out.println("la multiplicacion es : "+multiplicacion(a,b));
                    break;
                default:
                    System.out.println("esta ingresando un valor equivocado");
                    break;
            } 
        } while (!salir);
    }
    public static int suma(int a, int b){
        return a + b;
    }
    public static int resta(int a, int b){
        return a - b;
    }
    public static int multiplicacion(int a, int b){
        return a * b;
    }
    
    //TALLER CADENAS
    public static void menuCadenas(){
        System.out.println("\n\n===== TALLER CADENAS ======\n\n"
                + "1. Ocurrencias\n"
                + "2. Son todos letras?\n"
                + "3. Consonantes\n"
                + "4. Son iguales dos cadenas?\n"
                + "5. Concatenar dos cadenas\n"
                + "6. Invertir cadena\n"
                + "7. Palindromo\n"
                + "0. SALIR\n");

        char opcion = Input("Seleccione una opcion: ").charAt(0);
        System.out.println("\n");

        switch (opcion) {
            case '0':
                System.out.print("===== FIN DEL PROGRAMA =====");
                break;

            case '1':
                String cadena = Input("Ingrese una cadena: ");
                char letra = Input("Ingrese una letra: ").charAt(0);
                int ocurr = countStr(cadena, letra);
                System.out.println("Hay "+ ocurr +" ocurrencia"+((ocurr==1)?"":"s"));
                menuCadenas();
                break;

            case '2':
                boolean soloLetras = allLetters(Input("Ingrese una cadena: "));
                String salida = (soloLetras)?"Todas son letras":"NO Todas son letras";
                System.out.print(salida);
                menuCadenas();
                break;

            case '3':
                int consonantes = countConsonants(Input("Ingrese una cadena: "));
                System.out.println("Hay " + consonantes + " consonante" +((consonantes==1)?"":"s") );
                menuCadenas();
                break;

            case '4':
                String cadena1 = Input("Ingrese una cadena: ");
                String cadena2 = Input("Ingrese otra cadena: ");
                boolean iguales = (cadena1.equals(cadena2));
                System.out.println((iguales)?"Son Iguales":"No son iguales");;
                menuCadenas();
                break;

            case '5':
                String cadenaR = Input("Ingrese una cadena: ") + Input("Ingrese otra cadena: ");
                System.out.println("la cadena resultante es: " + cadenaR);
                menuCadenas();
                break;

            case '6':
                System.out.println(invert(Input("Ingrese una cadena: ")));
                menuCadenas();
                break;

            case '7':
                String cadenaP = Input("Ingrese una cadena: ");
                System.out.println((cadenaP.equals(invert(cadenaP)))?"Es palindrome":"NO Es palindrome");
                menuCadenas();
                break;

            default:
                System.out.println("\nSELECCIONE UNA OPCION VALIDA\n");
                menuCadenas();
                break;
        }
    }
}
