package app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Meeting {

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // TODO: do something smarter than making two DateTimeFormatters
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private List<Person> participants;
    private LocalDate date;
    private TimeInterval fromTo;

    public Meeting(List<Person> participantList, LocalDate date, TimeInterval interval)
                                                            throws IllegalArgumentException {
        if (participantList.isEmpty()) {
            throw new IllegalArgumentException("List of persons is empty. Meeting not created.");
        }
        if (interval.endTime.isBefore(interval.startTime)) {
            throw new IllegalArgumentException("End time of meeting can not be before start time. Meeting not created.");
        }

        this.participants = participantList;
        this.date = date;
        this.fromTo = interval;
    }

    ///////////////////// Printers, getters, setters /////////////////////

    public LocalTime getStartTime() {
        return this.fromTo.startTime;
    }

    public LocalTime getEndTime() {
        return this.fromTo.endTime;
    }

    public LocalDate getDate() {
        return this.date; // LocalDate is immutable, no need for defensive copying
    }

    @Override
    public String toString() {
        String date = this.date.format(dateFormatter);
        String start = this.fromTo.startTime.format(this.timeFormatter);
        String end = this.fromTo.endTime.format(this.timeFormatter);
        String participantsString = "";
        String participant;
        for (var person : this.participants) {
            participant = person.toString() + " ";
            participantsString += participant;
        }
        return String.format("Meeting at %s, %s - %s with: %s ", date, start, end, participantsString);
    }
}
