package com.kyjsoft.tp11plantornot.activities

import android.content.Intent
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kyjsoft.tp11plantornot.databinding.ActivityLoginBinding
import com.kyjsoft.tp11plantornot.model.G


class LoginActivity : AppCompatActivity() {

    val binding : ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val naver_client_id = "jZfk5vEJVXIhqK27tlfB"
        val naver_client_secret = "M3_UE0b_9i"

//        NaverIdLoginSDK.initialize(this@LoginActivity, naver_client_id, naver_client_secret, "네이버 아이디 로그인")

//        binding.buttonOAuthLoginImg.setOAuthLogin(launcher, oauthLoginCallback)


        // 회원가입
//        binding.tvSignup.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
//        }
//
//        binding.layoutEmail.setOnClickListener {
//            startActivity((Intent(this@LoginActivity, EmailLoginActivity::class.java)))
//        }

        // 간편 로그인 버튼
        binding.btnLoginGoogle.setOnClickListener { clickedLoginGoogle() }
        binding.btnLoginKakao.setOnClickListener { clickedLoginKakao() }
        binding.btnLoginNaver.setOnClickListener { clickedLoginNaver() }





        binding.btn.setOnClickListener {
            G.id = "로그인 아이디"

            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
            finish()

        }

    }

    fun clickedLoginNaver(){

    }
    fun clickedLoginKakao(){

    }
    fun clickedLoginGoogle(){

    }

}