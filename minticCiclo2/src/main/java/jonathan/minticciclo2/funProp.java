package jonathan.minticciclo2;

import java.util.Scanner;

/**
 * This abstract class contains some functions useful for a lot of programs which
 * there are not anyone defined to default in Java language.
 * This class is separated in two main categories: string and chars methods,
 * arrays and matrix methods.
 * there will be more categories and methods in the future.
 * 
 * @author Jonathan Alexander Chila Guevara
 */
public abstract class funProp {

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
    // ==========================================================================
    // STRING'S AND CHARACTER'S METHODS
    // ==========================================================================

    /**
     * this method returns true if all characters are letters
     * 
     * @param text this contains the string
     * @return a flag evaluated
     */
    public static boolean allLetters(final String text) {
        int unicode;
        int iter = 0;
        boolean flag;

        do {
            unicode = text.charAt(iter);
            flag = ((unicode >= 65 && unicode <= 90) || (unicode >= 97 && unicode <= 122));
            iter++;
        } while (flag && iter < text.length());
        return flag;
    }

    /**
     * this method counts how many consonants there are in the string
     * 
     * @param cadena insert a string
     * @return number of consonants in the string
     */
    public static int countConsonants(String cadena) {
        int cont = 0;
        char letter;
        cadena = cadena.toLowerCase();
        for (int i = 0; i < cadena.length(); i++) {
            letter = cadena.charAt(i);
            if ((letter != 'A' && letter != 'E' && letter != 'I' && letter != 'O' && letter != 'U' &&
                    letter != 'a' && letter != 'e' && letter != 'i' && letter != 'o' && letter != 'u') &&
                    allLetters(cadena)) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * this method counts the currencys inside a word
     * 
     * @param text1 insert a text type string
     * @param text2 insert a word type string or character
     * @return integer of times a word is found in text
     **/
    public static int countStr(final String text1, final Object text2) {// overloaded Function
        final String texto3 = text2.toString();
        int cont = 0;
        final int lenText1 = text1.length();
        final int lenText2 = texto3.length();
        if (lenText1 >= lenText2) {
            for (int i = 0; i < (lenText1 - lenText2 + 1); i++) {
                cont += ((text1.substring(i, i + lenText2)).equals(texto3)) ? 1 : 0;
            }
        }
        return cont;
    }

    /**
     * this method returns a inverted string of inserted string in arguments
     * 
     * @param text insert a string
     * @return string inverted
     */
    public static String invert(final String text) {
        String salida = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            salida += text.charAt(i);
        }
        return salida;
    }

    /**
     * this method returns a boolean depending if the inserted string is palindrome.
     * it uses a inverted method
     * 
     * @param text insert a string
     * @return a boolean dataType
     */
    public static boolean palindrome(final String text) {
        return (text.equals(invert(text)));
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

    // ==========================================================================
    // ARRAYS
    // ==========================================================================

    /**
     * This method calculates the array's average
     * 
     * @param vector it could be float or integer type
     * @return a float
     */
    public static float averageArray(float[] vector) {
        int sum = 0;

        for (int i = 0; i < vector.length; i++) {
            sum += vector[i];
        }
        float salida = (float) sum / (float) vector.length;
        return salida;
    }

    public static float averageArray(int[] vector) {
        return averageArray(toFloat(vector));
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
     * This method calculates the PRODUCT DOT between two arrays
     * (it is the sum of factors who multiplied each element of the same posicion)
     * 
     * @param vector1 it could be integer or float
     * @param vector2 it could be integer or float
     * @return an integer or float array. please use method sumArray in order to
     *         obtain a number
     */
    public static float[] dotP(float[] vector1, float[] vector2) {
        float[] result = new float[vector2.length];
        if (vector1.length == vector2.length) {
            for (int i = 0; i < vector1.length; i++) {
                result[i] = vector1[i] * vector2[i];
            }
        }
        return result;
    }
    
    public static int[] dotP(int[] vector1, int[] vector2) {
        return toInt(dotP(toFloat(vector1), toFloat(vector2)));
    }

    public static float[] dotP(int[] vector1, float[] vector2) {
        return dotP(toFloat(vector1), vector2);
    }

    public static float[] dotP(float[] vector1, int[] vector2) {
        return dotP(vector1, toFloat(vector2));
    }


    public static float[] fillArray(int col, float value) {
        float[] result = new float[col];
        for (int i = 0; i < col; i++) {
            result[i] = value;
        }
        return result;
    }

    public static int[] fillArray(int col, int value) {
        return toInt(fillArray(col, (float) value));
    }

    public static String[] fillArray(int col, Object value) {
        String[] result = new String[col];
        for (int i = 0; i < col; i++) {
            result[i] = value.toString();
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
     * This method finds the maximum value of an array
     * 
     * @param element insert a float or integer array
     * @return a number float or integer depending of parameters
     */
    public static float mayor(float[] element) {
        int index = 0;
        for (int i = 0; i < element.length; i++) {
            index = (element[i] > element[index]) ? i : index;
        }
        return element[index];
    }

    public static int mayor(int[] element) {
        return (int) mayor(toFloat(element));
    }

    public static String mayor(String[] element) {
        int index = 0;
        for (int i = 0; i < element.length; i++) {
            index = (element[i].length() > element[index].length()) ? i : index;
        }
        return element[index];
    }

    /**
     * This method finds the minimum value of an array
     * 
     * @param vector insert a float or integer array
     * @return a number float or integer depending of parameters
     */
    public static int menor(int[] vector) {
        return (-1) * mayor(scalarProduct(vector, -1));
    }

    public static float menor(float[] vector) {
        return (-1) * mayor(scalarProduct(vector, -1));
    }

    public static String menor(String[] element) {
        int index = 0;
        for (int i = 0; i < element.length; i++) {
            index = (element[i].length() < element[index].length()) ? i : index;
        }
        return element[index];
    }

    /**
     * This method returns an array wich is multiplied a scalar number to each
     * element of array
     * 
     * @param vector  insert a float or integer array
     * @param escalar insert a float or integer number
     * @return a float or integer array depending of parameters
     */
    public static float[] scalarProduct(float[] vector, float escalar) {
        float[] result = new float[vector.length];

        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * escalar;
        }
        return result;
    }

    public static int[] scalarProduct(int[] vector, int escalar) {

        int[] result = new int[vector.length];




        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * escalar;
        }
        return result;
    }

    public static float[] scalarProduct(int[] vector, float escalar) {
        return scalarProduct(toFloat(vector), escalar);
    }

    /**
     * this method returns a number who is the result of the sum of the all
     * elements of array
     * 
     * @param vector insert a float or integer array
     * @return a float or integer number depending of parameters
     */
    public static int sumArray(int[] vector) {
        int sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += vector[i];
        }
        return sum;
    }

    public static float sumArray(float[] vector) {
        float sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += vector[i];
        }
        return sum;
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

    // ==========================================================================
    // MATRIX
    // ==========================================================================

    /**
     * This method returns an array with values of the column in the parameter
     * 
     * @param matrix insert a float or integer matrix
     * @param col    insert a integer of the column you want to extrat
     * @return a float or integer or String array depending of parameters
     */
    public static String[] column(String[][] matrix, int col) {
        String[] result = new String[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][col];
        }
        return result;
    }

    public static int[] column(int[][] matrix, int col) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][col];
        }
        return result;
    }

    public static float[] column(float[][] matrix, int col) {
        float[] result = new float[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][col];
        }
        return result;
    }

    
    public static float[][] deleteColumn(float[][] matrix, int column) {
        int n = matrix.length;
        int m = matrix[0].length;
        float[][] result = new float[n][m - 1];

        // recorrer matriz resultado
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (j >= column) {
                    result[i][j] = matrix[i][j + 1];
                } else if (j < column) {
                    result[i][j] = matrix[i][j];
                }
            }
        }
        return result;
    }
    
    public static int[][] deleteColumn(int[][] matrix, int column) {
        return toInt(deleteColumn(toFloat(matrix), column));
    }

    public static String[][] deleteColumn(String[][] matrix, int column) {
        int n = matrix.length;
        int m = matrix[0].length;
        String[][] result = new String[n][m - 1];

        // recorrer matriz resultado
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (j >= column) {
                    result[i][j] = matrix[i][j + 1];
                } else if (j < column) {
                    result[i][j] = matrix[i][j];
                }
            }
        }
        return result;
    }


    public static float[][] deleteRow(float[][] matrix, int row) {
        int n = matrix.length;
        int m = matrix[0].length;
        float[][] result = new float[n - 1][m];

        // recorrer matriz resultado
        for (int i = 0; i < n - 1; i++) {
            if (i >= row) {
                result[i] = matrix[i + 1];
            } else if (i < row) {
                result[i] = matrix[i];
            }
        }
        return result;
    }

    public static int[][] deleteRow(int[][] matrix, int row) {
        return toInt(deleteRow(toFloat(matrix), row));
    }

    public static String[][] deleteRow(String[][] matrix, int row) {
        int n = matrix.length;
        int m = matrix[0].length;
        String[][] result = new String[n - 1][m];

        // recorrer matriz resultado
        for (int i = 0; i < n - 1; i++) {
            if (i >= row) {
                result[i] = matrix[i + 1];
            } else if (i < row) {
                result[i] = matrix[i];
            }
        }
        return result;
    }
    

    
    public static float determinante(float[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        float result = 0;
        if (n == m) {
            if (n == 2) {
                result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            } else {
                for (int j = 0; j < matrix.length; j++) {
                    result += Math.pow(-1, j) * matrix[0][j] *
                            determinante(deleteColumn(deleteRow(matrix, 0), j));
                }
            }
        }
        return result;
    }

    public static int determinante(int[][] matrix) {
        return (int) determinante(toFloat(matrix));
    }

    /**
     * This method calculates the PRODUCT DOT between two matrixes
     * (it is the sum of factors who multiplied each element of the same posicion)
     * 
     * @param matrix1 it could be integer or float
     * @param matrix2 it could be integer or float
     * @return an integer or float array. please use method sumArray in order to
     *         obtain a number
     */
    public static float[][] dotP(float[][] matrix1, float[][] matrix2) {
        float[][] result = new float[matrix2.length][matrix2[0].length];
        if (matrix1.length == matrix2.length) {
            for (int i = 0; i < matrix1.length; i++) {
                for (int j = 0; j < matrix1[0].length; j++) {
                    result[i][j] = matrix1[i][j] * matrix2[i][j];
                }
            }
        }
        return result;
    }

    public static int[][] dotP(int[][] vector1, int[][] vector2) {
        return toInt(dotP(toFloat(vector1), toFloat(vector2)));
    }

    public static float[][] dotP(int[][] vector1, float[][] vector2) {
        return dotP(toFloat(vector1), vector2);
    }

    public static float[][] dotP(float[][] vector1, int[][] vector2) {
        return dotP(vector1, toFloat(vector2));
    }


    public static float[][] fillArray(int fil, int col, float value) {
        float[][] result = new float[fil][col];
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = value;
            }
        }
        return result;
    }

    public static int[][] fillArray(int fil, int col, int value) {
        return toInt(fillArray(fil, col, (float) value));
    }

    public static String[][] fillArray(int fil, int col, Object value) {
        String[][] result = new String[fil][col];
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = value.toString();
            }
        }
        return result;
    }


    public static float[][] inverseMatrix(float[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        float[][] result = new float[n][m];
        float det = determinante(matrix);

        if (n == m && det != 0) { // evaluates if the matrix has an inverse matrix
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[i][j] = (float) ((1/det)*Math.pow(-1, i + j) *
                            determinante(deleteColumn(deleteRow(matrix, i), j)));
                }
            }
        }
        return traspouseArray(result);
    }

    public static float[][] inverseMatrix(int[][] matrix) {
        return inverseMatrix(toFloat(matrix));
    }

    /**
     * This method finds the maximum value of an matrix
     * 
     * @param matrix insert a float or integer matrix
     * @return a float or integer number depending of parameters
     */
    public static float mayor(float[][] matrix) {
        int index1 = 0;
        int index2 = 0;
        boolean isThis;

        for (int j = 0; j < matrix.length; j++) {
            for (int i = 0; i < matrix[0].length; i++) {
                isThis = matrix[j][i] > matrix[index1][index2];
                index1 = (isThis) ? j : index1;
                index2 = (isThis) ? i : index2;
            }
        }
        return matrix[index1][index2];
    }

    public static String mayor(String[][] matrix) {
        String temp[] = new String[matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            temp[j] = mayor(matrix[j]);
        }
        return mayor(temp);
    }

    public static int mayor(int[][] matrix) {
        return (int) mayor(toFloat(matrix));
    }

    /**
     * This method finds the minimum value of an matrix
     * 
     * @param matrix insert a float or integer array
     * @return a float or integer number depending of parameters
     */
    public static float menor(float[][] matrix) {
        return (-1) * mayor(scalarProduct(matrix, -1));
    }

    public static String menor(String[][] matrix) {
        String temp[] = new String[matrix.length];
        for (int j = 0; j < matrix.length; j++) {
            temp[j] = menor(matrix[j]);
        }
        return menor(temp);
    }

    public static int menor(int[][] matrix) {
        return (-1) * mayor(scalarProduct(matrix, -1));
    }

    /**
     * This void method prints a matrix in the console in one row separating each
     * element with a space character
     * 
     * @param matrix insert a float or integer or String matrix
     */
    public static void printArray(int[][] matrix) {
        printArray(toStr(matrix));
    }

    public static void printArray(float[][] matrix) {
        printArray(toStr(matrix));
    }

    public static void printArray(String[][] matrix) {
        int space = 2;
        String number, text;
        int spaceM;
        int[] spacesCol = new int[matrix[0].length];
        int[] spacesRow = new int[matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {// trought columns
            for (int j = 0; j < matrix.length; j++) {// trought rows
                spacesRow[j] = digits(matrix[j][i]);
            }
            spacesCol[i] = mayor(spacesRow) + space;
        }

        for (int j = 0; j < matrix.length; j++) {// trought rows
            for (int i = 0; i < matrix[j].length; i++) {// trought columns
                spaceM = spacesCol[i] - digits(matrix[j][i]);
                text = repetNtimes(" ", spaceM) + matrix[j][i];
                number = (i == matrix[j].length - 1) ? (text + "\n") : text;
                System.out.print(number);
            }
        }
    }

    /**
     * This method returns a matrix wich is multiplied a scalar number to each
     * element of matrix
     * 
     * @param vector  insert a float or integer matrix
     * @param escalar insert a float or integer number
     * @return a float or integer matrix depending of parameters
     */
    public static float[][] scalarProduct(float[][] vector, float escalar) {
        float[][] result = new float[vector.length][vector[0].length];
        for (int i = 0; i < vector.length; i++) {// trought rows
            result[i] = scalarProduct(vector[i], escalar);
        }
        return result;
    }

    public static int[][] scalarProduct(int[][] vector, int escalar) {
        int[][] result = new int[vector.length][vector[0].length];
        for (int i = 0; i < vector.length; i++) {// trought rows
            result[i] = scalarProduct(vector[i], escalar);
        }
        return result;
    }

    public static float[][] scalarProduct(int[][] vector, float escalar) {
        return scalarProduct(toFloat(vector), escalar);
    }

    /**
     * This method convert all elements of a matrix from integer to float
     * 
     * @param matrix insert an integers matrix
     * @return the same array converted in float type
     */
    public static float[][] toFloat(int[][] matrix) {
        float[][] arr = new float[matrix.length][matrix[0].length];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = toFloat(matrix[j]);
        }
        return arr;
    }

    /**
     * This method convert all elements of a matrix from float to integer
     * 
     * @param matrix insert an float matrix
     * @return the same matrix converted in integer type
     */
    public static int[][] toInt(float[][] matrix) {
        int[][] arr = new int[matrix.length][matrix[0].length];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = toInt(matrix[j]);
        }
        return arr;
    }

    /**
     * This method convert all elements of a matrix from number type to string
     * 
     * @param matrix insert an number matrix
     * @return the same array converted in String type
     */
    public static String[][] toStr(int[][] matrix) {
        String[][] arr = new String[matrix.length][matrix[0].length];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = toStr(matrix[j]);
        }
        return arr;
    }

    public static String[][] toStr(float[][] matrix) {
        String[][] arr = new String[matrix.length][matrix[0].length];
        for (int j = 0; j < arr.length; j++) {
            arr[j] = toStr(matrix[j]);
        }
        return arr;
    }

    
    public static float[][] traspouseArray(float[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        float[][] result = new float[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    public static int[][] traspouseArray(int[][] matrix) {
        return toInt(traspouseArray(toFloat(matrix)));
    }

    // ==========================================================================
    // NUMBERS
    // ==========================================================================

    /**
     * this method count how many elements it is represented in screen:
     * if it is a number, it counts digits
     * if it is string, it counts characters
     * 
     * @param element insert data to counts
     * @return integer
     */
    public static int digits(final Object element) {
        final int result = (element + "").length();
        return result;
    }

    // ==========================================================================
    // OTHERS
    // ==========================================================================

    /**
     * this method pauses the program a time defined in the parameter.
     * 
     * @param time
     */
    public static void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}// fin de la clase funProp