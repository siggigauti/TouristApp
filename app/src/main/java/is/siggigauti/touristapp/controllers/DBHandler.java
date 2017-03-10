package is.siggigauti.touristapp.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import is.siggigauti.touristapp.model.Company;
import is.siggigauti.touristapp.model.Trip;

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
    private static final String DATABASE_NAME = "touristTrips";

    //Table names
    private static final String TABLE_TRIPS = "trips";
    private static final String TABLE_COMPANY = "company";


    //Columns for TRIPS table.
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


    //Columns for COMPANY table.
    private static final String CMPNY_ID = "id";
    private static final String CMPNY_NAME = "name";
    private static final String CMPNY_DESC = "description";

    //Constructor
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP ALL TABLES AND RECREATE WITH CALLING onCreate again
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPANY);
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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip(1, "Diving", Date.valueOf("2017-05-21"), Date.valueOf("2017-05-21"), "Diving at pool", 0, 10, companies.get(0), 30000));
        trips.add(new Trip(2, "Sport Car Driving", Date.valueOf("2017-06-21"), Date.valueOf("2017-06-22"), "Drive a ferrari", 0, 3, companies.get(2), 10000));
        trips.add(new Trip(3, "Mountain Climbing", Date.valueOf("2017-07-2"), Date.valueOf("2017-07-2"), "See all the mountains", 0, 10, companies.get(0), 15000));
        trips.add(new Trip(4, "Horse riding", Date.valueOf("2017-07-4"), Date.valueOf("2017-07-5"), "Wanna pet a horse?", 0, 10, companies.get(1), 20000));
        trips.add(new Trip(5, "Diving", Date.valueOf("2017-05-21"), Date.valueOf("2017-05-21"), "Diving at pool", 0, 10, companies.get(0), 30000));
        trips.add(new Trip(6, "Sport Car Driving", Date.valueOf("2017-06-21"), Date.valueOf("2017-06-22"), "Drive a ferrari", 0, 3, companies.get(1), 10000));
        trips.add(new Trip(7, "Mountain Climbing", Date.valueOf("2017-07-2"), Date.valueOf("2017-07-2"), "See all the mountains", 0, 10, companies.get(0), 15000));
        trips.add(new Trip(8, "Horse riding", Date.valueOf("2017-07-4"), Date.valueOf("2017-07-5"), "Wanna pet a horse?", 0, 10, companies.get(2), 20000));
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
    }
    /*
    //Test to get all trips
    public ArrayList<Trip> getAllTrips() {
        ArrayList<Trip> tripList = new ArrayList<Trip>();
        String selectQuery = "SELECT * FROM "+TABLE_TRIPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Trip trip = new Trip(
                        cursor.getInt(0), cursor.getString(1), Date.valueOf(cursor.getString(2)),
                        Date.valueOf(cursor.getString(3)), cursor.getString(4), cursor.getInt(5),
                        cursor.getInt(6),
                );

            }
        }
    }*/

    public ArrayList<Company> getAllCompanies(){
        ArrayList<Company> companyList = new ArrayList<Company>();
        String selectQuery = "SELECT * FROM "+TABLE_COMPANY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Company company = new Company(
                        cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                companyList.add(company);
            }while(cursor.moveToNext());
        }
        return companyList;
    }
}
