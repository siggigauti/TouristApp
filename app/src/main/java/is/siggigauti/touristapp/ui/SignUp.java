package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.LoginDataBase;

public class SignUp extends AppCompatActivity {

    private EditText TextuserName, Textpassword, TextconfirmPassword;
    private Button createUserButton;

    LoginDataBase logindatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        logindatabase = new LoginDataBase(this);
        logindatabase = logindatabase.open();


        TextuserName = (EditText) findViewById(R.id.SignUpUserName);
        Textpassword = (EditText) findViewById(R.id.SignUpPassword);
        TextconfirmPassword = (EditText) findViewById(R.id.SignUpPasswordConfirmPassword);

        createUserButton = (Button) findViewById(R.id.SignUpButton);
        createUserButton.setOnClickListener(new View.OnClickListener() {

            //clicky
            public void onClick(View v) {
                String userName = TextuserName.getText().toString();
                String password = Textpassword.getText().toString();
                String confirmPassword = TextconfirmPassword.getText().toString();

                //checkum á empty
                if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //save data into db.
                    logindatabase.insertLogin(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                    Intent intentLogin = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intentLogin);
                }

            }
        });
    }



        @Override
        protected void onDestroy() {
            super.onDestroy();
            logindatabase.close();
        }
    }

