package Zoo;

public class Cat extends Animal implements AnimalWithSound {
    public Cat(String id, double height) {
        super(id, height);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow");
    }

    @Override
    public String getType() {
        return "Cat";
    }
}
