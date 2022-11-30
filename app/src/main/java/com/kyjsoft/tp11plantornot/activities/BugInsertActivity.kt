package com.kyjsoft.tp11plantornot.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityBugInsertBinding

class BugInsertActivity : AppCompatActivity() {

    val binding : ActivityBugInsertBinding by lazy { ActivityBugInsertBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this@BugInsertActivity).load(intent.getStringExtra("bugImage")).into(binding.iv)

        binding.btn.setOnClickListener {
            finish()


        }
    }
}