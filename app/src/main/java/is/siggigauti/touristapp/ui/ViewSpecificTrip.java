package is.siggigauti.touristapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.Date;

import is.siggigauti.touristapp.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewSpecificTrip extends AppCompatActivity {

    public static final String TAG = ViewSpecificTrip.class.getSimpleName();
    private double latitude = 64;
    private double longitude = -21;
    private CurrentWeather mCurrentWeather;

    private TextView title;
    private TextView description;
    private TextView price;
    private TextView startdate;
    private TextView enddate;
    private TextView maxcap;
    private TextView mincap;
    private TextView weatherText;

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
        weatherText = (TextView) findViewById(R.id.weatherTextView);


        getForecast(latitude, longitude);
    }

    public void book(int id){
        //bókaferð sem gerir insert inn í db

    }


    private void getForecast(double lat, double lon) {
        String apiKey = "a6bcdd96c91df987a3833e64d05b5987";
        String forecastUrl = "https://api.darksky.net/forecast/"+
                apiKey +"/"+ lat +","+ lon;

        if(isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCurrentWeather = getCurrentDetails(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Call the method to update the view.
                                    updateDetails();
                                }
                            });
                        } else {
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Network unavailable", Toast.LENGTH_LONG).show();
        }
    }


    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: " + timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG, currentWeather.getFormattedTime());

        return currentWeather;
    }

    private void updateDetails(){
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

        weatherText.setText("Hitastig verður: "+mCurrentWeather.getTemperature()+"°F.");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if(networkInfo!= null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }


}
