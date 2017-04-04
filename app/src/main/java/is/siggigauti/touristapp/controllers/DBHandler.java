package is.siggigauti.touristapp.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import is.siggigauti.touristapp.model.Category;
import is.siggigauti.touristapp.model.Company;
import is.siggigauti.touristapp.model.Trip;
import is.siggigauti.touristapp.model.User;
import is.siggigauti.touristapp.model.CatMatcher;


import static is.siggigauti.touristapp.model.DummyData.categories;


//import java.sql.Date;

/**
 * This is the DB Handler class, in it we have member variables (tilviksbreytur)
 * that are constants, describing the names of tables and columns.
 * After all the constants are initialized, we have 2 functions to override, onCreate and onUpdate.
 * The onCreate is called when an object of type DBHandler is initialized, inside it we 'glue'
 * togeather all the tables and columns into SQL queries and run those queries on the DB.
 * This creates all the Tables and columns defined.
 *
 * We will also have add methods for the tables that are run in the onCreate function, there
 * we insert some dummy data into the tables.
 *
 * Furthermore we have other CRUD operations, descriptions above each function respectively.
 *
 * Created by: Sigurður Gauti Samúelsson.
 */
public class DBHandler extends SQLiteOpenHelper {

    //Standard member variables of the DB
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "touristTrips.db";

    //variable to hold the db instance
    public SQLiteDatabase db;

    //Table names
    private static final String TABLE_TRIPS = "trips";
    private static final String TABLE_COMPANY = "company";
    private static final String TABLE_USER = "user";
    private static final String TABLE_CATEGORIES = "categories";
    private static final String TABLE_CATEGORIES_MATCHER = "cat_matcher";
    private static final String TABLE_BOOKINGS = "bookings";



    //TRIPS table-------------------------
    private static final String TRIP_ID = "id";
    private static final String TRIP_TITLE = "title";
    //ASIDE: SQLite notar TEXT til að geyma time.
    //DÆMI: ISO8601 string ("YYYY-MM-DD HH:MM:SS.SSS")
    private static final String TRIP_START_DATE = "startDate";
    private static final String TRIP_END_DATE = "endDate";
    private static final String TRIP_DESC = "description";
    private static final String TRIP_MIN_CAP = "minCap";
    private static final String TRIP_MAX_CAP = "maxCap";
    private static final String TRIP_COMPANY = "company_id"; //REMEMBER THIS IS FOREIGN KEY: ID OF COMPANY
    private static final String TRIP_PRICE = "price";

    //COMPANY table---------------------------------
    private static final String CMPNY_ID = "id";
    private static final String CMPNY_NAME = "name";
    private static final String CMPNY_DESC = "description";

    //USER TABLE------------------------------------
    private static final String USER_ID = "id";
    private static final String USER_NAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "email";

    //CATEGORY TABLE------------------------------------
    private static final String CATEGORY_ID = "id";
    private static final String CATEGORY_NAME = "category_name";


    //CATEGORY MATCHER TABLE------------------------------------
    private static final String CAT_MATCHER_ID = "category_matcherId";
    private static final String CAT_TRIP_ID = "tripId";
    private static final String CAT_ID = "categoryId";


    private static final String BOOKING_ID = "bookingId";
    private static final String BOOKING_USERID = "userId";
    private static final String BOOKING_TRIPID = "tripId";



    //create table into a string
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT" + ")";

    // eyðir töflu
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_CAT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CATEGORIES;
    private String DROP_CAT_MATCHER_TABLE = "DROP TABLE IF EXISTS " + TABLE_CATEGORIES_MATCHER;

    /*---------------------------------------------------------------*/



    //Constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }

    @Override
        public void onCreate(SQLiteDatabase db) {
        //Here we create all our tables, in the right order for the foreign TRIPs to work.
        //May be put into a seperate function.
        String CREATE_COMPANY_TABLE = "CREATE TABLE " + TABLE_COMPANY + "("
                + CMPNY_ID + " INTEGER PRIMARY KEY,"
                + CMPNY_NAME + " TEXT NOT NULL,"
                + CMPNY_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_COMPANY_TABLE);


        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + TRIP_ID + " INTEGER PRIMARY KEY,"
                + TRIP_TITLE + " TEXT NOT NULL,"
                + TRIP_START_DATE + " TEXT NOT NULL,"
                + TRIP_END_DATE + " TEXT NOT NULL,"
                + TRIP_DESC + " TEXT,"
                + TRIP_MIN_CAP + " INTEGER,"
                + TRIP_MAX_CAP + " INTEGER,"
                + TRIP_COMPANY + " INTEGER," 
                + TRIP_PRICE  + " INTEGER," 
                + "FOREIGN KEY (" + TRIP_COMPANY + ") REFERENCES " + TABLE_COMPANY + "(" + CMPNY_ID + "))";
        db.execSQL(CREATE_TRIPS_TABLE);


        String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIES + "("
                + CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + CATEGORY_NAME + " TEXT NOT NULL"
                + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);


        String CREATE_CATEGORY_MATCHER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIES_MATCHER + "("
                + CAT_TRIP_ID + " INTEGER,"
                + CAT_ID + " INTEGER"
                + ")";
        db.execSQL(CREATE_CATEGORY_MATCHER_TABLE);

        String CREATE_BOOKING_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_BOOKINGS + "("
                + BOOKING_ID + " INTEGER PRIMARY KEY,"
                + BOOKING_USERID + " INTEGER,"
                + BOOKING_TRIPID + " INTEGER"
                + ")";
        db.execSQL(CREATE_BOOKING_TABLE);



        //user table
        db.execSQL(CREATE_USER_TABLE);

        // Gamalt
        // db.execSQL(LoginDataBase.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP ALL TABLES AND RECREATE WITH CALLING onCreate again
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES_MATCHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);


        onCreate(db);
    }

    //Populates the DB with some dummy data.
    //Uses "this" keyword, meaning in the activity, after we create an instance of DBHandler,
    //We call this function to populate like this: dbHandler.populate()
    public void populate(){
        //Þarf að gera þetta svo við séum ekki að fylla alltaf með nýjum gögnum. Finnum lausn seinna.
        onUpgrade(this.getWritableDatabase(),1,1);
        ArrayList<Company> companies = new ArrayList<Company>();
        companies.add(new Company(1, "ReyEx","Reykjavik experiences for you."));
        companies.add(new Company(2, "Golden Circle","All around the circle."));
        companies.add(new Company(3, "Blue Dive","Go deep diving, all for your experience."));
        for(Company obj : companies){
            ContentValues values = new ContentValues();
            //values.put(CMPNY_ID, obj.getID());
            values.put(CMPNY_NAME, obj.getName());
            values.put(CMPNY_DESC, obj.getDescription());

            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(TABLE_COMPANY, null, values);
            db.close();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip(0, "Diving", changeStringToDate("2017-05-21"), changeStringToDate("2017-05-21"), "Diving at pool", 0, 10, companies.get(0), 30000));
        trips.add(new Trip(1, "Sport Car Driving", changeStringToDate("2017-06-21"), changeStringToDate("2017-06-22"), "Drive a ferrari", 0, 3, companies.get(2), 10000));
        trips.add(new Trip(2, "Mountain Climbing", changeStringToDate("2017-07-2"), changeStringToDate("2017-07-2"), "See all the mountains", 0, 10, companies.get(0), 15000));
        trips.add(new Trip(3, "Horse riding", changeStringToDate("2017-07-4"), changeStringToDate("2017-07-5"), "Wanna pet a horse?", 0, 10, companies.get(1), 20000));
        trips.add(new Trip(4, "Diving", changeStringToDate("2017-05-21"), changeStringToDate("2017-05-21"), "Diving at pool", 0, 10, companies.get(0), 30000));
        trips.add(new Trip(5, "Sport Car Driving", changeStringToDate("2017-06-21"), changeStringToDate("2017-06-22"), "Drive a ferrari", 0, 3, companies.get(1), 10000));
        trips.add(new Trip(6, "Mountain Climbing", changeStringToDate("2017-07-2"), changeStringToDate("2017-07-2"), "See all the mountains", 0, 10, companies.get(0), 15000));
        trips.add(new Trip(7, "Horse riding", changeStringToDate("2017-07-4"), changeStringToDate("2017-07-5"), "Wanna pet a horse?", 0, 10, companies.get(2), 20000));
        for(Trip obj : trips){
            ContentValues values = new ContentValues();
            //values.put(TRIP_ID, obj.getID());
            values.put(TRIP_TITLE, obj.getTitle());
            values.put(TRIP_START_DATE, df.format(obj.getStartDate()));
            values.put(TRIP_END_DATE, df.format(obj.getEndDate()));
            values.put(TRIP_DESC, obj.getDescription());
            values.put(TRIP_MIN_CAP, obj.getMinCap());
            values.put(TRIP_MAX_CAP, obj.getMaxCap());
            values.put(TRIP_COMPANY, obj.getCompany().getID());
            values.put(TRIP_PRICE, obj.getPrice());

            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(TABLE_TRIPS, null, values);
            db.close();
        }


// BÆTUM VIÐ CATEGORIES
        ArrayList<Category> catagories = new ArrayList<Category>();
        categories.add(new Category(1, "HASAR OG STEMMARI"));
        categories.add(new Category(2, "CRACKING A COLD ONE WITH THE BOYZZ"));
        categories.add(new Category(3, "SUPER FÖN AND LASZERS"));
        categories.add(new Category(4, "Útivist"));
        categories.add(new Category(5, "Bæjarferðir"));

        for(Category obj : categories){
            ContentValues values = new ContentValues();
            values.put(CATEGORY_NAME, obj.getName());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_CATEGORIES, null, values);
            db.close();
        }

// BÆTUM CATEGORIES Á TRIP OBJECTS - CATEGORY MATCHER - TRIP_ID og CAT_ID
        ArrayList<CatMatcher> cat_matcher = new ArrayList<CatMatcher>();  // Ferðir með id 1-8
        cat_matcher.add(new CatMatcher(1,0));   // Fyrst kemur id á ferðinni, svo á category
        cat_matcher.add(new CatMatcher(1,1));

        cat_matcher.add(new CatMatcher(2,0));
        cat_matcher.add(new CatMatcher(2,2));

        cat_matcher.add(new CatMatcher(3,2));
        cat_matcher.add(new CatMatcher(3,3));
        cat_matcher.add(new CatMatcher(3,4));
        cat_matcher.add(new CatMatcher(3,0));

        cat_matcher.add(new CatMatcher(4,0));
        cat_matcher.add(new CatMatcher(4,2));

        cat_matcher.add(new CatMatcher(5,0));
        cat_matcher.add(new CatMatcher(5,2));
        cat_matcher.add(new CatMatcher(6,1));
        cat_matcher.add(new CatMatcher(6,2));
        cat_matcher.add(new CatMatcher(6,3));
        cat_matcher.add(new CatMatcher(7,0));
        cat_matcher.add(new CatMatcher(7,2));
        cat_matcher.add(new CatMatcher(7,4));


        for(CatMatcher obj : cat_matcher){
            ContentValues values = new ContentValues();
            values.put(CAT_TRIP_ID, obj.getTripId());
            values.put(CAT_ID, obj.getCategoryId());
            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(TABLE_CATEGORIES_MATCHER, null, values);
            db.close();
        }

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(USER_NAME, "aa");
        value.put(USER_EMAIL, "a@a.is");
        value.put(USER_PASSWORD, "a");

        //insert into row in db.
        db.insert(TABLE_USER, null, value);
        db.close();
    }


    public void bookTrip(int userID, int tripID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BOOKING_USERID, userID);
        values.put(BOOKING_TRIPID, tripID);

        db.insert(TABLE_BOOKINGS, null, values);

        db.close();
    }




    //Test to get all trips
    public ArrayList<Trip> getAllTrips() {
        //Fyrst þarf að sækja companies.
        ArrayList<Company> companyList = getAllCompanies();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Trip> tripList = new ArrayList<Trip>();
        String selectQuery = "SELECT * FROM "+TABLE_TRIPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Trip trip = new Trip(
                        cursor.getInt(0),
                        cursor.getString(1),
                        changeStringToDate(cursor.getString(2)),
                        changeStringToDate(cursor.getString(3)),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        getCompanyByIdFromList(companyList, cursor.getInt(7)),
                        cursor.getInt(8)
                );

                tripList.add(trip);
            } while(cursor.moveToNext());
        }
        return tripList;
    }



    public Date changeStringToDate(String input){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(input);
        }catch (Exception ex){

        }
        return null;
    }

    private Company getCompanyByIdFromList(ArrayList<Company> companyList, int companyId) {
        //Fyrir öll companies, tékkum hvort við finnum ekki það company með rétt ID.
        for(Company company : companyList){
            if(company.getID()==companyId){
                return company;
            }
        }
        //Annars null, ætti ekki að gerast því companyId í TRIP töflu er foreign key.
        return null;
    }


    public ArrayList<Company> getAllCompanies(){
        ArrayList<Company> companyList = new ArrayList<Company>();
        String selectQuery = "SELECT * FROM "+TABLE_COMPANY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Company company = new Company(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                companyList.add(company);
            }while(cursor.moveToNext());
        }
        return companyList;
    }


    public Trip getTripFromId(int tripId){
        ArrayList<Company> companyList = getAllCompanies();
        Trip tripFromId;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String selectQuery = "SELECT * FROM "+TABLE_TRIPS+" WHERE id="+tripId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            tripFromId = new Trip(
                    cursor.getInt(0),
                    cursor.getString(1),
                    changeStringToDate(cursor.getString(2)),
                    changeStringToDate(cursor.getString(3)),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    getCompanyByIdFromList(companyList, cursor.getInt(7)),
                    cursor.getInt(8)
            );
            return tripFromId;
        }
        return null;
    }



    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> categoryList = new ArrayList<Category>();
        String selectQuery = "SELECT * FROM "+TABLE_CATEGORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Category category = new Category(
                        cursor.getInt(0),
                        cursor.getString(1));
                categoryList.add(category);
            }while(cursor.moveToNext());
        }
        return categoryList;
    }


    public ArrayList<CatMatcher> getAllCatMatcher(){
        ArrayList<CatMatcher> catList = new ArrayList<CatMatcher>();
        String selectQuery = "SELECT * FROM "+TABLE_CATEGORIES_MATCHER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                CatMatcher category = new CatMatcher(
                        cursor.getInt(0),
                        cursor.getInt(1));
                catList.add(category);
            }while(cursor.moveToNext());
        }
        return catList;
    }

    /**
     *
     * Creates user in db
     *
     **/
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(USER_NAME, user.getName());
        value.put(USER_EMAIL, user.getEmail());
        value.put(USER_PASSWORD, user.getPassword());

        //insert into row in db.
        db.insert(TABLE_USER, null, value);
        db.close();
    }

    /**
     *
     * get all users, fyrir admin area eða til þess að sjá all users
     *
     **/

    public List<User> getAllUser() {
        //fylki af dálkum
        String[] columns = {
                USER_ID,
                USER_NAME,
                USER_EMAIL,
                USER_PASSWORD
        };
        //röðum í röð
        String sort = USER_NAME + " ASC";

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        //query the user table
        Cursor cursor = db.query(TABLE_USER,
                columns,
                null,
                null,
                null,
                null,
                sort);
        if(cursor.moveToFirst()) {
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                userList.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    /**
     *
     * Update user info
     *
     **/

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());

        //update row
        db.update(TABLE_USER, values, USER_ID + " = ?",
                new String[] {String.valueOf(user.getID())});
        db.close();
    }


    /**
     *
     * Delete user
     *
     **/
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        //delete user by id
        db.delete(TABLE_USER, USER_ID + " = ?",

                new String[]{String.valueOf(user.getID())

                });

        db.close();
    }

    /**
     *
     * check if user exist
     *
     **/
    public boolean checkUser(String email) {

        String[] columns = {
                USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        //section criteria
        String selection = USER_EMAIL + " = ?";

        //selection argument
        String[] selectionArgs = {email};

        //query
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * check if user exist with email and pw
     *
     **/

    public boolean checkUser(String email, String password){
        String[] columns = {USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();

        //selection criteria
        String selection = USER_EMAIL + " = ?" + " AND " + USER_PASSWORD + " = ?";
        //selection argu
        String[] selectionArgs = {email, password};

        //query
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }


    public List<User> getUserInfo(String email) {
        //fylki af dálkum
        String[] columns = {
                USER_ID,
                USER_NAME,
                USER_EMAIL,
                USER_PASSWORD
        };
        //röðum í röð
        //String sort = USER_NAME + " ASC";

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selection = (USER_EMAIL + " = ?");
        //selection argu
        String[] selectionArgs = {email};

        //query the user table
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if(cursor.moveToFirst()) {
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
                userList.add(user);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }


}
