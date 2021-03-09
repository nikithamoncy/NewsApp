package com.nikitha.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.newsapp.network.ArticleModel
import com.squareup.picasso.Picasso

class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    var articles : List<ArticleModel>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.item_list,
                parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles?.size?: 0
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = articles?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.article_list_tv_title)
        val descrip : TextView = itemView.findViewById(R.id.article_list_tv_description)
        val cardView : CardView = itemView.findViewById(R.id.article_list_cardview)
        val image : ImageView = itemView.findViewById(R.id.article_list_image)
        val context = itemView.context
        fun bind(item : ArticleModel){
            title.text = item.title
            descrip.text = item.description
            Picasso.get().load(item.urlToImage).into(image)
            cardView.setOnClickListener {
                val intent = Intent(itemView.context,WebView::class.java)
                intent.putExtra("url",item.url)
                context.startActivity(intent)
            }
        }


    }


}