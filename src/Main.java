import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob A.", "Charlie", "Bob B.", "Dolly"};
        String[] emails = {"alice@mail.com", "bob@mail.com", "charlie@mail.com",
                           "bob@mail.com", "dolly@mail.com"};
        Scheduler scheduler = new Scheduler();

        for (int i = 0; i < names.length; i++) {
            try {
            scheduler.createAndAddPerson(names[i], emails[i]);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                continue;
            }
        }

        // list of persons (should only be 4, since Bob B. has a duplicate email address)
        scheduler.printPersonList();
        System.out.println();

        // create meetings
        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        scheduler.createMeeting(Arrays.asList(alice, bob), 9);
        alice.printSchedule();
        bob.printSchedule();
    }
}
