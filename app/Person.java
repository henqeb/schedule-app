// package source;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private String email;
    private List<Meeting> schedule;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.schedule = new ArrayList<>(); // TODO: change to HashMap<LocalDate, List<Meeting>>
    }

    /**
     * Checks if person is available for a meeting by iterating over schedule.
     * @param startTime start of meeting
     * @param endTime end of meeting
     * @return false if there exists a meeting in the schedule which collides with proposed meeting, true otherwise.
     */
    public boolean isAvailable(LocalDateTime startTime, LocalDateTime endTime) {
        if (this.schedule.isEmpty()) return true;

        int startDiff;
        int endDiff;
        for (Meeting meeting : this.schedule) {
            // if (newStartTime < otherEndTime && otherStartTime < newEndTime)
            startDiff = startTime.compareTo(meeting.getEndTime());
            endDiff = endTime.compareTo(meeting.getStartTime());
            if (startDiff < 0 && endDiff > 0) {
                return false;
            } 
        }

        return true;
    }

    public void addMeetingToSchedule(Meeting meeting) {
        this.schedule.add(meeting);
    }

    ///////////////////// Printers and getters /////////////////////

    public void printSchedule() {
        System.out.printf("%s's (%s) schedule\n", this.name, this.email);
        for (Meeting meeting : this.schedule) System.out.println(meeting);
    }

    public List<Meeting> getSchedule() {
        return new ArrayList<>(schedule);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.email);
    }
}