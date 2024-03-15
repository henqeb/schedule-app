import app.*;

import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Demo class for scheduling app.
 */
public class Main {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob A.", "Charlie", "Bob B.", "Dolly"};
        String[] emails = {"alice@mail.com", "bob@mail.com", "charlie@mail.com",
                           "bobby@mail.com", "dolly@mail.com"};
        Scheduler scheduler = new Scheduler();

        for (int i = 0; i < names.length; i++) {
            try {
            scheduler.createAndAddPerson(names[i], emails[i]);
            } catch (IllegalArgumentException e) {
                System.out.println(e);
                continue; 
            }
        }

        // oldDemo(scheduler);
        demo(scheduler);
        // test(scheduler);
    }

    public static void test(Scheduler scheduler) {
        Person alice = scheduler.getPerson(0);
        Person bob = scheduler.getPerson(1);
        Person charlie = scheduler.getPerson(2);
        Person dolly = scheduler.getPerson(4);

        LocalTime m0startTime = LocalTime.parse("08:00", timeFormatter);
        LocalTime m0endTime = LocalTime.parse("09:00", timeFormatter);
        TimeInterval m0interval = new TimeInterval(m0startTime, m0endTime);
        LocalDate m0date = LocalDate.parse("12.04.2024", dateFormatter);
        scheduler.createMeeting(Arrays.asList(alice, bob), m0date, m0interval);

        alice.printSchedule();
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
        Person dolly = scheduler.getPerson(4);

        // 2) create first meeting and print schedules
        System.out.println("2) Creating a meeting for Alice and Bob and printing schedules:");
        System.out.println("-------------------------------------------------------------------------------");
        LocalDate m0date = LocalDate.parse("12.03.2024", dateFormatter);
        LocalTime m0startTime = LocalTime.parse("08:00", timeFormatter);
        LocalTime m0endTime = LocalTime.parse("09:00", timeFormatter);
        TimeInterval m0interval = new TimeInterval(m0startTime, m0endTime);
        scheduler.createMeeting(Arrays.asList(alice, bob), m0date, m0interval);
        alice.printSchedule();
        bob.printSchedule(); System.out.println();
        
        // 3) more meetings on a different date (with overlaps)
        System.out.println("3) Creating more meetings on different date (with overlapping schedules):");
        System.out.println("-------------------------------------------------------------------------------");
        LocalDate m1date = LocalDate.parse("12.01.2024", dateFormatter);
        LocalTime m1startTime = LocalTime.parse("13:00", timeFormatter);
        LocalTime m1endTime = LocalTime.parse("14:00", timeFormatter);
        TimeInterval m1interval = new TimeInterval(m1startTime, m1endTime);
        scheduler.createMeeting(Arrays.asList(alice, bob), m1date, m1interval);
        
        LocalDate m2date = LocalDate.parse("12.01.2024", dateFormatter);
        LocalTime m2startTime = LocalTime.parse("13:15", timeFormatter);
        LocalTime m2endTime = LocalTime.parse("13:45", timeFormatter);
        TimeInterval m2interval = new TimeInterval(m2startTime, m2endTime);
        scheduler.createMeeting(Arrays.asList(charlie, dolly), m2date, m2interval);

        LocalDate m3date = LocalDate.parse("12.01.2024", dateFormatter);
        LocalTime m3startTime = LocalTime.parse("16:00", timeFormatter);
        LocalTime m3endTime = LocalTime.parse("17:00", timeFormatter);
        TimeInterval m3interval = new TimeInterval(m3startTime, m3endTime);
        scheduler.createMeeting(Arrays.asList(alice, bob), m3date, m3interval);

        LocalDate m4date = LocalDate.parse("12.01.2024", dateFormatter);
        LocalTime m4startTime = LocalTime.parse("15:45", timeFormatter);
        LocalTime m4endTime = LocalTime.parse("16:15", timeFormatter);
        TimeInterval m4interval = new TimeInterval(m4startTime, m4endTime);
        scheduler.createMeeting(Arrays.asList(dolly, bob), m4date, m4interval);
        System.out.println();

        LocalDate m5date = LocalDate.parse("12.01.2024", dateFormatter);
        LocalTime m5startTime = LocalTime.parse("15:30", timeFormatter);
        LocalTime m5endTime = LocalTime.parse("16:30", timeFormatter);
        TimeInterval m5interval = new TimeInterval(m5startTime, m5endTime);
        scheduler.createMeeting(Arrays.asList(charlie, dolly), m5date, m5interval);

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

    // public static void oldDemo(Scheduler scheduler) {
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
    //     LocalDateTime m0startTime = LocalDateTime.parse("01.01.2024 09:00", formatter);
    //     LocalDateTime m0endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
    //     scheduler.createMeeting(Arrays.asList(alice, bob), m0startTime, m0endTime);
    //     alice.printSchedule();
    //     System.out.println();
    //     bob.printSchedule();
    //     System.out.println();

    //     //3) conflicting schedules
    //     System.out.println("3) Setting up a meeting that conflicts with existing ones.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     LocalDateTime m1startTime = LocalDateTime.parse("01.01.2024 09:00", formatter);
    //     LocalDateTime m1endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
    //     scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), m1startTime, m1endTime);
    //     System.out.println();

    //     //4) suggest available timeslots based on existing individual schedules
    //     System.out.println("4) Suggesting available timeslots.");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     List<TimeInterval> availableTimeslots = scheduler.findAvailableTimeslots(Arrays.asList(alice, charlie, dolly),
    //                                                                    "01.01.2024");
    //     for (TimeInterval interval : availableTimeslots)
    //         System.out.printf("%s - %s\n", interval.startTime, interval.endTime);
        
    //     // create some more meetings
    //     LocalDateTime m2startTime = LocalDateTime.parse("01.01.2024 13:00", formatter);
    //     LocalDateTime m2endTime = LocalDateTime.parse("01.01.2024 14:00", formatter);
    //     scheduler.createMeeting(Arrays.asList(charlie, dolly, alice), m2startTime, m2endTime);
    //     // invalid starting time
    //     LocalDateTime m4startTime = LocalDateTime.parse("01.01.2024 13:10", formatter);
    //     LocalDateTime m4endTime = LocalDateTime.parse("01.01.2024 13:40", formatter);
    //     scheduler.createMeeting(Arrays.asList(bob, alice), m4startTime, m4endTime);

    //     LocalDateTime m3startTime = LocalDateTime.parse("01.01.2024 15:00", formatter);
    //     LocalDateTime m3endTime = LocalDateTime.parse("01.01.2024 10:00", formatter);
    //     scheduler.createMeeting(Arrays.asList(bob, alice), m3startTime, m3endTime);

        
    //     // 5) final result
    //     System.out.println("\nFinal schedules after creating more meetings:");
    //     System.out.println("-------------------------------------------------------------------------------");
    //     scheduler.printAllSchedules();
    // }
}