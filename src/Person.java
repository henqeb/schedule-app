import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class Person {

    private String name;
    private String email; // TODO: make unique (HashSet of mails in main class)
    private HashMap<Integer, Boolean> schedule; // <time, isFree>

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.schedule = new HashMap<>();
        initializeScheduleValues();
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

    public void initializeScheduleValues() {
        // 08:00 - 16:00 (last scheduling opportunity is 15:00)
        for (int i = 8; i < 16; i++) {
            schedule.put(i, true);
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }
}