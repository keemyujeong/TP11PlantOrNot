package com.kyjsoft.tp11plantornot

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.kyjsoft.tp11plantornot.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    val binding : ActivityMapBinding by lazy { ActivityMapBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(permissions, 100)
        }



    }

    fun clickBtn(){

        val intent : Intent = Intent(this, PickActivity::class.java)
        startActivity(intent)
        finish()
    }
};