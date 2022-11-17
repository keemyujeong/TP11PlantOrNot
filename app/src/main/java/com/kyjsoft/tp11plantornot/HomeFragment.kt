package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding
import com.kyjsoft.tp11plantornot.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*
import com.kyjsoft.tp11plantornot.WeatherResponse
import retrofit2.*
import retrofit2.Response

class HomeFragment: Fragment() {


    lateinit var binding: FragmentHomeBinding
    var items: MutableList<HomeRecyclerItem> = mutableListOf()
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 메뉴 토글
        drawerToggle = ActionBarDrawerToggle(requireActivity(), binding.drawerId, binding.toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerToggle.syncState()
        binding.drawerId.addDrawerListener(drawerToggle)
        binding.tvLocation.text = G.location
        binding.myPlant.text = G.plant
        binding.myPlant2.text = G.plant

        binding.nav.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_selectplant -> clickSelectPlant()
                R.id.menu_post -> clickMyPost()
                R.id.menu_location -> clickLocation()
            }
            binding.drawerId.closeDrawer(binding.nav)
            false
        } )

        // 홈 fragment 리사이클러 adapter
        binding.recyclerView.adapter = HomeAdapter(activity, items)
        loadData()



        // 날씨 적용
        val weatherApiKey = "4wlFPuoi69Pc78kZpfF7GpieaLhqRkeSrKZs9jk5ZqbKSSh4vfl2VXk36YbHSOSipfsuVFbBZk9wVLg+ubgvHw=="
        var retrofit : Retrofit = RetrofitHelper.getInstance("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")

        // scalars 파싱
//        retrofit.create(RetrofitService::class.java).dataToString(
//            weatherApiKey,
//            300, // 값이 500개는 되어야 최저 기온이랑, 최고 기온 받을 수 있다고 함.
//            1,
//            "JSON",
//            SimpleDateFormat("yyyyMMdd").format(Date()),
//            "0200",
//            56,
//            106
//        ).enqueue( object : Callback<String>{
//
//            override fun onResponse(
//                call: Call<String>,
//                response: Response<String>
//            ) {
//                val result: String? = response.body()
//                    Log.i("TAG", result.toString())
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                AlertDialog.Builder(requireContext()).setMessage("실패 : + ${t.message}").show()
//            }
//        })

        //SQLite에서 의 위치 값, nx, ny값
        val db : SQLiteDatabase = requireContext().openOrCreateDatabase("map", AppCompatActivity.MODE_PRIVATE, null)

        val cursor = db.rawQuery("SELECT location,nx,ny FROM map WHERE num=?", arrayOf("0")) ?: return

        while(cursor.moveToNext()){
            G.locationX = cursor.getString(1)
            G.locationY = cursor.getString(2)
        }
        Log.i("TAG-location", G.location)

        // 기상청 최저기온, 최고 기온 파싱
        retrofit.create(RetrofitService::class.java).getWeatherInfo(
            weatherApiKey,
            300, // 값이 500개는 되어야 최저 기온이랑, 최고 기온 받을 수 있다고 함.
            1,
            "JSON",
            SimpleDateFormat("yyyyMMdd").format(Date()),
            "0200",
            G.locationX.toInt(),
            G.locationY.toInt()
        ).enqueue( object : Callback<WeatherResponse>{

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val result: WeatherResponse? = response.body()
                result?.response?.body?.items?.item?.let {
//                    for(i in 0..it.totalCount){
//                        when(it.items.item[i].category){
//                            "TMN"-> binding.tvLowTemp.text = it.items.item[i].fcstValue
//                            "TMX"-> binding.tvHighTemp.text = it.items.item[i].fcstValue
//                        }
//                    }
                    it.forEach { item ->
                        when(item.category){
                            "TMN"-> binding.tvLowTemp.text = item.fcstValue
                            "TMX"-> binding.tvHighTemp.text = item.fcstValue
                        }
                    }

//                    Toast.makeText(requireContext(), result?.response?.body?.totalCount.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                AlertDialog.Builder(requireContext()).setMessage("날씨 정보를 불러오지 못했습니다.").show()
            }


        })


    }

    fun clickSelectPlant(){
        val intent : Intent = Intent(requireContext(), PickActivity::class.java)
        startActivity(intent)
    }

    fun clickMyPost(){
        val intent : Intent = Intent(requireContext(), MyPostActivity::class.java)
        startActivity(intent)
    }

    fun clickLocation(){
        val intent : Intent = Intent(requireContext(), MapActivity::class.java)
        startActivity(intent)
    }

    fun loadData(){
        // TODO html 파싱




        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))
        items.add(HomeRecyclerItem("농사 주기","농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다.농작업 일정이다."))

    }

}