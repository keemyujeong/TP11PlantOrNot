package com.kyjsoft.tp11plantornot

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

        binding.btn.setOnClickListener {
            val imm: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

            loadData()
        }



    }

    fun loadData(){

        val insectApiKey = "2022d5474582821a4f984e2b8988fecca95c"
        var retrofit : Retrofit = RetrofitHelper.getInstance("http://ncpms.rda.go.kr/npmsAPI/")
        // TODO 얘는 retrofit 객체를 따로 만들어야할 듯
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
//                        Log.i("TAG-I", it.insectKorName + it.cropName + it.oriImg)
//                    }
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<Service>, t: Throwable) {
//                Toast.makeText(requireContext(), "실패 : ${t.message}", Toast.LENGTH_SHORT).show()
//                Log.i("TAG-I", t.message.toString())
//            }
//        })

        // scalars
        retrofit.create(RetrofitService::class.java).insectDataToString(
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

