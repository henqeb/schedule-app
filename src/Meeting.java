import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Meeting {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private List<Person> participants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meeting(List<Person> participantList, LocalDateTime startTime, LocalDateTime endTime) {
        // TODO: (precondition) sjekk om personliste er tom
        // TODO: (precondition) endTime > startTime

        this.participants = participantList;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        // TODO: gjør stringen penere (fyll med komma)
        String start = this.startTime.format(formatter);
        String end = this.endTime.format(formatter);
        String participantsString = "";
        String participant;
        for (var person : this.participants) {
            participant = person.toString() + " ";
            participantsString += participant;
        }
        return String.format("Meeting at %s - %s with %s\n ", start, end, participantsString);
    }
}
