import java.util.List;
import java.util.ArrayList;

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

        scheduler.printPersonList();
    }
}
