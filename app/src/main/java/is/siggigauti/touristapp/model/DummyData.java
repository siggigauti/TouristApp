package is.siggigauti.touristapp.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DummyData{
    //List of stuffs

    public static ArrayList<User> users = new ArrayList <User>();
    public static ArrayList<Company> companies = new ArrayList <Company>();
    public static ArrayList<Trip> trips = new ArrayList <Trip>();
    public static ArrayList<CatMatcher> catMatchers = new ArrayList <CatMatcher>();
    public static ArrayList<Category> categories = new ArrayList <Category>();
    public static ArrayList<Booking> bookings = new ArrayList <Booking>();
    public static ArrayList<Basket> baskets = new ArrayList <Basket>();



    public DummyData() {
        //Fylla listana af gögnum
        SimpleDateFormat textFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");
        categories.add(new Category(1, "Diving"));
        catMatchers.add(new CatMatcher(categories.get(0), trips.get(0)));
    }


    public static ArrayList<User> getUsersArrayList(){
        users.add(new User(1,"Hannes", "Iceland", "pass1", "hannes@hi.is"));
        users.add(new User(2,"Björn", "Denmark", "pass2", "bjorn@hi.is"));
        users.add(new User(3,"Jónína", "Iceland", "pass3", "jonina@hi.is"));
        users.add(new User(4,"Ólöf", "Finland", "pass4", "olof@hi.is"));
        /*  Prentum út alla notendur ef viljum.
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).name);
        } */
        return users;
    }

    public static ArrayList<Company> getCompaniesArrayList(){
        companies.add(new Company(1, "ReyEx","Reykjavik experiences for you."));
        companies.add(new Company(2, "Golden Circle","All around the circle."));
        companies.add(new Company(3, "Blue Dive","Go deep diving, all for your experience."));
        return companies;
    }

    public static ArrayList<Trip> getTripsArrayList(){
        trips.add(new Trip(1, "Diving", Date.valueOf("2017-05-21"), Date.valueOf("2017-05-21"), "Diving at pool", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 30000));
        trips.add(new Trip(2, "Sport Car Driving", Date.valueOf("2017-06-21"), Date.valueOf("2017-06-22"), "Drive a ferrari", 0, 3, new Company(1, "ReyEx","Reykjavik experiences for you."), 10000));
        trips.add(new Trip(3, "Mountain Climbing", Date.valueOf("2017-07-2"), Date.valueOf("2017-07-2"), "See all the mountains", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 15000));
        trips.add(new Trip(4, "Horse riding", Date.valueOf("2017-07-4"), Date.valueOf("2017-07-5"), "Wanna pet a horse?", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 20000));
        trips.add(new Trip(5, "Diving", Date.valueOf("2017-05-21"), Date.valueOf("2017-05-21"), "Diving at pool", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 30000));
        trips.add(new Trip(6, "Sport Car Driving", Date.valueOf("2017-06-21"), Date.valueOf("2017-06-22"), "Drive a ferrari", 0, 3, new Company(1, "ReyEx","Reykjavik experiences for you."), 10000));
        trips.add(new Trip(7, "Mountain Climbing", Date.valueOf("2017-07-2"), Date.valueOf("2017-07-2"), "See all the mountains", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 15000));
        trips.add(new Trip(8, "Horse riding", Date.valueOf("2017-07-4"), Date.valueOf("2017-07-5"), "Wanna pet a horse?", 0, 10, new Company(1, "ReyEx","Reykjavik experiences for you."), 20000));
        return trips;
    }
}
