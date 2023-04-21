package com.example.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import com.example.timer.databinding.ActivityMain2Binding
import java.util.*

class MainActivity2 : AppCompatActivity() {
    lateinit var binding : ActivityMain2Binding
    private val viewModel: MainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val doingAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, viewModel.doingList)
        val doingListView = binding.lvToDo
        doingListView.adapter = doingAdapter
        val timeAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, viewModel.timeList)
        val timeListView = binding.lvTimeToDo
        timeListView.adapter = timeAdapter

        binding.btnAdd.setOnClickListener {
            if (binding.etWhatToDo.text.isNullOrEmpty() || binding.etTimeToDo.text.isNullOrEmpty()) {
                Toast.makeText(applicationContext,"Nie uzupełniono pola", Toast.LENGTH_SHORT).show()
            }
            else
            {
                viewModel.doingList.add(binding.etWhatToDo.text.toString())
                viewModel.timeList.add(binding.etTimeToDo.text.toString().toInt())
                doingListView.adapter = doingAdapter
                timeListView.adapter = timeAdapter
                binding.etWhatToDo.setText("")
                binding.etTimeToDo.setText("")
            }
        }

        binding.btnNext.setOnClickListener {
            TimeToGo()
        }
    }

    fun TimeToGo() {
        var newHour = ""
        var newMinute = ""
        for (time in viewModel.timeList){
            viewModel.sum += time
        }
        if (intent.hasExtra("HOUR")) {
            val hour = intent.getStringExtra("HOUR")
            val minute = intent.getStringExtra("MINUTE")
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY,hour.toString().toInt())
            calendar.set(Calendar.MINUTE,minute.toString().toInt())
            calendar.add(Calendar.MINUTE,-viewModel.sum)
            newHour = calendar.get(Calendar.HOUR_OF_DAY).toString()
            newMinute = calendar.get(Calendar.MINUTE).toString()

        }
        val intencja = Intent(applicationContext, MainActivity3:: class.java)
        intencja.putExtra("NEW_HOUR", newHour)
        intencja.putExtra("NEW_MINUTE", newMinute)
        startActivity(intencja)


    }
}