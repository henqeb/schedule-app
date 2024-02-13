import java.time.LocalTime;

public class TimeInterval {

        private final LocalTime startTime;
        private final LocalTime endTime;
        
        public TimeInterval(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = startTime;
        }
    
}
