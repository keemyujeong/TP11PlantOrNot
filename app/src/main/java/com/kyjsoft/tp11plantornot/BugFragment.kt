package com.kyjsoft.tp11plantornot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBugBinding
import org.jsoup.nodes.Document
import retrofit2.*
import retrofit2.Response
import kotlin.concurrent.thread

class BugFragment: Fragment() {

    lateinit var binding: FragmentBugBinding
    var items : MutableList<BugRecyclerItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBugBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = BugAdapter(requireContext(),items)

        items.clear()
        binding.recyclerView.adapter?.notifyDataSetChanged()

        binding.btn.setOnClickListener { loadData() }



    }

    fun loadData(){

        val insectApiKey = "2022d5474582821a4f984e2b8988fecca95c"
        var retrofit : Retrofit = RetrofitHelper.getInstance("http://ncpms.rda.go.kr/npmsAPI/")
//        retrofit.create(RetrofitService::class.java).getInsectData(
//            insectApiKey,
//            binding.etPlant.toString(),
//            binding.etInsect.toString()
//        ).enqueue(object : Callback<Service>{
//            override fun onResponse(call: Call<Service>, response: Response<Service>) {
//
//                items.clear()
//                binding.recyclerView.adapter?.notifyDataSetChanged()
//
//                var result : Service? = response.body()
//
//                result?.list?.item?.let {
//                    it.forEach {
//                        items.add(BugRecyclerItem(it.cropName,it.insectKorName,it.oriImg))
//                        Toast.makeText(requireContext(), "aaa", Toast.LENGTH_SHORT).show()
//                        binding.recyclerView.adapter?.notifyDataSetChanged()
//                        Log.i("TAG", it.insectKorName + it.cropName + it.oriImg)
//                    }
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<Service>, t: Throwable) {
//                Toast.makeText(requireContext(), "실패 : ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })

        // scalars
        retrofit.create(RetrofitService::class.java).InsectDataToString(
            insectApiKey,
            binding.etPlant.text.toString(),
            binding.etInsect.text.toString()
        ).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("TAG-I", response.toString())
                Log.i("TAG-I", response.body().toString())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
            }
        })




    }
}

