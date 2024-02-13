import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Map.Entry;

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
    public List<TimeInterval> findAvailableTimeslots(List<Person> persons, String dateInput) {
        LocalDateTime dateStartTime = LocalDateTime.parse(dateInput+" 00:00", formatter);
        LocalDateTime dateEndTime = LocalDateTime.parse(dateInput+" 23:59", formatter);
        List<TimeInterval> availableIntervals = new ArrayList<>();
        // HashMap<startTime, endTime> to represent existing time intervals and avoid adding duplicates
        HashMap<LocalTime, LocalTime> existingIntervals = new HashMap<>();

        List<TimeInterval> busyTimeslots = filterBusyTimeslots(existingIntervals);
        
        return availableIntervals;
    }

    public List<TimeInterval> filterBusyTimeslots(HashMap<LocalTime, LocalTime> existingIntervalMap) {
        List<TimeInterval> busyIntervals = new ArrayList<>();

        for (Meeting meeting : scheduledMeetings) {
            LocalTime currStartTime = meeting.getStartTime().toLocalTime();
            LocalTime currEndTime = meeting.getEndTime().toLocalTime();
            
            if (existingIntervalMap.containsKey(currStartTime)) {
                if (existingIntervalMap.get(currStartTime).compareTo(currEndTime) < 0) {
                    // replace interval with extended endTime
                    existingIntervalMap.put(currStartTime, currEndTime);
                }
                else {
                    continue; // time interval already exists, skip
                }
            }
            else {
                existingIntervalMap.put(currStartTime, currEndTime);
            }
        }

        for (Entry<LocalTime, LocalTime> entry : existingIntervalMap.entrySet()) {
            LocalTime startTime = entry.getKey();
            LocalTime endTime = entry.getValue();
            busyIntervals.add(new TimeInterval(startTime, endTime));
        }
        busyIntervals.sort(Comparator.comparing(TimeInterval::getEndTime));

        return busyIntervals;
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