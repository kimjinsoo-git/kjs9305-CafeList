package com.example.macaronagaintoay.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.macaronagaintoay.R
import android.util.Log
import androidx.lifecycle.Observer
import com.example.macaronagaintoay.Entity.DBVersionEntity
import com.example.macaronagaintoay.viewmodel.IntroViewModel
import java.lang.Exception


class IntroActivity : AppCompatActivity() {

    private val viewModel : IntroViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //viewModel.delete()
        viewModel.LastDbVersion.observe(this, Observer {
            try {


                Log.d("asdasd t", it.versions.toString())
                viewModel.getDBversion(it.versions!!)

            }catch (e : Exception){
                Log.d("asdasd r", "Exception")
                viewModel.insert(DBVersionEntity(null, 1))
            }
        })

        viewModel.vmDBstate.observe(this, Observer {
            if (it){
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }









}