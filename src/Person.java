import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Person {

    private String name;
    private String email;
    private List<Meeting> schedule;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.schedule = new ArrayList<>();
    }

    public void printSchedule() {
        System.out.printf("%s schedule\n", this);

        for (Entry<Integer, Boolean> entry : schedule.entrySet()) {
            String availability = "free";
            int time = entry.getKey();
            if (!entry.getValue()) availability = "busy";
            System.out.printf("%d:00 - %d:00: %s\n", time, time+1, availability);
        }
    }

    public boolean isAvailable(int time) {
        return schedule.get(time);
    }

    public void updateSchedule(int time, boolean isAvailable) {
        schedule.put(time, isAvailable);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }
}