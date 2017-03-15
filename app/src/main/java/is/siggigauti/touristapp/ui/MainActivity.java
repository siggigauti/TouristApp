package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.DBHandler;
import is.siggigauti.touristapp.model.Company;
import is.siggigauti.touristapp.model.Trip;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Búum til DBhandler fyrir okkur. Notum hann sem DB okkar.
        DBHandler dbHandler = new DBHandler(this);
        //Köllum á DB okkar og fallið populate sem fyllir hann af dummy gögnum.
        dbHandler.populate();

        //Smá kóði til að testa hvort gögnin koma ekki úr DB. Virkar, sjá niðurstöðu í logcat.
        ArrayList<Company> companies = dbHandler.getAllCompanies();
        for(Company company : companies){
            String log = "id: "+company.getID()+", Name: "+ company.getName()+", Description: " + company.getDescription();
            Log.d("Company::", log);
        }

        Button b1 = (Button) findViewById(R.id.Login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, HomePage.class);
                startActivity(in);
            }
        });
    }

 // https://www.tutorialspoint.com/android/android_navigation.htm - navigation
    // stór R þýðir resources, allt á ská
    // a view is anything on the screeen
    public void clickButton(View view){
        Log.i("Info", "ytt á takkaaa");
    }



}
