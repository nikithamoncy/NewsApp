package com.nikitha.newsapp

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.newsapp.databinding.ActivityMainBinding
import com.nikitha.newsapp.network.NewsApi
import com.nikitha.newsapp.network.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val API_KEY = "63c9cbd3e702452aa1963cfb109394ee"
    val source = "techcrunch"
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        binding.newsRecyclerview.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter()
        binding.newsRecyclerview.adapter = adapter

        NewsApi.retrofitService.getNews(source, API_KEY).enqueue(
            object : Callback<ResponseModel> {
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.i("error" , t.message.toString())
                }
                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    adapter.articles = response.body()?.articles
                    adapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                }
            })

    }
}

