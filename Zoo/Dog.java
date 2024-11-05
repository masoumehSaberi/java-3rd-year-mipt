package Zoo;

public class Dog extends Animal implements AnimalWithSound {
    public Dog(String id, double height) {
        super(id, height);
    }

    @Override
    public void makeSound() {
        System.out.println("Bark");
    }

    @Override
    public String getType() {
        return "Dog";
    }
}
