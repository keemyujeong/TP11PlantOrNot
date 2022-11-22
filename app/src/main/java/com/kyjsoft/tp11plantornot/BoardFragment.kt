package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardFragment: Fragment() {

    lateinit var binding : FragmentBoardBinding
    var items : MutableList<BoardRecyclerItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = BoardAdapter(requireContext(), items)

        binding.swipeRefresh.setOnRefreshListener {  // swipeRefresh 새로고침
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }
        binding.fab.setOnClickListener{ startActivity(Intent(requireContext(), EditActivity::class.java)) }

        loadData()

    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
        
        items.clear()
        binding.recyclerView.adapter?.notifyDataSetChanged()

        // loadboardDB.php
        RetrofitHelper.getInstance("Http://kyjsoft.dothome.co.kr")
            .create(RetrofitService::class.java).loadBoardDataFromServer()
            .enqueue(object : Callback<MutableList<BoardRecyclerItem>> {
                override fun onResponse(
                    call: Call<MutableList<BoardRecyclerItem>>,
                    response: Response<MutableList<BoardRecyclerItem>>
                ) {
                    items.clear()
                    binding.recyclerView.adapter?.notifyDataSetChanged()

                    response.body().let {
                        it?.forEach {
                           items.add(BoardRecyclerItem(it.boardno,it.imgurl,it.name,it.plant,it.title,it.text,it.file,it.date))
                            binding.recyclerView.adapter?.notifyItemInserted(0)
                        }
                    }
                }
                override fun onFailure(call: Call<MutableList<BoardRecyclerItem>>, t: Throwable) {
                    Toast.makeText(requireContext(), "실패", Toast.LENGTH_SHORT).show()
                    Log.i("TAG-B", t.message.toString())
                }
            })

    }



}