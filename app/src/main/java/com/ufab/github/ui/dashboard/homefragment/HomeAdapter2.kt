package com.ufab.github.ui.dashboard.homefragment

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ufab.github.data.model.home.HomeModel
import com.ufab.github.global.listener.DataAdapterListener
import com.ufab.github.global.viewholder.HomeViewHolder
import com.squareup.picasso.Picasso

class HomeAdapter2(private val picasso: Picasso,var homeFragmentViewModel:HomeFragmentViewModel):RecyclerView.Adapter<HomeViewHolder>(),DataAdapterListener<ArrayList<HomeModel>> {
    private val home= ArrayList<ArrayList<HomeModel>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
return HomeViewHolder.create(parent,homeFragmentViewModel,picasso)   }

    override fun getItemCount(): Int {
        return home.size
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(home[position])
    }

    override fun setData(data: ArrayList<HomeModel>) {
        home.clear()
        home.addAll(listOf(data))
        notifyDataSetChanged()    }
}