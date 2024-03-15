package app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Person {

    private String name;
    private String email;
    private HashMap<LocalDate, List<Meeting>> schedule; // TODO: change to SortedMap or TreeMap

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.schedule = new HashMap<>();
    }

    /**
     * Checks if person is available for a meeting by iterating over schedule.
     * @param startTime start of meeting
     * @param endTime end of meeting
     * @return false if there exists a meeting in the schedule which collides with proposed meeting, true otherwise.
     */
    public boolean isAvailable(LocalDate date, TimeInterval interval) {
        if (!this.schedule.containsKey(date) || this.schedule.get(date).isEmpty())
            return true;

        int startDiff;
        int endDiff;
        for (Meeting meeting : this.schedule.get(date)) {
            startDiff = interval.startTime.compareTo(meeting.getEndTime());
            endDiff = interval.endTime.compareTo(meeting.getStartTime());
            // if (newStartTime < otherEndTime && otherStartTime < newEndTime)
            if (startDiff < 0 && endDiff > 0) 
                return false;
        }

        return true;
    }

    public void addMeetingToSchedule(Meeting meeting) {
        LocalDate meetingDate = meeting.getDate();
        if (this.schedule.containsKey(meetingDate)) { // TODO: replace with HashMap.merge()?
            this.schedule.get(meetingDate).add(meeting);
        }
        else {
            this.schedule.put(meetingDate, Arrays.asList(meeting));
        }
    }

    ///////////////////// Printers, getters, setters /////////////////////

    public void printSchedule() {
        System.out.printf("%s's schedule:\n\n", this);

        for (Entry<LocalDate, List<Meeting>> entry : this.schedule.entrySet()) {
            System.out.printf("Date: %s\n", entry.getKey());
            System.out.println("---------------------------------------------------------------------------");
            for (Meeting meeting : entry.getValue()) {
                System.out.println(meeting);
            }
        }
    }

    /**
     * @return defensive copy of schedule field
     */
    public HashMap<LocalDate, List<Meeting>> getSchedule() {
        return new HashMap<>(this.schedule);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.email);
    }
}