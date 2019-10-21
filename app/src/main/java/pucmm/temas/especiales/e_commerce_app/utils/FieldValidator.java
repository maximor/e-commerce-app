package pucmm.temas.especiales.e_commerce_app.utils;

import android.widget.EditText;

public class FieldValidator {
    public FieldValidator() { }

    public static boolean isEmpty(EditText editText){
        String input = editText.getText().toString().trim();
        return input.length() == 0;
    }
}
