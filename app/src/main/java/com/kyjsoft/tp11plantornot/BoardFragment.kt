package com.kyjsoft.tp11plantornot

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding

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

        binding.fab.setOnClickListener{
            val intent : Intent = Intent(requireContext(), EditActivity::class.java)
            startActivity(intent)
        }

        loadData()



    }

    fun loadData(){



        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))
        items.add(BoardRecyclerItem("이미지", "사용자", "감자", "감자 지금 심어도 될까요?" , "새벽에는 기온이 많이 떨어져 있더군요. 밤에는 고라니가 많이 다니고.. 두더지도 많던데 두더지도 씨감자 먹나요? 좋은 씨감자를 구해서 잘 심고 싶거 든요..", "2022.11.02 17:01"))

    }



}