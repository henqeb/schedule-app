import java.util.HashMap;

public class Person {

    private String name;
    private String email; // TODO: make unique (HashSet of mails in main class)
    private HashMap<Integer, Boolean> schedule; // <time, isFree>

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.schedule = new HashMap<>();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }
}