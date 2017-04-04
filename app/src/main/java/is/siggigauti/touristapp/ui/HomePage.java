package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.Session;

public class HomePage extends AppCompatActivity {

        private TextView UserLabel;
        Session session;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(is.siggigauti.touristapp.R.layout.activity_homepage);

            // Session class instance
            session = new Session(getApplicationContext());


            // Check user login
            // If User is not logged in , This will redirect user to LoginActivity.
            if(session.checkLogin())
                finish();


            // get user data from session
            HashMap<String, String> user = session.getUserDetails();

            // get name
            String name = user.get(Session.KEY_NAME);

            // get email
            String email = user.get(Session.KEY_EMAIL);

            //get Id
            String ID = user.get(Session.KEY_ID);
            System.out.println("USERID this should be 1: "+ID);

            UserLabel = (TextView) findViewById(R.id.UserName);
            UserLabel.setText("USER: " + name + " Email: " + email);

            initButtons();




            /*
            // Go to Settings Page
            Button button_goToListOfTrips = (Button) findViewById(R.id.button_goToListOfTrips);
            button_goToListOfTrips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(HomePage.this, ListOfTrips.class);
                    startActivity(in);
                }
            });
            */

        }

    public void initButtons(){
        // Go to List of trips page
        Button button_goToListOfTrips = (Button) findViewById(R.id.button_goToListOfTrips);
        button_goToListOfTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(HomePage.this, ListOfTrips.class);

                startActivity(in);
            }
        });

        // Go to Your Bookings Page
        Button button_goToUserBookings = (Button) findViewById(R.id.button_goToUserBookings);
        button_goToUserBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, ListOfUsersBookings.class);
                startActivity(in);
            }
        });


        // Go to Your Saved Bookings Page
        Button button_goToUserSavedTrips = (Button) findViewById(R.id.button_goToUserSavedTrips);
        button_goToUserSavedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, ListOfSavedTrips.class);
                startActivity(in);
            }
        });

        // Go to Your Saved Bookings Page
        Button logout = (Button) findViewById(R.id.LogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // clear session og beint í login gluggan
                session.logoutUser();
            }
        });
    }
    }

