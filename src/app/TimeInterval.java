package app;

import java.time.LocalTime;

public class TimeInterval {

        public LocalTime startTime; // immutable, hence public
        public LocalTime endTime;
        
        public TimeInterval(LocalTime startTime, LocalTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        // used for sorting lambda
        public LocalTime getStartTime() {
            return this.startTime;
        }
}
