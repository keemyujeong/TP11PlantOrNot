package com.kyjsoft.tp11plantornot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bnv.setOnItemSelectedListener {

            when(it.itemId){

                R.id.menu_bnv_home -> replaceFragment(HomeFragment())
                R.id.menu_bnv_board -> replaceFragment(BoardFragment())
                R.id.menu_bnv_bug -> replaceFragment(BugFragment())

            }

            true // 리턴 값을 

        }

    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}