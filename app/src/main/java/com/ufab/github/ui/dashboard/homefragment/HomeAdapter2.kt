package com.ufab.github.ui.dashboard.homefragment

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.HomeViewHolder
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.home.HomeModel2

class HomeAdapter2(private val picasso: Picasso,var homeFragmentViewModel:HomeFragmentViewModel):RecyclerView.Adapter<HomeViewHolder>(),DataAdapterListener<List<HomeModel2>> {
    private val home= ArrayList<HomeModel2>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
return HomeViewHolder.create(parent,homeFragmentViewModel,picasso)   }

    override fun getItemCount(): Int {
        Log.d("datasize",home.size.toString())
        return home.size
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("adapter12345", Gson().toJson(home))
        holder.bind(home)
    }

    override fun setData(data: List<HomeModel2>) {
        home.clear()
        home.addAll(data)
        notifyDataSetChanged()    }


}
