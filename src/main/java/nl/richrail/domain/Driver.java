package nl.richrail.domain;

public class Driver {

    private String id;
    private String name;
    private int age;

    public Driver(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Driver: (id: " + this.id + ", name: " + this.name + ", age: " + this.age + ")";
    }

}
