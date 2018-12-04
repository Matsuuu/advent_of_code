import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shift {
    private int guardId;
    private List<SleepCycle> sleepCycles;
    private long sleepDuration;

    public Shift() {
        sleepCycles = new ArrayList<>();
        sleepDuration = 0;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public List<SleepCycle> getSleepCycles() {
        return sleepCycles;
    }

    public void setSleepCycles(List<SleepCycle> sleepCycles) {
        this.sleepCycles = sleepCycles;
    }

    public void addSleepCycle(SleepCycle sleepCycle) {
        this.sleepCycles.add(sleepCycle);
    }

    public long getSleepDuration() {
        return sleepDuration;
    }

    public void addSleepDuration(long durationToAdd) {
        this.sleepDuration += durationToAdd;
    }

    public void setSleepDuration(long sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    @Override
    public String toString() {
        StringBuilder buffer =  new StringBuilder("Guard number " + guardId + " slept for " + sleepDuration + " minutes.");
        buffer.append("\nSleeping occured at:");
        for(SleepCycle sc : sleepCycles) {
            buffer.append("\n" + sc.toString());
        }
        buffer.append("\n");
        return buffer.toString();
    }
}
