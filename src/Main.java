import app.*;

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

        // oldDemo(scheduler);
        demo(scheduler);
    }

    public static void demo(Scheduler scheduler) {
        // 1) list of persons (should only be 4, since Bob B. has a duplicate email address)
        System.out.println("1) Original list of persons (size should be 4).");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.printPersonList();
        System.out.println();

        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        Person charlie = scheduler.getPerson(2);
        Person dolly = scheduler.getPerson(3);

        // 2) create first meeting and print schedules
        System.out.println("2) Creating a meeting for Alice and Bob and printing schedules:");
        System.out.println("-------------------------------------------------------------------------------");
        LocalDateTime m0startTime = LocalDateTime.parse("12.03.2024 08:00", formatter);
        LocalDateTime m0endTime = LocalDateTime.parse("12.03.2024 09:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m0startTime, m0endTime);
        alice.printSchedule();
        bob.printSchedule(); System.out.println();
        
        // 3) more meetings on a different date (with overlaps)
        System.out.println("3) Creating more meetings on different date (with overlapping schedules):");
        System.out.println("-------------------------------------------------------------------------------");
        LocalDateTime m1startTime = LocalDateTime.parse("12.01.2024 13:00", formatter);
        LocalDateTime m1endTime = LocalDateTime.parse("12.01.2024 14:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m1startTime, m1endTime);
        
        LocalDateTime m2startTime = LocalDateTime.parse("12.01.2024 13:15", formatter);
        LocalDateTime m2endTime = LocalDateTime.parse("12.01.2024 13:45", formatter);
        scheduler.createMeeting(Arrays.asList(charlie, dolly), m2startTime, m2endTime);

        LocalDateTime m3startTime = LocalDateTime.parse("12.01.2024 16:00", formatter);
        LocalDateTime m3endTime = LocalDateTime.parse("12.01.2024 17:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m3startTime, m3endTime);

        LocalDateTime m5startTime = LocalDateTime.parse("12.01.2024 15:45", formatter);
        LocalDateTime m5endTime = LocalDateTime.parse("12.01.2024 16:15", formatter);
        scheduler.createMeeting(Arrays.asList(dolly, bob), m5startTime, m5endTime);
        System.out.println();

        LocalDateTime m4startTime = LocalDateTime.parse("12.01.2024 15:30", formatter);
        LocalDateTime m4endTime = LocalDateTime.parse("12.01.2024 16:30", formatter);
        scheduler.createMeeting(Arrays.asList(charlie, dolly), m4startTime, m4endTime);

        alice.printSchedule(); System.out.println();
        bob.printSchedule(); System.out.println();
        charlie.printSchedule(); System.out.println();
        dolly.printSchedule(); System.out.println();

        // 4) find available timeslots for everyone on 12.01.2024
        System.out.println("4) Finding available timeslots for everyone on 12.01.2024:");
        System.out.println("-------------------------------------------------------------------------------");
        List<TimeInterval> availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, bob, charlie, dolly),
                                                                       "12.01.2024");
        for (TimeInterval interval : availableTimeslots)
            System.out.printf("%s - %s\n", interval.startTime, interval.endTime);
        System.out.println();

        // 5) find available timeslots for everyone on 12.03.2024
        System.out.println("5) Finding available timeslots for everyone on 12.03.2024:");
        System.out.println("-------------------------------------------------------------------------------");
        availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, bob, charlie, dolly),
                                                                       "12.03.2024");
        for (TimeInterval interval : availableTimeslots)
            System.out.printf("%s - %s\n", interval.startTime, interval.endTime);
    }

    public static void oldDemo(Scheduler scheduler) {
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
        LocalDateTime m0startTime = LocalDateTime.parse("01.01.2024 09:00", formatter);
        LocalDateTime m0endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m0startTime, m0endTime);
        alice.printSchedule();
        System.out.println();
        bob.printSchedule();
        System.out.println();

        //3) conflicting schedules
        System.out.println("3) Setting up a meeting that conflicts with existing ones.");
        System.out.println("-------------------------------------------------------------------------------");
        LocalDateTime m1startTime = LocalDateTime.parse("01.01.2024 09:00", formatter);
        LocalDateTime m1endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
        scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), m1startTime, m1endTime);
        System.out.println();

        //4) suggest available timeslots based on existing individual schedules
        System.out.println("4) Suggesting available timeslots.");
        System.out.println("-------------------------------------------------------------------------------");
        List<TimeInterval> availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, charlie, dolly),
                                                                       "01.01.2024");
        for (TimeInterval interval : availableTimeslots)
            System.out.printf("%s - %s\n", interval.startTime, interval.endTime);
        
        // create some more meetings
        LocalDateTime m2startTime = LocalDateTime.parse("01.01.2024 13:00", formatter);
        LocalDateTime m2endTime = LocalDateTime.parse("01.01.2024 14:00", formatter);
        scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), m2startTime, m2endTime);
        // invalid starting time
        LocalDateTime m4startTime = LocalDateTime.parse("01.01.2024 13:10", formatter);
        LocalDateTime m4endTime = LocalDateTime.parse("01.01.2024 13:40", formatter);
        scheduler.createMeeting(Arrays.asList(bob, alice), m4startTime, m4endTime);

        LocalDateTime m3startTime = LocalDateTime.parse("01.01.2024 15:00", formatter);
        LocalDateTime m3endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
        scheduler.createMeeting(Arrays.asList(bob, alice), m3startTime, m3endTime);

        
        // 5) final result
        System.out.println("\nFinal schedules after creating more meetings:");
        System.out.println("-------------------------------------------------------------------------------");
        scheduler.printAllSchedules();
    }
}