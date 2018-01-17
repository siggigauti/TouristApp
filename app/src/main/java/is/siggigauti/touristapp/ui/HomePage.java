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

            session = new Session(getApplicationContext());
            if(session.checkLogin())
                finish();
            HashMap<String, String> user = session.getUserDetails();
            String name = user.get(Session.KEY_NAME);
            String email = user.get(Session.KEY_EMAIL);
            UserLabel = (TextView) findViewById(R.id.UserName);
            UserLabel.setText("USER: " + name + " Email: " + email);
            initButtons();
        }

    private void initButtons(){
        Button button_goToListOfTrips = (Button) findViewById(R.id.button_goToListOfTrips);
        button_goToListOfTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, ListOfTrips.class);
                startActivity(in);
            }
        });

        Button button_goToUserBookings = (Button) findViewById(R.id.button_goToUserBookings);
        button_goToUserBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, ListOfUsersBookings.class);
                startActivity(in);
            }
        });

        Button button_goToUserSavedTrips = (Button) findViewById(R.id.button_goToUserSavedTrips);
        button_goToUserSavedTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomePage.this, ListOfSavedTrips.class);
                startActivity(in);
            }
        });

        Button logout = (Button) findViewById(R.id.LogOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });
    }
    }

