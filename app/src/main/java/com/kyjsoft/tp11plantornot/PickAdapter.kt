package com.kyjsoft.tp11plantornot

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kyjsoft.tp11plantornot.databinding.PickRecyclerItemBinding

class PickAdapter(val context: Context, var items: MutableList<PickRecyclerItem>): RecyclerView.Adapter<PickAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : PickRecyclerItemBinding = PickRecyclerItemBinding.bind(itemView)

        init {
            itemView.setOnClickListener{
                G.plant = binding.tv.text.toString()
                val intent: Intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.pick_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.binding.tv.text = items[position].plant
    }

    override fun getItemCount(): Int = items.size


}