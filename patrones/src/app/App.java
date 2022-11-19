package app;

import enmarcar.Enmarcar;
import genericidad.SolucionGenericidad;
import objectSolution.ObjectSolution;

public class App {
    public static SolucionGenericidad<String>[] nombreLista;
    public static void main(String[] args) {

        SolucionGenericidad<Integer> objetoInteger = new SolucionGenericidad<Integer>(34);
        SolucionGenericidad<String> objetoString = new SolucionGenericidad<>("Diego");
        System.out.println(objetoInteger.getObj() + 15);
        System.out.println(objetoString.getObj().charAt(2));
        //Objetos();
    }

    public static void Objetos(){
        ObjectSolution objetoInteger = new ObjectSolution(12);
        ObjectSolution objetoDouble = new ObjectSolution(23.456d);
        ObjectSolution objetoString = new ObjectSolution("De nuevo, buenas noches tripulantes");
        //System.out.println(objetoInteger.getObj() +15);
        objetoInteger.DibujarObject();
        objetoDouble.DibujarObject();
        objetoString.DibujarObject();
        //System.out.println(objetoString.getObj().charAt(2));
    }

    public static void Strings(){
        Enmarcar enmarcarString = new Enmarcar("Buenas noches tripulantes");
        enmarcarString.dibujarString();
    }
    public static void Doubles(){
        Enmarcar enmarcarDouble= new Enmarcar(230.4587656d);
        enmarcarDouble.dibujarDouble();
    }

    public static void Enteros(){
        Enmarcar enmarcarEntero = new Enmarcar(34536);
        enmarcarEntero.dibujarInteger();
    }
}
