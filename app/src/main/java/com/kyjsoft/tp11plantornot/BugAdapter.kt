package com.kyjsoft.tp11plantornot

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.BugRecyclerItemBinding

class BugAdapter(var context : Context, var items : MutableList<BugRecyclerItem>) : RecyclerView.Adapter<BugAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : BugRecyclerItemBinding = BugRecyclerItemBinding.bind(itemView)


        init {
            binding.ivBug.setOnClickListener {
                var item = items[adapterPosition]
                context.startActivity(Intent(context, BugInsertActivity::class.java).putExtra("bugImage",item.bugImgUrl))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.bug_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvBugName.text = items.get(position).InsectName
        holder.binding.tvPlantName.text = items.get(position).plantName
        Glide.with(context).load(items.get(position).bugImgUrl).error(R.color.fruit).into(holder.binding.ivBug)
    }

    override fun getItemCount(): Int = items.size
}