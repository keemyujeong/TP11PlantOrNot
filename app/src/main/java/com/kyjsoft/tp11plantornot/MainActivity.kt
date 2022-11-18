package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationView
import com.kyjsoft.tp11plantornot.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var fragments : MutableList<Fragment?> = mutableListOf()
    val fragmentManager : FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        fragments.add(HomeFragment())
        fragments.add(null)
        fragments.add(null)


        fragmentManager.beginTransaction().add(R.id.fragment_container, fragments[0]!!).commit()

        binding.bnv.setOnItemSelectedListener { menuItem ->

            val tran = fragmentManager.beginTransaction()

            if (fragments[0] != null) tran.hide(fragments[0]!!)
            if (fragments[1] != null) tran.hide(fragments[1]!!)
            if (fragments[2] != null) tran.hide(fragments[2]!!)

            when (menuItem.itemId) {
                R.id.menu_bnv_home -> tran.show(fragments[0]!!)
                R.id.menu_bnv_board -> {
                    if (fragments[1] == null) {
                        fragments[1] = BoardFragment()
                        tran.add(R.id.fragment_container, fragments[1]!!)
                    }
                    tran.show(fragments[1]!!)
                }
                R.id.menu_bnv_bug -> {
                    if (fragments[2] == null) {
                        fragments[2] = BugFragment()
                        tran.add(R.id.fragment_container, fragments[2]!!)
                    }
                    tran.show(fragments[2]!!)
                }

            }
            tran.commit()
            true
        }


        // 서버에 있는 프로필을 한 번 싹 전역변수에 저장하기.
        var datapart: MutableMap<String, String> = HashMap()
        datapart["id"] = "로그인 아이디"

        var retrofit: Retrofit = RetrofitHelper.getInstance("Http://kyjsoft.dothome.co.kr")
        retrofit.create(RetrofitService::class.java).loadProfileDataToServer(
            datapart
        ).enqueue(object : Callback<MutableList<ProfileItem>> {
            override fun onResponse(
                call: Call<MutableList<ProfileItem>>,
                response: Response<MutableList<ProfileItem>>
            ) {
                response.body()!!.let {
                    it.forEach {
                        G.pic = it.imgurl
                        G.name = it.name
                        G.plant = it.plant
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<ProfileItem>>, t: Throwable) {
                AlertDialog.Builder(this@MainActivity).setMessage(t.message).show()
            }
        })

    }
}