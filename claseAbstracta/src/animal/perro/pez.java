package animal.perro;

import animal.Animal;

public class pez extends Animal {
    
    public pez() {
        super();
        setNombre("nemo");
    }

    public void animalMoverse() {
        System.out.println("el pez esta nadando.");
    }
}
