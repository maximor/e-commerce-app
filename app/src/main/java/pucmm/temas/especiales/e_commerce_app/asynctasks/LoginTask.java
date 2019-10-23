package pucmm.temas.especiales.e_commerce_app.asynctasks;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import pucmm.temas.especiales.e_commerce_app.utils.HttpConnection;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class LoginTask extends AsyncTask<Void, Void, Result> {

    private static final String LOGIN_URL = "http://ec2-3-86-40-181.compute-1.amazonaws.com:6789/login";

    private final String email;
    private final String password;
    private final Response.Listener listener;
    private final Response.ErrorListener errorListener;

    public LoginTask(String email, String password, Response.Listener listener, Response.ErrorListener errorListener){
        this.email = email;
        this.password = password;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    @Override
    protected Result doInBackground(Void... params) {
        final HttpConnection connection = new HttpConnection(LOGIN_URL);
        try{
            return new Result(connection.get());
        }catch (IOException e){
            return new Result<>(new Exception(e));
        }
    }

    @Override
    protected void onPostExecute(final Result success) {
        if (success.getResult() != null) {
            try {
                final JSONArray jsonArray = new JSONArray(String.valueOf(success.getResult()));
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    // Account exists, return true if the password matches.
                    if (!jsonObject.getString("user").equals(email) && !jsonObject.getString("email").equals(email)) {
                        errorListener.onErrorResponse(new Exception("No account found for email [" + email + "]"));
                    } else if (!jsonObject.getString("password").equals(password)) {
                        errorListener.onErrorResponse(new Exception("No password found for email [" + email + "]"));
                    } else {
                        listener.onResponse(jsonObject.put("RESPONSE", "SUCCESS"));
                    }
                }
            } catch (JSONException e) {
                try {
                    final JSONArray jsonArray = new JSONArray(String.valueOf(success.getResult()));
                    final JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                    errorListener.onErrorResponse(new Exception(jsonObject.getString("MESSAGE")));

                } catch (JSONException ex) {
                    errorListener.onErrorResponse(e);
                }
            }
        } else {
            errorListener.onErrorResponse(success.getError());
        }
    }

    @Override
    protected void onCancelled() {
        errorListener.onErrorResponse(new Exception("Task cancelled"));
    }
}
