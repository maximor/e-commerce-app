package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import pucmm.temas.especiales.e_commerce_app.asynctasks.Response;
import pucmm.temas.especiales.e_commerce_app.asynctasks.SignupTask;
import pucmm.temas.especiales.e_commerce_app.entities.User;
import pucmm.temas.especiales.e_commerce_app.utils.FieldValidator;

public class SignUpActivity extends AppCompatActivity  {
    //TODO: VERIFY WHAT IS IS PROVIDER PARAMETER.
    //TODO: ADD A LOADING BAR WHEN ANY CREATE BUTTON IS PRESSED.
    //TODO: LAUNCH ALERT MESSAGE WHEN A USER IS CREATED SUCCESSFULLY.
    //TODO: LAUNCH ALERT MESSAGE WHEN THERE'S AN ERROR.
    //TODO: VERIFY THE API RESPONSE TO CREATE AN IDEAL ALERT STRUCTURE.
    private EditText name;
    private EditText user;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText contact;
    private EditText dateBirth;
    private TextView forgotPassword;
    private TextView login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        updateUI();
    }

    private void updateUI() {
        this.name = (EditText) findViewById(R.id.txtName);
        this.user = (EditText) findViewById(R.id.txtUser);
        this.email = (EditText) findViewById(R.id.txtEmail);
        this.password = (EditText) findViewById(R.id.txtPassword);
        this.confirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        this.contact = (EditText) findViewById(R.id.txtContact);
        //Setting format to contact field
        this.contact.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        this.forgotPassword = (TextView) findViewById(R.id.viewForgot);
        this.login = (TextView) findViewById(R.id.viewLogin);
        this.signup = (Button) findViewById(R.id.btnSignup);


        //Access event for Forgot password
        this.forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showForgotPassword();
            }
        });

        this.login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showLogin();
            }
        });

        //When the signup button is clicked
        this.signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                registrate();
            }
        });
    }


    private void showForgotPassword(){
        Intent intent = new Intent(SignUpActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void showLogin(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void registrate(){
        boolean validator = true;
        if(FieldValidator.isEmpty(this.name)){
            this.name.setError("This field can not be blank");
            validator = false;
        }
        if(FieldValidator.isEmpty(this.user)){
            this.user.setError("This field can not be blank");
            validator = false;
        }
        if(FieldValidator.isEmpty(this.password)){
            this.password.setError("This field can not be blank");
            validator = false;
        }
        if(FieldValidator.isEmpty(this.confirmPassword)){
            this.confirmPassword.setError("This field can not be blank");
            validator = false;
        }
        if(FieldValidator.isEmpty(this.email)){
            this.email.setError("This field can not be blank");
            validator = false;
        }

        if(validator){
            //verifies if the passwords provided are igual, if it's the case then do the post
            if(!this.password.getText().toString().equals(this.confirmPassword.getText().toString())){
                this.password.setError("password doesn't match");
                this.confirmPassword.setError("password doesn't match");
            }else{
                User user = new User(this.name.getText().toString(),
                        this.user.getText().toString(),
                        this.email.getText().toString(),
                        this.password.getText().toString(),
                        this.contact.getText().toString(),
                        "",false);
                cleanTextFields();
                SignupTask signupTask = new SignupTask(user, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("JSON: ", response.toString());
                        //TODO: SEND A MESSAGE OF A SUCCESS USER CREATION
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(Exception error) {
                        Log.i("Error: ", error.getMessage());
                        //TODO: SEND A MESSAGE OF AN ERROR IF SOMETHING HAPPEN ON THE CREATION
                    }
                });
                signupTask.execute();
            }
        }
    }

    private void cleanTextFields(){
        this.name.setText(null);
        this.user.setText(null);
        this.password.setText(null);
        this.confirmPassword.setText(null);
        this.email.setText(null);
        this.contact.setText(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Login CheckPoint", "LoginActivity resumed");
        //TODO: CHECK INTERNET CONNECTION
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
