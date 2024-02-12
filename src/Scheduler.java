import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Functions as "main system" for the scheduling app.
 */
public class Scheduler {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private List<Person> personList;
    private HashSet<String> emailSet; // set of unique emails
    private List<Meeting> scheduledMeetings;

    public Scheduler() {
        this.personList = new ArrayList<>();
        this.emailSet = new HashSet<>();
        this.scheduledMeetings = new ArrayList<>();
    }

    // TODO: refactor
    /**
     * TODO: write docs
     */
    public void findAvailableTimeslots(List<Person> persons, String dateInput) {
        LocalDateTime dateStart = LocalDateTime.parse(dateInput+" 00:00", formatter);
        LocalDateTime dateEnd = LocalDateTime.parse(dateInput+" 23:59", formatter);

        // for (Person person : persons) {

        // }
    }

    /**
     * Schedules a meeting for given list of persons.
     * @param participantList persons to attend the meeting
     * @param startingTime starting time of meeting in format dd.MM.yyyy HH:mm
     * @param endTime end time of meeting in format dd.MM.yyyy HH:mm
     */
    public void createMeeting(List<Person> participantList, LocalDateTime startingTime, LocalDateTime endTime) {
        Meeting newMeeting = new Meeting(participantList, startingTime, endTime);
        
        // check availability of every person
        for (var person : participantList) {
            if (!person.isAvailable(startingTime, endTime)) {
                System.out.printf("%s is not available in given time interval %s - %s.\n",
                                  person, startingTime.format(formatter), endTime.format(formatter));
                return;
            }
        }
        
        // update availability
        for (var person : participantList) {
            person.addMeetingToSchedule(newMeeting);
        }

        scheduledMeetings.add(newMeeting);
    }

    /**
     * Creates a new instance of Person and adds said Person to list of all persons.
     * @throws IllegalArgumentException if duplicate email exists.
     */
    public void createAndAddPerson(String name, String email) throws IllegalArgumentException {
        if (!emailSet.add(email)) {
            String error = String.format("The email address %s already exists.", email);
            throw new IllegalArgumentException(error);
        }

        Person person = new Person(name, email);
        personList.add(person);        
    }

    // TODO:test fjern
    public void printAllMeetings() {
        for (Meeting meeting : scheduledMeetings) System.out.println(meeting);
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