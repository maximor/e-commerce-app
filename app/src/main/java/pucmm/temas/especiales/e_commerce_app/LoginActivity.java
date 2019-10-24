package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import pucmm.temas.especiales.e_commerce_app.asynctasks.LoginTask;
import pucmm.temas.especiales.e_commerce_app.asynctasks.Response;
import pucmm.temas.especiales.e_commerce_app.entities.User;
import pucmm.temas.especiales.e_commerce_app.utils.FieldValidator;
import pucmm.temas.especiales.e_commerce_app.utils.Networking;
import pucmm.temas.especiales.e_commerce_app.utils.UserSession;

public class LoginActivity extends AppCompatActivity {
    //TODO: LOADING BAR WHEN PRESS LOGIN BUTTON.
    private TextView message;
    private EditText user;
    private EditText password;
    private Button login;
    private TextView signup;
    private TextView forgotPassword;

    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        updateUI();
    }

    private void updateUI() {
        this.user = (EditText) findViewById(R.id.txtUser);
        this.password = (EditText) findViewById(R.id.txtPassword);
        this.login = (Button) findViewById(R.id.btnLogin);
        this.signup = (TextView) findViewById(R.id.viewSignup);
        this.forgotPassword = (TextView) findViewById(R.id.viewForgot);

        session = new UserSession(getApplicationContext());

        //access event for login authentication
        this.login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                auth();
            }
        });

        //Access event for signup
        this.signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showSignup();
            }
        });

        //Access event for Forgot password
        this.forgotPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showForgotPassword();
            }
        });
    }

    private void auth(){
        boolean validator = true;
        if(FieldValidator.isEmpty(this.user)){
            this.user.setError("This field can not be blank");
            validator = false;
        }else if(!FieldValidator.isEmailValid(this.user, this.user.getText().toString())){
            validator = false;
        }

        if(FieldValidator.isEmpty(this.password)){
            this.password.setError("This field can not be blank");
            validator = false;
        }else if(!FieldValidator.isPasswordValid(this.password, this.password.getText().toString())){
            validator = false;
        }

        if(validator){
            //verify if the internet is available before request the login
            if(Networking.getConnectionStatus(this)){
                LoginTask loginTask = new LoginTask(this.user.getText().toString(),
                        this.password.getText().toString(),
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                Log.i("JSON: ", response.toString());
                                Gson gson = new Gson();
                                User userInformation = gson.fromJson(response.toString(), User.class);

                                //store data in the shred preference to have a session
                                session.createLoginSession(userInformation.getId(),
                                        userInformation.getEmail(),
                                        userInformation.getUser(),
                                        userInformation.getName(),
                                        userInformation.getToken());


                                Intent intent = new Intent(LoginActivity.this, LoginSplash.class);
                                startActivity(intent);
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(Exception error) {
                        //send error message with Toast Class
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Incorrect email or password",
                                Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
                        toast.show();
                        user.setError("Verify email ["+user.getText().toString()+"], may be incorrect");
                        password.setError("Verify password, may be incorrect");
                    }
                });
                loginTask.execute();
            }else{
                Toast.makeText(this,"No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showSignup(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void showForgotPassword(){
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
