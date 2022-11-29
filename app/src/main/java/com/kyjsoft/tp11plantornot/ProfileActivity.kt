package com.kyjsoft.tp11plantornot

import android.content.Intent

import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityProfileBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import java.io.File

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    lateinit var profileimgUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"


        binding.civ.setOnClickListener { resultLauncher.launch(intent) }

        binding.btn.setOnClickListener {
            G.name = binding.et.text.toString()
            insertProfileDB()
            insertSQLite()
            Log.i("TAG-G", G.id + G.name + G.location + G.plant + G.pic)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    var resultLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                Glide.with(this).load(result.data?.data).error("").into(binding.civ)
                profileimgUrl = getPathFromUri(result.data?.data)
//                Toast.makeText(this@ProfileActivity, ""+imgUrl, Toast.LENGTH_SHORT).show()
                G.pic = profileimgUrl
            }
        }


    fun insertProfileDB(){

        var datapart : MutableMap<String, String> = HashMap()
        datapart["id"] = G.id
        datapart["name"] = G.name
        datapart["plant"] = G.plant

        val retrofit : Retrofit = RetrofitHelper.getInstance("Http://kyjsoft.dothome.co.kr")
        val retrofitService = retrofit.create(RetrofitService::class.java)
        lateinit var filePart : MultipartBody.Part
        profileimgUrl!!.let {
            val file = File(profileimgUrl)
            val requestBody = RequestBody.create(
                MediaType.parse("image/*"),
                file
            )
            filePart = MultipartBody.Part.createFormData(
                "file", file.name, requestBody
            )
        }

        retrofitService.postProfileDataToServer(datapart, filePart).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("TAG-profile", response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                AlertDialog.Builder(this@ProfileActivity).setMessage(t.message).show()
            }
        })


    }

    fun insertSQLite(){
        val db : SQLiteDatabase = openOrCreateDatabase("map", MODE_PRIVATE, null)

        // SQLite에 정보 저장
        db.execSQL("CREATE TABLE IF NOT EXISTS map( num INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, location TEXT, nx TEXT, ny TEXT )")
        db.execSQL("INSERT INTO map(id, location, nx, ny) VALUES(?,?,?,?)", arrayOf(G.id, G.location, G.locationX, G.locationY))
    }

//    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
//
//        Glide.with(this).load(it).error("").into(binding.civ)
//        // retrofit으로 보내야해서 이거 절대주소로 바꿔야함 it은 컨텐츠 주소
//        it.let {
//            Toast.makeText(this@ProfileActivity, , Toast.LENGTH_SHORT).show()
//        }
//    }


    fun getPathFromUri(uri: Uri?): String {
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