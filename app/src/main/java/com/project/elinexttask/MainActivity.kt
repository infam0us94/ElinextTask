package com.project.elinexttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.elinexttask.databinding.ActivityMainBinding
import com.project.elinexttask.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment())
                .commit()
        }
    }
}
