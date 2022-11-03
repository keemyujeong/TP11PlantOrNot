package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.civ.setOnClickListener { getContent.launch("image/*") }

        binding.btn.setOnClickListener {clickBtn()}

    }

    fun clickBtn(){
        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        Glide.with(this).load(it).error("").into(binding.civ)
    }




}