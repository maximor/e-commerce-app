package pucmm.temas.especiales.e_commerce_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import pucmm.temas.especiales.e_commerce_app.asynctasks.Response;
import pucmm.temas.especiales.e_commerce_app.asynctasks.SignupTask;
import pucmm.temas.especiales.e_commerce_app.dialog.DatePickerFragment;
import pucmm.temas.especiales.e_commerce_app.entities.User;
import pucmm.temas.especiales.e_commerce_app.utils.FieldValidator;

public class SignUpActivity extends AppCompatActivity  {
    //TODO: SET FORMAT TO PHONE FIELD.
    //TODO: ADD A LOADING BAR WHEN ANY CREATE BUTTON IS PRESSED.
    //TODO: LAUNCH ALERT MESSAGE WHEN A USER IS CREATED SUCCESSFULLY.
    //TODO: LAUNCH ALERT MESSAGE WHEN THERE'S AN ERROR.
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
    private Spinner rols;

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
        this.dateBirth = (EditText) findViewById(R.id.txtDateBirth);
        this.forgotPassword = (TextView) findViewById(R.id.viewForgot);
        this.login = (TextView) findViewById(R.id.viewLogin);
        this.signup = (Button) findViewById(R.id.btnSignup);
        this.rols = (Spinner) findViewById(R.id.cmbRol);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rols, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        rols.setAdapter(adapter);

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

        //Set event listener on click to call a dialog with a calendar to pick up user date of birth
        this.dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            switch (v.getId()){
                case R.id.txtDateBirth:
                    showDatePickerDialog();
                    break;
            }
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
        //TODO: VALIDATE ROL COMBOBOX
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
        if(FieldValidator.isEmpty(this.dateBirth)){
            this.dateBirth.setError("This field can not be blank");
            validator = false;
        }

        if(validator){
            User user = new User(
                    this.name.getText().toString(),
                    this.user.getText().toString(),
                    this.password.getText().toString(),
                    this.email.getText().toString(),
                    this.rols.getSelectedItem().toString(),
                    this.contact.getText().toString(),
                    this.dateBirth.getText().toString());

            cleanTextFields();
            SignupTask signupTask = new SignupTask(user, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("JSON: ", response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(Exception error) {
                    Log.i("Error: ", error.getMessage());
                }
            });
            signupTask.execute();

        }
    }

    private void cleanTextFields(){
        this.name.setText(null);
        this.user.setText(null);
        this.password.setText(null);
        this.confirmPassword.setText(null);
        this.email.setText(null);
        this.rols.setSelection(0);
        this.contact.setText(null);
        this.dateBirth.setText(null);
    }

    private void showDatePickerDialog(){
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // +1 because January is Zero
                final String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dateBirth.setText(selectedDate);
            }
        });
        newFragment.show(this.getSupportFragmentManager(), "datePicker");
    }
}
