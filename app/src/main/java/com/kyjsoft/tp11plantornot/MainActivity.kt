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


        drawerToggle = ActionBarDrawerToggle(this, binding.drawerId, binding.toolbar, R.string.drawer_open, R.string.drawer_close)
        binding.drawerId.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        binding.nav.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_selectplant -> clickSelectPlant()
                R.id.menu_post -> clickMyPost()
                R.id.menu_location -> clickLocation()
            }
            binding.drawerId.closeDrawer(binding.nav)
            false

        } )

        binding.bnv.setOnItemSelectedListener {

            when(it.itemId){

                R.id.menu_bnv_home -> replaceFragment(HomeFragment())
                R.id.menu_bnv_board -> replaceFragment(BoardFragment())
                R.id.menu_bnv_bug -> replaceFragment(BugFragment())

            }
            true // 리턴 값을
        }
    }
    fun clickSelectPlant(){
        val intent : Intent = Intent(this, PickActivity::class.java)
        startActivity(intent)
    }

    fun clickMyPost(){
        val intent : Intent = Intent(this, MyPostActivity::class.java)
        startActivity(intent)
    }

    fun clickLocation(){
        val intent : Intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}