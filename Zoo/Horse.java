package Zoo;

public class Horse extends Animal implements AnimalWithSound {
    public Horse(String id, double height) {
        super(id, height);
    }

    @Override
    public void makeSound() {
        System.out.println("Neigh");
    }

    @Override
    public String getType() {
        return "Horse";
    }
}
