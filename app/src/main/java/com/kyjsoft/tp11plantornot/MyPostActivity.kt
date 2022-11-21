package com.kyjsoft.tp11plantornot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import com.kyjsoft.tp11plantornot.databinding.ActivityMyPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MyPostActivity : AppCompatActivity() {

    var items : MutableList<MyPostRecyclerItem> = mutableListOf()
    val binding : ActivityMyPostBinding by lazy{ ActivityMyPostBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.adapter = MyPostAdapter(this, items)
        binding.iv.setOnClickListener { onBackPressed() }

        var datapart : MutableMap<String, String> = HashMap()
        datapart["id"] = "로그인 아이디"

        // TODO plantBoard에서 title, text / plantLike에서 boardno 개수 세기
        // 하지만 나는 다시 설계할거임. 처음 board 테이블에 like_count를 세는 php 코드를 만들기. -> mysql updata 사용

        val retrofit : Retrofit = RetrofitHelper.getInstance("http://kyjsoft.dothome.co.kr/")
        retrofit.create(RetrofitService::class.java).loadMyPostDataFromServer(datapart)
            .enqueue(object : Callback<MutableList<MyPostRecyclerItem>> {
                override fun onResponse( call: Call<MutableList<MyPostRecyclerItem>>, response: Response<MutableList<MyPostRecyclerItem>>) {
                    response.body()!!.let {
                        it.forEach {
                            items.add(MyPostRecyclerItem(it.text, it.title, it.like_count))
                        }
                    }
                }
                override fun onFailure(call: Call<MutableList<MyPostRecyclerItem>>, t: Throwable) {
                    AlertDialog.Builder(this@MyPostActivity).setMessage(t.message).show()
                }
            })

//        val retrofit : Retrofit = RetrofitHelper.getInstance("http://kyjsoft.dothome.co.kr/")
//        retrofit.create(RetrofitService::class.java).loadMyPostDataFromServerToString(datapart)
//            .enqueue(object : Callback<String> {
//                override fun onResponse( call: Call<String>, response: Response<String>) {
//                    response.body()!!.let {
//                        var title = HtmlCompat.fromHtml(response.body().toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
//                        Log.i("TAG", title.toString())
//
//                    }
//                }
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    AlertDialog.Builder(this@MyPostActivity).setMessage(t.message).show()
//                }
//            })

    }


}