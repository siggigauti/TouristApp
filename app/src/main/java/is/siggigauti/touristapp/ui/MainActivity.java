package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.DBHandler;
import is.siggigauti.touristapp.controllers.Session;
import is.siggigauti.touristapp.helpers.InputValidation;
import is.siggigauti.touristapp.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = MainActivity.this;

    ArrayList<User> UserInfo;
    User user;

    private Button LoginButton, SignUpButton;
    private EditText textUserName,
                     textPassword,
                     textEmail;
    private TextView textErrorEmail,
                     textErrorPassword;
    public boolean loggedIn = false;

    //input checker
    private InputValidation inputValidation;
    //db
    private DBHandler dbHandler;

    // User Session Manager Class
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        dbHandler.populate();
        // User Session Manager
        session = new Session(getApplicationContext());



        initViews();
        initListeners();
        initObjects();

    }

    private void initViews() {
        textEmail = (EditText) findViewById(R.id.MainEmail);
        textPassword = (EditText) findViewById(R.id.MainPassword);

        textErrorEmail = (TextView) findViewById(R.id.ErrorEmail);
        textErrorPassword = (TextView) findViewById(R.id.ErrorPassword);

        LoginButton = (Button) findViewById(R.id.MainLogin);
        SignUpButton = (Button) findViewById(R.id.MainSignUp);
    }

    //initilize listeners
    private void initListeners() {
        LoginButton.setOnClickListener(this);
        SignUpButton.setOnClickListener(this);
    }

    //initilize objects
    private void initObjects(){
        dbHandler = new DBHandler(this);
        inputValidation = new InputValidation(this);
    }

    //clicks
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MainLogin:
                verifyFromSQLite();
                break;
            case R.id.MainSignUp:
                Intent intentSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intentSignUp);
                break;
        }
    }

    //validate input text
    private void verifyFromSQLite() {

        if (!inputValidation.isInputEditTextFilled(textEmail, textErrorEmail ,"Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textEmail, textErrorEmail ,"Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textPassword, textErrorPassword ,"Enter Valid Password")) {
            return;
        }
        if (dbHandler.checkUser(textEmail.getText().toString().trim() ,
                textPassword.getText().toString().trim())) {



            String email = textEmail.getText().toString();

            UserInfo = (ArrayList<User>) dbHandler.getUserInfo(email);

            //sjá hvort ég sé að sækja gögnin rétt.

            int UserInfoID = 0;
            String UserInfoName = "";

            for(User user : UserInfo){
                UserInfoID = user.getID();
                UserInfoName = user.getName();
                /*
                String log = "id: "+user.getID()+", Name: "+ user.getName()+", Email: " + user.getEmail();
                Log.d("from UserInfo:", log);
                */
            }
            /*
            String msg = ("ID; " + UserInfoID + " Name " + UserInfoName);
            Log.d("UserInfo: ", msg);
            */

            session.createUserLoginSession(UserInfoID, UserInfoName, email);

            Intent accountsIntent = new Intent(activity, HomePage.class);

            //accountsIntent.putExtra("EMAIL", textEmail.getText().toString().trim());

            //delete inputs
            emptyInputEditText();

            startActivity(accountsIntent);

            finish();

        } else {
            //TOAST ERROR
            Toast.makeText(getApplicationContext(), "Error check email and pw", Toast.LENGTH_LONG).show();

        }
    }

        private void emptyInputEditText() {
        textPassword.setText(null);
        textEmail.setText(null);
    }
}
