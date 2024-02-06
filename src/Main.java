import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("Henrik", "henrik@mail.com");
        Person p2 = new Person("Kirneh", "kirneh@mail.com");
        Person p3 = new Person("Alice", "kirneh@mail.com");
        Person p4 = new Person("Bob", "kirneh@mail.com");
        Person p5 = new Person("Charlie", "kirneh@mail.com");
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);

        Meeting m1 = new Meeting(personList, 11);
    }
}
