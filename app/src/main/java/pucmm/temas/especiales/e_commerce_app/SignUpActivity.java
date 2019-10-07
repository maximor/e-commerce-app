package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private TextView forgotPassword;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.forgotPassword = (TextView) findViewById(R.id.viewForgot);
        this.login = (TextView) findViewById(R.id.viewLogin);

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

    }

    private void showForgotPassword(){
        Intent intent = new Intent(SignUpActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void showLogin(){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
