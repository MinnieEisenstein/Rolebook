package classes;

public class Behavior {
	private String date;
    private String details;
    

    public Behavior(String date, String details) {
        this.date = date;
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Details: " + details;
    }

}
