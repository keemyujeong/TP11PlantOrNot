package com.kyjsoft.tp11plantornot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kyjsoft.tp11plantornot.databinding.BoardRecyclerItemBinding
import com.kyjsoft.tp11plantornot.databinding.FragmentBoardBinding

class BoardAdapter(val context: Context, var items : MutableList<BoardRecyclerItem>) : RecyclerView.Adapter<BoardAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View = LayoutInflater.from(context).inflate(R.layout.board_recycler_item, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load("http://kyjsoft.dothome.co.kr/TPplantOrNot/"+items.get(position).imgUrl).error(R.drawable.profle).into(holder.binding.civ)
        holder.binding.tvName.text = items[position].name
        holder.binding.tvSelectplant.text = items[position].selectPlant
        holder.binding.tvTitle.text = items[position].title
        holder.binding.tvText.text = items[position].text
        Glide.with(context).load("http://kyjsoft.dothome.co.kr/TPplantOrNot/"+items[position].file).error(R.drawable.profle).into(holder.binding.iv)
        holder.binding.tvDate.text = items[position].date
    }

    override fun getItemCount(): Int = items.size

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding : BoardRecyclerItemBinding = BoardRecyclerItemBinding.bind(itemView)

        init { // class 영역에서 함수 말고 실행문을 쓸 때 초기화 블럭 안에 써라.
            binding.like.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    if(p1){
                        // 토글 true값일 때 처리 -> 레트로핏코드 쓰기. php코드도 필요함 TODO DB에 like 1 추가

                    }else{
                        // 토글 false값 일때 처리

                    }
                }
            })
        }

    }


}