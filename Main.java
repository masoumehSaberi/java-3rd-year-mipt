import Zoo.*;


public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();

        zoo.addSupervisorChangeListener(new Zoo.SupervisorChangeListener() {
            @Override
            public void onSupervisorChange(Animal animal, Supervisor oldSupervisor, Supervisor newSupervisor) {
                System.out.println("Supervisor changed for animal ID: " + animal.getId() +
                        " from " + (oldSupervisor != null ? oldSupervisor.getName() : "none") +
                        " to " + (newSupervisor != null ? newSupervisor.getName() : "none"));
            }
        });

        zoo.getAnimalsByType("Dog");
        Animal cat1 = new Cat("cat1", 0.5);
        Animal cat2 = new Cat("cat2", 0.75);

        //adding an animal with a used id:
        //Animal cat3 = new Cat("cat1", 0.25); // this will throw an exception

        Animal dog1 = new Dog("dog1", 2);
        Animal dog2 = new Dog("dog2", 1.5);
        Animal fish1 = new Fish("fish1", 0.20);
        zoo.addAnimal(cat1);
        zoo.addAnimal(cat2);
        zoo.addAnimal(dog1);
        zoo.addAnimal(dog2);
        zoo.addAnimal(fish1);
        System.out.println(zoo.getAnimalsByType("Cat"));
        zoo.removeAnimal("cat2");
        System.out.println(zoo.getAnimalsByType("Cat"));
        System.out.println(zoo.getAnimalsWithHeightGreaterThan(0.5));
        System.out.println(zoo.getSoundMakerAnimals());

        Supervisor sup1 = new Supervisor(1, "sup1");
        Supervisor sup2 = new Supervisor(2, "sup2");

        zoo.addSupervisor(sup1);
        zoo.addSupervisor(sup2);

        zoo.assignSupervisorToAnimal("cat1", 1);
        zoo.assignSupervisorToAnimal("dog1", 1);
        zoo.assignSupervisorToAnimal("dog2", 1);
        zoo.assignSupervisorToAnimal("fish1", 2);

        System.out.println(zoo.getAnimalsBySupervisor("sup1"));
        System.out.println(zoo.getAnimalsBySupervisor("sup2"));
        System.out.println(zoo.getAnimalsBySupervisor(1));

    }
}