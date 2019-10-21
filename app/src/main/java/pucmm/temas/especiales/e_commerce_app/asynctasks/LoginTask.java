package pucmm.temas.especiales.e_commerce_app.asynctasks;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class LoginTask extends AsyncTask<Void, Void, Result> {

    private static final String LOGIN_URL = "http://ec2-3-86-40-181.compute-1.amazonaws.com:9876/users";

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
        return null;
    }

    @Override
    protected void onPostExecute(final Result result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {

    }
}
