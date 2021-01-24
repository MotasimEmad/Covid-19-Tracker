package com.example.covid19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import android.content.Intent



class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mActionBar: ActionBar = supportActionBar!!
        mActionBar.hide()

        val thread = object : Thread() {
            override fun run() {
                super.run()
                try {
                    Thread.sleep(4000)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
        thread.start()
    }
}
