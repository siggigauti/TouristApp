package is.siggigauti.touristapp.model;

public class Basket {

    private final User user;
    private final Trip trip;

    public Basket(User user, Trip trip) {
        this.user = user;
        this.trip = trip;
    }

    public User getUser() {
        return user;
    }

    public Trip getTrip() {
        return trip;
    }
}
