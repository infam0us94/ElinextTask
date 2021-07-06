package com.project.elinexttask

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project.elinexttask.databinding.ActivityMainBinding
import com.project.elinexttask.ui.MainFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isNetworkConnected()) {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MainFragment())
                    .commit()
            }
        } else {
            Toast.makeText(this, "No Internet access available", Toast.LENGTH_LONG).show()
        }
    }

    private fun isNetworkConnected(): Boolean {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = conMgr.activeNetworkInfo
        return activeNetwork != null && activeNetwork.state == NetworkInfo.State.CONNECTED
    }
}
