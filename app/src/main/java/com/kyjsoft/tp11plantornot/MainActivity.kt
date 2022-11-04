package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.kyjsoft.tp11plantornot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
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