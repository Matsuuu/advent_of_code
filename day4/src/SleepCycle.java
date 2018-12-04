import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SleepCycle {
    private LocalDateTime sleepStartTime;
    private LocalDateTime sleepEndTime;

    public SleepCycle(LocalDateTime sleepStartTime, LocalDateTime sleepEndTime) {
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
    }

    public LocalDateTime getSleepStartTime() {
        return sleepStartTime;
    }

    public void setSleepStartTime(LocalDateTime sleepStartTime) {
        this.sleepStartTime = sleepStartTime;
    }

    public LocalDateTime getSleepEndTime() {
        return sleepEndTime;
    }

    public void setSleepEndTime(LocalDateTime sleepEndTime) {
        this.sleepEndTime = sleepEndTime;
    }

    public long getSleepDuration() {
        return ChronoUnit.MINUTES.between(this.sleepStartTime, this.sleepEndTime);
    }

    @Override
    public String toString() {
        return sleepStartTime + " - " + sleepEndTime;
    }
}
