package com.kyjsoft.tp11plantornot

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.BoardRecyclerItemBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardAdapter(val context: Context, var items : MutableList<BoardRecyclerItem>) : RecyclerView.Adapter<BoardAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.board_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load("http://kyjsoft.dothome.co.kr/TPplantOrNot/"+items[position].imgurl).error(R.drawable.profle).into(holder.binding.civ)
        holder.binding.tvName.text = items[position].name
        holder.binding.tvSelectplant.text = items[position].plant
        holder.binding.tvTitle.text = items[position].title
        holder.binding.tvText.text = items[position].text
        Glide.with(context).load("http://kyjsoft.dothome.co.kr/TPplantOrNot/"+items[position].file).error(R.drawable.profle).into(holder.binding.iv)
        holder.binding.tvDate.text = items[position].date


    }

    override fun getItemCount(): Int = items.size



    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : BoardRecyclerItemBinding = BoardRecyclerItemBinding.bind(itemView)

        init { // class 영역에서 함수 말고 실행문을 쓸 때 초기화 블럭 안에 써라.

            // 현재 클릭한 위치( position ) 얻어오기
            binding.like.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    if(p1){
                        // 토글 true값일 때 처리
                        val item = items[adapterPosition]
                        var datapart : MutableMap<String, String> = HashMap()
                        datapart["id"] = "로그인 아이디"
                        datapart["boardno"] = "${item.boardno}"
                        Log.i("TAG-boardnum", items[adapterPosition].boardno.toString())

                        val retrofit = RetrofitHelper.getInstance("http://kyjsoft.dothome.co.kr/")
                        val retrofitService = retrofit.create(RetrofitService::class.java)
                        retrofitService.postLikeDataFromServer(datapart).enqueue(object : Callback<String> {
                            override fun onResponse(call: Call<String>, response: Response<String>) {
                                Log.i("TAG-result", response.body().toString())
                            }
                            override fun onFailure(call: Call<String>, t: Throwable) {
                                AlertDialog.Builder(context).setMessage(t.message).show()
                            }
                        })


                    }else{
                        // 토글 false값 일때 처리
                        val item = items[adapterPosition]
                        var datapart : MutableMap<String, String> = HashMap()
                        datapart["boardno"] = "${item.boardno}"

                        val retrofit = RetrofitHelper.getInstance("http://kyjsoft.dothome.co.kr/")
                        retrofit.create(RetrofitService::class.java)
                            .deleteLikeDataFromServer(datapart).enqueue(object : Callback<String> {
                                override fun onResponse( call: Call<String>, response: Response<String>) {
                                    Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show()
                                }
                                override fun onFailure(call: Call<String>, t: Throwable) {
                                }
                            })

                    }
                }
            })
        }

    }


}