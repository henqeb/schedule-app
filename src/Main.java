import java.util.Arrays;

/**
 * Demo class for scheduling app.
 */
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
                continue; // continue for the sake of the demo
            }
        }

        // 1) list of persons (should only be 4, since Bob B. has a duplicate email address)
        System.out.println("1) Original list of persons.");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.printPersonList();
        System.out.println();

        // create meetings
        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        Person charlie = scheduler.getPerson(2);
        Person dolly = scheduler.getPerson(3);

        // 2) Schedules of Alice and Bob
        System.out.println("2) Schedules of Alice and Bob after creating a meeting at 09:00.");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.createMeeting(Arrays.asList(alice, bob), 9);
        alice.printSchedule();
        System.out.println();
        bob.printSchedule();
        System.out.println();

        //3) conflicting schedules
        System.out.println("3) Setting up a meeting that conflicts with existing ones.");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), 9);
        System.out.println();

        //4) suggest available timeslots based on existing individual schedules
        System.out.println("4) Suggesting available timeslots.");
        System.out.println("-------------------------------------------------------------------------------");
        boolean[] availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, charlie, dolly));
        
        // create some more meetings
        scheduler.createMeeting(Arrays.asList(alice, charlie, dolly), 13);
        // invalid starting time
        scheduler.createMeeting(Arrays.asList(alice, bob), 16);
        scheduler.createMeeting(Arrays.asList(alice, bob), 14);
        
        // 5) final result
        System.out.println("\nFinal schedules after creating more meetings:");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.printAllSchedules();
        // 6) Available timeslots for everyone
        System.out.println("6) Final available timeslots for everyone");
        System.out.println("-------------------------------------------------------------------------------");
        availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, bob, charlie, dolly));
    }
}
