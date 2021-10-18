package com.example.macaronagaintoay.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.macaronagaintoay.R
import android.util.Log
import androidx.lifecycle.Observer
import com.example.macaronagaintoay.viewmodel.IntroViewModel


class IntroActivity : AppCompatActivity() {

    private val viewModel : IntroViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        viewModel.LastDbVersion.observe(this, Observer {
            if(it != null){
                Log.d("asdasd t", it.versions.toString())
                viewModel.getDBversion(it.versions!!)
            } else {
                Log.d("asdasd r", "Exception")
                viewModel.getDBversion()
            }
        })

        viewModel.vmDBstate.observe(this, Observer {

                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        })

    }

}