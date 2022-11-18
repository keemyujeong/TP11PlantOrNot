package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    val binding : ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val naver_client_id = "jZfk5vEJVXIhqK27tlfB"
        val naver_client_secret = "M3_UE0b_9i"

//        NaverIdLoginSDK.initialize(this@LoginActivity, naver_client_id, naver_client_secret, "네이버 아이디 로그인")

//        binding.buttonOAuthLoginImg.setOAuthLogin(launcher, oauthLoginCallback)




        binding.btn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
            finish()

            G.id = "로그인 아이디"
        }




    }
}