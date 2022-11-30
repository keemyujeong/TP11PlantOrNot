package com.kyjsoft.tp11plantornot.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kyjsoft.tp11plantornot.R
import com.kyjsoft.tp11plantornot.databinding.HomeRecyclerItemBinding
import com.kyjsoft.tp11plantornot.model.HomeRecyclerItem


// context가 null일 수도 있기 때문에 물을표를 붙여야 homefragment에서 context를 알아들음 nullable로 인식함.
class HomeAdapter(val context: Context?, var items: MutableList<HomeRecyclerItem>) : RecyclerView.Adapter<HomeAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding: HomeRecyclerItemBinding = HomeRecyclerItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.home_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.binding.tvText.text = items[position].text
        holder.binding.tvTitle.text = items[position].title

    }

    override fun getItemCount(): Int = items.size

}