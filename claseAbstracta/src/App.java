import animal.Animal;
import animal.perro.Ave;
import animal.perro.perro;
import animal.perro.pez;



public class App {
    public static Animal[] animales;
    public static void main(String[] args){
        animales= new Animal[3];
        Animal perro = new perro();
        Animal pez = new pez();
        Animal ave = new Ave();

        animales[0] =perro ;
        animales[1] = ave;
        animales[2] = pez;

        listarMetodosAbstractos();

    }

    public static void  listarMetodosAbstractos(){
        for (Animal item : animales) {
            item.animalMoverse();
            item.animalAlimentarse();
            }
    }

}
