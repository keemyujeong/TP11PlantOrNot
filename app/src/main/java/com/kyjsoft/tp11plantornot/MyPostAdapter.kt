package com.kyjsoft.tp11plantornot

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kyjsoft.tp11plantornot.databinding.MyPostRecyclerItemBinding

class MyPostAdapter(val context: Context, var items : MutableList<MyPostRecyclerItem>) : RecyclerView.Adapter<MyPostAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : MyPostRecyclerItemBinding = MyPostRecyclerItemBinding.bind(itemView)

        init {
            binding.btnDelete.setOnClickListener {
                // MY DBSQL에서 해당 게시글 delete
            }
            binding.btnUpdate.setOnClickListener { context.startActivity(Intent(context, EditActivity::class.java)) }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView: View = LayoutInflater.from(context).inflate(R.layout.my_post_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvTitle.text = items.get(position).title
        holder.binding.tvText.text = items.get(position).text
        holder.binding.howManyLike.text = items.get(position).howManyLike.toString()
    }

    override fun getItemCount(): Int = items.size
}