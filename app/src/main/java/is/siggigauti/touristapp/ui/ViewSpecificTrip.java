package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

import is.siggigauti.touristapp.R;

public class ViewSpecificTrip extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView price;
    private TextView startdate;
    private TextView enddate;
    private TextView maxcap;
    private TextView mincap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_trip);

        Button button_tripsList_homeButton = (Button) findViewById(R.id.button_tripsList_homeButton);
        button_tripsList_homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ViewSpecificTrip.this, HomePage.class);
                startActivity(in);
            }
        });

        Button ViewSpecificTrip_BookTrip = (Button) findViewById(R.id.ViewSpecificTrip_BookTrip);
        ViewSpecificTrip_BookTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ViewSpecificTrip.this, MySavedTrips.class);
                startActivity(in);
            }
        });

        title = (TextView) findViewById(R.id.SpecificViewTitle);
        description = (TextView) findViewById(R.id.SpecificViewDescription);
        price = (TextView) findViewById(R.id.SpecificViewPrice);
        startdate = (TextView) findViewById(R.id.SpecificViewStartDate);
        enddate = (TextView) findViewById(R.id.SpecificViewEndDate);
        mincap = (TextView) findViewById(R.id.SpecificViewMinCap);
        maxcap = (TextView) findViewById(R.id.SpecificViewMaxCap);

        //title
        Intent intent = getIntent();
        String name = intent.getStringExtra("NameOfTrip");
        title.setText(name);

        //description
        String des = intent.getStringExtra("Description");
        description.setText(des);

        //price
        int pric = intent.getIntExtra("Price", 0);
        price.setText(String.valueOf("Verð: " + pric + " kr"));

        //DATE
        // Á eftir að laga hvað við skilum
        Date start = (Date) intent.getExtras().get("StartDate");
        startdate.setText(String.valueOf(start));

        Date eDate = (Date) intent.getExtras().get("EndDate");
        enddate.setText(String.valueOf(eDate));

        //mincap
        int minca = intent.getIntExtra("MinCap", 0);
        mincap.setText(String.valueOf(minca));

        //maxcap
        int maxca = intent.getIntExtra("MaxCap", 0);
        maxcap.setText(String.valueOf(maxca));
    }

    public void book(int id){
        //bókaferð sem gerir insert inn í db

    }


}
