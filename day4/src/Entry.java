import java.time.LocalDate;
import java.time.LocalDateTime;

public class Entry {
    private LocalDateTime date;
    private String action;

    public Entry(LocalDateTime date, String action) {
        this.date = date;
        this.action = action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
