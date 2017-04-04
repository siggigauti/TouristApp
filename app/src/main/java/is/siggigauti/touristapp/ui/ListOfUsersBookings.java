package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.DBHandler;
import is.siggigauti.touristapp.controllers.Session;
import is.siggigauti.touristapp.model.DummyData;
import is.siggigauti.touristapp.model.Trip;

public class ListOfUsersBookings extends AppCompatActivity {
    //Sækjum arrayLista af Trip objectum frá DummyData/gagnagrunn
    ArrayList<Trip> trips_booked_by_user;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(is.siggigauti.touristapp.R.layout.activity_user_bookings);

        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String userID = user.get(Session.KEY_ID);

        DBHandler dbHandler = new DBHandler(this);
        trips_booked_by_user = dbHandler.getBookedTripsByUserId(Integer.parseInt(userID));

        // Setjum gögnin í listann.
        populateListView();

        // Takki til að fara aftur heim
        Button button_userBookings_goHome = (Button) findViewById(R.id.button_userBookings_goHome);
        button_userBookings_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListOfUsersBookings.this, HomePage.class);
                startActivity(in);
            }
        });
    }


    private void populateListView(){
        ArrayAdapter<Trip> adapter = new MyListAdapter();
        ListView list_of_booked_trips = (ListView) findViewById(R.id.user_bookings_list_view);
        list_of_booked_trips.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Trip> {
        public MyListAdapter(){
            super(ListOfUsersBookings.this, R.layout.list_row_all_trips, trips_booked_by_user);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // Verum sure að við höfum view til að vinna með.
            View itemRowView = convertView;
            if (itemRowView == null){
                itemRowView = getLayoutInflater().inflate(R.layout.list_row_all_trips, parent, false);
            }

            // Finnum trip til að vinna með
            Trip currentTrip = trips_booked_by_user.get(position);
            // Fillum viewið, tökum út Trip fylki þann sem viljum vinna með og setjum það í hverja röð.
            TextView tripName = (TextView) itemRowView.findViewById(R.id.row_nameOfTrip);
            tripName.setText(currentTrip.getTitle());
            // StartDate
            TextView tripStartDate = (TextView) itemRowView.findViewById(R.id.row_startDateOfTrip);
            tripStartDate.setText(currentTrip.getStartDate().toString());
            // EndDate
            TextView tripEndDate = (TextView) itemRowView.findViewById(R.id.row_endDateOfTrip);
            tripEndDate.setText(currentTrip.getEndDate().toString());
            // Trip Price
            TextView tripPrice = (TextView) itemRowView.findViewById(R.id.row_priceOfTrip);
            tripPrice.setText(String.valueOf(currentTrip.getPrice()));

            return itemRowView;
        }
    }

}
