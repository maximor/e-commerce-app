package pucmm.temas.especiales.e_commerce_app.utils;

import android.widget.EditText;

public class FieldValidator {
    public FieldValidator() { }

    public static boolean isEmpty(EditText editText){
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }

    public static boolean isPasswordValid(final EditText view, final String pass) {
        if (pass.length() < 4 || pass.length() > 20) {
            view.setError("Password Must consist of 4 to 20 characters");
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(final EditText view, final String email) {
        if (email.length() < 4 || email.length() > 30) {
            view.setError("Email Must consist of 4 to 30 characters");
            return false;
        } else if (!email.matches("^[A-za-z0-9.@]+")) {
            view.setError("Only . and @ characters allowed");
            return false;
        } else if (!email.contains("@") || !email.contains(".")) {
            view.setError("Email must contain @ and .");
            return false;
        }
        return true;
    }
}
