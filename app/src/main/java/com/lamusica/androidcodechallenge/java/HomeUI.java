package com.lamusica.androidcodechallenge.java;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lamusica.androidcodechallenge.R;

public class HomeUI extends AppCompatActivity implements HomeInterface{
    Button btnCallApi;
    ConstraintLayout viewParentBackground;
    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ui);
        //initializing xml views
        btnCallApi= findViewById(R.id.home_btn_call_api);
        viewParentBackground= findViewById(R.id.home_view);

        //initializing java objects
        presenter= new HomePresenter(this,this);


        //setting button clicklistener
        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadData();
            }
        });

    }

    @Override
    public void changeButtonLabel(String labelText) {
        btnCallApi.setText(labelText);
    }

    @Override
    public void changeBackgroundColor(String colorCode) {
        viewParentBackground.setBackgroundColor(Color.parseColor(colorCode));
    }
}
