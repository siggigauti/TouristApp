package is.siggigauti.touristapp.model;

public class CatMatcher {

    private final Category category;
    private final Trip trip;

    public CatMatcher(Category category, Trip trip) {
        this.category = category;
        this.trip = trip;
    }

    public Category getCategory() {
        return category;
    }

    public Trip getTrip() {
        return trip;
    }
}
