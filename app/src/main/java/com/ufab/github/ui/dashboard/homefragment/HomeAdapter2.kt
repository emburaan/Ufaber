package com.ufab.github.ui.dashboard.homefragment

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.HomeViewHolder
import com.squareup.picasso.Picasso
import com.ufab.github.data.model.home.HomeModel2

class HomeAdapter2(private val picasso: Picasso,var homeFragmentViewModel:HomeFragmentViewModel):RecyclerView.Adapter<HomeViewHolder>(),DataAdapterListener<HomeModel2> {
    private val home= HomeModel2()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
return HomeViewHolder.create(parent,homeFragmentViewModel,picasso)   }

    override fun getItemCount(): Int {
        Log.d("datasize",home.size.toString())
        return 10
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(home)
    }

    override fun setData(data: HomeModel2) {
        home.clear()
        home.addAll(data)
        notifyDataSetChanged()    }


}