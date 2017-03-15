package is.siggigauti.touristapp.controllers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


/**
 * Created by odinnhelgason on 15/03/2017.
 */



public class LoginDataBase {

    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    //create new db
    static final String DATABASE_CREATE = "CREATE TABLE " + "LOGIN"
                                                            + "("
                                                            + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                                                            + "USERNAME TEXT,PASSWORD TEXT );";


    //variable to hold the db instance
    public SQLiteDatabase db;

    //context of app using db
    private final Context context;

    //DBHandler dbHandler = new DBHandler(this);
    private DBHandler dbhandler;

    public LoginDataBase(Context _context) {

        context = _context;
        dbhandler = new DBHandler(context);
    }

    public LoginDataBase open() throws SQLException {
        db = dbhandler.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }
    public void insertLogin(String userName, String password) {

        ContentValues newValues = new ContentValues();

        //Assign values for each row
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        //insert the row into your table
        db.insert("LOGIN", null, newValues);
        Toast.makeText(context, "InsertLogin Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public int deleteUser(String userName) {
        String WHERE = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", WHERE, new String[]{userName});
        Toast.makeText(context, "Number of deleted users: " + numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSingleUser(String userName){

        Cursor cursor = db.query("LOGIN", null, "USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount() < 1 ) {
            cursor.close();
            return "User DOES not Exist";
        }

        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void updateUserInfo(String userName, String password) {

        ContentValues updateValues = new ContentValues();
        updateValues.put("USERNAME", userName);
        updateValues.put("PASSWORD", password);

        String WHERE ="USERNAME=?";
        db.update("LOGIN", updateValues, WHERE, new String[]{userName});
    }



}
