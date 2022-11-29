package com.kyjsoft.tp11plantornot

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.FragmentBugBinding
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL


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

        binding.btn.setOnClickListener {
            val imm: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

            loadData()

            binding.etPlant.isCursorVisible = false
            binding.etInsect.isCursorVisible = false

        }

        binding.swipeRefresh.setOnRefreshListener {
            loadData()
            binding.swipeRefresh.isRefreshing = false
        }



    }


    fun loadData(){
        val baseUrl = "https://ncpms.rda.go.kr/npmsAPI/service?"
        val insectApiKey = "2022d5474582821a4f984e2b8988fecca95c"
        val address = baseUrl +
                "&serviceCode=SVC01&serviceType=AA001" +
                "&apiKey=${insectApiKey}" +
                "&cropName=" + binding.etPlant.text.toString() + "" +
                "&sickNameKor=" + binding.etInsect.text.toString() + ""

        object : Thread(){
            override fun run() {

                requireActivity().runOnUiThread {
                    items.clear()
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }

                val url = URL(address)
                var xpp = XmlPullParserFactory.newInstance().newPullParser()
                xpp.setInput(InputStreamReader(url.openStream()))

                var eventType = xpp.eventType
                var item :MutableMap<String, String> = mutableMapOf()
                while(eventType != XmlPullParser.END_DOCUMENT){
                    when(eventType){
                        XmlPullParser.START_DOCUMENT -> {}
                        XmlPullParser.START_TAG -> {
                            val tagName = xpp.name
                            if (tagName == "item"){
                            }
                            else if(tagName == "thumbImg") {
                                xpp.next()
                                item.put("bugImgUrl", xpp.text)
                            } else if(tagName == "cropName"){
                                xpp.next()
                                item.put("plantName", xpp.text)
                            } else if(tagName == "sickNameKor"){
                                xpp.next()
                                item.put("InsectName", xpp.text)
                            }
                        }
                        XmlPullParser.TEXT -> {}
                        XmlPullParser.END_TAG -> if(xpp.name == "item"){
                            items.add(BugRecyclerItem(item["plantName"], item["InsectName"], item["bugImgUrl"]))
                        }
                    }
                    eventType = xpp.next()
                }

                requireActivity().runOnUiThread {
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }


            }
        }.start()





    }
}

