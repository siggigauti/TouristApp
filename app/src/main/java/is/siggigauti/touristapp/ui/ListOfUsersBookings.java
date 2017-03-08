package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import is.siggigauti.touristapp.R;

public class ListOfUsersBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(is.siggigauti.touristapp.R.layout.activity_user_bookings);

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

}
