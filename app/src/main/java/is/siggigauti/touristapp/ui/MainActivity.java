package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.LoginDataBase;

public class MainActivity extends AppCompatActivity {

    private Button LoginButton, SignUpButton;
    private EditText textUserName, textPassword, textConfirmPassword;
    LoginDataBase logindatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //create a instance of SQLite DB
        logindatabase = new LoginDataBase(this);
        logindatabase = logindatabase.open();

        /*
        //Búum til DBhandler fyrir okkur. Notum hann sem DB okkar.
        DBHandler dbHandler = new DBHandler(this);
        //Köllum á DB okkar og fallið populate sem fyllir hann af dummy gögnum.
        dbHandler.populate();
        */

        LoginButton = (Button) findViewById(R.id.MainLogin);
        SignUpButton = (Button) findViewById(R.id.MainSignUp);

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intentSignUp);
            }
        });



        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                textUserName = (EditText) findViewById(R.id.MainUserName);
                textPassword = (EditText) findViewById(R.id.MainPassword);

                String userName = textUserName.getText().toString();
                String password = textPassword.getText().toString();
                String storePassword = logindatabase.getSingleUser(userName);


                if(password.equals(storePassword)){
                    Toast.makeText(MainActivity.this, "Congrats: Login Successful", Toast.LENGTH_LONG).show();


                    Intent intentLogin = new Intent(MainActivity.this, ListOfTrips.class);
                    startActivity(intentLogin);
                }else {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });


        /*
        //þetta fer allt ef login er successful.


        */

        /*Smá kóði til að testa hvort gögnin koma ekki úr DB. Virkar, sjá niðurstöðu í logcat.
        ArrayList<Company> companies = dbHandler.getAllCompanies();
        for(Company company : companies){
            String log = "id: "+company.getID()+", Name: "+ company.getName()+", Description: " + company.getDescription();
            Log.d("Company::", log);
        }
        */
    }





 // https://www.tutorialspoint.com/android/android_navigation.htm - navigation
    // stór R þýðir resources, allt á ská
    // a view is anything on the screeen
    public void clickButton(View view){
        Log.i("Info", "ytt á takkaaa");
    }



}
