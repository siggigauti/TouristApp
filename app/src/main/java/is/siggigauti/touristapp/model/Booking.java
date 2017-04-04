package is.siggigauti.touristapp.model;

public class Booking {

    private final int ID;
    private int user;
    private int trip;

    public Booking(int ID, int user, int trip) {
        this.ID = ID;
        this.user = user;
        this.trip = trip;
    }

    public int getID() {
        return ID;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getTrip() {
        return trip;
    }

    public void setTrip(int trip) {
        this.trip = trip;
    }
}
