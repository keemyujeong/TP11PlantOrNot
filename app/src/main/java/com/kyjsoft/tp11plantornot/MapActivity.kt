package com.kyjsoft.tp11plantornot

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.kakao.util.maps.helper.Utility
import com.kyjsoft.tp11plantornot.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

class MapActivity : AppCompatActivity() {

    val binding : ActivityMapBinding by lazy { ActivityMapBinding.inflate(layoutInflater) }
    val mapView by lazy { MapView(this) }
    lateinit var location: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }

        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(permissions, 100)
        }

        val keyHash : String = Utility.getKeyHash(this@MapActivity)
        Log.i("TAG-keyHash", keyHash)

        binding.mapContainer.addView(mapView)

        binding.search.setOnClickListener { input() }

    }
//    fun input(){
//
//        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
//
//        var retrofit = RetrofitHelper.getInstance("https://dapi.kakao.com/")
//        retrofit.create(RetrofitService::class.java).getKakaoAddresstoString(
//            "KakaoAK 14cec1c2faa0d112976ce952cbf71665",
//            binding.et.text.toString()
//        ).enqueue(object : Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Log.i("TAG-result", response.toString())
//                Log.i("TAG-result", response.body().toString())
//
//                Toast.makeText(this@MapActivity, "aaa", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@MapActivity, "주소를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }


    fun input(){

        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        var retrofit = RetrofitHelper.getInstance("https://dapi.kakao.com/")
        retrofit.create(RetrofitService::class.java).getKakaoAddress(
            "KakaoAK 14cec1c2faa0d112976ce952cbf71665",
            binding.et.text.toString()
        ).enqueue(object : Callback<KakaoMapData>{
            override fun onResponse(call: Call<KakaoMapData>, response: Response<KakaoMapData>) {
                Log.i("TAG-result",response.body()!!.documents[0].address.region_2depth_name)
                Log.i("TAG-result",response.body()!!.documents[0].address.x)
//                Toast.makeText(this@MapActivity, "aaa", Toast.LENGTH_SHORT).show()
                location = response.body()!!.documents[0].address.region_3depth_h_name
                region_2depth_name = response.body()!!.documents[0].address.region_2depth_name

                if(response.body() != null){
                    // 중심점 변경 + 줌 레벨 변경
                    mapView.setMapCenterPointAndZoomLevel(
                        MapPoint.mapPointWithGeoCoord(response.body()!!.documents[0].address.y.toDouble(), response.body()!!.documents[0].address.x.toDouble()),
                        3,
                        true
                    )
                    // 줌 인
                    mapView.zoomIn(true)
                    // 줌 아웃
                    mapView.zoomOut(true)

                } else{
                    Toast.makeText(this@MapActivity, "주소를 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<KakaoMapData>, t: Throwable) {
                Toast.makeText(this@MapActivity, "주소를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    lateinit var region_2depth_name : String // 카카오 로컬 API 지역 이름 변수

    fun clickBtn(){

        // 입력안하고 넘어가면 에러나기 때문에 그거 처리해야함.

        val intent : Intent = Intent(this, PickActivity::class.java)
        startActivity(intent)

        // TODO SQLite에 location 저장 -> 파싱한 "region_3depth_name" 이거만 저장
        G.location = location

        // 기상청 json 파싱
        val weatherJsonStr : String = assets.open("jsons/weatherXY.json").bufferedReader().use { it.readText() }
//        Log.i("TAG", weatherJsonStr)
        val gson = Gson()
        var weatherItem = gson.fromJson(weatherJsonStr, Array<WeatherItem>::class.java)
        weatherItem.let {
            it.forEach {
                when(it.region3){
                    G.location -> {
                        if(it.region2 == region_2depth_name){
                            G.locationX = it.nx
                            G.locationY = it.ny
                            Log.i("TAG", G.locationX + G.locationY)
                        }

                    }
                }
            }
        }
















        finish()
    }
};