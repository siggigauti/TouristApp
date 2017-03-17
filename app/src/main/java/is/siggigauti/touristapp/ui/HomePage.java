package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import is.siggigauti.touristapp.R;

public class HomePage extends AppCompatActivity {

        private TextView user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(is.siggigauti.touristapp.R.layout.activity_homepage);

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


            user = (TextView) findViewById(R.id.UserName);
            Intent intent = getIntent();
            String name = intent.getStringExtra("UserName");
            user.setText("USER: " + name);
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
    }

