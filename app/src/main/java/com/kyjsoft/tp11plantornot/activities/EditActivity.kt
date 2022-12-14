package com.kyjsoft.tp11plantornot.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.ActivityEditBinding
import com.kyjsoft.tp11plantornot.model.G
import com.kyjsoft.tp11plantornot.model.RetrofitHelper
import com.kyjsoft.tp11plantornot.model.RetrofitService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class EditActivity : AppCompatActivity() {

    val binding : ActivityEditBinding by lazy { ActivityEditBinding.inflate(layoutInflater) }
    lateinit var filePath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.iv.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"

            binding.tv.visibility = View.GONE

            resultLauncher.launch(intent)
        }
        binding.btn.setOnClickListener { clickBtn() }

    }

    var resultLauncher : ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode != RESULT_CANCELED) {
                Glide.with(this).load(result.data?.data).error("").into(binding.iv)
                filePath = getPathFromUri(result.data?.data)
            }
        }

    fun getPathFromUri(uri: Uri?): String {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(this, uri!!, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val result = cursor.getString(column_index)
        cursor.close()
//        Log.i("TAG-result", result)
        return result
    }


    fun clickBtn(){

        val title = binding.etTitle.text.toString()
        val text = binding.etContent.text.toString()

        val retrofit = RetrofitHelper.getInstance("Http://kyjsoft.dothome.co.kr")
        val retrofitService = retrofit.create(RetrofitService::class.java)
        lateinit var filepart: MultipartBody.Part // ?????? ????????? ????????? ???????????? ?????????????????? ??? ????????? ????????? ?????????
        filePath.let {
            val file = File(filePath)
            val requestBody = RequestBody.create(
                MediaType.parse("image/*"),
                file
            )
            filepart = MultipartBody.Part.createFormData(
                "file", file.name, requestBody
            )
//            Log.i("TAG-result", file.name)

        }

        // retrofit?????? ???????????? ?????????
        var dataPart : MutableMap<String, String> = HashMap() // String??? hashMap()?????? ????????? ?????????????????? ??? ????????? ????????? ?????????
        dataPart["id"] = "????????? ?????????"
        dataPart["name"] = G.name
        dataPart["title"] = title
        dataPart["text"] = text

        retrofitService.postBoardDataToServer(dataPart, filepart).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(this@EditActivity, "??????~", Toast.LENGTH_SHORT).show()
                Log.i("TAG-result", response.body().toString())
                finish()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@EditActivity, "?????? ??????", Toast.LENGTH_SHORT).show()
                Log.i("TAG-result", t.message.toString())
            }

        })


    }
}