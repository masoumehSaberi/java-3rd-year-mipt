package Zoo;

import java.util.HashSet;
import java.util.Set;

public abstract class Animal {
    private static final Set<String> ids = new HashSet<>();
    private final String id;
    private double height;
    private Supervisor supervisor;

    @Override
    public String toString(){
        return "{id : " + this.id + ", height : " + this.height + " }";
    }

    public Animal(String id, double height) {
        synchronized (ids) { // we use synchronize to ensure thread safety
            //ids must be unique
            if (ids.contains(id)) {
                throw new IllegalArgumentException("ID already exists: " + id);
            }
            ids.add(id);
        }
        this.id = id;
        this.height = height;
    }

    public void removeId(String id){
        ids.remove(id);
    }

    public String getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public boolean canMakeSound() {
        return this instanceof AnimalWithSound;
    }

    public abstract String getType();
}
