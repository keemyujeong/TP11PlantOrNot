package com.kyjsoft.tp11plantornot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationView
import com.kyjsoft.tp11plantornot.databinding.ActivityMainBinding

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

            if(fragments[0] != null) tran.hide(fragments[0]!!)
            if(fragments[1] != null) tran.hide(fragments[1]!!)
            if(fragments[2] != null) tran.hide(fragments[2]!!)

            when(menuItem.itemId) {
                R.id.menu_bnv_home -> tran.show(fragments[0]!!)
                R.id.menu_bnv_board -> {
                    if(fragments[1] == null) {
                        fragments[1] = BoardFragment()
                        tran.add(R.id.fragment_container,fragments[1]!!)
                    }
                    tran.show(fragments[1]!!)
                }
                R.id.menu_bnv_bug -> {
                    if(fragments[2] == null) {
                        fragments[2] = BugFragment()
                        tran.add(R.id.fragment_container,fragments[2]!!)
                    }
                    tran.show(fragments[2]!!)
                }

            }
            tran.commit()
            true
        }


//        replaceFragment(HomeFragment())
//
//        binding.bnv.setOnItemSelectedListener {
//
//            when(it.itemId){
//
//                R.id.menu_bnv_home -> replaceFragment(HomeFragment())
//                R.id.menu_bnv_board -> replaceFragment(BoardFragment())
//                R.id.menu_bnv_bug -> replaceFragment(BugFragment())
//
//            }
//            true
//        }
    }

//    fun replaceFragment(fragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.fragment_container, fragment)
//        fragmentTransaction.commit()
//    }
}