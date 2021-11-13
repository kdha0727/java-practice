package jump2java.chap5;

class Animal {

    public static void nop () {}

    protected String name;  // name space
    public int age;  // primitive type - call by value

    public void setName(String name) {  // object type - call by reference
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}

class Dog extends Animal {
    public void sleep() {
        System.out.println(this.name+" zzz");
    }
}

class HouseDog extends Dog {
    public void sleep() {  // overriding
        System.out.println(this.name+" zzz in house");
    }
    public void sleep(int hour) {  // overloading
        System.out.println(this.name+" zzz in house for " + hour + " hours");
    }

    public HouseDog() {}

    public HouseDog(String name) {
        this();
        setName(name);
    }

    public HouseDog(int age) {
        this();
        this.age = age;
    }

    public HouseDog(String name, int age) {
        this(age);
        setName(name);
    }
}

/* ************ */

abstract class Predator extends Animal{
    public abstract String getFood();
    public boolean isPredator() {
        return true;
    }
}

interface Barkable {
    void bark();  // interface method must be public, you can omit keyword
}

interface Nop<T> {
    void nop(T t);
}

interface IntercacePolymorphism extends Nop<Object>, Barkable {}


class Tiger extends Predator implements Barkable {
    public String getFood() {
        return "apple";
    }
    public void bark() {
        System.out.println("어흥");
    }
}

class Lion extends Predator implements Barkable {
    public String getFood() {
        return "banana";
    }
    public void bark() {
        System.out.println("으르렁");
    }
}

class Bouncer {

    public void feed(Predator predator) {
        System.out.println("feed " + predator.getFood());
    }
    public void barkAnimal(Barkable animal) {
        if (animal != null) {
            animal.bark();
        }
    }
    public boolean isBarkable(Animal animal) {
        return (animal instanceof Barkable);
    }
}

public class Main {

    public static void main(String[] args) {

        Animal cat = new Animal();
        cat.setName("yang");
        cat.age = 5;
        System.out.println(cat.getName());

        Animal nul = new Dog();  // IS-A
        System.out.println(nul.getName());  // null

        HouseDog dog = new HouseDog("jan");
        dog.sleep();
        dog.sleep(3);
        Dog.nop();

        Bouncer b = new Bouncer();
        Tiger tiger = new Tiger();
        Lion lion = new Lion();
        b.feed(tiger);
        b.feed(lion);

        b.barkAnimal(tiger);
        b.barkAnimal(lion);

    }
}
