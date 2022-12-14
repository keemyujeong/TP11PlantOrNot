package com.kyjsoft.tp11plantornot.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kyjsoft.tp11plantornot.R
import com.kyjsoft.tp11plantornot.databinding.ActivityMainBinding
import com.kyjsoft.tp11plantornot.fragments.BoardFragment
import com.kyjsoft.tp11plantornot.fragments.BugFragment
import com.kyjsoft.tp11plantornot.fragments.HomeFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var fragments : MutableList<Fragment?> = mutableListOf()
    val fragmentManager : FragmentManager by lazy { supportFragmentManager }

//    TODO 전제적으로 내 정보 관리에서 초기설정이아닌 설정 수정일때 클릭이벤트 달리하는 코드를 써야함.

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

        getSharedPreferences("initialSetting", MODE_PRIVATE).edit().putBoolean("isfirst", false).commit()


    }








}