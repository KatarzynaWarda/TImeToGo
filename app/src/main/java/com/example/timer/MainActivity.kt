package com.example.timer

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.timer.databinding.ActivityMainBinding
import java.time.LocalTime
import java.time.LocalTime.of
import java.util.*


class MainActivity : AppCompatActivity(){
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var hourText = ""
        var minuteText = ""

        binding.timePicker.setOnTimeChangedListener { view, hour, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            var hour = calendar.get(Calendar.HOUR_OF_DAY)
            var minute = calendar.get(Calendar.MINUTE)
            hourText = hour.toString()
            minuteText = minute.toString()
            if (minute <=9)
                viewModel.selectedTime = "${hour}:0${minute}"
            else
                viewModel.selectedTime = "${hour}:${minute}"
            binding.tvSelectedTime.text = viewModel.selectedTime
            binding.tvSelectedTime.textSize = 35F
        }

        binding.btnNext.setOnClickListener {
            if (binding.tvSelectedTime.text == "Wybierz godzinę wyjścia") {
                Toast.makeText(applicationContext,"Wybierz godzinę", Toast.LENGTH_SHORT).show()
            }
            else {
                val intencja = Intent(applicationContext, MainActivity2::class.java)
                intencja.putExtra("HOUR",hourText)
                intencja.putExtra("MINUTE",minuteText)
                startActivity(intencja)
            }

        }
    }
}