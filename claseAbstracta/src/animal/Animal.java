package animal;

/*CLASE ABSTRACTA:
 * una clase abstracta:
 * 1. solo puede ser padre
 * 2. no puede ser instanciada
 * 3. se usa para crear metodos abstractos
 * 4. los metodos abstractos son inplementados en las clases hijas
 * 5. si hay un metodo abstracto, la clase debe ser abstracta
 */
public abstract class Animal {
    protected String nombre;
    public Animal(){

    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract void animalMoverse();

    public void animalAlimentarse(){
        System.out.println("el animal "+ nombre +" se esta alimentando");
    }


    
}
