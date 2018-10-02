package com.lamusica.androidcodechallenge.java;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lamusica.androidcodechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePresenter {
    private HomeInterface uiInterface;
    private Context context;
    private String TAG="serverResponse";

    HomePresenter(Context context, HomeInterface uiInterface) {
        this.context= context;
        this.uiInterface = uiInterface;
    }

    public void loadData(){
        //declaring and initializing a new request queue
        RequestQueue apiRequestQueue= Volley.newRequestQueue(context);

        String apiUrl= context.getResources().getString(R.string.apiURL);

        //creating a JSON Object request
        JsonObjectRequest httpRequestForJsonObject= new JsonObjectRequest(Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.d(TAG, "onResponse: "+response.toString());
                        //strings to save response from server
                        String labelText="",backgroundColorCode="";

                        //De-serializing JSON response manually.
                        try {
                            JSONArray arrayOfData= response.getJSONArray("data");
                            JSONObject sixthElement= arrayOfData.getJSONObject(5);
                            labelText= sixthElement.getString("title");
                            backgroundColorCode= sixthElement.getString("color");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Error Parsing JSON", Toast.LENGTH_SHORT).show();
                        }

                        //checking if the proper strings have been initialized with expected values
                        // and then making change to the UI using the interface
                        if (!labelText.isEmpty()){
                            uiInterface.changeButtonLabel(labelText);
                        }if (!backgroundColorCode.isEmpty()){
                            uiInterface.changeBackgroundColor(backgroundColorCode);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });

        apiRequestQueue.add(httpRequestForJsonObject);
    }
}
