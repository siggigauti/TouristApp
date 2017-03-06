package is.siggigauti.touristapp.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DummyData {
    //List of stuffs

    ArrayList<User> users;
    ArrayList<Company> companies;
    ArrayList<Trip> trips;
    ArrayList<CatMatcher> catMatchers;
    ArrayList<Category> categories;
    ArrayList<Booking> bookings;
    ArrayList<Basket> baskets;

    public DummyData() {
        //Fylla listana af gögnum
        SimpleDateFormat textFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");

        users.add(new User(1,"Hannes", "Iceland", "pass1", "hannes@hi.is"));
        users.add(new User(2,"Björn", "Denmark", "pass2", "bjorn@hi.is"));
        users.add(new User(3,"Jónína", "Iceland", "pass3", "jonina@hi.is"));
        users.add(new User(4,"Ólöf", "Finland", "pass4", "olof@hi.is"));
        companies.add(new Company(1, "ReyEx","Reykjavik experiences for you."));
        companies.add(new Company(2, "Golden Circle","All around the circle."));
        companies.add(new Company(3, "Blue Dive","Go deep diving, all for your experience."));
        trips.add(new Trip(1, "Diving", Date.valueOf("2017-05-21"),Date.valueOf("2017-05-21"),"Diving at pool", 0, 10, companies.get(0)));
        categories.add(new Category(1, "Diving"));
        trips.get(0).getDescription();
        catMatchers.add(new CatMatcher(categories.get(0), trips.get(0)));
    }
}
