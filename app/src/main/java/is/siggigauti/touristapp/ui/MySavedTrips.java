package is.siggigauti.touristapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import is.siggigauti.touristapp.R;

public class MySavedTrips extends AppCompatActivity {

    private TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_saved_trips);

        //Hér kemur listi sem vv er b´uinn að bóka/vista.
        // þurfum að geta vistað í db lista sem einhver user er með.


        Title = (TextView) findViewById(R.id.MySavedTrips_Title);
        String name = "Saved Trips";
        Title.setText(name);



    }
}
