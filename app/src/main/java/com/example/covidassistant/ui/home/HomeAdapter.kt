package com.example.covidassistant.ui.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.covidassistant.R
import com.example.covidassistant.ui.detail.DetailActivity
import com.example.covidassistant.ui.detail.SupportActivity

/**
 * Created by Poonam on 06-06-2020.
 */
class HomeAdapter(
    val context: Context,
    private val homeModelList: ArrayList<HomeModel>
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun onBind() {
            itemView.findViewById<RelativeLayout>(R.id.layoutBG).background =
                context.resources.getDrawable(homeModelList[adapterPosition].color, null)
            itemView.findViewById<TextView>(R.id.titleText).text =
                homeModelList[adapterPosition].title

            itemView.setOnClickListener {
                if (homeModelList[adapterPosition].title == "Support")
                    context.startActivity(Intent(context, SupportActivity::class.java))
                else
                    context.startActivity(
                    Intent(context, DetailActivity::class.java)
                        .putExtra("url", homeModelList[adapterPosition].url)
                        .putExtra("title", homeModelList[adapterPosition].title)
                    )
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): HomeViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_home, viewGroup, false)
        return HomeViewHolder(itemView = itemView)
    }

    override fun getItemCount(): Int {
        return homeModelList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(homeViewHolder: HomeViewHolder, position: Int) {
        homeViewHolder.onBind()
    }
}