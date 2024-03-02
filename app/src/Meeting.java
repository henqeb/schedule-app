package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Meeting {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private List<Person> participants;
    private LocalDateTime startTime; // TODO: split into LocalDate and TimeInterval
    private LocalDateTime endTime;

    public Meeting(List<Person> participantList, LocalDateTime startTime,
                                                 LocalDateTime endTime) throws IllegalArgumentException {
        if (participantList.isEmpty()) {
            throw new IllegalArgumentException("List of persons is empty. Meeting not created.");
        }
        if (endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time of meeting can not be before start time. Meeting not created.");
        }

        this.participants = participantList;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    ///////////////////// Printers and getters /////////////////////

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        String start = this.startTime.format(this.formatter);
        String end = this.endTime.format(this.formatter);
        String participantsString = "";
        String participant;
        for (var person : this.participants) {
            participant = person.toString() + " ";
            participantsString += participant;
        }
        return String.format("Meeting at %s - %s with: %s ", start, end, participantsString);
    }
}
