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
import is.siggigauti.touristapp.model.DummyData;
import is.siggigauti.touristapp.model.Trip;

//https://www.youtube.com/watch?v=WRANgDgM2Zg
public class ListOfTrips extends AppCompatActivity {
    //Sækjum arrayLista af User objectum frá DummyData/gagnagrunn
    // Her er því nóg að provida endapunkt sem skilar arrayLista af User/whatever objecti
    //ArrayList<Trip> tripsArrayList = DummyData.getTripsArrayList();
    //DBHandler db = new DBHandler(this);
    ArrayList<Trip> tripsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trips);

        DBHandler dbHandler = new DBHandler(this);
        tripsArrayList = dbHandler.getAllTrips();

        ArrayList<Company> companies = dbHandler.getAllCompanies();
        for(Company company : companies){
            String log = "id: "+company.getID()+", Name: "+ company.getName()+", Description: " + company.getDescription();
            Log.d("from Listoftrips:", log);
        }

        // Setjum gögnin í listann.
        populateListView();
        registerClickCallback();

        Button button_tripsList_homeButton = (Button) findViewById(R.id.button_tripsList_homeButton);
        button_tripsList_homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ListOfTrips.this, HomePage.class);
                startActivity(in);
            }
        });
    }
    //skynja items á list.
    private void registerClickCallback(){
        ListView list = (ListView) findViewById(R.id.trip_list_view);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long     id) {
                Trip clickedTrip = tripsArrayList.get(position);

                //String msg = "you clicked on" + position;
                //Toast.makeText(ListOfTrips.this,msg,Toast.LENGTH_LONG).show();

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
            super(ListOfTrips.this, R.layout.list_row, tripsArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
         // Verum sure að við höfum view til að vinna með.
            View itemRowView = convertView;
            if (itemRowView == null){
                itemRowView = getLayoutInflater().inflate(R.layout.list_row, parent, false);
            }

         // Finnum trip til að vinna með
            Trip currentTrip = tripsArrayList.get(position);
         // Fillum viewið, tökum út Trip fylki þann sem viljum vinna með og setjum það í hverja röð.
            TextView tripName = (TextView) itemRowView.findViewById(R.id.row_nameOfTrip);
            tripName.setText(currentTrip.getTitle());
            // StartDate: ATH Þarf að formatta þetta betur
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




    public void clickButton(View view){
        Log.i("klikk", "ytt á takka");

        ArrayList<Trip> usersArrayList = DummyData.getTripsArrayList();

        for (int i = 0; i < usersArrayList.size(); i++) {
            System.out.println(usersArrayList.get(i).getPrice());
        }
    }


}
