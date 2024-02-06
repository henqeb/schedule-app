import java.util.List;

public class Meeting {
    
    private List<Person> participants;
    // using an integer to represent time, as meetings can only start at the hour mark and last one hour.
    // e.g., 14:00 is represented as 14, 08:00 as 8, etc.
    private int startingTime;

    public Meeting(List<Person> participantList, int startingTime) {
        this.participants = participantList;
        this.startingTime = startingTime;
    }
}
