import java.time.LocalTime;

public class TimeInterval {

        public final LocalTime startTime;
        public final LocalTime endTime;
        
        public TimeInterval(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // used for sorting lambda
        public LocalTime getEndTime() {
            return endTime;
        }

        // ---=---
        public LocalTime getStartTime() {
            return startTime;
        }
}
