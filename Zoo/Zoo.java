package Zoo;

import java.util.*;


interface AnimalWithSound {
    void makeSound();
}


public class Zoo {
    private final Map<String, Animal> animals;
    private final Map<Integer, Supervisor> supervisors;
    private final TreeSet<Animal> animalsByHeight;
    private final Map<String, Set<Animal>> animalsByType;

    public Zoo() {
        animals = new HashMap<>();
        supervisors = new HashMap<>();
        animalsByHeight = new TreeSet<>(Comparator.
                comparingDouble(Animal::getHeight));
        this.animalsByType = new HashMap<>();
    }


    public Zoo(Set<Animal> initialAnimals) {
        this();
        for (Animal animal : initialAnimals) {
            animals.put(animal.getId(), animal);
            animalsByHeight.add(animal);
            animalsByType.computeIfAbsent(animal.getType(), k -> new HashSet<>())
                    .add(animal);

        }
    }

    private final Set<SupervisorChangeListener> supervisorChangeListeners = new HashSet<>();
    public interface SupervisorChangeListener {
        void onSupervisorChange(Animal animal, Supervisor oldSupervisor, Supervisor newSupervisor);
    }
    public void addSupervisorChangeListener(SupervisorChangeListener listener) {
        supervisorChangeListeners.add(listener);
    }

    public void removeSupervisorChangeListener(SupervisorChangeListener listener) {
        supervisorChangeListeners.remove(listener);
    }

    private void notifySupervisorChange(Animal animal, Supervisor oldSupervisor, Supervisor newSupervisor) {
        for (SupervisorChangeListener listener : supervisorChangeListeners) {
            listener.onSupervisorChange(animal, oldSupervisor, newSupervisor);
        }
    }


    public void addAnimal(Animal animal) {
        animals.put(animal.getId(), animal);
        animalsByHeight.add(animal);
        animalsByType.computeIfAbsent(animal.getType(), k -> new HashSet<>())
                .add(animal);
    }


    public void removeAnimal(String id) {
        animalsByHeight.remove(animals.get(id));
        animalsByType.get(animals.get(id).getType()).remove(animals.get(id));
        animals.remove(id);
    }

    public void addSupervisor(Supervisor supervisor) {
        if (supervisors.containsKey(supervisor.getId())) {
            throw new IllegalArgumentException("Supervisor with ID " + supervisor.getId() + " already exists.");
        }
        supervisors.put(supervisor.getId(), supervisor);
    }

    public void removeSupervisor(int supervisorId) {
        Supervisor supervisor = supervisors.remove(supervisorId);
        if (supervisor != null) {
            for (Animal animal : supervisor.getAnimals()) {
                animal.setSupervisor(null);
            }
            supervisor.getAnimals().clear();
        }
    }

    public void assignSupervisorToAnimal(String animalId, int supervisorId) {
        Animal animal = animals.get(animalId);
        Supervisor supervisor = supervisors.get(supervisorId);

        if (animal == null || supervisor == null) {
            throw new IllegalArgumentException("Invalid animal or supervisor ID.");
        }

        Supervisor oldSupervisor = animal.getSupervisor();

        if (oldSupervisor != null) {
            oldSupervisor.removeAnimal(animal);
        }

        supervisor.addAnimal(animal);
        animal.setSupervisor(supervisor);
        notifySupervisorChange(animal, oldSupervisor, supervisor);
    }

    public Set<Animal> getAnimalsBySupervisor(int supervisorId) {
        Supervisor supervisor = supervisors.get(supervisorId);
        return supervisor != null ? supervisor.getAnimals() : new HashSet<>();
    }

    public Set<Animal> getAnimalsBySupervisor(String name) {
        Set<Animal> result = new HashSet<>();
        for (Supervisor supervisor : supervisors.values()) {
            if (supervisor.getName().equals(name)) {
                result.addAll(supervisor.getAnimals());
            }
        }
        return result;
    }


    public Set<Animal> getAnimalsWithHeightGreaterThan(double height) {
        // Create a dummy animal with the specified height for comparison
        Animal dummyAnimal = new Animal("dummy", height) {
            @Override
            public String getType() {
                return "dummy";}
        };
        return animalsByHeight.tailSet(dummyAnimal, false);
    }

    public Set<Animal> getSoundMakerAnimals() {
        Set<Animal> result = new HashSet<>();
        for (Animal animal : animals.values()) {
            if (animal.canMakeSound()) {
                result.add(animal);
            }
        }
        return result;
    }


    public Set<Animal> getAnimalsByType(String type) {
        return animalsByType.getOrDefault(type, Collections.emptySet());
    }
}

