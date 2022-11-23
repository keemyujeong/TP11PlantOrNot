package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
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
            G.id = "로그인 아이디"

            insertSQLite()

            startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
            finish()

        }

    }
    fun insertSQLite(){
        val db : SQLiteDatabase = openOrCreateDatabase("map", MODE_PRIVATE, null)

        // SQLite에 정보 저장
        db.execSQL("CREATE TABLE IF NOT EXISTS map( num INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, location TEXT, nx TEXT, ny TEXT )")
        db.execSQL("INSERT INTO map(id, location, nx, ny) VALUES(?,?,?,?)", arrayOf(G.id, G.location, G.locationX, G.locationY))
    }
}