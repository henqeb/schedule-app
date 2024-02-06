import java.util.HashMap;

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

    public boolean isAvailable(int time) {
        return schedule.get(time);
    }

    public void updateSchedule(int time, boolean isAvailable) {
        schedule.put(time, isAvailable);
    }

    public void initializeScheduleValues() {
        for (int i = 0; i < 8; i++) {
            schedule.put(i, true);
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, email);
    }
}