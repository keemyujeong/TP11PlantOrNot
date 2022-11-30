package com.kyjsoft.tp11plantornot.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kyjsoft.tp11plantornot.R
import com.kyjsoft.tp11plantornot.activities.EditActivity
import com.kyjsoft.tp11plantornot.activities.MyPostActivity
import com.kyjsoft.tp11plantornot.databinding.MyPostRecyclerItemBinding
import com.kyjsoft.tp11plantornot.model.MyPostRecyclerItem
import com.kyjsoft.tp11plantornot.model.RetrofitHelper
import com.kyjsoft.tp11plantornot.model.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostAdapter(val context: Context, var items : MutableList<MyPostRecyclerItem>) : RecyclerView.Adapter<MyPostAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : MyPostRecyclerItemBinding = MyPostRecyclerItemBinding.bind(itemView)

        init {
            binding.btnDelete.setOnClickListener {
                // MY SQL에서 해당 게시글 delete
                val item = items[adapterPosition]
                Log.i("TAG", item.boardno.toString())

                var datapart : MutableMap<String, String> = HashMap()
                datapart["boardno"] = "${item.boardno}"

                val retrofit = RetrofitHelper.getInstance("http://kyjsoft.dothome.co.kr/")
                retrofit.create(RetrofitService::class.java)
                    .deleteMyPostDataFromServer(datapart).enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            (context as MyPostActivity).binding.recyclerView.adapter?.notifyDataSetChanged()
                            context.loadData()

                            Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                        override fun onFailure(call: Call<String>, t: Throwable) {
                        }
                    })
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
        holder.binding.tvDate.text = items[position].date
        holder.binding.howManyLike.text = items.get(position).like_count.toString()
    }

    override fun getItemCount(): Int = items.size
}