package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {

    val binding : ActivityEditBinding by lazy { ActivityEditBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }


    }

    fun clickBtn(){
        finish()
    }
}