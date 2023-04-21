package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import androidx.activity.viewModels
import com.example.timer.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityMain3Binding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.hasExtra("NEW_HOUR")) {
            val hour = intent.getStringExtra("NEW_HOUR")
            val minute = intent.getStringExtra("NEW_MINUTE")
            viewModel.newHour = hour.toString().toInt()
            viewModel.newMinute = minute.toString().toInt()
            if (viewModel.newMinute <= 9)
                binding.tvTime.text = "${viewModel.newHour}:0${viewModel.newMinute}"
            else
                binding.tvTime.text = "${viewModel.newHour}:${viewModel.newMinute}"
        }

        binding.btnAlarm.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "pora się zbierać!")
                putExtra(AlarmClock.EXTRA_HOUR, viewModel.newHour)
                putExtra(AlarmClock.EXTRA_MINUTES, viewModel.newMinute)
            }
            startActivity(intent)
        }
    }
}