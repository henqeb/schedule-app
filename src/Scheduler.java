import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Functions as "main system" for the scheduling app.
 */
public class Scheduler {

    private List<Person> personList;
    private HashSet<String> emailSet; // set of unique emails
    private List<Meeting> scheduledMeetings;

    public Scheduler() {
        this.personList = new ArrayList<>();
        this.emailSet = new HashSet<>();
        this.scheduledMeetings = new ArrayList<>();
    }

    /**
     * 
     * @param participantList
     * @param startingTime
     */
    public void createMeeting(List<Person> participantList, int startingTime) {
        // check if participants are free
        for (var person : participantList) {
            if (!person.isAvailable(startingTime)) {
                System.out.printf("%s is not available at given time.", person);
                break;
            }
            person.updateSchedule(startingTime, false);
        }
    }

    /**
     * Creates a new instance of Person and adds said Person to list of all persons.
     * @throws IllegalArgumentException
     */
    public void createAndAddPerson(String name, String email) throws IllegalArgumentException {
        // check if email already exists
        if (!emailSet.add(email)) {
            String error = String.format("The email address %s already exists.", email);
            throw new IllegalArgumentException(error);
        }

        Person person = new Person(name, email);
        personList.add(person);        
    }

    // TODO:test fjern
    public void printPersonList() {
        for (var person : personList) System.out.println(person);
    }
}