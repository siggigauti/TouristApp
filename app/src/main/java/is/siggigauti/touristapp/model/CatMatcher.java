package is.siggigauti.touristapp.model;

public class CatMatcher {

    private final int categoryId;
    private final int tripId;

    public CatMatcher( int categoryId, int tripId) {
        this.categoryId = categoryId;
        this.tripId = tripId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getTripId() {
        return tripId;
    }


}
