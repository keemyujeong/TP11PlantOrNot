package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    var imgUrl : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"


        binding.civ.setOnClickListener { resultLauncher.launch(intent) }

        binding.btn.setOnClickListener {clickBtn()}

    }

    var resultLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                Glide.with(this).load(result.data?.data).error("").into(binding.civ)
                imgUrl = getPathFromUri(result.data?.data)
                Toast.makeText(this@ProfileActivity, ""+imgUrl, Toast.LENGTH_SHORT).show()

            }
        }


    fun clickBtn(){

        G.name = binding.et.text.toString()
        G.pic = imgUrl.toString()

        val intent : Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

        // TODO 한꺼번에 dothome서버에 저장 ( location 빼고 나머지 저장 )


    }




//    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
//
//        Glide.with(this).load(it).error("").into(binding.civ)
//        // retrofit으로 보내야해서 이거 절대주소로 바꿔야함 it은 컨텐츠 주소
//        it.let {
//            Toast.makeText(this@ProfileActivity, , Toast.LENGTH_SHORT).show()
//        }
//    }


    fun getPathFromUri(uri: Uri?): String { // TODO  여기 에러남
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(this, uri!!, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_index)
        cursor.close()
        return result
    }




}