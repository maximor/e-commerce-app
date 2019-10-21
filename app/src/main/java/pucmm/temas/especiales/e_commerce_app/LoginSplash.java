package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class LoginSplash extends AppCompatActivity {

    private final static int SPLASH_TIME_OUT = 3000;
    private AnimationDrawable loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash);

        updateUI();
    }

    private void updateUI(){

        final ImageView loadingImage = (ImageView) findViewById(R.id.imageView);
        loadingImage.setBackgroundResource(R.drawable.loading);
        loading = (AnimationDrawable) loadingImage.getBackground();
        loading.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoginSplash.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
