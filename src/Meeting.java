import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    
    private List<Person> participants;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Meeting(List<Person> participantList, LocalDateTime startTime, LocalDateTime endTime) {
        this.participants = participantList;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
