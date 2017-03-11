package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import is.siggigauti.touristapp.R;

public class ViewSpecificTrip extends AppCompatActivity {

    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_trip);
        textview = (TextView) findViewById(R.id.SpecificViewTitle);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NameOfTrip");
        textview.setText(name);

        /*
        intent.putExtra("NameOfTrip", clickedTrip.getTitle());
        intent.putExtra("Id", clickedTrip.getID());
        intent.putExtra("Description", clickedTrip.getDescription());
        intent.putExtra("Price", clickedTrip.getPrice());
        intent.putExtra("StartDate", clickedTrip.getStartDate());
        intent.putExtra("EndDate", clickedTrip.getEndDate());
        intent.putExtra("MinCap", clickedTrip.getMinCap());
        intent.putExtra("MaxCap", clickedTrip.getMaxCap());
        */
        /*
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mViewText.setText(bundle.getString("NameOfTrip"));
        }
        */

        //AÐ VIÐ SÉUM AÐ KLIKKA Á RÉTT.....
        /*
        Intent intent = getIntent();
        String name = intent.getStringExtra("NameOfTrip");

        if(name == null) {
            name = "Friend";
        }
        Log.d(TAG, name);
        */

    }
}
