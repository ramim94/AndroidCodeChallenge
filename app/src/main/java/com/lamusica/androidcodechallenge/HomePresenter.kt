package com.lamusica.androidcodechallenge

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class HomePresenter(val context: Context,val uiInterface: HomeInterface) {
    lateinit var requstQueue : RequestQueue

    fun loadData(){
        //getting the String value of URL from strings.xml
        val apiURL= context.resources.getString(R.string.apiURL)

        //The library volley automatically executes data on the background thread
        requstQueue= Volley.newRequestQueue(context)

        //creating the HTTP request object using volley's StringRequest
        val stringRequest= StringRequest(Request.Method.GET,apiURL, Response.Listener {
            val completeApiResponse = JSONObject(it) as JSONObject
            //getting the Array named as 'data' in the Response
            val dataArray= completeApiResponse.getJSONArray("data");

            //getting the 'title' and 'color' fields of the 6th element
            //we can also run a for loop here to get all the elements in the array
            val elementTitle= dataArray.getJSONObject(5).getString("title")
            val backgroundColorCode= dataArray.getJSONObject(5).getString("color")

            //checking to see if the strings are correctly read from the API and
            //then using the interface to make changes to UI
            if (elementTitle.isNotEmpty()){
                uiInterface.changeButtonLabel(elementTitle)
            }
            if (backgroundColorCode.isNotEmpty()){
                uiInterface.changeBackgroundColor(backgroundColorCode)
            }
            //a log used for debugging and checking response
            Log.d("serverResponse",elementTitle)
        }, Response.ErrorListener {
            //a log used for debugging and checking response
                   Log.d("serverResponseError",it.toString())
                })

        requstQueue.add(stringRequest)
    }
}