package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import myJDBC.MyJDBC;




public abstract class funProp {
    public static MyJDBC myJDBC;
    public static enum NEED{EXIST,NOT_EXIST};
    public static enum NEED_PSWD{CREATE,VALIDATE};
    public static enum USUARIO{ADMINISTRADOR,CLIENTE,NO_IDENTIFICADO};
    public static enum ENTIDAD{PROVEEDOR,BICICLETA,MOTO,CLIENTE,INTENCIONES,SALIR,NULO};
    public static enum DATO{NOMBRE,APELLIDO,TELEFONO,CONTRASENA,  PRECIO_UNITARIO,ANIO,  ALIAS,FABRICANTE,TIPO,FECHA,  ACTUALIZAR,SALIR, DIRECCION_PROVEEDOR, AUTONOMIA, PROVEEDOR_ID};
    public static enum ACCION{CREAR,MODIFICAR,CONSULTAR,ELIMINAR,SALIR};
    public static enum FABRICANTE{MOTO, BICICLETA,NO_IDENTIFICADO};

    /**
     * this function simulates 'Input' function from Python language
     * 
     * @param text this is a String to deploy on screen to drop the fact
     * @return this is a string, in which could be applied parse to convert the
     *         dataType
     */
    public static String Input(String text) {
        String dato = null;
        try {
            System.out.print(text);
            Scanner scan1 = new Scanner(System.in);
            dato = scan1.nextLine();
        } catch (Exception e) {System.out.println("\nerror\n");}
        return dato;
    }

    /**
     * sirve para obligar al usuario a elegir una y solo una opcion. 
     * Queda en bucle hasta que se elija una opcion correcta.
     * @param mensaje mensaje que visualiza el usuario donde estan las opciones a elegir
     * @param opciones es un arreglo puro int[] que contiene las opciones que puede elegir. 
     * @return devuelve la opcion elegida dentro del grupo de opciones
     */
    public static int confirmation(String mensaje, int[] opciones) {
        boolean validacion=false;
        int salida=0;
        do {
            try {
                salida = Integer.valueOf(Input(mensaje));
                if (isValueInto(opciones, salida)) {validacion=true;break;}
                else {System.out.println("\nOPCION NO VALIDA - elija de nuevo\n");}
            } catch (Exception e) {System.out.println("\nOPCION NO VALIDA - elija de nuevo\n");}
        } while (!validacion);
        return salida;
    }

    public static long confirmation(String mensaje, long minimum, long maximum) {
        boolean validacion=false;
        long salida=0;
        do {
            try {
                salida = Long.valueOf(Input(mensaje));
                validacion = (salida >= minimum && salida <= maximum) ?true:false;
                if(!validacion) {System.out.println("\nOPCION NO VALIDA - elija de nuevo\n");}
            } catch (Exception e) {System.out.println("\nOPCION NO VALIDA - elija de nuevo\n");}
        } while (!validacion);
        return salida;
    }

    public static int confirmation(String mensaje, int minimum, int maximum) {
        return (int) (confirmation(mensaje, Long.valueOf(minimum), Long.valueOf(maximum)));
    }
  
    /**
     * this method repeats a string n times
     * 
     * @param cadena put the String which will repeat n times
     * @param times  number of times that string will be reapeated
     * @return return a string*nTimes
     */
    public static String repetNtimes(final String cadena, final int times) {
        String result = "";
        for (int i = 0; i < times; ++i) {
            result += cadena;
        }
        return result;
    }

   
    /**
     * this method returns a pure list [] of integers within concecutive series
     * starting with the first number until the end number and with a step.
     * @param first integer number
     * @param end integer number
     * @param step integer number
     * @return returns a list with the series
     */
    public static int[] consecutiveList(int first, int end, int step) {
        int dim = (Integer) (end-first+step)/step;
        int[] result = new int[dim];
        
        for (int i = 0; i < dim; i++) {
            result[i] = first + i*step;
        }
        return result;
    }

   
    /**
     * this method returns boolean value depending if a value is inside of array
     * @param vector this type have to be  string, integer or float
     * @param value this type is an object  
     * @return true or false if finds a value in array
     */
    public static boolean isValueInto(String[] vector, Object value) {
        boolean result = false;
        String valor = value +"" ;
        for (String object : vector) {
            result |= (object.equals(valor))?true:false;
        }
        return result;
    }
    
    public static boolean isValueInto(int[] vector, Object value) {
        return isValueInto(toStr(vector), value);
    }

    public static boolean isValueInto(float[] vector, Object value) {
        return isValueInto(toStr(vector), value);
    }


    /**
     * This void method prints an array in the console in one row separating each
     * element with a space character
     * 
     * @param arreglo insert a float or integer or String array
     */
    public static String printArray(float[] arreglo) {
        return printArray(toStr(arreglo));
    }

    public static String printArray(int[] arreglo) {
        return printArray(toStr(arreglo));
    }

    public static String printArray(String[] arreglo) {
        String result = "";
        int space = 3;
        String number, text;
        String sep=",";
        for (int i = 0; i < arreglo.length; i++) {
            text = sep + repetNtimes(" ", space);
            number =arreglo[i] + ((i == arreglo.length - 1) ? ("\n") : text);
            //System.out.print(number);
            result += number;
        }
        return result;
    }

    /**
     * This method convert all elements of an array from integer to float
     * 
     * @param element insert an integers array
     * @return the same array converted in float type
     */
    public static float[] toFloat(final int[] element) {
        final int size = element.length;
        final float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (float) element[i];
        }
        return arr;
    }

    /**
     * This method convert all elements of an array from float to integer
     * 
     * @param element insert an float array
     * @return the same array converted in integer type
     */
    public static int[] toInt(final float[] element) {
        final int size = element.length;
        final int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) element[i];
        }
        return arr;
    }

    /**
     * This method convert all elements of an array from number type to string
     * 
     * @param element insert an number array
     * @return the same array converted in String type
     */
    public static String[] toStr(int[] element) {
        int size = element.length;
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = String.valueOf(element[i]);
        }
        return arr;
    }

    public static String[] toStr(float[] element) {
        int size = element.length;
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = String.valueOf(element[i]);
        }
        return arr;
    }




// METODOS GENERALES PARA SOLUCION DE ESTE PROGRAMA
    public static ArrayList<String[]> menuConsultarGeneral(ENTIDAD entidad, String tabla, String columnasSQL) {
        myJDBC = new MyJDBC("grupo53", "grupo53", "Grupo53Ciclo2*");
        myJDBC.ConnectionMyDB();
        return (entidad != ENTIDAD.CLIENTE)? myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla):myJDBC.READ_as_ARRAY("SELECT "+ columnasSQL + " FROM " + tabla + " WHERE alias != 'admin';");
    }

    public static String[] arraylistToList(ArrayList<String[]> arraylist, int col) {
        String[] salida = new String[arraylist.size()];
        int cont = 0;
        for (String[] item : arraylist) {
            salida[cont] = item[col];
            cont++;
        }
        return salida;
    }

    public static String[][] toMatrixString(ArrayList<String[]> array, int columnas) {//pasa de un ArrayList a una matriz
        String[][] salida = new String[array.size()][columnas];
        int cont = salida.length-1;
        for (String[] item: array) {//iteracion por filas o item
            for (int i = 0; i < columnas; i++) {//iteracion por columna
                salida[cont][i] = item[i]; 
            }
            cont--;
        }
        return salida;
    }
    
    public static String TimeNow(){ //obtencion de fecha y hora de ahora
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return myDateObj.format(myFormatObj);
    }

    public static String toString(String textToString) {
        return "\n" + repetNtimes("-", 50) + textToString +"\n" + repetNtimes("-", 50);
    }

}// fin de la clase funProp