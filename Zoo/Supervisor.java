package Zoo;

import java.util.HashSet;
import java.util.Set;

public class Supervisor {
    private int id;
    private String name;
    private final Set<Animal> animals;
    private static final Set<Integer> ids = new HashSet<>();

    public Supervisor(int id, String name) {
        this.name = name;
        this.animals = new HashSet<>();
        synchronized (ids) {
            if (ids.contains(id)) {
                throw new IllegalArgumentException("ID already exists: " + id);
            }
            ids.add(id);
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    @Override
    public String toString(){
        return "{id : " + this.id + ", height : " + this.name + " }";
    }
}
