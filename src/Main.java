import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demo class for scheduling app.
 */
public class Main {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    
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

        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        Person charlie = scheduler.getPerson(2);
        Person dolly = scheduler.getPerson(3);

        LocalDateTime m1startTime = LocalDateTime.parse("12.01.2024 13:30", formatter);
        LocalDateTime m1endTime = LocalDateTime.parse("12.01.2024 14:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m1startTime, m1endTime);
        
        LocalDateTime m2startTime = LocalDateTime.parse("12.01.2024 13:30", formatter);
        LocalDateTime m2endTime = LocalDateTime.parse("12.01.2024 14:00", formatter);
        scheduler.createMeeting(Arrays.asList(bob, dolly), m2startTime, m2endTime);
        
        LocalDateTime m5startTime = LocalDateTime.parse("12.01.2024 20:00", formatter);
        LocalDateTime m5endTime = LocalDateTime.parse("12.01.2024 23:30", formatter);
        scheduler.createMeeting(Arrays.asList(bob, dolly), m5startTime, m5endTime);

        LocalDateTime m3startTime = LocalDateTime.parse("12.01.2024 08:30", formatter);
        LocalDateTime m3endTime = LocalDateTime.parse("12.01.2024 10:30", formatter);
        scheduler.createMeeting(Arrays.asList(dolly, charlie), m3startTime, m3endTime);

        LocalDateTime m4startTime = LocalDateTime.parse("12.01.2024 08:30", formatter);
        LocalDateTime m4endTime = LocalDateTime.parse("12.01.2024 09:30", formatter);
        scheduler.createMeeting(Arrays.asList(bob, dolly), m4startTime, m4endTime);


        List<TimeInterval> availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, charlie), "12.01.2024");
        for (TimeInterval interval : availableTimeslots)
            System.out.printf("%s - %s\n", interval.startTime, interval.endTime);
    }

    public static void demo() {
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

        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        Person charlie = scheduler.getPerson(2);
        Person dolly = scheduler.getPerson(3);

        LocalDateTime m1startTime = LocalDateTime.parse("12.01.2024 13:30", formatter);
        LocalDateTime m1endTime = LocalDateTime.parse("12.01.2024 14:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m1startTime, m1endTime);
        
        LocalDateTime m2startTime = LocalDateTime.parse("12.01.2024 13:00", formatter);
        LocalDateTime m2endTime = LocalDateTime.parse("12.01.2024 13:30", formatter);
        scheduler.createMeeting(Arrays.asList(charlie, bob), m2startTime, m2endTime);

        System.out.println("x) Printing all schedules\n");
        scheduler.printAllSchedules();
    }

    // public static void oldDemo() {
    //     String[] names = {"Alice", "Bob A.", "Charlie", "Bob B.", "Dolly"};
    //     String[] emails = {"alice@mail.com", "bob@mail.com", "charlie@mail.com",
    //                        "bob@mail.com", "dolly@mail.com"};
    //     Scheduler scheduler = new Scheduler();

    //     for (int i = 0; i < names.length; i++) {
    //         try {
    //         scheduler.createAndAddPerson(names[i], emails[i]);
    //         } catch (IllegalArgumentException e) {
    //             System.out.println(e);
    //             continue; // continue for the sake of the demo
    //         }
    //     }

    //     // 1) list of persons (should only be 4, since Bob B. has a duplicate email address)
    //     System.out.println("1) Original list of persons.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     scheduler.printPersonList();
    //     System.out.println();

    //     // create meetings
    //     Person alice = scheduler.getPerson(0);
    //     Person bob = scheduler.getPerson(1);
    //     Person charlie = scheduler.getPerson(2);
    //     Person dolly = scheduler.getPerson(3);

    //     // 2) Schedules of Alice and Bob
    //     System.out.println("2) Schedules of Alice and Bob after creating a meeting at 09:00.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     scheduler.createMeeting(Arrays.asList(alice, bob), 9);
    //     alice.printSchedule();
    //     System.out.println();
    //     bob.printSchedule();
    //     System.out.println();

    //     //3) conflicting schedules
    //     System.out.println("3) Setting up a meeting that conflicts with existing ones.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), 9);
    //     System.out.println();

    //     //4) suggest available timeslots based on existing individual schedules
    //     System.out.println("4) Suggesting available timeslots.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     boolean[] availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, charlie, dolly));
        
    //     // create some more meetings
    //     scheduler.createMeeting(Arrays.asList(alice, charlie, dolly), 13);
    //     // invalid starting time
    //     scheduler.createMeeting(Arrays.asList(alice, bob), 16);
    //     scheduler.createMeeting(Arrays.asList(alice, bob), 14);
        
    //     // 5) final result
    //     System.out.println("\nFinal schedules after creating more meetings:");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     scheduler.printAllSchedules();
    //     // 6) Available timeslots for everyone
    //     System.out.println("6) Final available timeslots for everyone");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, bob, charlie, dolly));
    // }
}
