package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import is.siggigauti.touristapp.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = (Button) findViewById(R.id.buttonNav);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, HomePage.class);
                startActivity(in);
            }
        });
    }

 // https://www.tutorialspoint.com/android/android_navigation.htm - navigation
    // stór R þýðir resources, allt á ská
    // a view is anything on the screeen
    public void clickButton(View view){
        Log.i("Info", "ytt á takkaaa");
    }



}
