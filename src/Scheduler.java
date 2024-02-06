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
     * Schedules meetings (by updating availabilities) for given list of persons.
     * @param participantList persons to attend the meeting.
     * @param startingTime starting time of meeting.
     */
    public void createMeeting(List<Person> participantList, int startingTime) {
        if (startingTime < 8 || startingTime > 15) {
            System.out.println("Error: Invalid starting time for the meeting. Specify a new starting time between 08:00 and 15:00");
            return;
        }

        for (var person : participantList) {
            if (!person.isAvailable(startingTime)) {
                System.out.printf("%s is not available at %d:00.\n", person, startingTime);
                break;
            }
            person.updateSchedule(startingTime, false);
        }

        scheduledMeetings.add(new Meeting(participantList, startingTime));
    }

    /**
     * Creates a new instance of Person and adds said Person to list of all persons.
     * @throws IllegalArgumentException if duplicate email exists.
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

    public void printAllSchedules() {
        for (var person : personList) {
            person.printSchedule();
            System.out.println();
        }
    }

    public void printPersonList() {
        for (var person : personList) System.out.println(person);
    }

    public Person getPerson(int i) {
        return personList.get(i);
    }
}