package is.siggigauti.touristapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import is.siggigauti.touristapp.R;
import is.siggigauti.touristapp.controllers.DBHandler;
import is.siggigauti.touristapp.helpers.InputValidation;
import is.siggigauti.touristapp.model.User;

import static is.siggigauti.touristapp.R.id.SignUpButton;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText textuserName, textpassword, textconfirmPassword, textEmail;
    private TextView textErrorName, textErrorPassword, textErrorConfirmPassword, textErrorEmail;
    private Button createUserButton;

    private DBHandler dbHandler;
    private InputValidation inputValidation;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        initListeners();
        initObjects();
    }

    //init views

    private void initViews() {

        //útlit
        textuserName = (EditText) findViewById(R.id.SignUpUserName);
        textpassword = (EditText) findViewById(R.id.SignUpPassword);
        textconfirmPassword = (EditText) findViewById(R.id.SignUpPasswordConfirmPassword);
        textEmail = (EditText)findViewById(R.id.SignUpEmail);

        //buttons
        createUserButton = (Button) findViewById(SignUpButton);

        //errors
        textErrorName = (TextView) findViewById(R.id.ErrorName);
        textErrorEmail = (TextView) findViewById(R.id.ErrorEmail);
        textErrorPassword = (TextView) findViewById(R.id.ErrorPassword);
        textErrorConfirmPassword = (TextView) findViewById(R.id.ErrorConfirmPassword);

    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        dbHandler = new DBHandler(this);
        user = new User();
    }

    private void initListeners() {
        createUserButton.setOnClickListener(this);
    }

    //clicky

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case SignUpButton:
                postDataToSQLite();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textuserName, textErrorName, "Enter Full Name")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textEmail, textErrorEmail , "Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textEmail, textErrorEmail ,"Enter Valid Email")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textpassword,textErrorPassword ,"Enter Password")) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textpassword, textconfirmPassword,textErrorConfirmPassword,
                "Password does not matches")) {
            return;
        }

        if (!dbHandler.checkUser(textEmail.getText().toString().trim())) {

            user.setName(textuserName.getText().toString().trim());
            user.setEmail(textEmail.getText().toString().trim());
            user.setPassword(textpassword.getText().toString().trim());

            dbHandler.addUser(user);

            // Toast Bar to show success message that record saved successfully
            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
            //clear text
            emptyInputEditText();

            //go to login page
            Intent intentLogin = new Intent(SignUp.this, MainActivity.class);
            startActivity(intentLogin);

        } else {
            // Toast to show error message that record already exists
            Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_LONG).show();

        }
    }

    private void emptyInputEditText() {
        textuserName.setText(null);
        textEmail.setText(null);
        textpassword.setText(null);
        textconfirmPassword.setText(null);
    }


}



