package com.lamusica.androidcodechallenge

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import kotlinx.android.synthetic.main.activity_home_ui.*

class HomeViewController : AppCompatActivity() , HomeInterface{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_ui)
        //initializing the helper class to call api with 2 constructor,
        //first the context of the activity, second the Interface object to allow Presenter class
        //to make changes to the UI
        val presenter = HomePresenter(this,this)

        //setting button clickListener
        home_btn_call_api.setOnClickListener {
            //using helper class object to load data from API
            presenter.loadData()
        }
    }

    override fun changeButtonLabel(elementTitle: String) {
        //changes the button label with the text received from the API
        home_btn_call_api.text = elementTitle
    }

    override fun changeBackgroundColor(backgroundColorCode: String) {
        //changes the backgroundColor with the color code received from the API
        home_view.setBackgroundColor(Color.parseColor(backgroundColorCode))
    }
}
