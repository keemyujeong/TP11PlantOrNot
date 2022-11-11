package com.kyjsoft.tp11plantornot

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityEditBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class EditActivity : AppCompatActivity() {

    val binding : ActivityEditBinding by lazy { ActivityEditBinding.inflate(layoutInflater) }
    lateinit var imgPath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }
        binding.iv.setOnClickListener{
            getContent.launch("image/*")
            binding.tv.visibility = View.GONE


        }


    }

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        Glide.with(this).load(it).error("").into(binding.iv)
        imgPath = getPathFromUri(it)
        AlertDialog.Builder(this@EditActivity).setMessage(imgPath.toString()).show()
    }

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



    fun clickBtn(){
        var title = binding.etTitle.toString()
        var text = binding.etContent.toString()


        // retrofit으로 웹서버에 올리기
        var dataPart : MutableMap<String, String> = HashMap()
        dataPart["id"] = "로그인 아이디"
        dataPart["title"] = title
        dataPart["text"] = text

        val retrofit = RetrofitHelper.getInstance("Http://kyjsoft.dothome.co.kr")
        val retrofitService = retrofit.create(RetrofitService::class.java)
        lateinit var filepart: MultipartBody.Part // 파일 데이터 객체
        imgPath.let {
            val file = File(imgPath)
            val requestBody = RequestBody.create(
                MediaType.parse("image/*"),
                file
            )
            filepart = MultipartBody.Part.createFormData(
                "img", file.name, requestBody
            )
        }

        retrofitService.postDataToServer(dataPart, filepart).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(this@EditActivity, "성공~", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@EditActivity, "실패 ㅋㅋ", Toast.LENGTH_SHORT).show()
            }

        })


    }
}