package Zoo;

public class Hippo extends Animal implements AnimalWithSound {
    public Hippo(String id, double height) {
        super(id, height);
    }

    @Override
    public void makeSound() {
        System.out.println("Grunt");
    }

    @Override
    public String getType() {
        return "Hippo";
    }
}
