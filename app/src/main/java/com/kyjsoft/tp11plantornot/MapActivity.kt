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
import com.kakao.util.maps.helper.Utility
import com.kyjsoft.tp11plantornot.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapActivity : AppCompatActivity() {

    val binding : ActivityMapBinding by lazy { ActivityMapBinding.inflate(layoutInflater) }
    val mapView by lazy { MapView(this) }

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
    fun input(){

        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        var retrofit = RetrofitHelper.getInstance("https://dapi.kakao.com/")
        retrofit.create(RetrofitService::class.java).getKakaoAddress(
            "KakaoAK 03ae22142159c30687438546256ab699",
            binding.et.toString()
        ).enqueue(object : Callback<KakaoMapData>{
            override fun onResponse(call: Call<KakaoMapData>, response: Response<KakaoMapData>) {
                Toast.makeText(this@MapActivity, "aaa", Toast.LENGTH_SHORT).show()
                var result = response.body()
                Log.i("TAG-result", result?.documents?.size.toString())

                // 중심점 변경 + 줌 레벨 변경
                mapView.setMapCenterPointAndZoomLevel(
                    MapPoint.mapPointWithGeoCoord(result?.documents?.get(0)?.address?.x!!.toDouble(), result.documents?.get(0)?.y!!.toDouble()),
                    5,
                    true
                )

                // 줌 인
                mapView.zoomIn(true)

                // 줌 아웃
                mapView.zoomOut(true)


            }

            override fun onFailure(call: Call<KakaoMapData>, t: Throwable) {
                Toast.makeText(this@MapActivity, "주소를 다시 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun clickBtn(){

        val intent : Intent = Intent(this, PickActivity::class.java)
        startActivity(intent)
        finish()
    }
};