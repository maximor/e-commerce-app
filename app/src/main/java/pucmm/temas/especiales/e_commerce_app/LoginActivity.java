package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pucmm.temas.especiales.e_commerce_app.utils.FieldValidator;

public class LoginActivity extends AppCompatActivity {
    private TextView message;
    private EditText user;
    private EditText password;
    private Button login;
    private TextView signup;
    private TextView forgotPassword;

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
        }

        if(FieldValidator.isEmpty(this.password)){
            this.password.setError("This field can not be blank");
            validator = false;
        }

        if(validator){

            if(this.user.getText().toString().equals("admin") && this.password.getText().toString().equals("admin")){
                Intent intent = new Intent(LoginActivity.this, LoginSplash.class);
                startActivity(intent);
            }else{

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
