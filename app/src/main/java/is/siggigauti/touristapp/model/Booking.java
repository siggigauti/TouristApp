package is.siggigauti.touristapp.model;

public class Booking {

    private final int ID;
    private User user;
    private Trip trip;

    public Booking(int ID, User user, Trip trip) {
        this.ID = ID;
        this.user = user;
        this.trip = trip;
    }

    public int getID() {
        return ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
