import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("Henrik", "henrik@mail.com");
        Person p2 = new Person("Kirneh", "kirneh@mail.com");
        Person p3 = new Person("Alice", "alice@mail.com");
        Person p4 = new Person("Bob A.", "bob@mail.com");
        Person p5 = new Person("Charlie", "charlie@mail.com");
        Person p6 = new Person("Bob B.", "bob@mail.com"); // test duplicate mails
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);
        personList.add(p5);
        personList.add(p6);

        Scheduler scheduler = new Scheduler(personList);

        Meeting m1 = new Meeting(personList, 11);
    }
}
