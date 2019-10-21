package pucmm.temas.especiales.e_commerce_app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpConnection {
    private URL url;
    private int responseCode;
    private String method;
    private HashMap<String, String> requests;
    private HttpURLConnection connection;

    public HttpConnection() { }

    public HttpConnection(final String url){
        this(url, null, "GET");
    }

    public HttpConnection(final String url, final String method){
        this(url, null, method);
    }

    public HttpConnection(final String url, final Map<String, String> requests, final String method){
        this.method = method;
        this.requests = new HashMap<>();
        this.requests.put("Content-Type", "application/json");
        this.requests.put("TOKEN", "9elv4YuVmrjJo4_WViokmyhhtlWgE6AMo5br45om");

        if(requests != null){
            this.requests.putAll(requests);
        }

        try{
            this.url = new URL(url);
            connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod(this.method);

            for(Map.Entry<String, String> entry: this.requests.entrySet()){
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            if(method.equals("POST")){
                connection.setDoOutput(true);
            }else{
                connection.setDoOutput(false);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String get() throws IOException{
        final BufferedReader reader;

        if(responseCode == HttpURLConnection.HTTP_OK){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        try{
            return getReader(reader);
        }catch(IOException e){
            throw new IOException(e);
        }
    }

    public String post(String json) throws IOException{
        final BufferedReader reader;
        connection.getOutputStream().write(json.getBytes("utf-8"));
        if(HttpURLConnection.HTTP_OK == 200){
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        }else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        try{
            return getReader(reader);
        }catch (IOException e){
            throw new IOException(e);
        }finally{
            connection.disconnect();
            reader.close();
        }
    }

    private String getReader(final BufferedReader reader) throws IOException{
        final StringBuilder stringBuilder = new StringBuilder();
        try{
            String line = null;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }catch (IOException e){
            throw new IOException(e);
        }finally {
            connection.disconnect();
            reader.close();
        }
    }

    public int getResponseCode(){
        return responseCode;
    }
}
