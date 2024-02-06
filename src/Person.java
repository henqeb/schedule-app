public class Person {

    private String name;
    private String email; // TODO: make unique (HashSet of mails in main class)

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }
}