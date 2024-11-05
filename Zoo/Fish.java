package Zoo;

public class Fish extends Animal {
    public Fish(String id, double height) {
        super(id, height);
    }

    @Override
    public String getType() {
        return "Fish";
    }
}
