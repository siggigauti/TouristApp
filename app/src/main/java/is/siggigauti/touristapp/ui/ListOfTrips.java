package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.DBHandler;
import is.siggigauti.touristapp.model.Company;
import is.siggigauti.touristapp.model.Trip;

//https://www.youtube.com/watch?v=WRANgDgM2Zg
public class ListOfTrips extends AppCompatActivity {

    ArrayList<Trip> tripsArrayList = new ArrayList<Trip>();
    public static int idToSearchFor[] = new int[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trips);

        Button button_goToFilterPage = (Button) findViewById(R.id.button_tripsList_filterButton);
        button_goToFilterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListOfTrips.this, Filter.class);
                startActivity(in);
            }
        });
        DBHandler dbHandler = new DBHandler(this);
        dbHandler.populate();
        boolean getAll = true;
            for(int i = 0; i < idToSearchFor.length; i++){
                if(idToSearchFor[i] == 1){
                    getAll = false;
                    // Hér vantar fall til að sækja og setja í fylki öll trip object með id i+1
                    if(dbHandler.getTripFromId(i) != null){
                        tripsArrayList.add(dbHandler.getTripFromId(i+1));
                    }
                }
            }
        if(getAll){
            tripsArrayList = dbHandler.getAllTrips();
        }
        populateListView();
        registerClickCallback();
        Button button_tripsList_homeButton = (Button) findViewById(R.id.button_tripsList_homeButton);
        button_tripsList_homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListOfTrips.this, HomePage.class);
                for(int i = 0; i < idToSearchFor.length; i++){
                    idToSearchFor[i] = 0;
                }
                startActivity(in);
            }
        });
    }

    private void registerClickCallback(){
        ListView list = (ListView) findViewById(R.id.trip_list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long     id) {
                Trip clickedTrip = tripsArrayList.get(position);

                Intent intent = new Intent(ListOfTrips.this, ViewSpecificTrip.class);

                intent.putExtra("NameOfTrip", clickedTrip.getTitle());
                intent.putExtra("Id", clickedTrip.getID());
                intent.putExtra("Description", clickedTrip.getDescription());
                intent.putExtra("Price", clickedTrip.getPrice());
                intent.putExtra("StartDate", clickedTrip.getStartDate());
                intent.putExtra("EndDate", clickedTrip.getEndDate());
                intent.putExtra("MinCap", clickedTrip.getMinCap());
                intent.putExtra("MaxCap", clickedTrip.getMaxCap());

                startActivity(intent);
            }
        });
    }


    private void populateListView(){
        ArrayAdapter<Trip> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.trip_list_view);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter <Trip> {
        public MyListAdapter(){
            super(ListOfTrips.this, R.layout.list_row_all_trips, tripsArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
         // Verum sure að við höfum view til að vinna með.
            View itemRowView = convertView;
            if (itemRowView == null){
                itemRowView = getLayoutInflater().inflate(R.layout.list_row_all_trips, parent, false);
            }

            Trip currentTrip = tripsArrayList.get(position);
            TextView tripName = (TextView) itemRowView.findViewById(R.id.row_nameOfTrip);
            tripName.setText(currentTrip.getTitle());
            TextView tripStartDate = (TextView) itemRowView.findViewById(R.id.row_startDateOfTrip);
            tripStartDate.setText(currentTrip.getStartDate().toString());
            TextView tripEndDate = (TextView) itemRowView.findViewById(R.id.row_endDateOfTrip);
            tripEndDate.setText(currentTrip.getEndDate().toString());
            TextView tripPrice = (TextView) itemRowView.findViewById(R.id.row_priceOfTrip);
            tripPrice.setText(String.valueOf(currentTrip.getPrice()));

            return itemRowView;
        }
    }
}
