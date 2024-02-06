import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Functions as "main system" for the scheduling app.
 */
public class Scheduler {

    private List<Person> personList;
    private HashSet<String> emailSet; // set of unique emails

    public Scheduler() {
        this.personList = new ArrayList<>();
        this.emailSet = new HashSet<>();
    }

    /**
     * 
     * @throws IllegalArgumentException
     */
    public void createAndAddPerson(String name, String email) throws IllegalArgumentException {
        // check if email already exists
        if (!emailSet.add(email)) {
            String error = String.format("Error: The email address %s already exists.", email);
            throw new IllegalArgumentException(error);
        }

        Person person = new Person(name, email);
        personList.add(person);
        
        return;
    }
}